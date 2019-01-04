package cn.goldlone.graduation.analysis

import org.apache.spark.sql.types.{DoubleType, StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SparkSession}

import scala.collection.mutable.ListBuffer

/**
  * Description: xxx <br/>
  * Copyright 2019 CN <br/>
  * This program is protected by copyright laws.<br/>
  * Create time: 2019-01-03 22:07
  *
  * @author CN
  * @version 1.0
  */
object TestSQL {
  
  def main(args: Array[String]): Unit = {
  
    val spark: SparkSession = SparkSession.builder()
        .master("local[*]")
        .appName("test-sql")
        .config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
        .getOrCreate()
    
    // 加载表信息
    LoadJobInfo.load(spark)
    
    spark.sql("select * from jobs limit 20").show()
    
    
    
    // 每种岗位的数量、平均工资、最低工资、最高工资
//    spark.sql("select type," +
//        "  count(1) job_count, " +
//        "  avg(salary) avg_salary," +
//        "  min(salary) min_salary," +
//        "  max(salary) max_salary " +
//        "from jobs " +
//        "group by type " +
//        "order by avg(salary) desc").show(100)
    
    // 有多个时间
//    spark.sql("select distinct date from jobs").show(1000)
  
//    spark.sql("select distinct salary from jobs order by salary desc").show(2000)
    
//    spark.sql("select distinct address from jobs").show(1000)
//    spark.sql("select experience, count(1) from jobs group by experience").show(1000)
//    spark.sql("select education, count(1) from jobs group by education").show(1000)
//    spark.sql("select distinct company_type from jobs").show(1000)
//    spark.sql("select distinct company_scala from jobs").show(1000)
//    spark.sql("select distinct company_trade from jobs").show(1000)
    
//    val df2 = spark.sql("select education, url from jobs where education='unknown'")
//    df2.foreach(row => {
//      println(row.getString(1))
//    })
    
//    spark.sql("select experience, avg(salary) from jobs group by experience order by avg(salary) desc").show()
    
    // 地域与 岗位数量、平均工资间的关系（根据岗位数量逆序）
//    spark.sql(
//      """
//        | select address, count(1), avg(salary)
//        | from jobs
//        | group by address
//        | order by count(1) desc
//      """.stripMargin).show(100000)
    
    // 随时间变化，岗位的需求量
//    spark.sql(
//      """
//        | select date, count(1)
//        | from jobs
//        | group by date
//        | order by date
//      """.stripMargin).show(10000)
    
//    spark.sql(
//      """
//        | select type, count(1)
//        | from jobs
//        | group by type
//        | order by count(1) desc
//      """.stripMargin).show(100)
    
    spark.close()
  }
  
}
