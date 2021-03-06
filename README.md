<h2 align="center">imcoder-base</h2>
<div align="center">
  <a href="https://github.com/vuejs/vue">
    <img src="https://img.shields.io/badge/java-1.8-blue.svg" alt="java">
  </a>
  <a href="https://github.com/ElemeFE/element">
    <img src="https://img.shields.io/badge/mybatis--plus-3.3.0-brightgreen.svg" alt="mybatis--plus">
  </a>
  <a href="https://www.echartsjs.com/zh/index.html">
    <img src="https://img.shields.io/badge/imcoder--base-1.0.9-orange" alt="imcoder-base">
  </a>
</div>

本项目基于mybatis-plus进行了二次封装，包含了基本的增删改查以及分页API接口。
封装了公共返回，使基础开发更加简单。本项目已上传至maven目前版本号是1.0.9。
关于mybatis-plus的用法请参考mybatis-plus官方文档https://mp.baomidou.com/guide/
<br>
## maven依赖
```xml
<dependency>
    <groupId>fun.imcoder.cloud</groupId>
    <artifactId>imcoder-base</artifactId>
    <version>1.0.9</version>
</dependency>
```

## 使用方式
### Model层
只需继承BaseModel即可，示例如下
```java
@Data
@TableName("user")
public class User extends BaseModel {
    private Integer id;
    private String username;
    private String email;
    private String password;
}
```
BaseModel里包含了createTime，modifyTime两个必有字段，所以你的表中必须有create_time和modify_time字段，
BaseModel还有一个排序字段order，这个是非必有字段，用来指定按照哪个字段来排序。

### Service层
只需继承BaseService即可拥有基础的增删改查能力，示例如下
```java
public interface UserService extends BaseService<User> {
}

@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {
}
```

### Mapper层
只需继承BaseMapper即可，示例如下
```java
public interface UserMapper extends BaseMapper<User> {
}
```

需要注意的是BaseMapper里自定义了一部分增删改查，这部分需要你自己去按照Mybatis那样在xml里编写Sql，
这部分是预留给你扩展使用。