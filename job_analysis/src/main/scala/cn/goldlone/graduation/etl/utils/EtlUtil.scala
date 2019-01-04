package cn.goldlone.graduation.etl.utils

import java.text.DecimalFormat

import cn.goldlone.graduation.constant.JobLabel

/**
  * Description: ETL工具类
  *   1. 解析岗位类别
  *   2. 解析薪资状况
  *   3. 解析岗位关键词(工作地点, 工作经验, 学历, 发布时间)
  *   4. 探测岗位描述中的学历信息
  *   <br/>
  * Copyright 2019 CN <br/>
  * This program is protected by copyright laws.<br/>
  * Create time: 2019-01-02 16:27
  *
  * @author CN
  * @version 1.0
  */
object EtlUtil {
  
  /**
    * 根据岗位名称分析岗位类别
    * @param title 岗位名称
    * @return (一级分类、二级分类、是否为实习生:0、1)
    */
  def getJobType(title: String): (String, String, Int) = {
    var jobType: String = null
  
    title.toLowerCase match {
    
      // 项目经理
      case x: String if x.contains("项目经理") ||
          x.contains("项目主任") ||
          x.contains("pm工程师") ||
          x.contains("pm-power") ||
          x.contains("project manager") =>
        jobType = JobLabel.PROJECT_MANAGER
    
      // 产品经理
      case x: String if x.contains("产品经理") ||
          x.contains("product manager") =>
        jobType = JobLabel.PRODUCT_MANAGER
    
      // 销售经理
      case x: String if x.contains("销售经理") ||
          x.contains("sales account manager") ||
          x.contains("sales manager") =>
        jobType = JobLabel.SALES_MANAGER
    
      // 架构师
      case x: String if x.contains("架构") ||
          x.contains("高级工程师") ||
          x.contains("高级软件工程师") =>
        jobType = JobLabel.ARCHITECT
    
      // 大数据
      case x: String if x.contains("大数据") ||
          x.contains("数据仓库") ||
          x.contains("数仓") ||
          x.contains("hadoop") ||
          x.contains("etl") ||
          x.contains("spark") ||
          x.contains("可视化") ||
          x.contains("数据工程师") ||
          x.contains("数据开发") ||
          x.contains("data and analytics") ||
          x.contains("big data") ||
          x.contains("数据专员") ||
          x.contains("统计分析") ||
          x.contains("数据统计") ||
          x.contains("数据处理") ||
          x.contains("bi工程师") ||
          x.contains("data engineer") ||
          x.contains("数据平台") =>
        jobType = JobLabel.BIG_DATA
    
      // 前端
      case x: String if x.contains("前端") ||
          x.contains("h5") ||
          x.contains("html5") ||
          x.contains("web") ||
          x.contains("nodejs") ||
          x.contains("node.js") ||
          x.contains("vue") =>
        jobType = JobLabel.FRONT_END
    
      // java
      case x: String if x.contains("java") =>
        jobType = JobLabel.JAVA
    
      // php
      case x: String if x.contains("php") =>
        jobType = JobLabel.PHP
    
      // 后端
      case x: String if x.contains("后端") ||
          x.contains(".net") ||
          x.contains("服务器端开发") ||
          x.contains("后台") =>
        jobType = JobLabel.BACK_END
    
      // python
      case x: String if x.contains("python") =>
        jobType = JobLabel.PYTHON
    
      // c/c++/c#
      case x: String if x.contains("c++") ||
          x.contains("c#") ||
          x.contains("c语言") =>
        jobType = JobLabel.C
    
      // 算法
      case x: String if x.contains("算法") ||
          x.contains("人工智能") ||
          x.contains("nlp") ||
          x.contains("知识图谱") ||
          x.contains("舆情分析师") ||
          x.contains("语音识别") ||
          x.contains("自然语言处理") =>
        jobType = JobLabel.AI
    
      // 数据分析
      case x: String if x.contains("数据挖掘") ||
          x.contains("数据分析") ||
          x.contains("数据建模") =>
        jobType = JobLabel.DATA_MINING
    
      // 云计算
      case x: String if x.contains("云计算") ||
          x.contains("云系统") =>
        jobType = JobLabel.CLOUD_COMPUTING
    
      // 区块链
      case x: String if x.contains("区块链") =>
        jobType = JobLabel.BLOCK_CHAIN
    
      // 数据库
      case x: String if x.contains("数据库") ||
          x.contains("dba") =>
        jobType = JobLabel.DATABASE
    
      // 硬件
      case x: String if x.contains("硬件") ||
          x.contains("嵌入式") ||
          x.contains("单片机") ||
          x.contains("物联网") ||
          x.contains("fpga") =>
        jobType = JobLabel.HARDWARE
    
      // 运维
      case x: String if x.contains("运维") =>
        jobType = JobLabel.OPERATION_AND_MAINTENANCE
    
      // 测试
      case x: String if x.contains("测试") =>
        jobType = JobLabel.TEST_ENGINEER
    
      // 爬虫
      case x: String if x.contains("爬虫") =>
        jobType = JobLabel.CRAWLER
    
      // 移动开发
      case x: String if x.contains("android") ||
          x.contains("ios") ||
          x.contains("app") ||
          x.contains("手机软件") ||
          x.contains("安卓") ||
          x.contains("移动开发") =>
        jobType = JobLabel.ANDROID_IOS
    
      // UI
      case x: String if x.contains("平面") ||
          x.contains("视觉") ||
          x.contains("美工") ||
          x.contains("设计") =>
        jobType = JobLabel.UI
    
      // 网络主管
      case x: String if x.contains("网络主管") =>
        jobType = JobLabel.NETWORK_MANAGE
    
      // 内容运营
      case x: String if x.contains("运营") ||
          x.contains("seo") =>
        jobType = JobLabel.CONTENT_SPECIALIST
    
      // 小程序
      case x: String if x.contains("小程序") =>
        jobType = JobLabel.MINI_PROGRAM
    
      // 游戏
      case x: String if x.contains("游戏") ||
          x.contains("unity3d") ||
          x.contains("unity") =>
        jobType = JobLabel.GAME
    
      // 软件工程师
      case x: String if x.contains("软件工程师") ||
          x.contains("software engineer") ||
          x.contains("软件研发工程师") ||
          x.contains("开发工程师") ||
          x.contains("研发工程师") ||
          x.contains("erp工程师") ||
          x.contains("it engineer") ||
          x.contains("程序员") ||
          x.contains("计算机工程师") ||
          x.contains("需求工程师") ||
          x.contains("it系统工程师") ||
          x.contains("it工程师") ||
          x.contains("需求分析") ||
          x.contains("erp专员") ||
          x.contains("软件开发") =>
        jobType = JobLabel.SOFTWARE_ENGINEER
    
      // 技术支持
      case x: String if x.contains("技术支持") ||
          x.contains("it support engineer") ||
          x.contains("it支持") ||
          x.contains("it服务") =>
        jobType = JobLabel.SUPPORT_ENGINEER
    
      // 老师
      case x: String if x.contains("老师") ||
          x.contains("教师") ||
          x.contains("讲师") ||
          x.contains("teacher") ||
          x.contains("校长") ||
          x.contains("助教") =>
        jobType = JobLabel.TEACHER
    
      // 销售
      case x: String if x.contains("销售") ||
          x.contains("市场") =>
        jobType = JobLabel.SALES
    
      // 财务
      case x: String if x.contains("财务") ||
          x.contains("会计") =>
        jobType = JobLabel.FINANCE
    
      // 人事
      case x: String if x.contains("人事") ||
          x.contains("hr") ||
          x.contains("人力资源") =>
        jobType = JobLabel.PERSONNEL
    
      // 后期
      case x: String if x.contains("摄影") ||
          x.contains("后期") =>
        jobType = JobLabel.POST_PROCESSING
    
      // 总裁、总监
      case x: String if x.contains("总裁") ||
          x.contains("总监") ||
          x.contains("负责人") ||
          x.contains("主任") ||
          x.contains("经理") ||
          x.contains("副总") ||
          x.contains("主管") =>
        jobType = JobLabel.BOSS
    
      // 实习生
      case x: String if x.contains("实习") ||
          x.contains("应届") ||
          x.contains("校招") =>
        jobType = JobLabel.FRESH_GRADUATE
    
      case _ =>
        jobType = JobLabel.UNKNOWN
//        println(s"未匹配岗位名: [$title]")
    }
    
    ("一级分类", jobType, 0)
  }
  
  /**
    * 提取薪资，薪资范围则取中间值
    * @param salary 薪资信息
    * @return 月工资
    */
  def getSalary(salary: String): (Double, Double, Double) = {
  
    // 下限
    var minSalary: Double = 0.0
    // 上限
    var maxSalary: Double = 0.0
    // 中值
    var midSalary: Double = 0.0
    
    // 薪资金额单位
    var scala: Int = 1
  
    // 分析货币金额单位
    var tmp: String = null
    salary match {
      case x if x.contains("万") =>
        scala = 10000
        tmp = salary.substring(0, salary.indexOf("万"))
      case x if x.contains("千") =>
        scala = 1000
        tmp = salary.substring(0, salary.indexOf("千"))
      case x if x.contains("百") =>
        scala = 100
        tmp = salary.substring(0, salary.indexOf("百"))
      case x if x.contains("元") =>
        tmp = salary.substring(0, salary.indexOf("元"))
      case _ =>
        tmp = null
    }
  
    // 分析具体金额
    if(tmp != null) {
      try  {
        val arr = tmp.split("-")
        if (arr.length == 1) {
          // 薪资为固定值表示
          minSalary = arr(0).toDouble * scala
          maxSalary = minSalary
          midSalary = minSalary
        } else if (arr.length == 2) {
          // 区间值表示
          minSalary = arr(0).toDouble * scala
          maxSalary = arr(1).toDouble * scala
          // 计算中值，并保留两位小数
          val df = new DecimalFormat("0.00")
          midSalary = (minSalary + maxSalary) / 2
          midSalary = df.format(midSalary).toDouble
        }
      } catch {
        case _: Exception =>
      }
    }
  
    // 工资周期全部转换为月单位
    salary match {
      case x if x.contains("年") =>
        minSalary = minSalary / 12
        maxSalary = maxSalary / 12
        midSalary = midSalary / 12
        
      case x if x.contains("天") =>
        minSalary = minSalary * 30
        maxSalary = maxSalary * 30
        midSalary = midSalary * 30
        
      case _ =>
        minSalary = minSalary
        maxSalary = maxSalary
        midSalary = midSalary
    }
    
    (minSalary, maxSalary, midSalary)
  }
  
  
  /**
    * 解析岗位关键词
    * @param keywords 关键词
    * @return (工作地点, 工作经验, 学历, 发布时间)
    */
  def parseKeywords(keywords: String): (String, String, String, String) = {
    val unknown = "unknown"
    
    // 工作地点
    var address = unknown
    // 工作经验
    var experience = "-1"
    // 学历
    var education = unknown
    // 发布时间
    var date = unknown
  
    val arr = keywords.split(",")
    if(arr.length > 3) {
      address = arr(0)
      if(address.contains("-")) {
        address = address.substring(0, address.indexOf("-"))
      }
    
      if(arr(1).contains("无")) {
        experience = "0"
      } else {
        if(arr(1).indexOf("年") != -1) {
          experience = arr(1).substring(0, arr(1).indexOf("年"))
          val tmp = experience.split("-")
          if(tmp.length == 1) {
            experience = tmp(0)
          } else if(tmp.length == 2) {
            val min = tmp(0).toInt
            val max = tmp(1).toInt
            experience = ""
            for(i <- min until max) {
              experience += i.toString + ","
            }
            experience += max
          } else {
            experience = "-1"
          }
        } else {
          println(arr(1))
          experience = "-1"
        }
      }
      
      education = arr(2)
      if(education.equals("初中")) {
        education = "初中"
      } else if(education.contains("中技")) {
        education = "中专"
      } else if(education.contains("招")) {
        education = unknown
      }
      
      for(i <- 2 until arr.length) {
        if(arr(i).contains("发布")) {
          date = arr(i).substring(0, arr(i).indexOf("发布"))
          val tmp = date.split("-")
          if(tmp.length == 2) {
            if(tmp(0).toInt > 5) {
              date = "2018-" + date
            } else {
              date = "2019-" + date
            }
          } else {
            date = unknown
          }
        }
      }
    }
  
    (address, experience, education, date)
  }
  
  
  /**
    * 检测岗位描述信息中所包含的学历信息
    * @param description 岗位描述信息
    * @return
    */
  def detectEducation(description: String): String = {
    var ret: String = "unknown"
    // 初中、高中、中专、中技、大专、本科、硕士、博士
    description match {
      case x if x.contains("博士") => ret = "博士"
      case x if x.contains("硕士") => ret = "硕士"
      case x if x.contains("本科") => ret = "本科"
      case x if x.contains("大专") => ret = "大专"
      case x if x.contains("中专") || x.contains("中技") => ret = "中专"
      case x if x.contains("高中") => ret = "高中"
      case x if x.contains("初中") => ret = "初中"
      case _ => ret = "unknown"
    }
    
    ret
  }
  
  /**
    * 获取岗位特色信息（数组）
    * @param features 特色信息
    * @return String数组
    */
  def getFeatures(features: String): Array[String] = {
    if(features == null || features.isEmpty) {
      return Array[String]()
    }
    
    features.split(",")
  }
  
  
  
}
