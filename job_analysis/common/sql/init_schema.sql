-- 一类维度
drop table if exists dim_primary_type;
create table dim_primary_type (
  id int auto_increment not null,
  name varchar(30),
  primary key (id)
) default charset=utf8;

-- 二类维度
drop table if exists dim_second_type;
create table dim_second_type (
  id int auto_increment not null,
  name varchar(30),
  primary key (id)
) default charset=utf8;

-- 时间维度
drop table if exists dim_date;
create table dim_date (
  id int auto_increment not null,
  date varchar(30),
  primary key (id)
) default charset=utf8;

-- 学历维度
drop table if exists dim_education;
create table dim_education (
  id int auto_increment not null,
  name varchar(30),
  primary key (id)
) default charset=utf8;

-- 地区维度
drop table if exists dim_city;
create table dim_city (
  id int auto_increment not null,
  name varchar(30),
  primary key (id)
) default charset=utf8;


-- 多维度下工作需求量
drop table if exists info_job_count;
create table info_job_count (
  id int auto_increment not null,
  dim_primary_type_id int,
  dim_second_type_id int,
  dim_date_id int,
  dim_city_id int,
  dim_experience int,
  dim_education_id int,
  count int default 0,
  primary key (id)
) default charset=utf8;





-- 配合视图  存储结果

