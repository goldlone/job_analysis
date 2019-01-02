# import requests
# import time
# from lxml import html
# import pymysql
# import sys
# import random

# DB_URL = 'li.goldlone.cn'
# DB_USER = 'cn'
# DB_PASSWORD = 'abc.123'
# DB_NAME = 'spider'
# DB_CHARSET = 'utf8'
#
# def crawl_ips():
#     '''
#     爬取西刺免费代理的地址池
#     :return: 无返回
#     '''
#     headers = {
#         "Host": "www.xicidaili.com",
#         "Upgrade-Insecure-Requests": "1",
#         "User-Agent": "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.75 Safari/537.36"
#     }
#     url = "http://www.xicidaili.com/nn/1"
#
#     r = requests.get(url, headers=headers)
#     t1 = html.fromstring(r.text)
#     total_page = int(t1.xpath('//div[@class="pagination"]/a')[-2].xpath('text()')[0])
#
#     conn = pymysql.connect(DB_URL, DB_USER, DB_PASSWORD, DB_NAME, charset=DB_CHARSET)
#     for current_page in range(1, total_page + 1):
#         if current_page == 1:
#             response = r
#         else:
#             url = "http://www.xicidaili.com/nn/" + str(current_page)
#             response = requests.get(url, headers=headers)
#         s = html.fromstring(response.text)
#         all_list = s.xpath('//table[@id="ip_list"]/tr')[1:]
#         cursor = conn.cursor()
#         for item in all_list[1:]:
#             try:
#                 line = item.xpath('./td')
#                 ip = line[1].xpath('string()')
#                 port = line[2].xpath('string()')
#                 address = ''
#                 if len(line[3].xpath('./a')) > 0:
#                     address = line[3].xpath('./a/text()')[0]
#                 type = line[5].xpath('string()')
#                 speed = 0.0
#                 if len(line[6].xpath('./div/@title')) > 0:
#                     speed_str = line[6].xpath('./div/@title')[0]
#                     speed = float(speed_str[:-1])
#
#                 # print(ip, port, address, type, speed)
#                 sql = '''
#                     INSERT
#                     INTO proxy_ip(ip, port, address, proxy_type, speed)
#                     VALUES ('{0}', '{1}', '{2}', '{3}', '{4}');
#                 '''
#                 cursor.execute(sql.format(ip, port, address, type, speed))
#                 conn.commit()
#             except:
#                 print(sys.exc_info())
#         time.sleep(random.randint(300, 600))
#     conn.close()
#
# crawl_ips()


# import pymongo
# client = pymongo.MongoClient(host='li.goldlone.cn', port=27017)
# db = client["test"]
# p = db["persons"]
#
# p.insert_one()

# import redis
# redis_client = redis.Redis(host="li.goldlone.cn", port=6379, db=0)
#
# redis_url_ok_key = "jobs:qiancheng51_url_ok"
# redis_url_need_key = "jobs:qiancheng51_url_need"
#
# links = ['https://jobs.51job.com/shanghai/108326727.html?s=01&t=0',
#  'https://jobs.51job.com/shanghai-pdxq/80446141.html?s=01&t=0',
#  'https://jobs.51job.com/wuhan-dhxjs/109546711.html?s=01&t=0',
#  'https://jobs.51job.com/shenzhen-nsq/109544219.html?s=01&t=0',
#  'https://jobs.51job.com/chengdu-gxq/109076054.html?s=01&t=0',
#  'https://jobs.51job.com/shanghai-ypq/108832468.html?s=01&t=0',
#  'https://jobs.51job.com/shanghai-pdxq/109539342.html?s=01&t=0',
#  'https://jobs.51job.com/guangzhou-pyq/109538256.html?s=01&t=0',
#  'https://jobs.51job.com/qingdao-hdq/109536026.html?s=01&t=0',
#  'https://jobs.51job.com/hangzhou-xhq/109535680.html?s=01&t=0',
#  'https://jobs.51job.com/beijing-hdq/109534652.html?s=01&t=0',
#  'https://jobs.51job.com/chengdu/109534106.html?s=01&t=0',
#  'https://jobs.51job.com/changsha/109533698.html?s=01&t=0',
#  'https://jobs.51job.com/beijing-hdq/109532073.html?s=01&t=0',
#  'https://jobs.51job.com/shanghai-jaq/109531101.html?s=01&t=0',
#  'https://jobs.51job.com/zhenjiang/109530596.html?s=01&t=0',
#  'https://jobs.51job.com/shanghai-bsq/109528846.html?s=01&t=0',
#  'https://jobs.51job.com/hangzhou-xhq/109526600.html?s=01&t=0',
#  'https://jobs.51job.com/beijing/109525820.html?s=01&t=0',
#  'https://jobs.51job.com/beijing-hdq/109525656.html?s=01&t=0',
#  'https://jobs.51job.com/shanghai-ptq/109524828.html?s=01&t=0',
#  'https://jobs.51job.com/chongqing-yzq/109522280.html?s=01&t=0',
#  'https://jobs.51job.com/beijing-cyq/109521156.html?s=01&t=0',
#  'https://jobs.51job.com/shanghai-xhq/109497277.html?s=01&t=0',
#  'https://jobs.51job.com/zhengzhou-gxq/109247159.html?s=01&t=0',
#  'https://jobs.51job.com/yingtan/109213299.html?s=01&t=0',
#  'https://jobs.51job.com/beijing-cpq/108853966.html?s=01&t=0',
#  'https://jobs.51job.com/shenzhen-ftq/108672940.html?s=01&t=0',
#  'https://jobs.51job.com/zhengzhou/108075247.html?s=01&t=0',
#  'https://jobs.51job.com/sanya/107104842.html?s=01&t=0',
#  'https://jobs.51job.com/nanjing/106074525.html?s=01&t=0',
#  'https://jobs.51job.com/guangzhou-thq/105845127.html?s=01&t=0',
#  'https://jobs.51job.com/changsha/105491765.html?s=01&t=0',
#  'https://jobs.51job.com/qingdao/104459787.html?s=01&t=0',
#  'https://jobs.51job.com/shenzhen-ftq/104114173.html?s=01&t=0',
#  'https://jobs.51job.com/jinan-lxq/102931201.html?s=01&t=0',
#  'https://jobs.51job.com/shenzhen/101826475.html?s=01&t=0',
#  'https://jobs.51job.com/beijing-cyq/101450454.html?s=01&t=0',
#  'https://jobs.51job.com/shenzhen-ftq/100976822.html?s=01&t=0',
#  'https://jobs.51job.com/shanghai-xhq/97498737.html?s=01&t=0',
#  'https://jobs.51job.com/shanghai-sjq/95433704.html?s=01&t=0',
#  'https://jobs.51job.com/shanghai-pdxq/93372867.html?s=01&t=0',
#  'https://jobs.51job.com/shenzhen-nsq/91976157.html?s=01&t=0',
#  'https://jobs.51job.com/shanghai-pdxq/91413141.html?s=01&t=0',
#  'https://jobs.51job.com/shanghai-jaq/70845951.html?s=01&t=0',
#  'https://jobs.51job.com/beijing/109514989.html?s=01&t=0',
#  'https://jobs.51job.com/beijing-hdq/109165272.html?s=01&t=0']
#
# # for link in links:
# #     redis_client.lpush(redis_url_need_key, link)
#
# while True:
#     res = redis_client.brpop(redis_url_need_key)
#     print(type(res))
#     print(res)
#     print(res[1])
#     print(type(res[1]))
#     print(res[1].decode())
#     print("waiting...")
#     break


