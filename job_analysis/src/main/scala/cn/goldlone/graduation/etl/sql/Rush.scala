package cn.goldlone.graduation.etl.sql

import cn.goldlone.graduation.etl.entity.JobInfoQianCheng51
import com.alibaba.fastjson.parser.Feature
import com.alibaba.fastjson.serializer.SerializerFeature
import com.alibaba.fastjson.{JSON, JSONObject}
import org.apache.spark.sql.{Row, SparkSession}


/**
  * Description: 清洗数据 <br/>
  * Copyright 2019 CN <br/>
  * This program is protected by copyright laws.<br/>
  * Create time: 2019-01-01 21:32
  *
  * @author CN
  * @version 1.0
  */
object Rush {
  
  
  def main(args: Array[String]): Unit = {
  
//    val sparkConf = new SparkConf()
    val spark = SparkSession.builder()
        .appName("rush-qiancheng51")
        .master("local[*]")
        .getOrCreate()
    
    val rdd = spark.sparkContext.textFile("E:\\2018_12_31.txt")
    
//    rdd.foreach(println)
  
    // {
    //  "title": "大数据开发工程师(职位编号：技术二部)",
    //  "salary": "1.2-2.5万/月",
    //  "keywords": "广州-黄埔区,2年经验,本科,招6人,12-29发布",
    //  "features": "五险一金,员工旅游,绩效奖金,年终奖金,定期体检",
    //  "description": "职位描述：  1、基于每日百亿级展现数据，搭建大数据处理平台;实现流式事件的实时计算，落地储存。对于离线数据完成数据仓库的建立，为数据挖掘提供有效的数据；  2、使用用户行为数据，挖掘 数据层次关系，构建用户画像和风险控制；  3、数据分析，挖掘，模型具体的产品化；了解金融领域业务，能和PM合作，基于数据驱动持续优化数据产品。   职位要求：  1、本科及以上学历，数学或计算机相关专业，2年以上工作经验； 2、熟悉C++、JAVA、python中的一种或多种编程技术，编程能力强，熟悉大数据处理技术，善于学习应用业界领先数据架构和技术；  3、1年以上对大数据开源组件有使用经验，对hadoop/hive/spark/ES/druid 其中一项精通；  4、熟悉数据挖掘算法，包括数据清洗.统计学习.分类聚类算法，并能够验证算法效果；  5、善于学习，思维活跃，善于从数据中发现.思考并解决问题；  6、有金融领域研发经验者优先；",
    //  "company_name": "广州广电运通信息科技有限公司",
    //  "company_type": "国企",
    //  "company_scala": "1000-5000人",
    //  "company_trade": "计算机软件,互联网/电子商务",
    //  "company_description": "广州无线电集团下属广电运通金融电子股份有限公司根据集团发展规划及经营的需要，以自有资金于2013年11月投资3000万成立全资子公司“广州广电运通信息科技有限公司”，作为专门开展软件业务的平台，专业从事计算机软、硬件技术及系统集成的开发和销售服务机构。  作为金融IT综合服务提供商，广州广电运通信息科技有限公司（简称：运通信息）为银行、保险、基金、证券及互联网等金融行业提供整体解决方案和软件产品，业务范围涵盖了规划咨询、软件开发实施、系统集成、人力外包及系统维护等服务，是中国金融行业客户重要的IT服务提供商和战略合作伙伴。 面对竞争激烈的金融服务市场，运通信息凭借专业的服务管理及强大的技术实力，不断创新服务模式，规范服务标准，在实现客户价值回报最大化的同时稳健成长。 ",
    //  "url": "https://jobs.51job.com/guangzhou-hpq/103030895.html"
    //}
    
//    val dataRdd = rdd.map(line =>{
//      val json = JSON.parseObject(line)
//      val title = json.getString("title")
//      val salary = json.getString("salary")
//      Row(title, salary)
//    })
    
//    val schema = StructType(List(StructField("title", StringType),
//      StructField("salary", StringType)))
//
//    val df = spark.createDataFrame(dataRdd, schema)

//    df.registerTempTable("job")
    
    // 1058
//    val frame = spark.sql("select title, count(1) c from job where instr(title, '大数据') != 0 group by title order by c desc")
    
//    frame.show(3000)
    
    
//    df.show()
  
//    val dataSet = spark.createDataset(rdd)
//    dataSet.show(20)
    
    val jobClass = new JobInfoQianCheng51().getClass
    rdd.foreach(line => {
      val job = JSON.parseObject(line, jobClass)
      println(job.getTitle)
    })
  
    spark.stop()
  }
  


}
