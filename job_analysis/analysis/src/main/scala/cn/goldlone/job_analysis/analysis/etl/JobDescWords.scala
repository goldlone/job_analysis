package cn.goldlone.job_analysis.analysis.etl

import java.io.{BufferedReader, File, InputStreamReader, PrintWriter}
import java.nio.file.Paths

import cn.goldlone.graduation.constant.DataConfig
import cn.goldlone.job_analysis.analysis.etl.utils.EtlUtil
import cn.goldlone.job_analysis.analysis.constant.DataConfig
import cn.goldlone.job_analysis.analysis.entity.JobInfoQianCheng51
import com.alibaba.fastjson.JSON
import com.huaban.analysis.jieba.JiebaSegmenter.SegMode
import com.huaban.analysis.jieba.{JiebaSegmenter, WordDictionary}
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.JavaConverters._
import scala.collection.mutable

/**
  * Description: 对岗位描述分词，
  *   1.不同岗位生成分词结果
  *   2.对分词结果进行统计并逆序 <br/>
  * Copyright 2019 CN <br/>
  * This program is protected by copyright laws.<br/>
  * Create time: 2019-01-04 19:13
  *
  * @author CN
  * @version 1.0
  */
object JobDescWords {
  
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setMaster("local[*]")
        .setAppName("tarnsform-51job")
        .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    val sc = new SparkContext(conf)

    val rdd = sc.textFile(DataConfig.DATA_SOURCE)

    // 加载停用词
    val stopwords = new mutable.HashSet[String]()
    val isr = new InputStreamReader(getClass.getClassLoader
        .getResourceAsStream("dicts/stopwords.txt"))
    val br = new BufferedReader(isr)
    var word: String = br.readLine()
    while(word != null) {
      stopwords.add(word)
      word = br.readLine()
    }

    // 广播停用词
    val stopwordsBC = sc.broadcast(stopwords)

    // 开始分词，并过滤停用词
    val jobWordsRDD = rdd.mapPartitions(it => {
      val listMap = new mutable.ListBuffer[(String, mutable.ListBuffer[String])]
      val jobClass = new JobInfoQianCheng51().getClass

      // 加载自定义词典
      val path = Paths.get(new File(getClass.getClassLoader
          .getResource("dicts/jieba.dict").getPath).getAbsolutePath)
      WordDictionary.getInstance().loadUserDict(path)

      // 创建分词器
      val segmenter = new JiebaSegmenter()

      it.foreach(line => {
        if(line != null && !"".equals(line)) {
          val jobInfo = JSON.parseObject(line, jobClass)

          val jobType = EtlUtil.getJobType(jobInfo.getTitle)

          // 这里仅保留大数据
//          if(JobLabel.BIG_DATA.equals(jobType._2)) {
            // 进行分词
            val tokenList = segmenter.process(jobInfo.getDescription, SegMode.SEARCH).asScala

            val list = new mutable.ListBuffer[String]()
            // 过滤停用词
            for(token <- tokenList) {
              if(!token.word.equals(" ") &&
                  !token.word.equals("\u00a0") && // web端的空格
                  !token.word.equals("\t") &&
                  !stopwordsBC.value.contains(token.word)) {
                list.append(token.word)
              }
            }

            listMap.append((jobType._1+"_"+jobType._2+"_"+String.valueOf(jobType._3), list))
//          }
        }
      })

      listMap.iterator
    }).reduceByKey((x, y) => {
      x.appendAll(y)
      x
      // (jobType, word_list)
    }).cache()
  
    
    // 保存分词结果
    jobWordsRDD.foreach(item => {
      val file = new File(DataConfig.WORDS_SEGMENTATION + "/" + item._1 + ".txt")
      // 文件不存在则递归创建
      if(!file.getParentFile.exists()) {
        file.getParentFile.mkdirs()
        file.createNewFile()
      } else if(!file.exists()) {
        file.createNewFile()
      }
      
      // 打开输出文件
      val out = new PrintWriter(file, "utf-8")
      val writeLine = (elem: String) => {
        out.write(elem+"\n")
      }
      item._2.foreach(writeLine)
      out.close()
    })
  
    
    // 按岗位分组统计词频，并逆序
    jobWordsRDD.mapPartitions(it => {
      // ((jobType, word), count)
      val list = new mutable.ListBuffer[((String, String), Int)]
      it.foreach(item => {
        item._2.foreach(word => {
          list.append(((item._1, word), 1))
        })
      })
      list.iterator
    }).reduceByKey(_ + _).mapPartitions(it => {
      // (jobType, (word, count))
      val list = new mutable.ListBuffer[(String, (String, Int))]
      it.foreach(elem => {
        list.append((elem._1._1, (elem._1._2, elem._2)))
      })
      list.iterator
    }).groupByKey().map(item => {
      // 根据词频逆序
      (item._1, item._2.toList.sortBy(_._2).reverse)
    }).foreach(item => {
      val file = new File(DataConfig.WORDS_SEGMENTATION_WORD_COUNT + "/" + item._1 + ".txt")
      if(!file.getParentFile.exists()) {
        file.getParentFile.mkdirs()
      }
      if(!file.exists()) {
        file.createNewFile()
      }
      val out = new PrintWriter(file, "utf-8")
      val writeLine = (elem: (String, Int)) => {
        out.write(elem._1+"\t"+ String.valueOf(elem._2) +"\n")
      }
      item._2.foreach(writeLine)
      out.close()
    })

    sc.stop()
  }
  
}
