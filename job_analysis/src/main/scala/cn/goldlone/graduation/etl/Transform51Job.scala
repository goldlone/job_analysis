package cn.goldlone.graduation.etl

import java.io.File

import cn.goldlone.graduation.constant.{DataConfig, JobSubType}
import cn.goldlone.graduation.etl.entity.JobInfoQianCheng51
import cn.goldlone.graduation.etl.utils.EtlUtil
import com.alibaba.fastjson.JSON
import org.apache.spark.{SparkConf, SparkContext}

import scala.util.matching.Regex

/**
  * Description: 解析前程无忧的招聘信息，并存储 <br/>
  * Copyright 2019 CN <br/>
  * This program is protected by copyright laws.<br/>
  * Create time: 2019-01-03 21:37
  *
  * @author CN
  * @version 1.0
  */
object Transform51Job {
  
  def main(args: Array[String]): Unit = {
    
    val conf = new SparkConf()
    conf.setMaster("local[*]")
        .setAppName("tarnsform-51job")
        .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    val sc = new SparkContext(conf)
    
    val dataPath = DataConfig.DATA_SOURCE
    val etlPath = DataConfig.DATA_SOURCE_ETL
    
    val f = new File(etlPath)
    if(f.exists()) {
      if(f.isDirectory){
        for (ff <- f.listFiles())
          ff.delete()
      }
      f.delete()
    }
    
    val linesRDD = sc.textFile(dataPath)
    
    linesRDD.mapPartitions(it => {
      
      val list = new scala.collection.mutable.ListBuffer[String]()
      val jobClass = new JobInfoQianCheng51().getClass
      
      it.foreach(elem => {
        if(elem != null && !"".equals(elem)) {
          val jobInfo = JSON.parseObject(elem, jobClass)
          val pattern = new Regex("https://jobs.51job.com.*")
          if(pattern.findAllIn(jobInfo.getUrl).length == 1) {
            // 岗位类型
            val jobType = EtlUtil.getJobType(jobInfo.getTitle)
            
            if(!JobSubType.UNKNOWN.equals(jobType._2)) {
              // 薪资状况
              val salary = EtlUtil.getSalary(jobInfo.getSalary)
              val minSalary = salary._1
              val maxSalary = salary._2
              val midSalary = salary._3
  
              // (工作地点, 工作经验, 学历, 发布时间)
              val keywords = EtlUtil.parseKeywords(jobInfo.getKeywords)
  
              // 工作地点
              val address = keywords._1
  
              // 工作经验
              val experience = keywords._2
  
              // 学历
              var education = keywords._3
              if(education.equals("unknown")) {
                // 关键词中未检测到学历信息，则从描述信息中查找
                education = EtlUtil.detectEducation(jobInfo.getDescription)
              }
  
              // 发布时间
              val date = keywords._4
  
              val buffer = new StringBuilder()
              buffer.append(date).append("\u0001")                          // 发布时间
                  .append(jobInfo.getTitle).append("\u0001")                // 岗位名称
                  .append(jobType._1).append("\u0001")                      // 岗位一级类别
                  .append(jobType._2).append("\u0001")                      // 岗位二级类别
                  .append(jobType._3).append("\u0001")                      // 岗位是否是实习生
                  .append(minSalary).append("\u0001")                       // 薪资下限
                  .append(maxSalary).append("\u0001")                       // 薪资上限
                  .append(midSalary).append("\u0001")                       // 薪资中值
                  .append(address).append("\u0001")                         // 工作地点
                  .append(experience).append("\u0001")                      // 工作经验
                  .append(education).append("\u0001")                       // 学历
                  .append(jobInfo.getFeatures).append("\u0001")             // 岗位特色
                  .append(jobInfo.getDescription).append("\u0001")          // 岗位描述
                  .append(jobInfo.getCompany_name).append("\u0001")         // 企业名称
                  .append(jobInfo.getCompany_type).append("\u0001")         // 企业类型
                  .append(jobInfo.getCompany_scala).append("\u0001")        // 企业规模
                  .append(jobInfo.getCompany_trade).append("\u0001")        // 企业领域
                  .append(jobInfo.getCompany_description).append("\u0001")  // 企业描述
                  .append(jobInfo.getUrl)                                   // 招聘链接
  
              list.append(buffer.toString())
            }
          }
        }
      })
    
      list.iterator
    }).saveAsTextFile(etlPath)
  
  
    sc.stop()
  }
  
}
