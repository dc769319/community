## Charles社区
- 基于SpringBoot
- 集成github登录

## 资料
- [github授权登录](https://developer.github.com/apps/building-oauth-apps/authorizing-oauth-apps/)
- [datasource](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-sql.html)
- [拦截器](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-handlermapping-interceptor)
- [Mybatis Generator](https://mybatis.org/generator/)
- [mybatis-spring-boot-autoconfigure](http://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)
- [SpringBoot官网](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [SpringBoot错误处理](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-error-handling)

## 脚本
### flyway命令
```shell
mvn flyway:migrate 
```
### MBG执行
```shell
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
```
### 数据表
#### user
```sql
create table USER
(
	ID INTEGER AUTO_INCREMENT primary key NOT NULL,
	ACCOUNT_ID VARCHAR(100) not null,
	NAME VARCHAR(50) not null,
	TOKEN CHAR default '36',
	GMT_CREATE BIGINT,
	GMT_MODIFIED BIGINT
)
;

comment on table USER is '用户信息表'
;
```
#### question
```sql
CREATE TABLE question
(
    id int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    title varchar(50) NOT NULL,
    description text,
    tag varchar(100),
    gmt_create bigint,
    gmt_modified bigint,
    creator int,
    view_count int,
    like_count int,
    comment_count int
);
```

## 部署
```
mvn clean # 删除maven项目
mvn compile # 编译源码
mvn package # 打包
mvn compile package -Dmaven.test.skip=true

```