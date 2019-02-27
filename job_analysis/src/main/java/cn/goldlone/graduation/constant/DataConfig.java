package cn.goldlone.graduation.constant;

import java.io.IOException;
import java.util.Properties;

/**
 * Description: 数据源配置信息 <br/>
 * Copyright 2019 CN <br/>
 * This program is protected by copyright laws.<br/>
 * Create time: 2019-01-04 19:15
 *
 * @author CN
 * @version 1.0
 */
public class DataConfig {


  /**
   * 数据存放基本路径
   */
  public static String BASE_DIR = null;


  /**
   * 源数据存储位置
   */
  public static String DATA_SOURCE = null;


  /**
   * 清洗后的源数据
   */
  public static String DATA_SOURCE_ETL = null;


  // 加载配置信息
  static {

    Properties properties = new Properties();

    try {
      properties.load(DataConfig.class.getClassLoader().getResourceAsStream("config.properties"));

      BASE_DIR = properties.getProperty("base_dir", "../data");
      DATA_SOURCE = DataConfig.BASE_DIR + properties.getProperty("data_source", "qiancheng51");
      DATA_SOURCE_ETL = DataConfig.BASE_DIR + properties.getProperty("data_source_etl", "qiancheng51_etl");

    } catch (IOException e) {
      e.printStackTrace();
    }

  }


  /**
   * 分词结果目录
   */
  public static String WORDS_SEGMENTATION = BASE_DIR + "/word_segmentation";

  /**
   * 分词结果-词频统计目录
   */
  public static String WORDS_SEGMENTATION_WORD_COUNT = BASE_DIR + "/word_segmentation_wc";


}
