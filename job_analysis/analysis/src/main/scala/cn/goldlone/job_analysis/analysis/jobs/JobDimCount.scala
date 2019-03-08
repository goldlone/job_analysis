package cn.goldlone.job_analysis.analysis.jobs

import java.io.{BufferedReader, File, InputStreamReader, PrintWriter}
import java.nio.file.Paths

import cn.goldlone.graduation.constant.DataConfig
import cn.goldlone.graduation.entity.JobInfo
import cn.goldlone.job_analysis.analysis.etl.JobDescWords.getClass
import cn.goldlone.job_analysis.analysis.etl.utils.EtlUtil
import cn.goldlone.job_analysis.analysis.constant.DataConfig
import cn.goldlone.job_analysis.analysis.entity.JobInfo
import cn.goldlone.job_analysis.analysis.entity.JobInfoQianCheng51
import com.alibaba.fastjson.JSON
import com.huaban.analysis.jieba.JiebaSegmenter.SegMode
import com.huaban.analysis.jieba.{JiebaSegmenter, WordDictionary}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * Description: xxx <br/>
  * Copyright 2019 CN <br/>
  * This program is protected by copyright laws.<br/>
  * Create time: 2019-03-08 11:06
  *
  * @author CN
  * @version 1.0
  */
object JobDimCount {
  
  def main(args: Array[String]): Unit = {
  
    val conf = new SparkConf().setMaster("local[*]").setAppName(JobDimCount.getClass.getSimpleName)
    val sc = new SparkContext(conf)
    
    val fileRDD = sc.textFile(DataConfig.DATA_SOURCE_ETL)

    val lineRDD = fileRDD.map(line => {
      val arr = line.split("\u0001")
      if(arr.length == 19) {
        val info = new JobInfo()
        info.setDate(arr(0))
        info.setTitle(arr(1))
        info.setPrimaryType(arr(2))
        info.setSecondType(arr(3))
        info.setIsGraduated(arr(4))
        info.setMinSalary(arr(5))
        info.setMaxSalary(arr(6))
        info.setMidSalary(arr(7))
        info.setAddress(arr(8))
        info.setExperience(arr(9))
        info.setEducation(arr(10))
        info.setFeatures(arr(11))
        info.setDescription(arr(12))
        info.setCompanyName(arr(13))
        info.setCompanyType(arr(14))
        info.setCompanyScala(arr(15))
        info.setCompanyTrade(arr(16))
        info.setCompanyDescription(arr(17))
        info.setUrl(arr(18))
        
        (info.getUrl, info)
      } else {
        null
      }
    }).filter(_ != null)                //
        .reduceByKey((item, _) => item) // 根据URL去重
        .map(_._2).cache()              // 映射 去掉Key
    
    
    // *** 一类、二类、时间、地区、工作经验、学历  分组 统计数量 ***
    allDimCount(lineRDD)
    
    
    // 一类、二类、
    
    
    
//    // *** 岗位描述分词  ***
//    // 1. 加载停用词
//    val stopWords = new mutable.HashSet[String]()
//    val isr = new InputStreamReader(getClass.getClassLoader
//        .getResourceAsStream("dicts/stopwords.txt"))
//    val br = new BufferedReader(isr)
//    var word: String = br.readLine()
//    while(word != null) {
//      stopWords.add(word)
//      word = br.readLine()
//    }
//
//    // 2. 广播停用词
//    val stopWordsBC = sc.broadcast(stopWords)
//
//    // 3. 开始分词，并过滤停用词
//    val jobWordsRDD = rdd.mapPartitions(it => {
//      val listMap = new mutable.ListBuffer[(String, mutable.ListBuffer[String])]
//      val jobClass = new JobInfoQianCheng51().getClass
//
//      // 加载自定义词典
//      val path = Paths.get(new File(getClass.getClassLoader
//          .getResource("dicts/jieba.dict").getPath).getAbsolutePath)
//      WordDictionary.getInstance().loadUserDict(path)
//
//      // 创建分词器
//      val segmenter = new JiebaSegmenter()
//
//      it.foreach(line => {
//        if(line != null && !"".equals(line)) {
//          val jobInfo = JSON.parseObject(line, jobClass)
//
//          val jobType = EtlUtil.getJobType(jobInfo.getTitle)
//
//          // 这里仅保留大数据
//          //          if(JobLabel.BIG_DATA.equals(jobType._2)) {
//          // 进行分词
//          val tokenList = segmenter.process(jobInfo.getDescription, SegMode.SEARCH).asScala
//
//          val list = new mutable.ListBuffer[String]()
//          // 过滤停用词
//          for(token <- tokenList) {
//            if(!token.word.equals(" ") &&
//                !token.word.equals("\u00a0") && // web端的空格
//                !token.word.equals("\t") &&
//                !stopwordsBC.value.contains(token.word)) {
//              list.append(token.word)
//            }
//          }
//
//          listMap.append((jobType._1+"_"+jobType._2+"_"+String.valueOf(jobType._3), list))
//          //          }
//        }
//      })
//
//      listMap.iterator
//    }).reduceByKey((x, y) => {
//      x.appendAll(y)
//      x
//      // (jobType, word_list)
//    }).cache()
//
//
//    // 保存分词结果
//    jobWordsRDD.foreach(item => {
//      val file = new File(DataConfig.WORDS_SEGMENTATION + "/" + item._1 + ".txt")
//      // 文件不存在则递归创建
//      if(!file.getParentFile.exists()) {
//        file.getParentFile.mkdirs()
//        file.createNewFile()
//      } else if(!file.exists()) {
//        file.createNewFile()
//      }
//
//      // 打开输出文件
//      val out = new PrintWriter(file, "utf-8")
//      val writeLine = (elem: String) => {
//        out.write(elem+"\n")
//      }
//      item._2.foreach(writeLine)
//      out.close()
//    })
//
//
//    // 按岗位分组统计词频，并逆序
//    jobWordsRDD.mapPartitions(it => {
//      // ((jobType, word), count)
//      val list = new mutable.ListBuffer[((String, String), Int)]
//      it.foreach(item => {
//        item._2.foreach(word => {
//          list.append(((item._1, word), 1))
//        })
//      })
//      list.iterator
//    }).reduceByKey(_ + _).mapPartitions(it => {
//      // (jobType, (word, count))
//      val list = new mutable.ListBuffer[(String, (String, Int))]
//      it.foreach(elem => {
//        list.append((elem._1._1, (elem._1._2, elem._2)))
//      })
//      list.iterator
//    }).groupByKey().map(item => {
//      // 根据词频逆序
//      (item._1, item._2.toList.sortBy(_._2).reverse)
//    }).foreach(item => {
//      val file = new File(DataConfig.WORDS_SEGMENTATION_WORD_COUNT + "/" + item._1 + ".txt")
//      if(!file.getParentFile.exists()) {
//        file.getParentFile.mkdirs()
//      }
//      if(!file.exists()) {
//        file.createNewFile()
//      }
//      val out = new PrintWriter(file, "utf-8")
//      val writeLine = (elem: (String, Int)) => {
//        out.write(elem._1+"\t"+ String.valueOf(elem._2) +"\n")
//      }
//      item._2.foreach(writeLine)
//      out.close()
//    })
  
    sc.stop()
  }
  
  /**
    *
    * @param rdd
    */
  def allDimCount(rdd: RDD[JobInfo]): Unit = {
    rdd.flatMap(info => {
      val expArr = info.getExperience.split(",")
      val infoBuffer = new ArrayBuffer[((String, String, String, String, String, String), Int)]()
    
      for(exp <- expArr) {
        infoBuffer.append(((info.getPrimaryType, info.getSecondType,
            info.getDate, info.getAddress, exp, info.getEducation), 1))
      }
    
      infoBuffer
    }).reduceByKey(_ + _).sortBy(_._2, ascending = false).take(100).foreach(println)
  }
  
}
