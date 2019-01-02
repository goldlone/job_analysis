import re
import time
import random
import redis
from lxml import html
import logging

import utils.MyRequests as MyRequests

formatter = logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s')

fh = logging.FileHandler('logs/crawl_search.log', encoding='utf-8')
fh.setLevel(logging.INFO)
fh.setFormatter(formatter)

sh = logging.StreamHandler()
sh.setLevel(logging.DEBUG)
sh.setFormatter(formatter)

log = logging.getLogger("crawl_search")
log.addHandler(sh)
log.addHandler(fh)
log.setLevel(logging.INFO)

redis_client = redis.Redis(host="li.goldlone.cn", port=6379, db=0)

# 待爬取的URL
redis_url_need_key = "jobs:qiancheng51_url_need"
# 爬取完成的URL
redis_url_ok_key = "jobs:qiancheng51_url_ok"
# 待解析的检索页
redis_page_url = "jobs:qiancheng51_page_url"

headers = {
    'Host': 'search.51job.com',
    'Upgrade-Insecure-Requests': '1',
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36'
}


def add_page_url(url):
    """
    将需要解析的搜索页的URL添加到队列中
    :param url:
    :return:
    """
    redis_client.lpush(redis_page_url, url)


def get_links(url):
    """
    获取搜索页的所有链接
    """
    try:
        r = MyRequests.get(url, headers=headers, timeout=10)
        r.encoding = 'gbk'
        reg = re.compile(r'class="t1 ">.*? <a target="_blank" title=".*?" href="(.*?)".*? <span class="t2">', re.S)
        links = re.findall(reg, r.text)
        # 将链接放入带爬取列表
        for link in links:
            log.info("get url: " + link)
            if not redis_client.sismember(redis_url_ok_key, link):
                log.info("==> save url: " + link)
                redis_client.lpush(redis_url_need_key, link)

        t = html.fromstring(r.text)
        page_btn = t.xpath('//*[@id="resultList"]/div[@class="dw_page"]/div/div/div/ul/li[@class="bk"]/a')
        if len(page_btn) == 2 and page_btn[1].text == '下一页':
            add_page_url(page_btn[1].attrib["href"])
        elif len(page_btn) == 1 and page_btn[0].text == '下一页':
            add_page_url(page_btn[0].attrib["href"])
        elif len(page_btn) > 2:
            for e in page_btn:
                if e.text == '下一页':
                    add_page_url(e.attrib["href"])

    except Exception as e:
        print(e)
        add_page_url(url)


if __name__ == '__main__':
    # 从待解析搜索页面队列中拿取一个URL
    while True:
        url_data = redis_client.brpop(redis_page_url)
        url = url_data[1]
        get_links(url)

        # 休息一刻
        sleep_time = random.randint(0, 2)
        log.info("now sleep {}s".format(sleep_time))
        time.sleep(sleep_time)
