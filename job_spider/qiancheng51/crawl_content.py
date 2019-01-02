import utils.MyRequests as MyRequests
import requests
from lxml import html
import re
import redis
import time
import random
import json
import pymongo
import datetime
import logging

redis_client = redis.Redis(host="li.goldlone.cn", port=6379, db=0)

# 爬取完成的URL
redis_url_ok_key = "jobs:qiancheng51_url_ok"
# 待爬取的URL
redis_url_need_key = "jobs:qiancheng51_url_need"

# mongo_client = pymongo.MongoClient(host='li.goldlone.cn',port=27017)
# mongo_db = mongo_client["jobs"]
# mongo_col_qiancheng = mongo_db["qiancheng"]

headers = {
    'Host': 'search.51job.com',
    'Upgrade-Insecure-Requests': '1',
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36'
}

logging.basicConfig(level=logging.INFO)


def get_content(link):
    '''
    获取详情页的具体信息
    '''
    if len(link) > 0:
        sub_link = link
        if str(link).__contains__("?"):
            sub_link = link[0: link.index("?")]
        if not redis_client.sismember(redis_url_ok_key, sub_link):
            # r = requests.get(sub_link, headers=headers, timeout=10)
            r = MyRequests.get(sub_link, headers=headers, timeout=10)
            # s = requests.session()
            # s.keep_alive = False
            r.encoding = 'gbk'

            t = html.fromstring(r.text)
            try:
                # 过滤无效字符
                pattern = re.compile(r'[\r|\t|\n|\xa0]')

                # 岗位名称
                title_elem = t.xpath("/html/body/div[3]/div[2]/div[2]/div/div[1]/h1/@title")
                title = ""
                if len(title_elem) > 0:
                    title = title_elem[0]
                # 月薪
                salary_elem = t.xpath("/html/body/div[3]/div[2]/div[2]/div/div[1]/strong/text()")
                salary = ""
                if len(salary_elem) > 0:
                    salary = salary_elem[0]

                # 关键词
                keywords = t.xpath("/html/body/div[3]/div[2]/div[2]/div/div[1]/p[2]/text()")
                # address = re.sub(pattern, "", keywords[0])
                # experience = re.sub(pattern, "", keywords[1])
                # education = re.sub(pattern, "", keywords[2])
                # num = re.sub(pattern, "", keywords[3])
                # date = re.sub(pattern, "", keywords[4])
                keywords = re.sub(pattern, "", ",".join(keywords))

                # 岗位特色
                features = t.xpath("/html/body/div[3]/div[2]/div[2]/div/div[1]/div/div/span/text()")
                features = ",".join(features)

                # 岗位描述
                description = t.xpath("/html/body/div[3]/div[2]/div[3]/div[1]/div/p/text()")
                description = " ".join(description)

                # 公司名称
                company_name_elem = t.xpath("/html/body/div[3]/div[2]/div[4]/div[1]/div[1]/a/p/@title")
                company_name = ""
                if len(company_name_elem) > 0:
                    company_name = company_name_elem[0]

                # 公司信息
                company_infos = t.xpath("/html/body/div[3]/div[2]/div[4]/div[1]/div[2]/p/@title")

                company_type = ""
                company_scala = ""
                company_trade = ""

                if len(company_infos) >= 3:
                    # 公司类型
                    company_type = company_infos[0]
                    # 公司规模
                    company_scala = company_infos[1]
                    # 公司所处行业
                    company_trade = company_infos[2]

                # 公司描述
                company_description = t.xpath("/html/body/div[3]/div[2]/div[3]/div[3]/div/text()")
                company_description = re.sub(pattern, "", " ".join(company_description))

                info = {
                    # "address": address,
                    # "experience": experience,
                    # "education": education,
                    # "num": num,
                    # "date": date,
                    "title": title,
                    "salary": salary,
                    "keywords": keywords,
                    "features": features,
                    "description": description,
                    "company_name": company_name,
                    "company_type": company_type,
                    "company_scala": company_scala,
                    "company_trade": company_trade,
                    "company_description": company_description,
                    "url": sub_link
                }

                # 存储到文件
                filename = datetime.datetime.now().strftime('%Y_%m_%d')
                with open("../data/qiancheng51/"+filename+".txt", 'a+', encoding='utf-8') as file:
                    file.write(json.dumps(info, ensure_ascii=False) + '\n')

                # 存储到MongoDB
                # mongo_col_qiancheng.insert_one(info)

                # 保存相似岗位的链接
                similar_job_urls = t.xpath("/html/body/div[3]/div[2]/div[4]/div[2]/div[2]/div/a[1]/@href")
                for link in similar_job_urls:
                    redis_client.lpush(redis_url_need_key, link)

            except Exception as e:
                logging.error("解析或存储异常<{}>".format(sub_link))
                logging.exception(e)
                redis_client.lpush(redis_url_need_key, sub_link)
                return False

            redis_client.sadd(redis_url_ok_key, sub_link)
            logging.info("success crawl url: {}".format(sub_link))
            return True
        else:
            logging.info("重复URL<{}>".format(sub_link))
            return False
    return True


# 从待爬取队列中拉取URL
while True:
    link_data = redis_client.brpop(redis_url_need_key)
    link = link_data[1].decode()
    print(link)
    get_content(link)
    sleep_time = random.randint(0, 2)
    logging.info("休息{}s".format(sleep_time))
    time.sleep(sleep_time)
