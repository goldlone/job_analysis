package cn.goldlone.graduation.analysis

import cn.goldlone.graduation.constant.DataConfig
import org.apache.spark.sql.types._
import org.apache.spark.sql.{DataFrame, Row, SparkSession}

import scala.collection.mutable.ListBuffer

/**
  * Description: 加载岗位招聘信息 <br/>
  * Copyright 2019 CN <br/>
  * This program is protected by copyright laws.<br/>
  * Create time: 2019-01-04 18:55
  *
  * @author CN
  * @version 1.0
  */
object LoadJobInfo {
  
  /**
    * 创建岗位信息DataFrame，并注册临时视图
    * @param spark
    * @return
    */
  def load(spark: SparkSession): DataFrame = {
    // 清洗后的数据路径
    val okPath = DataConfig.DATA_SOURCE_OK
  
    val rdd = spark.sparkContext.textFile(okPath)
  
    val dataRDD = rdd.mapPartitions(it => {
      val list = new ListBuffer[Row]()
      it.foreach(elem => {
        val arr = elem.split("\u0001")
        if(arr.length == 19) {
          list.append(Row(arr(0), arr(1), arr(2) , arr(3),
            arr(4), arr(5), arr(6), arr(7), arr(8), arr(9),
            arr(10), arr(11), arr(12), arr(13), arr(14),
            arr(15), arr(16), arr(17), arr(18)))
        }
      })
    
      list.iterator
    }).cache()
  
    val schema = StructType(Seq(
      StructField("date", StringType),
      StructField("title", StringType),
      StructField("primary_type", StringType),
      StructField("sub_type", StringType),
      StructField("is_graduate", StringType),
      StructField("min_salary", StringType),
      StructField("max_salary", StringType),
      StructField("mid_salary", StringType),
      StructField("address", StringType),
      StructField("experience", StringType),
      StructField("education", StringType),
      StructField("features", StringType),
      StructField("description", StringType),
      StructField("company_name", StringType),
      StructField("company_type", StringType),
      StructField("company_scala", StringType),
      StructField("company_trade", StringType),
      StructField("company_desc", StringType),
      StructField("job_link", StringType)))
  
    val df = spark.createDataFrame(dataRDD, schema)
  
    // 注册临时视图
    df.createTempView("jobs")
    
    df
  }
  
}
