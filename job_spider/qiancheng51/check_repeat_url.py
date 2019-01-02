# -*- coding: utf-8 -*-

"""
循环去重带爬取URL列表
每隔1s进行检测一条
从左边拿出一条，非重复则放置右侧
"""

import redis
import time
import logging

formatter = logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s')

fh = logging.FileHandler('logs/check_repeat_url.log', encoding='utf-8')
fh.setLevel(logging.INFO)
fh.setFormatter(formatter)

sh = logging.StreamHandler()
sh.setLevel(logging.DEBUG)
sh.setFormatter(formatter)

log = logging.getLogger("check_repeat_url")
log.addHandler(sh)
log.addHandler(fh)
log.setLevel(logging.INFO)


redis_client = redis.Redis(host='li.goldlone.cn', port=6379, db=0)

# 待爬取的URL
redis_url_need_key = "jobs:qiancheng51_url_need"
# 爬取完成的URL
redis_url_ok_key = "jobs:qiancheng51_url_ok"


import re
pattern = re.compile("https://jobs.51job.com/.*")

while True:
    link_data = redis_client.blpop(redis_url_need_key)
    link = link_data[1].decode()

    if re.match(pattern, link) is None:
        continue

    sub_link = link
    if str(link).__contains__("?"):
        sub_link = link[0: link.index("?")]
        log.info("判断 <{}> 是否重复".format(sub_link))
    if not redis_client.sismember(redis_url_ok_key, sub_link):
        log.info("==> 未重复: {}".format(sub_link))
        redis_client.rpush(redis_url_need_key, sub_link)

    time.sleep(1)


