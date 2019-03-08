package cn.goldlone.job_analysis.analysis.etl

import java.io.{BufferedInputStream, BufferedReader, File, InputStreamReader}
import java.nio.file.{Path, Paths}

import cn.goldlone.job_analysis.analysis.entity.JobInfoQianCheng51
import com.alibaba.fastjson.JSON

import scala.collection.JavaConverters._
import com.huaban.analysis.jieba.{JiebaSegmenter, SegToken, WordDictionary}
import com.huaban.analysis.jieba.JiebaSegmenter.SegMode

import scala.collection.mutable
import scala.util.matching.Regex

/**
  * Description: xxx <br/>
  * Copyright 2019 CN <br/>
  * This program is protected by copyright laws.<br/>
  * Create time: 2019-01-02 15:49
  *
  * @author CN
  * @version 1.0
  */
object Test {
  
  def main(args: Array[String]): Unit = {
  
//    // 加载自定义词典
//    val path = Paths.get(new File(getClass.getClassLoader.getResource("dicts/jieba.dict").getPath).getAbsolutePath)
//    WordDictionary.getInstance().loadUserDict(path)
//
//    // 分词测试
//    val segmenter = new JiebaSegmenter()
//    val line = "1.    产品方面，参与大数据体系的设计、开发、维护，通过数据仓库、元数据、质量体系有效的管理和组织PG级别的数据，构建知识图谱 2.    数据处理方面，参与市场调研系统产生的大数据的处理、推理，通过对数据的理解，挖掘数据价值；   1.      所学专业是计算机、数学、统计等相关专业皆可; 2.      有较强的动手能力和学习能力。 3.      熟悉JAVA至少3年以上实际项目开发经验。熟悉Python者优先。 4.      并掌握spring、mybatis等开源J2EE框架。 5.      熟悉HTML5、CSS、javascript，jquery等前端开发技术。 6.      熟悉Oracle、mysql，熟悉SQL。 7.      熟悉UNIX或者LINUX操作; 8.      具备扎实的专业基础，良好的沟通能力和团队合作，主动积极、乐于面对挑战; 9.      有参与数据处理、分析、挖掘等相关项目优先 ; 10.   对Hadoop、Hive、Spark、Hbase等分布式平台有一定的理解和应用经验者优先;    "
//    val list: mutable.Buffer[SegToken] = segmenter.process(line, SegMode.SEARCH).asScala
//
//    // 加载停用词
//    val stopwords = new mutable.HashSet[String]()
//    val isr = new InputStreamReader(getClass.getClassLoader.getResourceAsStream("dicts/stopwords.txt"))
//    val br = new BufferedReader(isr)
//    var word: String = br.readLine()
//    while(word != null) {
//      stopwords.add(word)
//      word = br.readLine()
//    }
//
//    for(token <- list) {
//      if(!token.word.equals(" ") &&
//          !token.word.equals("\u00a0") &&
//          !token.word.equals("\t") &&
//          !stopwords.contains(token.word))
//        println(token.word)
//    }
  
//    val line = "{\"title\": \"大数据应用开发工程师\", \"salary\": \"2-4万/月\", \"keywords\": \"上海-浦东新区,3-4年经验,本科,招1人,11-19发布\", \"features\": \"五险一金,补充医疗保险,员工旅游,交通补贴,餐饮补贴,通讯补贴,绩效奖金,弹性工作,定期体检\", \"description\": \"岗位描述：  1.能够带领小组工程师进行实时和离线的海量数据应用系统开发； 2.负责大数据应用研发攻克技术难关和技术决策； 3.负责大数据采集、存储框架研究，在线或离线数据存储模型设计； 4.负责大数据平台新技术的开发使用和性能优化，测试 5.完成部门领导交代的其他工作。  职位要求:  1.计算机(统计、应用数学)相关专业本科及以上学历，3年以上开发经验； 2.精通hadoop相关各种开源项目，比如HDFS/Hive/Hbase等有实际应用； 3.熟悉Kafka/Spark/Druid/Kudu/Impala者优先； 4. 海量数据存储的安装部署、优化、二次开发经验，对大规模数据存储、传输、处理等有丰富的经验； 5.在数据挖掘、数据密集型处理、分布式计算、网格计算领域有深入理论基础； 6.具有较强的文档撰写能力，有很强的沟通能力、表达能力、团队合作精神，对工作有热情，能承受压力。 \", \"company_name\": \"珠海横琴极盛科技有限公司\", \"company_type\": \"上市公司\", \"company_scala\": \"50-150人\", \"company_trade\": \"金融/投资/证券,互联网/电子商务\", \"company_description\": \"极盛科技是国盛金控（002670）旗下的全资子公司。 充分利用集团母公司旗下的证券、基金、资管、投资等业务资源，通过与时俱进的、前沿的技术手段， 全面促进金控平台资金融通的合规性和在不确定环境下的风险控制能力，逐步打造并发展出个性化的产品和服务体系。  我们的金融科技是建立在对传统金融的专业理解基础上， 通过科技的文化、基因、体制的有机融合，依靠一支极具创新能力、战斗力的金融科技研发团队， 在金融专业通讯协同、合规监控、财富管理服务、多资产交易系统等方面开展深入的研发与应用， 并致力于结合大数据、云计算以及人工智能等技术手段挖掘真正有价值的金融行业垂直应用， 从而形成公司专业化的竞争优势。\", \"url\": \"https://jobs.51job.com/shanghai-pdxq/101371987.html\"}"
//    val jobInfo = JSON.parseObject(line, new JobInfoQianCheng51().getClass)
//    println(EtlUtil.parseKeywords(jobInfo.getKeywords))
  
  
  
  
  
//    val pattern = new Regex("https://jobs.51job.com.*")
//    println(pattern.findAllIn("https://jobs.51job.com/hangzhou-xhq/109535680.html").length)
  
    val a = "1000"
    val b = "2000"
    
    println(a.compareTo(b))
    
  }
  
  
  
}
