#开始

基于JDK1.8。用了java8中stream()新特性，所以最好是使用新版本的jdk

修改数据源配置

首先创建MySql数据库 （默认SSSP）

在dbconfig.properties中配好自己的数据源。否则连接失败会失败！

项目使用框架

本项目基于Spring + SpringMVC + Spring-Data-JPA + Spring-Data-JPA-Extra + maven 进行构建，

如果你还不熟悉Spring-Data-JPA建议先学习一下Spring-Data-JPA基本使用,

Spring-Data-JPA-Extra是对JPA扩展的模版(动态)SQL,弥补JPA复杂查询，动态查询而产生的酷似Mybatis动态SQL,

Spring-Data-JPA-Extra相关信息可移步至https://github.com/slyak/spring-data-jpa-extra了解

数据初始化

test包下面有4个Test，按顺序JUnit执行每一个*Test类中addTest方法

1======>MenuTest

2======>OperationTest

3======>RoleTest

4======>UserTest
