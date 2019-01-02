# -*- coding: utf-8 -*-

"""
添加搜索页
"""

from urllib import parse
import redis

redis_client = redis.Redis(host="li.goldlone.cn", port=6379, db=0)

# 待解析的检索页
redis_page_url = "jobs:qiancheng51_page_url"

# keywords = ["大数据", "前端", "后端", "Java", "php", "python", "h5", "算法", "项目经理"]
keywords = ["算法"]

for keyword in keywords:
    key = parse.quote(parse.quote(keyword))
    page = 1
    url = 'http://search.51job.com/list/000000,000000,0000,00,9,99,' + key + ',2,' + str(page) + '.html'

    print(url)
    redis_client.lpush(redis_page_url, url)
