package cn.goldlone.job_analysis.analysis.entity;

import java.io.Serializable;

/**
 * Description: xxx <br/>
 * Copyright 2019 CN <br/>
 * This program is protected by copyright laws.<br/>
 * Create time: 2019-03-08 11:45
 *
 * @author CN
 * @version 1.0
 */
public class JobInfo implements Serializable {

  // 发布日期
  private String date;

  // 岗位名称
  private String title;

  // 岗位一级类别
  private String primaryType;

  // 岗位二级类别
  private String secondType;

  // 岗位是否是实习生
  private String isGraduated;

  // 薪资下限
  private String minSalary;

  // 薪资上限
  private String maxSalary;

  // 薪资中值
  private String midSalary;

  // 工作地点
  private String address;

  // 工作经验
  private String experience;

  // 学历
  private String education;

  // 岗位特色
  private String features;

  // 岗位描述
  private String description;

  // 企业名称
  private String companyName;

  // 企业类型
  private String companyType;

  // 企业规模
  private String companyScala;

  // 企业领域
  private String companyTrade;

  // 企业描述
  private String companyDescription;

  // 链接
  private String url;

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getPrimaryType() {
    return primaryType;
  }

  public void setPrimaryType(String primaryType) {
    this.primaryType = primaryType;
  }

  public String getSecondType() {
    return secondType;
  }

  public void setSecondType(String secondType) {
    this.secondType = secondType;
  }

  public String getIsGraduated() {
    return isGraduated;
  }

  public void setIsGraduated(String isGraduated) {
    this.isGraduated = isGraduated;
  }

  public String getMinSalary() {
    return minSalary;
  }

  public void setMinSalary(String minSalary) {
    this.minSalary = minSalary;
  }

  public String getMaxSalary() {
    return maxSalary;
  }

  public void setMaxSalary(String maxSalary) {
    this.maxSalary = maxSalary;
  }

  public String getMidSalary() {
    return midSalary;
  }

  public void setMidSalary(String midSalary) {
    this.midSalary = midSalary;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getExperience() {
    return experience;
  }

  public void setExperience(String experience) {
    this.experience = experience;
  }

  public String getEducation() {
    return education;
  }

  public void setEducation(String education) {
    this.education = education;
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

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String getCompanyType() {
    return companyType;
  }

  public void setCompanyType(String companyType) {
    this.companyType = companyType;
  }

  public String getCompanyScala() {
    return companyScala;
  }

  public void setCompanyScala(String companyScala) {
    this.companyScala = companyScala;
  }

  public String getCompanyTrade() {
    return companyTrade;
  }

  public void setCompanyTrade(String companyTrade) {
    this.companyTrade = companyTrade;
  }

  public String getCompanyDescription() {
    return companyDescription;
  }

  public void setCompanyDescription(String companyDescription) {
    this.companyDescription = companyDescription;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
