# -*- coding:utf-8 -*-
from fake_useragent import UserAgent
import random


class FakeUserAgent:
    '''
    生成伪装的User-Agent
    '''
    def __init__(self):
        self.ua = UserAgent()
        self.data = []
        for key in self.ua.data_browsers.keys():
            tmp_list = self.ua.data_browsers.get(key)
            for item in tmp_list:
                self.data.append(item)

    def random(self):
        '''
        随机取出一个User-Agent
        :return: str => user-agent
        '''
        return self.data[random.randint(0, len(self.data) - 1)]
