import threading
import requests
import time
from lxml import html
import pymysql
import sys
import random

DB_URL = 'li.goldlone.cn'
DB_USER = 'cn'
DB_PASSWORD = 'abc.123'
DB_NAME = 'spider'
DB_CHARSET = 'utf8'


class IpProxy:

    def __init__(self):
        DeleteIPThread(self).start()
        pass

    def get_ip_http(self):
        '''
        从数据库中随机拿一个有效IP
        返回None时表示没有地址可用了
        :return: (ip, port, speed, type) or None
        '''
        conn = pymysql.connect(DB_URL, DB_USER, DB_PASSWORD, DB_NAME, charset=DB_CHARSET)
        cursor = conn.cursor()
        sql = '''
            select ip,port,speed,proxy_type 
            from proxy_ip 
            where proxy_type='HTTP' 
            order by rand()
            limit 1;
        '''
        cursor.execute(sql)
        # (ip, port, speed, type)
        res = cursor.fetchone()
        conn.close()
        if res is not None:
            if self.judge_ip(res[0], res[1]):
                return res
            else:
                return self.get_ip_http()
        self.crawl_ips()
        return self.get_ip_http()

    def get_ip_https(self):
        '''
        从数据库中随机拿一个有效IP
        返回None时表示没有地址可用了
        :return: (ip, port, speed, type) or None
        '''
        conn = pymysql.connect(DB_URL, DB_USER, DB_PASSWORD, DB_NAME, charset=DB_CHARSET)
        cursor = conn.cursor()
        sql = '''
            select ip,port,speed,proxy_type 
            from proxy_ip 
            WHERE proxy_type='HTTPS' 
            order by rand() 
            limit 1;
        '''
        cursor.execute(sql)
        # (ip, port, speed, type)
        res = cursor.fetchone()
        conn.close()
        if res is not None:
            if self.judge_ip(res[0], res[1]):
                return res
            else:
                return self.get_ip_https()
        self.crawl_ips()
        return self.get_ip_https()

    def crawl_ips(self):
        '''
        爬取西刺免费代理的地址池
        :return: 无返回
        '''
        headers = {
            "Host": "www.xicidaili.com",
            "Upgrade-Insecure-Requests": "1",
            "User-Agent": "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.75 Safari/537.36"
        }
        url = "http://www.xicidaili.com/nn/1"

        r = requests.get(url, headers=headers)
        t1 = html.fromstring(r.text)
        total_page = int(t1.xpath('//div[@class="pagination"]/a')[-2].xpath('text()')[0])

        conn = pymysql.connect(DB_URL, DB_USER, DB_PASSWORD, DB_NAME, charset=DB_CHARSET)
        for current_page in range(1, total_page + 1):
            if current_page == 1:
                response = r
            else:
                url = "http://www.xicidaili.com/nn/" + str(current_page)
                response = requests.get(url, headers=headers)
            s = html.fromstring(response.text)
            all_list = s.xpath('//table[@id="ip_list"]/tr')[1:]
            cursor = conn.cursor()
            for item in all_list[1:]:
                try:
                    line = item.xpath('./td')
                    ip = line[1].xpath('string()')
                    port = line[2].xpath('string()')
                    address = ''
                    if len(line[3].xpath('./a')) > 0:
                        address = line[3].xpath('./a/text()')[0]
                    type = line[5].xpath('string()')
                    speed = 0.0
                    if len(line[6].xpath('./div/@title')) > 0:
                        speed_str = line[6].xpath('./div/@title')[0]
                        speed = float(speed_str[:-1])

                    # print(ip, port, address, type, speed)
                    sql = '''
                        INSERT 
                        INTO proxy_ip(ip, port, address, proxy_type, speed) 
                        VALUES ('{0}', '{1}', '{2}', '{3}', '{4}');
                    '''
                    cursor.execute(sql.format(ip, port, address, type, speed))
                    conn.commit()
                except:
                    print(sys.exc_info())
            time.sleep(random.randint(300, 600))
        conn.close()

    def judge_ip(self, ip, port):
        '''
        判断给出的代理 ip 是否可用
        :param ip:
        :param port:
        :return:
        '''
        http_url = 'https://www.163.com/'
        proxy_url = 'http://{0}:{1}'.format(ip, port)
        proxy_dict = {
            'http': proxy_url
        }
        try:
            # print("正在测试代理IP是否可用 => ", proxy_url)
            response = requests.get(http_url, proxies=proxy_dict, timeout=5)
        except:
            # print("代理：", proxy_url, "不可用，即将从数据库中删除")
            self.delete_ip(ip)
            return False
        else:
            code = response.status_code
            if 200 <= code < 300:
                # print("代理 => ", proxy_url, "可用")
                return True
            else:
                self.delete_ip(ip)
                # print("代理：", proxy_url, "不可用，即将从数据库中删除")
                return False

    def delete_ip(self, ip):
        '''
        删除不可用的IP
        :param ip:
        :return:
        '''
        db = pymysql.connect(DB_URL, DB_USER, DB_PASSWORD, DB_NAME, charset=DB_CHARSET)
        cursor = db.cursor()
        sql = '''
            delete from proxy_ip WHERE ip='{0}';
        '''
        cursor.execute(sql.format(ip))
        db.commit()
        db.close()


class DeleteIPThread(threading.Thread):
    '''
    剔除代理池中无效IP的守护线程
    '''

    def __init__(self, proxy):
        super().__init__()
        self.proxy = proxy
        self.daemon = True

    def run(self):
        conn = pymysql.connect(DB_URL, DB_USER, DB_PASSWORD, DB_NAME, charset=DB_CHARSET)
        cursor = conn.cursor()
        sql = "select ip, port from proxy_ip;"
        while True:
            cursor.execute(sql)
            all_list = cursor.fetchall()
            for ip, port in all_list:
                self.proxy.judge_ip(ip, port)
            # self.proxy.crawl_ips()
            time.sleep(60)


if __name__ == '__main__':
    my_proxy = IpProxy()
    my_proxy.crawl_ips()
    # proxy = my_proxy.get_ip_http()
    # if proxy is not None:
    #     print(proxy)
    # time.sleep(60)
