# -*- coding: utf-8 -*-

from utils.IpProxy import IpProxy
from utils.FakeUserAgent import FakeUserAgent

import requests
import time

ua = FakeUserAgent()
ip_proxy = IpProxy()


def get(url, headers=None, timeout=10, count=0):
    if headers is None:
        headers = {
            "User-Agent": ua.random()
        }
    else:
        headers["User-Agent"] = ua.random()

    http_proxy = ip_proxy.get_ip_http()
    proxies = {
        "http": "{0}:{1}".format(http_proxy[0], http_proxy[1])
    }

    # print(url)
    # if count >= 4:
    #     time.sleep(30)
    #     return get(url, headers, timeout)

    try:
        r = requests.get(url, headers=headers, proxies=proxies, timeout=timeout)
        s = requests.session()
        code = r.status_code
        if 200 <= code < 300:
            return r
        else:
            count += 1
            print("请求失败[{0}]".format(http_proxy[0]))
            # ip_proxy.delete_ip(http_proxy[0])
            return get(url, headers, timeout, count + 1)
    except requests.exceptions.Timeout:
        print("请求失败[{0}]".format(http_proxy[0]))
        # ip_proxy.delete_ip(http_proxy[0])
        return get(url, headers, timeout, count + 1)

# if __name__ == '__main__':
# #     # r = get("https://www.xicidaili.com/nn/1")
#     r = get("https://jobs.51job.com/qingdao-hdq/109536026.html?s=01&t=0")
#     print(r.status_code)
#     print(r.text)
