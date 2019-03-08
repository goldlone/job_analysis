package cn.goldlone.job_analysis.analysis.entity;

import java.io.Serializable;

/**
 * Description: 前程无忧岗位招聘信息 <br/>
 * Copyright 2019 CN <br/>
 * This program is protected by copyright laws.<br/>
 * Create time: 2019-01-01 22:07
 *
 * @author CN
 * @version 1.0
 */
public class JobInfoQianCheng51 implements Serializable {

  // 岗位名称
  private String title;

  // 薪资
  private String salary;

  // 关键词
  private String keywords;

  // 岗位特色
  private String features;

  // 岗位描述
  private String description;

  // 企业名称
  private String company_name;

  // 企业类型
  private String company_type;

  // 企业规模
  private String company_scala;

  // 企业领域
  private String company_trade;

  // 企业描述
  private String company_description;

  // 详细页URL
  private String url;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getSalary() {
    return salary;
  }

  public void setSalary(String salary) {
    this.salary = salary;
  }

  public String getKeywords() {
    return keywords;
  }

  public void setKeywords(String keywords) {
    this.keywords = keywords;
  }

  public String getFeatures() {
    return features;
  }

  public void setFeatures(String features) {
    this.features = features;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCompany_name() {
    return company_name;
  }

  public void setCompany_name(String company_name) {
    this.company_name = company_name;
  }

  public String getCompany_type() {
    return company_type;
  }

  public void setCompany_type(String company_type) {
    this.company_type = company_type;
  }

  public String getCompany_scala() {
    return company_scala;
  }

  public void setCompany_scala(String company_scala) {
    this.company_scala = company_scala;
  }

  public String getCompany_trade() {
    return company_trade;
  }

  public void setCompany_trade(String company_trade) {
    this.company_trade = company_trade;
  }

  public String getCompany_description() {
    return company_description;
  }

  public void setCompany_description(String company_description) {
    this.company_description = company_description;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @Override
  public String toString() {
    return "JobInfoQianCheng51{" +
            "title='" + title + '\'' +
            ", salary='" + salary + '\'' +
            ", keywords='" + keywords + '\'' +
            ", features='" + features + '\'' +
            ", description='" + description + '\'' +
            ", company_name='" + company_name + '\'' +
            ", company_type='" + company_type + '\'' +
            ", company_scala='" + company_scala + '\'' +
            ", company_trade='" + company_trade + '\'' +
            ", company_description='" + company_description + '\'' +
            ", url='" + url + '\'' +
            '}';
  }
}
