package cn.goldlone.graduation.constant;

import java.util.HashMap;

/**
 * Description: 岗位二级分类 <br/>
 * Copyright 2019 CN <br/>
 * This program is protected by copyright laws.<br/>
 * Create time: 2019-01-04 10:14
 *
 * @author CN
 * @version 1.0
 */
public class JobSubType {
  // 互联网IT
//  public static final String UN = "Java开发";
//  public static final String UN = "UI设计师";
//  public static final String UN = "Web前端";
//  public static final String UN = "PHP";
//  public static final String UN = "Python";
//  public static final String UN = "Android";
//  public static final String UN = "美工";
//  public static final String UN = "深度学习";
//  public static final String UN = "算法工程师";
//  public static final String UN = "Hadoop";
//  public static final String UN = "Node.js";
//  public static final String UN = "数据开发";
//  public static final String UN = "数据分析师";
//  public static final String UN = "数据架构";
//  public static final String UN = "人工智能";
//  public static final String UN = "区块链";
//  public static final String UN = "电气工程师";
//  public static final String UN = "电子工程师";
//  public static final String UN = "PLC";
//  public static final String UN = "测试工程师";
//  public static final String UN = "设备工程师";
//  public static final String UN = "硬件工程师";
//  public static final String UN = "结构工程师";
//  public static final String UN = "工艺工程师";
//  public static final String UN = "产品经理";
//  public static final String UN = "新媒体运营";
//  public static final String UN = "运营专员";
//  public static final String UN = "淘宝运营";
//  public static final String UN = "天猫运营";
//  public static final String UN = "产品助理";
//  public static final String UN = "产品运营";
//  public static final String UN = "淘宝客服";
//  public static final String UN = "游戏运营";
//  public static final String UN = "编辑";
//
//  // 金融
//  public static final String UN = "投资经理";
//  public static final String UN = "风控";
//  public static final String UN = "催收";
//  public static final String UN = "银行柜员";
//  public static final String UN = "银行销售";
//  public static final String UN = "信审";
//  public static final String UN = "信用卡";
//  public static final String UN = "贷款";
//  public static final String UN = "金融产品";
//  public static final String UN = "汽车金融";
//  public static final String UN = "金融研究";
//  public static final String UN = "证券";
//  public static final String UN = "交易员";
//  public static final String UN = "投资经理";
//  public static final String UN = "期货";
//  public static final String UN = "操盘手";
//  public static final String UN = "基金";
//  public static final String UN = "股票";
//  public static final String UN = "投资顾问";
//  public static final String UN = "信托";
//  public static final String UN = "典当";
//  public static final String UN = "担保";
//  public static final String UN = "信贷";
//  public static final String UN = "权证";
//  public static final String UN = "财产保险";
//  public static final String UN = "保险内勤";
//  public static final String UN = "理赔";
//  public static final String UN = "精算师";
//  public static final String UN = "保险销售";
//  public static final String UN = "理财顾问";
//  public static final String UN = "查勘定损";
//  public static final String UN = "车险";
//
//  // 房地产_建筑
//  public static final String UN = "土建工程师";
//  public static final String UN = "施工员";
//  public static final String UN = "资料员";
//  public static final String UN = "预算员";
//  public static final String UN = "造价员";
//  public static final String UN = "一级建造师";
//  public static final String UN = "室内设计师";
//  public static final String UN = "土建";
//  public static final String UN = "暖通";
//  public static final String UN = "项目经理";
//  public static final String UN = "电气工程师";
//  public static final String UN = "建筑设计";
//  public static final String UN = "置业顾问";
//  public static final String UN = "房地产销售";
//  public static final String UN = "房地产招商";
//  public static final String UN = "开发报建";
//  public static final String UN = "房地产策划";
//  public static final String UN = "房地产开发";
//  public static final String UN = "房地产评估";
//  public static final String UN = "地产经纪";
//  public static final String UN = "物业";
//  public static final String UN = "物业经理";
//  public static final String UN = "保安";
//  public static final String UN = "客服";
//  public static final String UN = "物业管理";
//  public static final String UN = "物业客服";
//  public static final String UN = "电工";
//  public static final String UN = "物业主管";
//  public static final String UN = "物业维修";
//  public static final String UN = "消防";
//  public static final String UN = "客服主管";
//  public static final String UN = "前台";
//  public static final String UN = "文员";
//  public static final String UN = "物业项目经理";
//
//  // 贸易
//  public static final String UN = "采购";
//  public static final String UN = "外贸";
//  public static final String UN = "外贸业务员";
//  public static final String UN = "外贸跟单";
//  public static final String UN = "采购助理";
//  public static final String UN = "外贸日语";
//  public static final String UN = "采购专员";
//  public static final String UN = "外贸英语";
//  public static final String UN = "外贸助理";
//  public static final String UN = "采购经理";
//  public static final String UN = "买手";
//
//  // 零售
//  public static final String UN = "导购";
//  public static final String UN = "营业员";
//  public static final String UN = "店长";
//  public static final String UN = "收银员";
//  public static final String UN = "销售";
//  public static final String UN = "导购员";
//  public static final String UN = "督导";
//  public static final String UN = "客服";
//  public static final String UN = "新零售产品";
//  public static final String UN = "理货员";
//
//  // 物流
//  public static final String UN = "供应链";
//  public static final String UN = "物流专员";
//  public static final String UN = "物流经理";
//  public static final String UN = "物流运营";
//  public static final String UN = "物流跟单";
//  public static final String UN = "物流管理";
//  public static final String UN = "物仓调度";
//  public static final String UN = "货运代理";
//  public static final String UN = "报检报关";
//  public static final String UN = "仓储管理";
//
//  // 教育
//  public static final String UN = "教师";
//  public static final String UN = "英语老师";
//  public static final String UN = "课程顾问";
//  public static final String UN = "英语";
//  public static final String UN = "教务";
//  public static final String UN = "美术老师";
//  public static final String UN = "幼教";
//  public static final String UN = "小学教师";
//  public static final String UN = "班主任";
//  public static final String UN = "助教";
//
//  // 传媒
//  public static final String UN = "编导";
//  public static final String UN = "摄影师";
//  public static final String UN = "编剧";
//  public static final String UN = "摄影";
//  public static final String UN = "后期制作";
//  public static final String UN = "制片";
//  public static final String UN = "记者";
//  public static final String UN = "剪辑";
//  public static final String UN = "化妆师";
//
//  // 广告
//  public static final String UN = "广告创意";
//  public static final String UN = "美术指导";
//  public static final String UN = "策划经理";
//  public static final String UN = "文案";
//  public static final String UN = "广告制作";
//  public static final String UN = "媒介";
//  public static final String UN = "广告审核";
//  public static final String UN = "平面设计";
//  public static final String UN = "网页设计";
//  public static final String UN = "插画师";
//  public static final String UN = "工业设计";
//  public static final String UN = "视觉设计";
//
//  // 服务业
//  public static final String UN = "美容师";
//  public static final String UN = "美容学徒";
//  public static final String UN = "美容导师";
//  public static final String UN = "纹绣师";
//  public static final String UN = "医美";
//  public static final String UN = "美甲师";
//  public static final String UN = "健身教练";
//  public static final String UN = "导游";
//  public static final String UN = "旅游顾问";
//  public static final String UN = "旅游计调";
//  public static final String UN = "签证";
//  public static final String UN = "旅游销售";
//  public static final String UN = "票务";
//  public static final String UN = "服务员";
//  public static final String UN = "收银员";
//  public static final String UN = "店长";
//  public static final String UN = "酒店前台";
//  public static final String UN = "酒店管理";
//  public static final String UN = "餐饮管理";
//  public static final String UN = "收银";
//  public static final String UN = "保安";
//  public static final String UN = "保洁";
//  public static final String UN = "月嫂";
//  public static final String UN = "保姆";
//  public static final String UN = "家政";
//  public static final String UN = "婚礼策划";
//  public static final String UN = "育婴师";
//  public static final String UN = "催乳师";
//  public static final String UN = "司机";
//
//  // 市场
//  public static final String UN = "市场营销";
//  public static final String UN = "市场策划";
//  public static final String UN = "市场顾问";
//  public static final String UN = "市场总监";
//  public static final String UN = "市场推广";
//  public static final String UN = "SEO";
//  public static final String UN = "品牌经理";
//  public static final String UN = "SEM";
//  public static final String UN = "商务渠道";
//  public static final String UN = "网络营销";
//  public static final String UN = "活动策划";
//  public static final String UN = " APP推广";
//
//  // 销售
//  public static final String UN = "销售专员";
//  public static final String UN = "销售经理";
//  public static final String UN = "客户代表";
//  public static final String UN = "销售代表";
//  public static final String UN = "BD经理";
//  public static final String UN = "大客户销售";
//  public static final String UN = "渠道销售";
//  public static final String UN = "销售助理";
//  public static final String UN = "电话销售";
//  public static final String UN = "销售顾问";
//  public static final String UN = "商品经理";
//  public static final String UN = "广告销售";
//  public static final String UN = "网络营销";
//  public static final String UN = "营销主管";
//  public static final String UN = "销售总监";
//  public static final String UN = "商务总监";
//  public static final String UN = "城市经理";
//
//  // 人事
//  public static final String UN = "人力资源主管";
//  public static final String UN = "招聘";
//  public static final String UN = "HRBP";
//  public static final String UN = "人力资源专员";
//  public static final String UN = "培训";
//  public static final String UN = "薪资福利";
//  public static final String UN = "绩效考核";
//  public static final String UN = "人力资源经理";
//  public static final String UN = "人力资源总监";
//  public static final String UN = "员工关系";
//  public static final String UN = "组织发展";
//
//  // 财务
//  public static final String UN = "会计";
//  public static final String UN = "出纳";
//  public static final String UN = "财务顾问";
//  public static final String UN = "结算";
//  public static final String UN = "税务";
//  public static final String UN = "风控";
//  public static final String UN = "财务经理";
//  public static final String UN = "财务主管";
//  public static final String UN = "财务分析";
//  public static final String UN = "法务专员";
//  public static final String UN = "律师";
//  public static final String UN = "法律顾问";
//  public static final String UN = "法务主管";
//
//  // 行政
//  public static final String UN = "行政专员";
//  public static final String UN = "前台";
//  public static final String UN = "行政主管";
//  public static final String UN = "经理助理";
//  public static final String UN = "后勤";
//  public static final String UN = "司机";
//  public static final String UN = "行政经理";
//  public static final String UN = "行政总监";



}
