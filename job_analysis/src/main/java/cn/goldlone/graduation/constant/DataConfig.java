package cn.goldlone.graduation.constant;

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
  public static final String BASE_DIR = "E:/workspace/graduation_project/data";


  /**
   * 源数据存储位置
   */
  public static final String DATA_SOURCE = BASE_DIR + "/qiancheng51";


  /**
   * 清洗后的源数据
   */
  public static final String DATA_SOURCE_OK = BASE_DIR + "/qiancheng51_ok";


  /**
   * 分词结果目录
   */
  public static final String WORDS_SEGMENTATION = BASE_DIR + "/word_segmentation";

  /**
   * 分词结果-词频统计目录
   */
  public static final String WORDS_SEGMENTATION_WORD_COUNT = BASE_DIR + "/word_segmentation_wc";



}
