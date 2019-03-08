package cn.goldlone.job_analysis.analysis.etl

import java.io.{File, FileOutputStream}

import cn.goldlone.graduation.constant.DataConfig
import cn.goldlone.job_analysis.analysis.etl.utils.EtlUtil
import cn.goldlone.job_analysis.analysis.constant.DataConfig
import cn.goldlone.job_analysis.analysis.entity.JobInfoQianCheng51
import com.alibaba.fastjson.JSON
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Description: 抽取Job类型 <br/>
  * Copyright 2019 CN <br/>
  * This program is protected by copyright laws.<br/>
  * Create time: 2019-01-04 21:26
  *
  * @author CN
  * @version 1.0
  */
object ExtractJobType {
  
  def main(args: Array[String]): Unit = {
  
    val conf = new SparkConf()
    conf.setMaster("local[*]")
        .setAppName("tarnsform-51job")
        .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    val sc = new SparkContext(conf)
  
    val rdd = sc.textFile(DataConfig.DATA_SOURCE)
    val jobClass = new JobInfoQianCheng51().getClass
    val filePath = new File(DataConfig.BASE_DIR + "/jobType/")
    if(!filePath.exists())
      filePath.mkdirs()
    
    rdd.map(line => {
      val jobInfo: JobInfoQianCheng51 = JSON.parseObject(line, jobClass)
      val jobType = EtlUtil.getJobType(jobInfo.getTitle)
//      val salary = EtlUtil.getSalary(jobInfo.getSalary)
//      val keywords = EtlUtil.parseKeywords(jobInfo.getKeywords)
      
      (jobType, jobInfo)
//      // 发布时间
//      val date = keywords._4
//      // 岗位名称
//      val title = jobInfo.getTitle
//      // 岗位一级类别
//      val primaryType = jobType._1
//      // 岗位二级类别
//      val subType = jobType._2
//      // 岗位是否是实习生
//      val isGraduate = jobType._3
//      // 薪资下限
//      val minSalary = salary._1
//      // 薪资上限
//      val maxSalary = salary._2
//      // 薪资中值
//      val midSalary = salary._3
//      // 工作地点
//      val address = keywords._1
//      // 工作经验
//      val experience = keywords._2
//      // 学历
//      val education = keywords._3
//      // 岗位特色
//      val features = jobInfo.getFeatures
//      // 岗位描述
//      val description = jobInfo.getDescription
//
//      // 企业名称
//      val companyName = jobInfo.getCompany_name
//      // 企业类型
//      val companyType = jobInfo.getCompany_type
//      // 企业规模
//      val companyScala = jobInfo.getCompany_scala
//      // 企业领域
//      val companyTrade = jobInfo.getCompany_trade
//      // 企业描述
//      val companyDescription = jobInfo.getCompany_description
//      // 招聘链接
//      val jobLink = jobInfo.getUrl
//
//      val builder = new StringBuilder
//      builder.append(date).append("\u0001")
//          .append(title).append("\u0001")
//          .append(primaryType).append("\u0001")
//          .append(subType).append("\u0001")
//          .append(isGraduate).append("\u0001")
//          .append(minSalary).append("\u0001")
//          .append(maxSalary).append("\u0001")
//          .append(midSalary).append("\u0001")
//          .append(address).append("\u0001")
//          .append(experience).append("\u0001")
//          .append(education).append("\u0001")
//          .append(features).append("\u0001")
//          .append(description).append("\u0001")
//          .append(companyName).append("\u0001")
//          .append(companyType).append("\u0001")
//          .append(companyScala).append("\u0001")
//          .append(companyTrade).append("\u0001")
//          .append(companyDescription).append("\u0001")
//          .append(jobLink).append("\u0001")
//
//      builder.toString()
    }) .groupByKey().foreach(item => {
      val jobType = item._1
      val filename = jobType._1 + "_" + jobType._2 + "_" + String.valueOf(jobType._3) + ".txt"
      val fis = new FileOutputStream(filePath.getAbsolutePath+"/"+filename)

      item._2.foreach(elem => fis.write((elem.getTitle + "\t" + elem.getUrl + "\n").getBytes()))
      fis.close()
    })
  
  }
  
}
