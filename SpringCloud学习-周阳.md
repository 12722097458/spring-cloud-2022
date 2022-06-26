>  **SpringCloud + SpringCloud alibaba**
>
> https://www.bilibili.com/video/BV18E411x7eT?p=3&spm_id_from=pageDriver

SpringCloud ：分布式的微服务架构的

一站式解决方案，是多种为服务器架构落地技术的集合体，俗称微服务全家桶

![image-20220606232051604](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220606232051.png)

![image-20220607213434054](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220607213441.png)

# A、SpringCloud

## 一、SpringCloud入门Eureka

#### 1、SpringCloud和SpringBoot版本选择

```shell
https://github.com/spring-projects/spring-boot/releases/ 可以看到spring-boot的最新版本。
https://spring.io/projects/spring-boot#learn  可以看到官网SpringBoot的版本
# 官网推荐大概版本:  cloud  --> boot
https://spring.io/projects/spring-cloud#overview
https://spring.io/projects/spring-cloud#learn  通过点击	Reference Doc.，可以看到官方推荐的具体cloud-boot版本
https://docs.spring.io/spring-cloud/docs/Hoxton.SR12/reference/html/
Release Train Version: Hoxton.SR12
Supported Boot Version: 2.3.12.RELEASE

# 具体版本对应关系选择
https://start.spring.io/actuator/info
# 终极方案，在spring-cloud-alibaba里，推荐了boot-cloud-cloudalibaba的版本关系
https://github.com/alibaba/spring-cloud-alibaba/wiki/%E7%89%88%E6%9C%AC%E8%AF%B4%E6%98%8E
```



**小版本**

Spring Cloud 小版本分为:

SNAPSHOT： 快照版本，随时可能修改

M： MileStone，M1表示第1个里程碑版本，一般同时标注PRE，表示预览版版。

SR： Service Release，SR1表示第1个正式版本，一般同时标注GA：(GenerallyAvailable),表示稳定版本。



#### 2、各组件版本确定

| 工具          | 版本           |
| ------------- | -------------- |
| SpringCloud   | Hoxton.SR12    |
| SpringBoot    | 2.3.12.RELEASE |
| cloud alibaba | 2.2.7.RELEASE  |
| java          | java11         |
| maven         | 3.5.2          |
| mysql         | 8.0.22         |



#### 3、基本环境搭建

##### 1、 创建project-父工程搭建

###### 1.1 创建project

![image-20210219093916795](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220606224125.png)

![image-20210219095354279](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220606224125.png)

![image-20210224070754791](D:\我的文件\gitRepository\cloud-image\img\image-20210224070754791.png)

###### 1.2 字符编码

![image-20210219101052852](D:\我的文件\gitRepository\cloud-image\img\image-20210219101052852.png)

###### 1.3 注解生效激活

![image-20210219101146786](D:\我的文件\gitRepository\cloud-image\img\image-20210219101146786.png)

###### 1.4 配置父项目的pom.xml文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>com.ityj.springcloud</groupId>
	<artifactId>spring-cloud-2022</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>pom</packaging>
	<description>Parent project for Spring Cloud learning!</description>

	<!-- 统一管理jar包版本 -->
	<properties>
		<java.version>11</java.version>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<maven.compiler.source>11</maven.compiler.source>
		<maven.compiler.target>11</maven.compiler.target>
		<spring-boot.version>2.3.12.RELEASE</spring-boot.version>
		<spring-cloud.version>Hoxton.SR12</spring-cloud.version>
		<spring-cloud-alibaba.version>2.2.7.RELEASE</spring-cloud-alibaba.version>
		<mysql.version>8.0.22</mysql.version>
		<druid.version>1.2.9</druid.version>
		<mybatis-plus.version>3.5.1</mybatis-plus.version>
	</properties>

	<!-- 子模块继承之后，提供作用：锁定版本+子module不用写groupId和version
	  	  只提供声明，对应的依赖没有引入
	  -->
	<dependencyManagement>
		<dependencies>
			<!--spring boot 2.3.12.RELEASE-->
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-dependencies</artifactId>
				<version>${spring-boot.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
			<!--spring cloud Hoxton.SR12-->
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>${spring-cloud.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
			<!--spring cloud alibaba 2.2.7.RELEASE-->
			<dependency>
				<groupId>com.alibaba.cloud</groupId>
				<artifactId>spring-cloud-alibaba-dependencies</artifactId>
				<version>${spring-cloud-alibaba.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>

			<dependency>
				<groupId>mysql</groupId>
				<artifactId>mysql-connector-java</artifactId>
				<version>${mysql.version}</version>
			</dependency>

			<dependency>
				<groupId>com.baomidou</groupId>
				<artifactId>mybatis-plus-boot-starter</artifactId>
				<version>${mybatis-plus.version}</version>
			</dependency>

			<dependency>
				<groupId>com.alibaba</groupId>
				<artifactId>druid-spring-boot-starter</artifactId>
				<version>${druid.version}</version>
			</dependency>

		</dependencies>
	</dependencyManagement>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<fork>true</fork>
					<addResources>true</addResources>
				</configuration>
			</plugin>
		</plugins>
	</build>

</project>
```



###### 1.5 dependencies VS dependencyManagement

* (1) dependencyManagement用于父类管理的，通常会在一个项目的最顶层父pom文件中出现。
* (2) pom中的dependencyManagement元素能让子项目中引入依赖而无需显式地列出版本号。maven会沿着父子层级关系向上走，直到找到一个拥有dependencyManagement元素的项目，然后就会使用这个dependencyManagement指定的版本号。
* (3) 子项目需要的依赖，需要显式地声明，无需添加版本号。（子项目指定版本号就会优先使用自己设置的）

##### 2、创建payment8001子模块（生产者）

###### 1.1 建module

cloud-provider-payment8001生产者

`https://github.com/12722097458/spring-cloud-2022/commit/cca9413143c31cdaf850f44835dd42fa2a1aa1b1`

![image-20220607225049860](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220607225049.png)



###### 1.2 改pom

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>spring-cloud-2022</artifactId>
        <groupId>com.ityj.springcloud</groupId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-provider-payment8081</artifactId>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>

        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
        </dependency>

        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>

        <dependency>
            <groupId>com.github.xiaoymin</groupId>
            <artifactId>knife4j-spring-boot-starter</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
    </dependencies>

</project>
```

###### 1.3 写配置文件yml

> 如果pom引入后，yml配置文件没有变成spring的树叶形状，需要考虑对应的依赖是否正确引入了。鼠标左键能否查看dependency的详细信息

```yml
server:
  port: 8001

spring:
  application:
    name: cloud-payment-service
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://192.168.137.110:3306/db_cloud?useSSL=true&useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai
    username: root
    password: root

knife4j:
  enable: true    # http://localhost:8001/doc.html
  setting:
    language: en-US
```

###### 1.4 编写启动类

```java
@SpringBootApplication
@MapperScan("com.ityj.springcloud.mapper")
public class Payment8001Starter {
    public static void main(String[] args) {
        SpringApplication.run(Payment8081Starter.class, args);
    }
}

```

###### 1.5 编写业务代码

（1）数据表创建

```sql
 CREATE TABLE payment (
 id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
 serial VARCHAR(200)
) ENGINE=INNODB AUTO_INCREMENT=1 
```

（2）实体类entity编写

```java
@Data
@TableName("payment")
public class PaymentPO implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "serial")
    private String serial;
}

@Data
public class PaymentDTO {
    @NotEmpty(message = "The value of serial can not be empty!")
    private String serial;
}
```

编写了返回给前端的统一接口CommonResult

```java
@Data
@AllArgsConstructor
public class CommonResult<T> implements Serializable {
    private static final Integer SUCCESS_CODE = 0;
    private static final Integer FAIL_CODE = -1;

    private Integer code;
    private String msg;
    private transient T data;

    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(SUCCESS_CODE, CommonConstant.SUCCESS_MSG, data);
    }

    public static CommonResult<String> success() {
        return new CommonResult<>(SUCCESS_CODE, CommonConstant.SUCCESS_MSG, null);
    }

    public static <T> CommonResult<T> fail(String message) {
        return new CommonResult<>(FAIL_CODE, message, null);
    }
}
```

Knife4j文档管理配置

```java
@EnableSwagger2WebMvc
@Configuration
public class Knife4jConfig {

    private final OpenApiExtensionResolver openApiExtensionResolver;

    @Autowired
    public Knife4jConfig(OpenApiExtensionResolver openApiExtensionResolver) {
        this.openApiExtensionResolver = openApiExtensionResolver;
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ityj.springcloud.controller"))
                .paths(PathSelectors.any())
                .build()
                .extensions(openApiExtensionResolver.buildExtensions(""));
    }
 
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Cloud Learning")
                .description("Restful API")
                .termsOfServiceUrl("www.ityj.com")
                .version("version 1.0")
                .build();
    }
}
```

```yml
knife4j:
  enable: true    # http://localhost:8001/doc.html
  setting:
    language: en-US
```

（3）通过mybatis-plus实现mapper创建

```java
public interface PaymentMapper extends BaseMapper<PaymentPO> {
}
```



（4）service业务层编写

```java
public interface PaymentService extends IService<PaymentPO> {
    String save(PaymentDTO paymentDTO);
}


@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, PaymentPO> implements PaymentService {

    @Transactional
    @Override
    public String save(PaymentDTO paymentDTO) {
        PaymentPO paymentPO = new PaymentPO();
        BeanUtils.copyProperties(paymentDTO, paymentPO);
        int insert = baseMapper.insert(paymentPO);
        return insert > 0 ? null : "Save error!";
    }
}
```

（5）controller编写

```java
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/get/{id}")
    public CommonResult<PaymentPO> getById(@PathVariable("id") Long id) {
        return CommonResult.success(paymentService.getById(id));
    }

    @PostMapping("/save")
    public CommonResult<String> save(@RequestBody @Valid PaymentDTO paymentDTO) {
        String message = paymentService.save(paymentDTO);
        return StringUtils.hasText(message) ? CommonResult.fail(message) : CommonResult.success();
    }

}
```

（6）测试即可

`http://localhost:8001/doc.html`

##### 3、创建order消费者模块

创建cloud-consumer-order80消费者

同样是以下步骤

###### 1.1 建module

![image-20210219172645910](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220606224238.png)

###### 1.2 改pom

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>spring-cloud-2022</artifactId>
        <groupId>com.ityj.springcloud</groupId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-consumer-order80</artifactId>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <!--热部署-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>

        <dependency>
            <groupId>com.github.xiaoymin</groupId>
            <artifactId>knife4j-spring-boot-starter</artifactId>
        </dependency>

    </dependencies>

</project>
```

###### 1.3 改配置文件yml

服务端设置端口号为80

```yml
server:
  port: 80

spring:
  application:
    name: cloud-order-service

knife4j:
  enable: true    # http://localhost:80/doc.html
  setting:
    language: en-US
```

###### 1.4 编写启动类

```java
@SpringBootApplication
public class ConsumerOrder80Starter {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrder80Starter.class, args);
    }
}
```

###### 1.5 编写业务代码

这里是消费者，需要调用生产者provider，因此用到RestTemplate技术，类似于httpClient连接客户端的技术。

> RestTemplate 是从 Spring3.0 开始支持的一个 HTTP 请求工具，它提供了常见的REST请求方案的模版，例如 GET 请求、POST 请求、PUT 请求、DELETE 请求以及一些通用的请求执行方法 exchange 以及 execute。

使用步骤：

（1）编写配置类获取RestTemplate

```java
@Configuration
public class ApplicationContextConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
```

（2）和controller控制器共同实现服务间的调用

```java
@RestController
@Slf4j
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String PAYMENT_URL = "http://localhost:8001/";

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<PaymentPO> getById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "payment/get/" + id, CommonResult.class);
    }

    @GetMapping("/consumer/payment/save")
    public CommonResult<String> save(PaymentDTO paymentDTO) {
        log.info("Save: {}", paymentDTO);
        return restTemplate.postForObject(PAYMENT_URL + "payment/save", paymentDTO, CommonResult.class);
    }
}


//实体类CommonResult和Payment和payment8001一模一样：后续优化
```

##### 4、抽取公共模块，减少重复代码

[git提交记录](https://github.com/12722097458/spring-cloud-2022/commit/99db2b7a884b49505e6b67bbfafe56ce38db98a3)

创建模块cloud-api-commons

###### 1.1 建module

###### 1.2 改pom

###### 1.3 抽取共性代码

（1）相同的CommonResult和Payment实体类在payment和order两个模块中都使用到了，可以抽取出来，两个模块再引入commons

###### 1.4 order和payment模块引入commons



#### 4、Eureka单机服务注册中心

> Eureka是spring cloud中的一个负责服务注册与发现的组件；
>
> 一个Eureka中分为eureka server和eureka client。其中eureka server是作为服务的注册与发现中心。eureka client既可以作为服务的生产者，又可以作为服务的消费者。

<img src="https://i.loli.net/2021/02/23/cJQSgPer5Hz2hvG.png" alt="image-20210220071348813" style="float:left;" />

#####  1、单机Eureka服务端

**建Eureka服务端cloud-eureka-server7001**[git提交记录](https://github.com/12722097458/spring-cloud-2022/commit/48fbcf85611c759fcb84732f85b0bf67fc641ed7)

IDEA生成eurekaServer端服务注册中心类似物业公司

###### 1.1 建module

![image-20210220073323569](https://i.loli.net/2021/02/23/8vXtz1sB6oel5Vr.png)

###### 1.2 改pom

引入Eureka服务端的依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>spring-cloud-2022</artifactId>
        <groupId>com.ityj.springcloud</groupId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-eureka-server7001</artifactId>

    <dependencies>
        <dependency>
            <groupId>com.ityj.springcloud</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.parent.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        </dependency>
    </dependencies>

</project>
```

###### 1.3 改yml

```yml
server:
  port: 7001

eureka:
  instance:
    hostname: localhost  #eureka服务端的实例名字
  client:
    register-with-eureka: false    #表识不向注册中心注册自己
    fetch-registry: false   #表示自己就是注册中心，职责是维护服务实例，并不需要去检索服务
    service-url:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/    #设置与eureka server交互的地址查询服务和注册服务都需要依赖这个地址

```

###### 1.4 启动类

```java
@SpringBootApplication
@EnableEurekaServer
public class EurekaServer7001Starter {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer7001Starter.class, args);
    }
}

```

1.5 启动测试

[http://localhost:7001/](http://localhost:7001/)

![image-20210220074914430](D:\我的文件\gitRepository\cloud-image\img\image-20210220074914430.png)



##### 2、服务提供方8081入驻eurekaServer

EurekaClient端cloud-provider-payment8081将注册进EurekaServer成为服务提供者provider，类似尚硅谷学校对外提供授课服务

###### 1.1 改pom

添加eurekaClient的依赖

```xml
<!--eureka客户端-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

###### 1.2 改启动类

标注此项目是eureka客户端

```java
@EnableEurekaClient   // 表明是eureka客户端
```

###### 1.3 改yml

标注是否注册进eureka，并指明eureka服务端地址

```yml
eureka:
  client:
    register-with-eureka: true
    fetchRegistry: true
    service-url:
      defaultZone: http://localhost:7001/eureka           # eureka服务端的地址
```

###### 1.4 测试

启动8081，并访问http://localhost:7001/

![image-20210220080936972](D:\我的文件\gitRepository\cloud-image\img\image-20210220080936972.png)



##### 3、服务消费者订单模块80入驻eurekaServer

同理操作即可。及得yml加上spring.application.name=cloud-order-service

修改后，启动80服务，访问http://localhost:7001/

![image-20210220081853525](D:\我的文件\gitRepository\cloud-image\img\image-20210220081853525.png)



#### 5、Eureka集群服务注册中心

如果是单机版的Eureka服务，容错性差。违背了微服务RPC远程服务调用的核心：高可用。

需要搭建Eureka注册中心集群，实现负载均衡+故障容错

![image-20220609233637906](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220609233645.png)

##### 1、EurekaServer集群环境构建

###### 1.1  新建7002server和7002server

参考7001server新建cloud-eureka-server7002/cloud-eureka-server7002：新建后再修改相关的配置

###### 1.2 修改Windows映射配置

* 找到C:\Windows\System32\drivers\etc路径下的hosts文件
* 修改映射配置添加进hosts文件
  * 127.0.0.1  eureka7001.com
  * 127.0.0.1  eureka7002.com
  * 127.0.0.1  eureka7003.com

```shell
# Copyright (c) 1993-2009 Microsoft Corp.
#
# This is a sample HOSTS file used by Microsoft TCP/IP for Windows.
#
# This file contains the mappings of IP addresses to host names. Each
# entry should be kept on an individual line. The IP address should
# be placed in the first column followed by the corresponding host name.
# The IP address and the host name should be separated by at least one
# space.
#
# Additionally, comments (such as these) may be inserted on individual
# lines or following the machine name denoted by a '#' symbol.
#
# For example:
#
#      102.54.94.97     rhino.acme.com          # source server
#       38.25.63.10     x.acme.com              # x client host

# localhost name resolution is handled within DNS itself.
#	127.0.0.1       localhost
#	::1             localhost
127.0.0.1  eureka7001.com
127.0.0.1  eureka7002.com
127.0.0.1  eureka7003.com
```

1.3 修改EurekaServer 7001/7002/7003的配置文件

7001:

```shell
server:
  port: 7001

eureka:
  instance:
    hostname: eureka7001.com  #eureka服务端的实例名字
  client:
    register-with-eureka: false    #表识不向注册中心注册自己
    fetch-registry: false   #表示自己就是注册中心，职责是维护服务实例，并不需要去检索服务
    service-url:
      defaultZone: http://eureka7002.com:7002/eureka/,http://eureka7003.com:7003/eureka/    #设置与eureka server交互的地址查询服务和注册服务都需要依赖这个地址
```

7002:

```yml
server:
  port: 7002

eureka:
  instance:
    hostname: eureka7002.com  #eureka服务端的实例名字
  client:
    register-with-eureka: false    #表识不向注册中心注册自己
    fetch-registry: false   #表示自己就是注册中心，职责是维护服务实例，并不需要去检索服务
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka/,http://eureka7003.com:7003/eureka/    #设置与eureka server交互的地址查询服务和注册服务都需要依赖这个地址；互相守望，相互注册。多个用逗号隔开
```

7003:

```yml
server:
  port: 7003

eureka:
  instance:
    hostname: eureka7003.com  #eureka服务端的实例名字
  client:
    register-with-eureka: false    #表识不向注册中心注册自己
    fetch-registry: false   #表示自己就是注册中心，职责是维护服务实例，并不需要去检索服务
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka/,http://eureka7002.com:7002/eureka/    #设置与eureka server交互的地址查询服务和注册服务都需要依赖这个地址；互相守望，相互注册。多个用逗号隔开
```

###### 1.3 测试

因为在C:\Windows\System32\drivers\etc路径下的hosts文件修改了localhost的配置

所以此时localhost可以映射为  -- 》  eureka7001.com  、 eureka7002.com 和 eureka7003.com

此时启动7001、7002和7003服务

访问以下链接都可以：（7001、7002和7003服务互相注册）

* http://localhost:7001/
* http://127.0.0.1:7001/
* http://eureka7001.com:7001/

![image-20220610000904571](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220610000904.png)



##### 2、将客户端发布到EurekaServer集群上

将支付服务8081微服务和订单服务80发布到上面2台Eureka集群配置中

###### 1.1 修改yml配置

只需要修改对应的配置文件即可

```yml
eureka:
  client:
    register-with-eureka: false    #true  标识不向注册中心注册自己   false 不注册
    fetchRegistry: true
    service-url:
    #defaultZone: http://localhost:7001/eureka           # eureka服务端的地址 ：单机版
    defaultZone: http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka,http://eureka7003.com:7003/eureka  #集群版
```

###### 1.2 测试

启动项目，访问http://eureka7001.com:7001/ ， http://eureka7002.com:7002/ 和 http://eureka7002.com:7002/

![image-20220610003000951](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220610003001.png)

发现相互注册成功



访问http://eureka7001.com:80/doc.html对应的链接并进行调试，看结果是否成功！

对于http请求，80端口默认可以不写。https默认：443

![image-20210220101539732](D:\我的文件\gitRepository\cloud-image\img\image-20210220101539732.png)



##### 3、支付服务提供者8081和8082集群环境构建

服务提供者8082和8081保持一致，消费者访问时通过交替访问8081和8082实现负载均衡，增加稳定性。

###### 1.1 模仿8081，创建8082的module

###### 1.2 修改8082yml端口为8082

###### 1.3 修改8082和8082的controller返回结果：带上端口号，方便知道调用的服务

###### 1.4 修改cloud-consumer-order80中的ApplicationContextConfig.getRestTemplate()

* 添加注解@LoadBalanced  开启负载均衡：轮询

* 并修改restTemplate的url为`PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE/"; `

###### 1.5 测试

启动服务，访问http://localhost/consumer/payment/getPayment/11

发现访问的服务是8081和8082交替执行，实现了负载均衡。



##### 4、actuator微服务信息完善

```yml
eureka:
  instance:
    instance-id: payment8081      # actuator微服务信息完善
    prefer-ip-address: true       #访问路径可以显示ip

```

可以自定义显示的服务器信息

![image-20210220111433326](D:\我的文件\gitRepository\cloud-image\img\image-20210220111433326.png)



##### 5、服务发现DiscoveryClient

```java
import org.springframework.cloud.client.discovery.DiscoveryClient;

@Autowired
private DiscoveryClient discoveryClient;

@RequestMapping(path = "/discoveryClient", method = RequestMethod.GET)
public Object discoveryClient() {
    log.info("进入 discoveryClient()。。。");

    List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
    for (ServiceInstance instance : instances) {
        log.info(instance.getInstanceId() + "-" + instance.getScheme() + "-" + instance.getHost() + ":"
                 + instance.getPort() + "-" + instance.getUri() + "-" + instance.getMetadata());
    }
    List<String> services = discoveryClient.getServices();
    log.info("services：{}", services.toString());
    return discoveryClient;

}
```

启动类添加注解：

```java
@SpringBootApplication
@EnableEurekaClient   // 表明是eureka客户端
@EnableDiscoveryClient
public class Payment8081Starter {
    public static void main(String[] args) {
        SpringApplication.run(Payment8081Starter.class, args);
    }
}

```



##### 6、Eureka的自我保护机制

Eureka默认的配置eureka.server.enable-self-preservation=true，开启了自我保护机制。

![image-20220611172429514](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220611172436.png)

> Eureka的保护模式主要是用于一组客户端和Eureka Server之间存在网络分区场景下的保护。一旦进入保护模式，Eureka Server将会尝试保护其服务注册表中的信息，不再删除服务注册表中的数据，也就是不会销毁任何微服务。（payment8001即使宕机，也会在Eureka界面显示。）

==一句话：某时刻某一个微服务不可用了，Eureka不会立刻清理，依旧会对该微服务的信息进行保存==

==属于CAP里面的AP分支==



###### （1）禁用自我保护机制（一般不用）

1.1 注册中心eureakeServer端7001 

> 修改eureka.server.enable-self-preservation=false
>
> Eureka里的一个定时任务，每隔60秒清除无效instance
>
> eureka.server.eviction-interval-timer-in-ms=60000

1.2 eureake Client端8001

默认的心跳时间如下：

> eureka.instance.lease-renewal-interval-in-seconds=30
>
> eureka.instance.lease-expiration-duration-in-seconds: 90

也就是每隔30秒会向Eureka Server发送一个健康信号。Eureka服务器在收到上一次健康信号超过90秒会将其剔除。需要client-server配合使用。

###### （2）测试

修改后页面如下：

![image-20220611173912378](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220611173912.png)

![image-20220611174152884](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220611174152.png)

![image-20220611174207315](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220611174207.png)



## 二、服务注册进zookeeper（单机版）

### 1、在linux上安装zookeeper

（1）下载

`http://archive.apache.org/dist/zookeeper/`下载zookeeper-3.4.9.tar.gz

（2）解压安装

下载完成后进入目录`/app/tools/cloud`通过rz命令，将下载好的zookeeper-3.4.9.tar.gz进行解压

命令为：`tar -zxvf zookeeper-3.4.9.tar.gz `

（3）安装后`cd zookeeper-3.4.9`进入目录，通过`mkdir zkData`创建文件夹，通过配置用于存储持久化的数据。

（4）简单修改配置文件

```shell
cd /app/tools/cloud/zookeeper-3.4.9/conf
# 复制文件
cp zoo_sample.cfg zoo.cfg
vi zoo.cfg
#将 dataDir修改为  --》 dataDir=/app/tools/cloud/zookeeper-3.4.9/zkData

cd /app/tools/cloud/zookeeper-3.4.9/bin
# 可以启动或关闭zk服务器，以及启动zk客户端。
sh zkServer.sh stop      # 关闭zk服务器
sh zkServer.sh start     # 启动zk服务器
sh zkServer.sh status    # 查看状态
sh zkCli.sh              # 启动zk客户端

#zk客户端基本命令
ls /
ls /service
get /service/zookeeper
```

对于集群模式：

```shell
# 201、202、203三台服务器都安装zookeeper
# 1、在zkData目录下新建一个myid的文件，并指定唯一ID--》1 2 3
cd /app/tools/cloud/zookeeper-3.4.9/zkData
touch myid
vi myid   --> 分别指定 1 2 3 

# 2、再次配置zoo.cfg文件
vi /app/tools/cloud/zookeeper-3.4.9/conf/zoo.cfg
集群模式：在最后一行添加以下配置
#######################cluster##########################
server.1=192.168.118.201:2888:3888
server.2=192.168.118.202:2888:3888
server.3=192.168.118.203:2888:3888

server.1/2/3表示myid设置的值的大小

# 3. 分别启动201、202、203的三台zookeeper
cd /app/tools/cloud/zookeeper-3.4.9/bin
sh zkServer.sh start



成功启动后sh zkServer.sh status发现202是leader，201和203都是follower（记着关闭防火墙）
```

（5）zookeeper基本命令操作

```shell
help
ls /
ls2 /    # 'ls2' has been deprecated. Please use 'ls [-s] path' instead.
ls -s /
# 创建两个节点，创建节点时必须同时写入数据
create /sanguo "songjiang"             # 持久存在
get /sanguo   # 取数

create -e /sanguo/wuguo "zhouyu"       # 临时存在，客户端退出重新进入后就消失了 

# 监听
get /sanguo watch  # 如果此时其他客户端修改了/sanguo节点的数值，就可以被发现（只可以监听一次）
另一服务器set /sanguo "shizitou"，
此时可以监控到WatchedEvent state:SyncConnected type:NodeDataChanged path:/sanguo

stat /   # 查看节点的详细信息
```

（6）zookeeper面试题：

**请简述 ZooKeeper 的选举机制？**

1）半数机制：集群中半数以上机器存活，集群可用。所以 Zookeeper 适合安装奇数台 服务器。 

2）Zookeeper 虽然在配置文件中并没有指定 Master 和 Slave。但是，Zookeeper 工作时， 是有一个节点为 Leader，其他则为 Follower，Leader 是通过内部的选举机制临时产生的。

**ZooKeeper 的监听原理是什么？**

![image-20210317162016666](D:\我的文件\gitRepository\cloud-image\img\image-20210317162016666.png)

### 2、创建一个生产者8084和一个消费者80注册入zookeeper

#### 1. 创建一个服务提供者cloud-provider-payment8084

##### （1）建module

##### （2）改pom

这里需要注意的是 zookeeper的依赖版本问题。zookeeper-3.5.3-beta.jar:3，如果linux安装的zookeeper版本为3.4.9，和springboot默认版本不一致，会导致启动报错：解决方案1安装3.5.3-beta的程序。2是排除3.5.3-beta依赖，导入3.4.9的zookeeper

这里用的是第一种，完全模仿8001创建出8004. pom删除eureka引入zookeeper

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-zookeeper-discovery</artifactId>
</dependency>
```

##### （3）写yml

和8001的区别如下：端口以及注册中心。删除eureka的配置，添加zookeeper的配置

```yml
server:
  port: 8004

  cloud:
    zookeeper:
      connect-string: 192.168.137.110:2181
```

##### （4）启动类

```java
@SpringBootApplication
@MapperScan("com.ityj.springcloud.mapper")
@EnableDiscoveryClient
public class Payment8004Starter {
    public static void main(String[] args) {
        SpringApplication.run(Payment8004Starter.class, args);
    }
}
```

##### （5）编写业务代码

和8001保持一致，改了一下路径

```java
@Slf4j
@RestController
@RequestMapping("/zk/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/get/{id}")
    public CommonResult<PaymentDTO> getById(@PathVariable("id") Long id) {
        PaymentDTO paymentDTO = paymentService.getPaymentById(id);
        return CommonResult.success(paymentDTO, "ServerPort:" + serverPort);
    }

    @PostMapping("/save")
    public CommonResult<String> save(@RequestBody @Valid PaymentDTO paymentDTO) {
        String message = paymentService.save(paymentDTO);
        return StringUtils.hasText(message) ? CommonResult.fail(message) : CommonResult.success("ServerPort:" + serverPort);
    }
}
```

##### （6）测试

启动Payment8084ZkStarter服务，在linux上查看zookeeper的服务信息

![image-20220611225000762](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220611225000.png)

##### （7）总结

当8004宕机时，查看zookeeper注册中心的情况，再次重启观察对应的实例名称。

![image-20220611225405184](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220611225405.png)

==zookeeper的服务节点是临时节点（CP）==



#### 2. 创建一个消费者cloud-consumerzk-order80

##### （1）建module

##### （2）改pom，模仿消费者80，把注册中心dependency改成zk

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-zookeeper-discovery</artifactId>
</dependency>
```

##### （3）改yml

```yml
server:
  port: 80

spring:
  application:
    name: cloud-order-service

  cloud:
    zookeeper:
      connect-string: 192.168.137.110:2181
```

##### （4）启动类

```java
@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerOrderZK80Starter {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrderZK80Starter.class, args);
    }
}
```

##### （5）业务代码

消费者消费服务提供者，通过RestTemplate进行信息交互，所以先要引入RestTemplate

和另一个80保持一致，改了一下路径

```java
@RestController
@Slf4j
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String PAYMENT_URL = "http://cloud-payment-service/";   // zookeeper注意大小写

    @GetMapping("/consumer/zk/payment/get/{id}")
    public CommonResult<PaymentDTO> getById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "zk/payment/get/" + id, CommonResult.class);
    }

    @GetMapping("/consumer/zk/payment/save")
    public CommonResult<String> save(PaymentDTO paymentDTO) {
        log.info("Save: {}", paymentDTO);
        return restTemplate.postForObject(PAYMENT_URL + "zk/payment/save", paymentDTO, CommonResult.class);
    }
}
```

##### （6）测试

启动80，看到zookeeper已经注册了80服务

![image-20220611231209323](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220611231209.png)

再访问业务代码http://localhost/consumer/zk/payment/get/1

![image-20220611232447627](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220611232447.png)



## 三、服务注册与发现进consul

https://www.consul.io/intro/index.html  官网地址

https://www.springcloud.cc/spring-cloud-consul.html   使用教程

### 1、下载并安装consul

1.1 在官网下载consul_1.9.3_linux_amd64.zip

1.2 下载完成后上传到/app/tools/cloud，并通过`unzip consul_1.9.3_linux_amd64.zip`进行解压。

1.3 解压完成后出现一个consul的文件，执行`./consul`看到如下界面说明安装成功

![image-20210221103008302](D:\我的文件\gitRepository\cloud-image\img\image-20210221103008302.png)

1.4 安装成功后执行以下命令进行启动

```shell
./consul agent -dev -ui -node=consul-dev -client=192.168.118.128
```

![image-20210221103057842](https://i.loli.net/2021/02/23/3VLah8cKJP94pvG.png)

启动成功后再浏览器上访问：http://192.168.118.128:8500

![image-20210221103133975](D:\我的文件\gitRepository\cloud-image\img\image-20210221103133975.png)

出现页面表示安装成功，如果页面打不开，记着关闭防火墙重试。



**使用docker安装consul(使用中)**

```shell
docker pull consul:latest

docker run -d --name consul-dev -p 8500:8500 consul:latest
```

![image-20220612101037615](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220612101044.png)



### 2、创建服务提供者，并入驻consul

#### 1.1 建module

新建Module支付服务provider8006

#### 1.2 改pom

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>spring-cloud-2022</artifactId>
        <groupId>com.ityj.springcloud</groupId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-providerconsul-payment8006</artifactId>

    <dependencies>
        <dependency>
            <groupId>com.ityj.springcloud</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.parent.version}</version>
        </dependency>

        <!--当前模块测试consul作为服务注册中心-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-consul-discovery</artifactId>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>

        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
        </dependency>

        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>

    </dependencies>

</project>
```

#### 1.3 改yml

```yml
server:
  port: 8006

spring:
  application:
    name: cloud-consul-payment-service
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://192.168.137.110:3306/db_cloud?useSSL=true&useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai
    username: root
    password: root

  cloud:
    consul:
      host: 192.168.137.110
      port: 8500
      discovery:
        service-name: ${spring.application.name}
        prefer-ip-address: true
        ip-address: 192.168.1.8
```

#### 1.4 启动类

```java
@SpringBootApplication
@MapperScan("com.ityj.springcloud.mapper")
@EnableDiscoveryClient
public class Payment8006Starter {
    public static void main(String[] args) {
        SpringApplication.run(Payment8006Starter.class, args);
    }
}
```

#### 1.5 业务编写

```java
@Slf4j
@RestController
@RequestMapping("/consul/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/get/{id}")
    public CommonResult<PaymentDTO> getById(@PathVariable("id") Long id) {
        PaymentDTO paymentDTO = paymentService.getPaymentById(id);
        return CommonResult.success(paymentDTO, "ServerPort:" + serverPort);
    }

    @PostMapping("/save")
    public CommonResult<String> save(@RequestBody @Valid PaymentDTO paymentDTO) {
        String message = paymentService.save(paymentDTO);
        return StringUtils.hasText(message) ? CommonResult.fail(message) : CommonResult.success("ServerPort:" + serverPort);
    }
}
```

#### 1.6 测试

启动Payment8006Starter服务

访问http://192.168.137.110:8500

![image-20220612113328045](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220612113328.png)

访问http://localhost:8006/consul/payment/get/1

![image-20220612112306505](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220612112306.png)



### 3、创建消费者，并入驻consul

#### 1.1 建module

新建Module消费服务cloud-consumerconsul-order80

#### 1.2 改pom

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>spring-cloud-2022</artifactId>
        <groupId>com.ityj.springcloud</groupId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-consumerconsul-order80</artifactId>

    <dependencies>
        <dependency>
            <groupId>com.ityj.springcloud</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.parent.version}</version>
        </dependency>

        <!--作为consul测试的消费者-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-consul-discovery</artifactId>
        </dependency>
    </dependencies>
</project>
```

#### 1.3 改yml

```yml
server:
  port: 80

spring:
  application:
    name: cloud-consul-order-service

  cloud:
    consul:
      host: 192.168.137.110
      port: 8500
      discovery:
        service-name: ${spring.application.name}
        prefer-ip-address: true
        ip-address: 192.168.1.8
```



#### 1.4 启动类

```java
@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerOrderConsul80Starter {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrderConsul80Starter.class, args);
    }
}
```

#### 1.5 业务编写

```java
@RestController
@Slf4j
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String PAYMENT_URL = "http://cloud-consul-payment-service/";

    @GetMapping("/consumer/consul/payment/get/{id}")
    public CommonResult<PaymentDTO> getById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "consul/payment/get/" + id, CommonResult.class);
    }

    @GetMapping("/consumer/consul/payment/save")
    public CommonResult<String> save(PaymentDTO paymentDTO) {
        log.info("Save: {}", paymentDTO);
        return restTemplate.postForObject(PAYMENT_URL + "consul/payment/save", paymentDTO, CommonResult.class);
    }
}

```

#### 1.6 测试

![image-20220612113523370](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220612113523.png)

访问http://localhost/consumer/consul/payment/get/1

![image-20220612113606255](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220612113606.png)

#### 1.7 Consul总结

==CP，强一致性和分区容错性。==

当某个服务宕机，一定时间未收到心跳响应，直接剔除。

## 四、eureka、zookeeper以及consul异同

CAP：分布式系统有三个指标。CAP理论关注粒度是数据，而不是整体系统设计的策略

* C：Consistency(强一致性)
* A：Availability(可用性)
* P：Partition tolerance(分区容错)：基本都需要满足

![image-20220612115001174](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220612115001.png)

1. eureka强调AP，保证服务能正常运行。当某一个服务宕机时，并不会立即将其注册信息删除。好死不如赖活
2. zookeeper只有一个客户端，没有UI页面。
3. zookeeper和consul针对CP。



## 五、服务调用Ribbon & Feign

#### 1、Spring Cloud Ribbon

> Spring Cloud Ribbon是基于Netflix Ribbon实现的一套客户端负载均衡工具。
>
> 主要提供客户端的软件负载均衡算法和服务调用

**1、负载均衡（Load Balancer）介绍**

简单地说：负载均衡就是将用户请求平摊分配到多个服务上，从而达到系统的HA（高可用）。

常见的负载均衡软件有：Nginx，LVS，硬件F5等

**2、Ribbon本地负载均衡和Nginx服务端负载均衡区别**

（1）Nginx是服务端负载均衡，客户端所有的请求都会交给nginx，然后由nginx实现转发请求。即负载均衡是由服务端实现的。

（2）Ribbon是本地负载均衡，在调用微服务接口时，会在注册中心获取服务注册信息服务列表后 缓存到JVM的本地，从而在本地实现远程RPC远程服务调用技术。

> 一个牙疼的病人来到医院，需要找一个口腔科的医生。
>
> 病人A到医院，进行挂号，挂了一个口腔科的号码，可以认为这里是Nginx进行分配的。
>
> 口腔科有多位医生，没人负责一天，给病人A看病的医生可以认为是由Ribbon本地负载的。

**3、Ribbon能干嘛：负载均衡+RestTemplate调用**

eureka依赖已经引入可Ribbon，所以加入@LoadBalancer可以实现默认的轮询的负载均衡策略

```yml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

![image-20210221173506210](D:\我的文件\gitRepository\cloud-image\img\image-20210221173506210.png)



##### （1）Ribbon的负载规则替换

Ribbon核心组件IRule：根据特定算法从服务列表中选取一个要访问的服务

![image-20210221174753781](https://i.loli.net/2021/02/23/Kmgs1ERfH83IOSa.png)

修改order80的负载规则：访问消费者的controller时，默认对服务提供者payment8081和8082是轮询使用。现在改为随机方式，

###### （1）写配置。

这里要求不配置文件不能被SpringBoot的@ComponetScan扫描到，否则自定义的这个配置类就会被所有的Ribbon客户端共享，达不到特殊定制化的目的。（ssssss）

![image-20210221175753534](D:\我的文件\gitRepository\cloud-image\img\image-20210221175753534.png)

```java
package com.ityj.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRandomRule {
    @Bean
    public IRule randomRule() {
        //定义为随机负载规则
        return new RandomRule();
    }
}
```

###### （2）改启动类

```java
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE", configuration = MyRandomRule.class)   //修改其负载规则
```

###### （3）启动服务，进行测试

http://localhost/consumer/payment/get/1

多次访问，对应端口随机改变。表明切换成功。

![image-20210221181929665](D:\我的文件\gitRepository\cloud-image\img\image-20210221181929665.png)

```java
IRule --> AbstractLoadBalancerRule --> RoundRobinRule(默认的轮询机制)  -->choose()方法实现服务器的选择。
```



#### 2、Feign和Open Feign

什么是Feign？

> Feign是一个声明式的WebService客户端，使用Feign能让编写Web Service更加简单。它的使用方法是定义一个服务接口，然后在上面添加注解。Feign可以与Eureka和Ribbon组合使用达到负载均衡。

![image-20210221183416461](D:\我的文件\gitRepository\cloud-image\img\image-20210221183416461.png)

​	

##### 1、Open Feign使用步骤

###### （1）建module

建一个cloud-consumer-openfeign-order80的module

###### （2）改pom

这里主要是多了一个`spring-cloud-starter-openfeign`的坐标。包含了ribbon的依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>spring-cloud-2022</artifactId>
        <groupId>com.ityj.springcloud</groupId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-consumer-openfeign-order80</artifactId>

    <dependencies>
        <dependency>
            <groupId>com.ityj.springcloud</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.parent.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>

        <!--通过openfeign实现对payment生产者的http调用-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
    </dependencies>


</project>
```

###### （3）写yml

```yml
server:
  port: 80

spring:
  application:
    name: cloud-openfeign-order-service

eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka,http://eureka7003.com:7003/eureka #集群版
```

###### （4）启动类

```java
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients   // openfeign
public class ConsumerOpenFeignOrder80Starter {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOpenFeignOrder80Starter.class, args);
    }
}
```

###### （5）编写业务代码

1. 新建PaymentFeignService接口并新增注解@FeignClient(value = "CLOUD-PAYMENT-SERVICE")

2. 将服务提供者8081的controller声明复制过来，当做接口

   ```java
   @Component
   @FeignClient(value = "CLOUD-PAYMENT-SERVICE")
   public interface PaymentFeignService {
   
       @GetMapping("/payment/get/{id}")
       CommonResult<PaymentDTO> getById(@PathVariable("id") Long id);
   
       @PostMapping("/payment/save")
       CommonResult<String> save(@RequestBody @Valid PaymentDTO paymentDTO);
   }
   ```
3. 编写controller，调用PaymentFeignService接口。

```java
@RestController
@RequestMapping("/openfeign")
@Slf4j
public class OrderFeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<PaymentDTO> getById(@PathVariable("id") Long id) {
        return paymentFeignService.getById(id);
    }

    @GetMapping("/consumer/payment/save")
    public CommonResult<String> save(PaymentDTO paymentDTO) {
        return paymentFeignService.save(paymentDTO);
    }
}
```

###### （6）测试

启动7001,7002,7003,8081,8082以及feign80

http://localhost/openfeign/consumer/payment/get/1

结果成功显示，并且端口交替返回，表示ribbon的负载均衡已生效。

###### （7）总结

* 启动类上要标明开启OpenFeign注解`@EnableFeignClients`
* 自定义的service接口，需要标明`@FeignClient(value = "CLOUD-PAYMENT-SERVICE") `
* 默认的消费者等待返回结果的超时时间是1S



##### 2、Open Feign修改超时时间

修改yml配置文件

添加

```
feign:
  client:
    config:
      default:
        connectTimeout: 5000         # 设置feign调用producer的最大超时时间
        readTimeout: 5000
```



##### 3、OpenFeign日志打印功能

（1）添加配置文件

```java
@Configuration
public class FeignConfig {

    @Bean
    public Logger.Level loggerLevel() {
        return Logger.Level.FULL;
    }

}
```

（2）修改yml

```yml
logging:
  level:
    com.ityj.springcloud.service.PaymentFeignService: debug
```

（3）结果

![image-20220618102137428](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220618102144.png)



## 六、服务降级Hystrix

分布式系统面临的问题：

复杂分布式结构中的应用程序有多个依赖关系，每个依赖关系在调用的时候不可避免会出现失败的情况，如果不妥善处理，就可能出现''雪崩''严重后果。

### 1、Hystrix介绍

![image-20210222083030356](D:\我的文件\gitRepository\cloud-image\img\image-20210222083030356.png)

![image-20210222083203460](D:\我的文件\gitRepository\cloud-image\img\image-20210222083203460.png)

### 2、Hystrix服务提供者8001构建

#### （1）建module

新建module：cloud-provider-hystrix-payment8001

#### （2）改pom

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>spring-cloud-2022</artifactId>
        <groupId>com.ityj.springcloud</groupId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-provider-hystrix-payment8001</artifactId>

    <dependencies>
        <dependency>
            <groupId>com.ityj.springcloud</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.parent.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
    </dependencies>

</project>
```

#### （3）写yml

```yml
server:
  port: 8001

spring:
  application:
    name: cloud-provider-hystrix-payment

eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka,http://eureka7003.com:7003/eureka  #集群版

  instance:
    instance-id: payment8001
    prefer-ip-address: true
```

#### （4）启动类

```java
@SpringBootApplication
@EnableEurekaClient
public class PaymentHystrix8001Starter {
    public static void main(String[] args) {
        SpringApplication.run(PaymentHystrix8001Starter.class, args);
    }
}
```

#### （5）业务逻辑编写

service中包含两个方法：一个是直接返回入参，几乎不耗时；另一个是休眠一定时间再返回入参，会等待一定时间。

```java
@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public String success(Long id) {
        return Thread.currentThread().getName() + "-success-" + id;
    }

    @SneakyThrows
    @Override
    public String timeout(Long id) {
        long time = 3;
        TimeUnit.SECONDS.sleep(time);
        return Thread.currentThread().getName() + "-timeout:" + time + "-" + id;
    }
}

```

```java
@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/success/{id}")
    public CommonResult<String> success(@PathVariable("id") Long id) {
        String result = paymentService.success(id);
        log.info(result);
        return CommonResult.success(result);
    }

    @GetMapping("/timeout/{id}")
    public CommonResult<String> timeout(@PathVariable("id") Long id) {
        String result = paymentService.timeout(id);
        log.info(result);
        return CommonResult.success(result);
    }
}
```

#### （6）测试

启动 7001， 7002，7003, cloud-provider-hystrix-payment8001

访问Eureka：http://localhost:7001/，看到8001已经注册进去

![image-20220619160033493](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220619160040.png)

访问  [localhost:8001/payment/timeout/312](http://localhost:8001/payment/timeout/312)

 [localhost:8001/payment/success/312](http://localhost:8001/payment/success/312)

都可以正常运行

#### (7) 利用JMeter测试高并发情况

##### Jmeter安装

> https://baijiahao.baidu.com/s?id=1663295636729480721&wfr=spider&for=pc

* https://jmeter.apache.org/ 下载apache-jmeter-5.4.1.tgz

* 下载后进行解压，解压完成即可

* 鼠标左键双击bin目录下 jmeter.bat或ApacheJMeter.jar进行启动

  ![image-20220619160723910](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220619160724.png)

* 配置一个连接进行高并发模拟：一组200个线程，执行100次

  ![image-20220619160744081](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220619160744.png)

  ![image-20220619160757119](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220619160757.png)

##### 结论

**测试最后发现，当paymentInfo_timeout进行高并发调用时，也会影响到paymentInfo_OK接口效率。**

### 3、Hystrix消费者80

#### （1）建module

新建cloud-consumer-feign-hystrix-order80来访问8081，模拟出现问题的情况

#### （2）改pom

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>spring-cloud-2022</artifactId>
        <groupId>com.ityj.springcloud</groupId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-consumer-feign-hystrix-order80</artifactId>

    <dependencies>
        <dependency>
            <groupId>com.ityj.springcloud</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.parent.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>

        <!--通过openfeign实现对payment生产者的http调用-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
    </dependencies>
</project>
```

#### （3）改yml

```yml
server:
  port: 80

spring:
  application:
    name: cloud-openfeign-order-service

eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka,http://eureka7003.com:7003/eureka  #集群版

feign:
  client:
    config:
      default:
        connectTimeout: 5000         # 设置feign调用producer的最大超时时间
        readTimeout: 5000
```

#### （4）启动类

```java
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ConsumerOrderHystrix80Starter {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrderHystrix80Starter.class, args);
    }
}
```

#### （5）业务层

> 通过openfeign访问服务提供者8001接口编写

```java
@Component
@FeignClient(value = "cloud-provider-hystrix-payment")
public interface PaymentFeignService {

    @GetMapping("/payment/success/{id}")
    CommonResult<String> success(@PathVariable("id") Long id);

    @GetMapping("/payment/timeout/{id}")
    CommonResult<String> timeout(@PathVariable("id") Long id);
}
```

controller实现

```java
@RestController
@RequestMapping("/hystrix")
@Slf4j
public class OrderFeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/success/{id}")
    public CommonResult<String> success(@PathVariable("id") Long id) {
        return paymentFeignService.success(id);
    }

    @GetMapping("/consumer/payment/timeout/{id}")
    public CommonResult<String> timeout(@PathVariable("id") Long id) {
        return paymentFeignService.timeout(id);
    }

}
```

调用[localhost/hystrix/consumer/payment/success/312](http://localhost/hystrix/consumer/payment/success/312)，正常访问。

**注意：消费者调用8081用的是open feign，而open feign默认的超时时间是1秒**

添加配置，即可正常访问了。

```yml
feign:
  client:
    config:
      default:
        connectTimeout: 5000         # 设置feign调用producer的最大超时时间
        readTimeout: 5000
```



### 4、问题解决（Hystrix）

#### 1. 服务降级（fallback）：设置一个兜底策略

主要用到三个注解

```java
@HystrixCommand、@EnableCircuitBreaker和@DefaultProperties
```

这些可以用在生产者上也可以用在消费者上。一**般情况是会用于消费者端。**

##### （1）这里先说明用于8001的服务提供者身上：

使用步骤：

1. 在想要处理的业务层方法上添加@HystrixCommand注解并添加相关属性

   ```java
   @SneakyThrows
   @Override
   @HystrixCommand(fallbackMethod = "timeoutHandler", commandProperties = {
       @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")  //3秒钟以内就是正常的业务逻辑
   })
   public String timeout(Long id) {
       //Double.valueOf(null);
       long time = 5;
       TimeUnit.SECONDS.sleep(time);
       return Thread.currentThread().getName() + "-timeout:" + time + "-" + id;
   }
   
   // 兜底策略
   public String timeoutHandler(Long id) {
       return Thread.currentThread().getName() + "-timeoutHandler: 程序繁忙，请稍后再试---" + id;
   }
   ```

2. 在启动类上添加@EnableCircuitBreaker注解

3. 启动8001服务，访问[localhost:8001/payment/timeout/312](http://localhost:8001/payment/timeout/312)

   ![image-20220619195652334](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220619195652.png)

   

4. 处理runtimeException, 在@HystrixCommand指定method即可。上面对于超时的配置同样适用于普通报错。

   

##### （2）对80的消费者端进行fallback服务降级

* 1. 配置文件修改

```yml
feign:
  hystrix:
    enabled: true #如果处理自身的容错就开启。开启方式与生产端不一样。
```

* 2. 启动类添加注解

启动类添加`@EnableHystrix       // 启动Hystrix服务降级`

* 3. 编写逻辑代码

```java
@GetMapping("/consumer/payment/timeout/{id}")
@HystrixCommand(fallbackMethod = "orderTimeoutHandler", commandProperties = {
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
})
public CommonResult<String> timeout(@PathVariable("id") Long id) {
    return paymentFeignService.timeout(id);
}

public CommonResult<String> orderTimeoutHandler(Long id) {
    return CommonResult.fail("orderTimeoutHandler: 80消费者端无法在规定时间内获取到响应数据或者程序出错！id = " + id);
}
```

消费者order80认为1.5秒内的返回为正常的，否则就走超时的兜底逻辑。

* 4.测试可以发现超时或者order端报错能走到自己写的兜底策略

  ![image-20220619202529186](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220619202529.png)



##### （3）问题总结与优化

###### 优化1:defaultFallback

> ​	现在每个方法需要写一个兜底策略，代码冗余严重。可以用@DefaultProperties(defaultFallback = "")注解，对一系列方法配置一个默认的策略。
>
> ```java
>// 全局的兜底策略 ： fallback方法
> public CommonResult<String> globalFallBackHandler() {
>     return CommonResult.fail(Thread.currentThread().getName() + "全局兜底策略执行了。。。");
> }
>    ```
> 
> ```java
>@DefaultProperties(defaultFallback = "globalFallBackHandler", commandProperties = {
>         @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000")
> })
> ```
>
> ```java
> @GetMapping("/consumer/payment/success/{id}")
> @HystrixCommand
> public CommonResult<String> success(@PathVariable("id") Long id) {
>        //Double.valueOf("sdf");
>     return paymentFeignService.success(id);
>    }
> ```
>    

此时可以直接关闭服务提供者8001，然后调用/consumer/payment/success/{id}，可以看到走了defaultFallback

![image-20220619213230501](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220619213230.png)

###### 优化2:FeignClient--> fallback属性

> ```java
>@Component
> @FeignClient(value = "cloud-provider-hystrix-payment")
> public interface PaymentFeignService {
> ```
> 
> 一个自定义的FeignClient接口，可以指向一个服务提供者，因此可以根据这个特点，对这个接口进行实现，完成定制化。避免和业务逻辑混一起。
>
> （1）新建一个类HystrixOrderServiceImpl.java实现接口OrderService
>
> ```java
>@Service
> public class PaymentFallbackService implements PaymentFeignService {
>     @Override
>     public CommonResult<String> success(Long id) {
>         return CommonResult.fail("PaymentFallbackService.success()---> 进入了openfeign默认的兜底策略");
>        }
>    
>        @Override
>        public CommonResult<String> timeout(Long id) {
>            return CommonResult.fail("PaymentFallbackService.timeout()---> 进入了openfeign默认的兜底策略");
>     }
>    }
>    ```
>    
>    （2）修改openfeign访问生产者的统一接口OrderService
>    
> 添加fallback的属性。
> 
>```java
> @Component
>@FeignClient(value = "cloud-provider-hystrix-payment", fallback = PaymentFallbackService.class)
> public interface PaymentFeignService {}
>```
> 
> （3）确保配置文件yml开启了hystrix
> 
> ```yml
> feign:
>    hystrix:
>      	enabled: true #如果处理自身的容错就开启。开启方式与生产端不一样。
>```
> 
> （4）启动相关服务器。进行测试。关闭8001或者在服务提供方制造错误，
>   
>    再次调用链接[localhost/hystrix/consumer/payment/success/89465](http://localhost/hystrix/consumer/payment/success/89465)，发现进入服务降级的方法。
> 
>![image-20220619215826365](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220619215826.png)



#### 2. 服务熔断（break）

![image-20210222221754446](D:\我的文件\gitRepository\cloud-image\img\image-20210222221754446.png)

**如何配置服务熔断：**

1、修改cloud-provider-hystrix-payment8001，在PaymentService类中添加paymentCircuitBreaker方法以及其对应的异常服务降级（兜底）方法paymentCircuitBreaker_fallback

```java
@HystrixCommand(fallbackMethod = "circuitBreakHandler", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),  //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),   //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),  //时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") //失败率达到多少后跳闸
})
@Override
public String circuitBreak(Long id) {
    if (id < 0) {
        throw new RuntimeException("Id cannot less than zero!");
    }
    return Thread.currentThread().getName() + "-------" + id;
}

public String circuitBreakHandler(Long id) {
    return "服务熔断后fallback---> circuitBreakHandler: 当前服务不可用，请稍等重试！id = " + id;
}
```

2、 添加对应的controller调用接口

```java
@GetMapping("/consumer/payment/circuitBreak/{id}")
public CommonResult<String> circuitBreak(@PathVariable("id") Long id) {
    return paymentFeignService.circuitBreak(id);
}
```

3、启动类添加注解

```java
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker  // 开启服务熔断
public class PaymentHystrix8001Starter {
    public static void main(String[] args) {
        SpringApplication.run(PaymentHystrix8001Starter.class, args);
    }
}
```

4、检查配置文件(80)

```yml
feign:
  hystrix:
    enabled: true
```

5、测试服务熔断

上述方法为：当id为负数时，抛出异常。

而配置的服务熔断为：10秒范围内最少10次请求，并且60%以上的失败时，会进行熔断。而一段时间后，如果访问的成功率较好，将会恢复链路。

准备链接[localhost/hystrix/consumer/payment/circuitBreak/787](http://localhost/hystrix/consumer/payment/circuitBreak/787)和[localhost/hystrix/consumer/payment/circuitBreak/-111](http://localhost/hystrix/consumer/payment/circuitBreak/-111)

模拟正常：多次访问第一个链接，无事发生

模拟异常：连续多次访问第二个链接*（抛异常），一段时间后再访问第一个链接，发现第一个链接也返回异常数据：服务熔断掉了；继续访问第一个链接，一段时间后发现可以正常访问了，表示链路恢复了。

![image-20210222222923089](D:\我的文件\gitRepository\cloud-image\img\image-20210222222923089.png)

![image-20210222222957912](D:\我的文件\gitRepository\cloud-image\img\image-20210222222957912.png)



#### 3.  服务限流（flowlimit）



### 5. 服务监控hystrixDashboard

![image-20210223184536259](https://i.loli.net/2021/02/23/z6Ak2EbF4ZRamqi.png)

#### 1、搭建dashboard9001项目

（1）新建module，新建cloud-consumer-hystrix-dashboard9001

（2）改pom

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>spring-cloud-2022</artifactId>
        <groupId>com.ityj.springcloud</groupId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-consumer-hystrix-dashboard9001</artifactId>

    <dependencies>
        <dependency>
            <groupId>com.ityj.springcloud</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.parent.version}</version>
        </dependency>

        <!--新增hystrix dashboard-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
        </dependency>

    </dependencies>
</project>
```

（3）改yml

```yml
server:
  port: 9001         # http://localhost:9001/hystrix

hystrix:
  dashboard:
    proxy-stream-allow-list: "*"    # 解决HystrixDashboard 监控页面 Unable to connect to Command Metric Stream.
```

（4）启动类

需要添加@EnableHystrixDashboard新的注解

```java
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboard9001Starter {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboard9001Starter.class, args);
    }
}
```

（5）测试

启动http://localhost:9001/hystrix

![image-20220622221450147](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220622221457.png)

#### 2、启动8001，并让dashbord9001对8001进行监控

##### （1）pom

确保8001中有actuator的依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

##### （2）确保开启了actuator/hystrix.stream 的监控配置

```yml
server:
  port: 8001

...

management:
  endpoints:
    web:
      exposure:
        include: health,info,hystrix.stream #根据需求增删路径
```

##### （3）启动7001,7002,7003,8001,801,9001dashbord

访问http://localhost:9001/hystrix监控平台，并对http://localhost:8001/actuator/hystrix.stream进行监控；

![image-20220622221754227](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220622221754.png)

对8001服务熔断的接口进行访问测试：

http://localhost:8001//payment/circuitBreak/333   成功

http://localhost:8001//payment/circuitBreak/-11  失败

多次访问失败链接，发现断路器打开。一段时间后再次访问失败的链接，发现断路器关闭，可以正常工作。

![image-20220622222057553](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220622222057.png)

![image-20220622222810853](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220622222811.png)



![image-20220623201314636](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220623201321.png)

### 六、Gateway网关

> 原本网关主要是使用zuul1.X但是，1.X存在一些问题，在升级到2.X过程中公司内部出现一些问题，导致效率低下。Spring选择自己研发GateWay

![image-20210224070032972](D:\我的文件\gitRepository\cloud-image\img\UlYvqHrB8ZW2nCc.png)

![image-20210224070059661](https://i.loli.net/2021/02/24/zBaVjUgdoXkfQMO.png)

![](https://i.loli.net/2021/02/24/zBaVjUgdoXkfQMO.png)



Spring Cloud Gateway 使用的Webflux中的reactor-netty响应式编程组件，底层使用了Netty通讯框架

能干嘛：

* 反向代理
* 鉴权
* 流量控制
* 熔断
* 日志监控

![image-20210224073052192](C:/Users/ayinj/AppData/Roaming/Typora/typora-user-images/image-20210224073052192.png)

![image-20210224222548368](D:\我的文件\gitRepository\cloud-image\img\image-20210224222548368.png)

![image-20210224222615115](D:\我的文件\gitRepository\cloud-image\img\image-20210224222615115.png)

![image-20210224222702294](D:\我的文件\gitRepository\cloud-image\img\image-20210224222702294.png)



#### 1、三大核心概念

##### 1.1 Route(路由)

路由是构建网关的基本模块，它由ID，目标URI，一系列的断言和过滤器组成，如果断言为true则匹配该路由

##### 1.2 Predicate（断言）

参考的是java8的java.util.function.Predicate开发人员可以匹配HTTP请求中的所有内容（例如请求头或请求参数），如果请求与断言相匹配则进行路由

##### 1.3 Filter(过滤)

指的是Spring框架中GatewayFilter的实例，使用过滤器，可以在请求被路由前或者之后对请求进行修改。

![image-20210224222858532](D:\我的文件\gitRepository\cloud-image\img\image-20210224222858532.png)

#### 2、搭建gateway9527项目

（1）建module

创建cloud-gateway-gateway9527项目。

（2）改pom

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>spring-cloud-2022</artifactId>
        <groupId>com.ityj.springcloud</groupId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-gateway-gateway9527</artifactId>

    <dependencies>
        <!--新增gateway-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-gateway</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>

        <dependency>
            <groupId>com.ityj.springcloud</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>0.0.1-SNAPSHOT</version>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-web</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>com.github.xiaoymin</groupId>
                    <artifactId>knife4j-spring-boot-starter</artifactId>
                </exclusion>
            </exclusions>
        </dependency>

    </dependencies>


</project>
```

（3）改yml

```yml
server:
  port: 9527
spring:
  application:
    name: cloud-gateway
eureka:
  instance:
    hostname: cloud-gateway-service
    instance-id: gateway9527
    prefer-ip-address: true
  client:
    service-url:
      register-with-eureka: true
      fetch-registry: true
      defaultZone: http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka,http://eureka7003.com:7003/eureka
```



（4）主启动类

```java
@SpringBootApplication
@EnableEurekaClient
public class Gateway9527Starter {
    public static void main(String[] args) {
        SpringApplication.run(Gateway9527Starter.class, args);
    }
}

```

（5）测试

目前网关没有做任何工作，只是将自己注册进了eureka

启动7001,7002,7003，8001, 以及9275查看eureka是否将其注册进去了。

http://eureka7001.com:7001/

![image-20220626154842647](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220626154849.png)



#### 3、9527网关路由映射8081

启动8081，访问http://localhost:8001/payment/get/1能 正常返回结果。

需求：我们目前不想暴露8001端口，希望在8001外面套一层9527

即访问  http://localhost:9527/payment/get/1也可以正常访问。



只需要修改9527的配置文件

```yml
spring:
  cloud:
    gateway:
      routes:
        - id: payment_route #路由的ID，没有固定规则但要求唯一，建议配合服务名
          uri: http://localhost:8001   #匹配后提供服务的路由地址
          predicates:
            - Path=/payment/get/**   #断言,路径相匹配的进行路由

        - id: payment_route2
          uri: http://localhost:8001
          predicates:
            - Path=/payment/discovery  #断言,路径相匹配的进行路由
```



测试：

访问http://localhost:9527/payment/get/1，可以正常返回结果。

![image-20220626155050615](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220626155050.png)

#### 4、通过配置类实现路由

```java
@Configuration
public class GatewayRouteConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();

        routes.route("route_mil", x -> x.path("/mil").uri("http://news.baidu.com/guonei")).build();
        routes.route("route_game", x -> x.path("/game").uri("http://news.baidu.com/game")).build();
        return routes.build();
    }
}
```

和通过配置文件编写的一样：

```yml
spring:
  cloud:
    gateway:
      routes:
        - id: route_mil #路由的ID，没有固定规则但要求唯一，建议配合服务名      http://news.baidu.com/mil
          uri: http://news.baidu.com   #匹配后提供服务的路由地址
          predicates:
            - Path=/mil   #断言,路径相匹配的进行路由

        - id: route_game
          uri: http://news.baidu.com
          predicates:
            - Path=/game  #断言,路径相匹配的进行路由
```

![image-20220626171645488](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220626171645.png)

#### 5、通过微服务名实现动态路由

>  默认情况下Gateway会根据注册中心的服务列表，以注册中心上微服务名为路径创建动态路由进行转发，从而实现动态路由的功能

在8081和8082服务下都有`/payment/get/1获取端口号的方法，8001和8002也注册进了Eureka，服务名称为**CLOUD-PAYMENT-SERVICE**。可以通过这个服务名结合gateway实现负载均衡。

1、启动Eureka7001和Eureka7001、7003、gateway9527和服务8081与服务8082

2、修改gateway9527的yml

* 添加spring.cloud.gateway.discovery.locator.enabled=true
* 修改spring.cloud.gateway.routes[1/2].uri=lb://CLOUD-PAYMENT-SERVICE

```yml
server:
  port: 9527
spring:
  application:
    name: cloud-gateway
  cloud:
    gateway:
      routes:
        - id: payment_route #路由的ID，没有固定规则但要求唯一，建议配合服务名
          #uri: http://localhost:8001   #匹配后提供服务的路由地址
          uri: lb://CLOUD-PAYMENT-SERVICE   #匹配后提供服务的路由地址
          predicates:
            - Path=/payment/get/**   #断言,路径相匹配的进行路由

        - id: payment_route2
          uri: http://localhost:8001
          predicates:
            - Path=/payment/discovery  #断言,路径相匹配的进行路由


        - id: route_guoji #路由的ID，没有固定规则但要求唯一，建议配合服务名      http://news.baidu.com/guoji
          #uri: http://news.baidu.com   #匹配后提供服务的路由地址
          uri: lb://CLOUD-PAYMENT-SERVICE   #匹配后提供服务的路由地址
          predicates:
            - Path=/guoji   #断言,路径相匹配的进行路由

        - id: route_guonei
          uri: http://news.baidu.com
          predicates:
            - Path=/guonei  #断言,路径相匹配的进行路由
      discovery:
        locator:
          enabled: true

eureka:
  instance:
    hostname: cloud-gateway-service
    instance-id: gateway9527
    prefer-ip-address: true
  client:
    service-url:
      register-with-eureka: true
      fetch-registry: true
      defaultZone: http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka,http://eureka7003.com:7003/eureka
```

3、测试

调用连接 http://localhost:9527/payment/get/1

发现服务端口8081和8082轮询调用

![image-20220626173155755](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220626173155.png)

#### 6、Predicate的使用

启动9527项目，发现启动项里有很多Route Predicate Factories

![image-20210225213937118](D:\我的文件\gitRepository\cloud-image\img\image-20210225213937118.png)

![image-20210225214038310](D:\我的文件\gitRepository\cloud-image\img\image-20210225214038310.png)

总结：说白了，Predicate就是为了实现一组匹配规则，让请求过来找到对应的Route进行处理

添加一系列的and条件

```yml
predicates:
    - Path=/payment/get/**   #断言,路径相匹配的进行路由
    - After=2022-06-26T17:55:13.076168800+08:00[Asia/Shanghai]   # 可以通过 ZonedDateTime.now() 获取携带时区的当前时间。多个predicates之间相当于and，这里的意思是只有在2021-02-25T22:41:56.214+08:00[Asia/Shanghai]之后此路由才能生效。
	- Cookie=username,Jack     # curl -X GET http://localhost:9527/payment/get/1 --cookie "username=Jack"
    - Header=X-Request-Id, \d+   #请求头中要有X-Request-Id属性并且值为整数的正则表达式 curl -X GET http://localhost:9527/payment/get/1 -H "X-Request-Id:123" --cookie "username=Jack"
    - Host=**.jack.com   #   curl -X GET http://localhost:9527/payment/get/1 -H "Host:www.jack.com" -H "X-Request-Id:123" --cookie "username=Jack"
```

#### 7、Filter的使用

![image-20210225221404766](D:\我的文件\gitRepository\cloud-image\img\image-20210225221404766.png)

##### 1、自定义全局GlobalFilter过滤器

（1）在9725gateway服务中添加MyLoginGateWayFilter模拟对未登录的用户过滤

即：当请求中不含username=xxx的键值对时，进行拒绝。

```java
@Component
@Slf4j
public class MyLoginGateWayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("进入MyLoginGateWayFilter。。。。start...");
        String username = exchange.getRequest().getQueryParams().getFirst("username");
        if (ObjectUtils.isEmpty(username)) {
            log.info("用户未登录，无法进入。。。o(╥﹏╥)o");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        log.info("用户已登录，可以进入。。。☺");
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
```

（2）测试

正确：http://localhost:9527/payment/getPort?username=23

![image-20210225222635105](D:\我的文件\gitRepository\cloud-image\img\image-20210225222635105.png)

错误：http://localhost:9527/payment/getPort

![image-20210225222658038](D:\我的文件\gitRepository\cloud-image\img\image-20210225222658038.png)

### 七、config分布式配置中心

![image-20210227211918837](D:\我的文件\gitRepository\cloud-image\img\image-20210227211918837.png)

![image-20210227212440433](D:\我的文件\gitRepository\cloud-image\img\image-20210227212440433.png)

![image-20210227212452728](D:\我的文件\gitRepository\cloud-image\img\image-20210227212452728.png)

#### 1、Config服务端配置与测试

##### 1、安装git，并创建springcloud-config的仓库

https://blog.csdn.net/weixin_44588243/article/details/114207103

##### 2、在Gitee上创建一个项目springcloud-config仓库

用于存放公用的配置文件，作为config服务器的远程服务器文件。  --》config server

（1）建module：cloud-config-center-3344

（2）改pom:

<artifactId>spring-cloud-config-server</artifactId>

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>springcloud-0219-00</artifactId>
        <groupId>com.ityj.springcloud</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-config-center-3344</artifactId>

    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-config-server</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <dependency>
            <groupId>com.ityj.springcloud</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>


</project>
```

（3）改yml

```yml
server:
  port: 3344
spring:
  application:
    name: cloud-config-center
  cloud:
    config:
      server:
        git:
          uri: https://gitee.com/yj1109/springcloud-config.git   # 注意选择https对应的uri
          search-paths:
            - springcloud-config
      label: master
eureka:
  client:
    service-url:
      defaultZone: http://localhost:7001/eureka,http://localhost:7002/eureka

# http://config-3344.com:3344/master/config-dev.yml
```

uri这里选择的是gitee新建的公开仓库

![image-20210228105802674](D:\我的文件\gitRepository\cloud-image\img\image-20210228105802674.png)

（4）启动类

作为配置文件服务端，需要添加注解：@EnableConfigServer

```java
@SpringBootApplication
@EnableConfigServer    // 作为config配置的服务端
public class ConfigCenter3344Starter {
    public static void main(String[] args) {
        SpringApplication.run(ConfigCenter3344Starter.class, args);
    }
}
```

（5）测试

启动eureka服务端：7001,7002以及新建的3344配置文件服务端。

访问http://config-3344.com:3344/master/config-dev.yml（config-dev.yml是码云远程仓库的文件），看能否获取到其内容。

结果可以。

![image-20210228110036727](D:\我的文件\gitRepository\cloud-image\img\image-20210228110036727.png)

#### 2、Config客户端配置与测试

服务端3344访问远程仓库的数据并作为各个微服务的配置中心，而各个微服务3355,3366。。。通过3344来获取和远程仓库一致的配置信息。

（1）建module：cloud-config-client-3355

（2）改pom

没有加server就是客户端

<artifactId>spring-cloud-starter-config</artifactId>

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>springcloud-0219-00</artifactId>
        <groupId>com.ityj.springcloud</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-config-client-3355</artifactId>

    <dependencies>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <dependency>
            <groupId>com.ityj.springcloud</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

</project>
```

（3）加bootstap.yml

![image-20210228111514141](D:\我的文件\gitRepository\cloud-image\img\image-20210228111514141.png)

由于idea不会将bootstrap.yml转换成带小叶子的spring配置文件，需要手动操作。

![image-20210228112325189](D:\我的文件\gitRepository\cloud-image\img\image-20210228112325189.png)

找到对应的文件就行了。

```yml
server:
  port: 3355

spring:
  application:
    name: config-client
  cloud:
    # Config客户端配置
    config:
      label: master    # 分支名称
      name: config     # 配置文件名称
      profile: dev     # 配置文件后缀
      uri: http://localhost:3344   # 上述三个属性：master分支上的config-dev.yml的配置文件，会被读取。http://config-3344.com:3344/master/config-dev.yml
eureka:
  client:
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka
```

（4）启动类编写

```java
@SpringBootApplication
@EnableEurekaClient
public class ConfigClient3355Starter {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClient3355Starter.class, args);
    }
}
```

（5）业务类编写

访问远程仓库对应的属性，看能否正常获取。其实访问的是3344服务端

```java
@RestController
@Slf4j
public class MyClientController {

    @Value(value = "${remote.version}")
    private String version;

    // http://localhost:3355/getRemoteVersion
    @GetMapping(path = "/getRemoteVersion")
    public String getRemoteVersion() {
        log.info("进入getRemoteVersion。。。。。。");
        return "version : " + version;
    }

}
```

（6）启动测试

启动时，第一次bootstrap.yml写成了bootstrp，导致无法扫描到这个配置文件，所以访问远程仓库属性导致注入失败，一直报错。



启动7001,7002,3344以及3355

访问http://config-3344.com:3344/master/config-dev.yml，正常。

访问http://localhost:3355/getRemoteVersion，正常访问，可以获取到远程仓库的属性。（是通过3344访问出来的）



继续测试：

修改远程仓库的配置内容，3344访问可以立即更新，而3355/getRemoteVersion获取的还是未更新前的属性。

重启3355后，3355也能获取到最新的配置了。



现在就出现了一个问题，分布式配置还未实现动态刷新，如果每次更改都需要重启，将会非常麻烦。



#### 3、Config客户端之动态刷新 - 手动版

避免每次更新配置都要重启客户端微服务3355，我们需要修改3355模块：

（1）POM引入actuator监控

确保已经引入了

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

目前此依赖要求和<artifactId>spring-boot-starter-web</artifactId>绑定，两者同时引入。除了网关gateway，其他都加上。（2）修改YML，暴露监控端口

3355中添加

```yml
management:
  endpoints:
    web:
      exposure:
        include: "*"
```

（3）修改控制器

添加注解`@RefreshScope`

（4）重启3355，测试

启动7001,7002,3344,3355

访问http://config-3344.com:3344/master/config-dev.yml，正常。

访问http://localhost:3355/getRemoteVersion，正常。



修改远程仓库的config-dev.yml

访问http://config-3344.com:3344/master/config-dev.yml，直接更新，得到最新数据。

访问http://localhost:3355/getRemoteVersion，未更新。

？？？

**还需要一个步骤，需要发送一个post请求，来手动刷新配置。**

`curl -X POST "http://localhost:3355/actuator/refresh"`

![image-20210228125554231](D:\我的文件\gitRepository\cloud-image\img\image-20210228125554231.png)

刷新完，无需重启项目，再次访问http://localhost:3355/getRemoteVersion，发现已经是最新的数据。



（5）总结

此时每次修改后都需要运维人员手动调用链接进行刷新，也很麻烦。

需要找到一种方法：可否广播，一次通知，处处生效？



### 八、SpringCloud Bus 消息总线

**Spring Cloud Bus配合Spring Cloud Config使用可以实现配置的动态刷新，通过广播实现一次通知，处处生效。**

![image-20210228161626445](D:\我的文件\gitRepository\cloud-image\img\image-20210228161626445.png)

Bus支持两种消息代理：RabbitMQ和Kafka

![image-20210228161738048](D:\我的文件\gitRepository\cloud-image\img\image-20210228161738048.png)



为何被称为总线？

![image-20210228161759871](D:\我的文件\gitRepository\cloud-image\img\image-20210228161759871.png)

#### 1、RabbitMQ环境配置

（1）下载安装Erlang

（2）下载安装rabbitmq-server

安装完毕，进入sbin目录：D:\Java\RabbitMQ\rabbitmq-server-3.7.15\rabbitmq_server-3.7.15\sbin

cmd进入命令窗口，执行`rabbitmq-plugins enable rabbitmq_management`命令：

![image-20210228162120060](D:\我的文件\gitRepository\cloud-image\img\image-20210228162120060.png)

（3）访问地址查看是否安装成功

点击可视化插件：RabbitMQ Service - start，启动服务，并访问http://localhost:15672/

默认用户为 guest/guest

![image-20210228162400029](D:\我的文件\gitRepository\cloud-image\img\image-20210228162400029.png)

![image-20210228162408910](D:\我的文件\gitRepository\cloud-image\img\image-20210228162408910.png)

![image-20210228162427749](D:\我的文件\gitRepository\cloud-image\img\image-20210228162427749.png)



#### 2、模仿3355创建config客户端3366

只有端口为3366，其余都和3355相同

创建完毕，测试：

启动7001,7002,3344,3355,3366

访问http://config-3344.com:3344/master/config-dev.yml：结果为最新配置。

http://localhost:3355/getRemoteVersion：也是最新的

http://localhost:3366/getRemoteVersion：也是最新的



**修改远程配置文件config-dev.yml**

发现只有3344会立即更新，而3355和3366都需要分别执行以下命令刷新后可以正常显示最新的配置：

```shell
curl -X POST "http://localhost:3355/actuator/refresh"
curl -X POST "http://localhost:3366/actuator/refresh"
```



#### 3、修改配置，实现全局广播

一次通知，处处生效

> 利用消息总线触发一个服务端ConfigServer的/bus/refresh端点,而刷新所有客户端的配置（更加推荐）

![image-20210228221042471](D:\我的文件\gitRepository\cloud-image\img\image-20210228221042471.png)

##### （1）给cloud-config-center-3344配置中心服务端添加消息总线支持

1. 改pom

   新增依赖

   ```xml
   <dependency>
       <groupId>org.springframework.cloud</groupId>
       <artifactId>spring-cloud-starter-bus-amqp</artifactId>
   </dependency>
   ```

2. 改yml

   ```yml
   server:
     port: 3344
   spring:
     application:
       name: cloud-config-center
     cloud:
       config:
         server:
           git:
             uri: https://gitee.com/yj1109/springcloud-config.git   # 注意选择https对应的uri
             search-paths:
               - springcloud-config
         label: master
   
     # 配置rabbitMQ的属性
     rabbitmq:
       host: localhost
       port: 5672
       username: guest
       password: guest
       
   eureka:
     client:
       service-url:
         defaultZone: http://localhost:7001/eureka,http://localhost:7002/eureka
   
   # http://config-3344.com:3344/master/config-dev.yml
   
   management:
     endpoints:
       web:
         exposure:
           include: 'bus-refresh'
    
   ```

   添加了rabbitMQ的配置和最后的bus配置。

##### （2）给cloud-config-center-3355/3366客户端添加消息总线支持

1. 改pom

   添加依赖，和3344一致

   ```xml
   <dependency>
       <groupId>org.springframework.cloud</groupId>
       <artifactId>spring-cloud-starter-bus-amqp</artifactId>
   </dependency>
   ```

2. 添加rabbitMQ的配置

   ```yml
   rabbitmq:
       host: localhost
       port: 5672
       username: guest
       password: guest
   ```

   

##### （3）测试

启动7001,7002,3344,3355,3366以及rabbitMQ的客户端

访问http://config-3344.com:3344/master/config-dev.yml：结果为最新配置。

http://localhost:3355/getRemoteVersion：也是最新的

http://localhost:3366/getRemoteVersion：也是最新的

修改远程的配置文件，发现3355和3366都不会更新。

？？？

需要手动对3344进行一次刷新，刷新后3344会广播到对应的客户端上，进行配置更新。

```shell
curl -X POST "http://localhost:3344/actuator/bus-refresh"
```

执行完毕后3355和3366客户端的配置已经能够获取到最新的了。

一次请求，处处生效。

#### 4、实现定点广播

如果指向定点广播，不想全局广播。即指定客户端进行广播，可以使用以下的请求进行处理。

**公式：http://localhost:配置中心的端口号/actuator/bus-refresh/{destination}**

eg:只通知3355，不通知3366

`curl -X POST "http://localhost:3344/actuator/bus-refresh/config-client-3355:3355"`

最后发现，实现了通知3355而未通知3366



### 九、SpringCloud Stream消息驱动

#### 1、消息驱动概述

![image-20210301163054066](D:\我的文件\gitRepository\cloud-image\img\image-20210301163054066.png)

为什么用Cloud Stream？

![image-20210301163212648](D:\我的文件\gitRepository\cloud-image\img\image-20210301163212648.png)

![image-20210301163219732](D:\我的文件\gitRepository\cloud-image\img\image-20210301163219732.png)

![image-20210301163226514](D:\我的文件\gitRepository\cloud-image\img\image-20210301163226514.png)

> Stream中的消息通信方式遵循了发布-订阅模式，Topic主题进行广播：在RabbitMQ就是Exchange，在kafka中就是Topic。



![image-20210301163440328](D:\我的文件\gitRepository\cloud-image\img\image-20210301163440328.png)

![image-20210301163451470](D:\我的文件\gitRepository\cloud-image\img\image-20210301163451470.png)

![image-20210301163701364](D:\我的文件\gitRepository\cloud-image\img\image-20210301163701364.png)

#### 2、消息驱动之生产者8801项目搭建

（1）cloud-stream-rabbitmq-provider8801创建

（2）pom修改

特殊：

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-stream-rabbit</artifactId>
</dependency>
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>springcloud-0219-00</artifactId>
        <groupId>com.ityj.springcloud</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-stream-rabbitmq-provider8801</artifactId>

    <dependencies>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-stream-rabbit</artifactId>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-eureka-server -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>

        <dependency>
            <groupId>com.ityj.springcloud</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.version}</version>
        </dependency>


        <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>


        <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-devtools -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

    </dependencies>

</project>
```

（3）改yml

```yml
server:
  port: 8801

spring:
  application:
    name: cloud-stream-provider
  cloud:
    stream:
      binders: # 在此处配置要绑定的rabbitmq的服务信息；
        defaultRabbit: # 表示定义的名称，用于于binding整合
          type: rabbit # 消息组件类型
          environment: # 设置rabbitmq的相关的环境配置
            spring:
              rabbitmq:
                host: localhost
                port: 5672
                username: guest
                password: guest
      bindings: # 服务的整合处理
        output: # 这个名字是一个通道的名称
          destination: studyExchange # 表示要使用的Exchange名称定义
          content-type: application/json # 设置消息类型，本次为json，文本则设置“text/plain”
          binder: defaultRabbit  # 设置要绑定的消息服务的具体设置

eureka:
  client: # 客户端进行Eureka注册的配置
    service-url:
      defaultZone: http://localhost:7001/eureka,http://localhost:7002/eureka
  instance:
    lease-renewal-interval-in-seconds: 2 # 设置心跳的时间间隔（默认是30秒）
    lease-expiration-duration-in-seconds: 5 # 如果现在超过了5秒的间隔（默认是90秒）
    instance-id: send-8801.com  # 在信息列表时显示主机名称
    prefer-ip-address: true     # 访问的路径变为IP地址
```



（4）业务代码

1. 发送消息接口

   ```java
   public interface MessageProviderService {
       String sendMessage();
   }
   ```

2. 发送消息接口实现类

   ```java
   package com.ityj.springcloud.service.impl;
   
   import com.ityj.springcloud.service.MessageProviderService;
   import lombok.extern.slf4j.Slf4j;
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.cloud.stream.annotation.EnableBinding;
   import org.springframework.cloud.stream.messaging.Source;
   import org.springframework.messaging.MessageChannel;
   import org.springframework.messaging.support.MessageBuilder;
   
   import java.time.LocalDateTime;
   import java.util.UUID;
   
   @EnableBinding(Source.class) //定义消息的推送管道
   @Slf4j
   public class MessageProviderServiceImpl implements MessageProviderService {
   
       @Autowired
       private MessageChannel output; // 消息发送管道
   
       @Override
       public String sendMessage() {
           String uuid = UUID.randomUUID().toString();
           log.info("uuid = {}", uuid);
           output.send(MessageBuilder.withPayload(uuid).build());
           return "success：" + LocalDateTime.now();
       }
   }
   ```

3. controller

   ```java
   package com.ityj.springcloud.controller;
   
   import com.ityj.springcloud.service.MessageProviderService;
   import lombok.extern.slf4j.Slf4j;
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.web.bind.annotation.GetMapping;
   import org.springframework.web.bind.annotation.RestController;
   
   @RestController
   @Slf4j
   public class SendMessageController {
   
       @Autowired
       private MessageProviderService messageProviderService;
   
       @GetMapping(path = "/sendMessage")
       public String sendMessage() {
           return messageProviderService.sendMessage();
       }
   }
   ```

（4）测试

启动7001，7002, 以及这个8801；启动rabbitMQ服务器

访问http://localhost:8801/sendMessage，最终可以在rabbitMQ页面上看到波动情况。

![image-20210301180146276](D:\我的文件\gitRepository\cloud-image\img\image-20210301180146276.png)

#### 3、消息驱动之消费者8802

（1）新建cloud-stream-rabbitmq-consumer8802

（2）改pom

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>springcloud-0219-00</artifactId>
        <groupId>com.ityj.springcloud</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-stream-rabbitmq-consumer8802</artifactId>

    <dependencies>


        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-stream-rabbit</artifactId>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-eureka-server -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>

        <dependency>
            <groupId>com.atguigu.springcloud</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.version}</version>
        </dependency>


        <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>


        <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-devtools -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

    </dependencies>


</project>
```

（3）写yml

```yml
server:
  port: 8802

spring:
  application:
    name: cloud-stream-consumer
  cloud:
    stream:
      binders: # 在此处配置要绑定的rabbitmq的服务信息；
        defaultRabbit: # 表示定义的名称，用于于binding整合
          type: rabbit # 消息组件类型
          environment: # 设置rabbitmq的相关的环境配置
            spring:
              rabbitmq:
                host: localhost
                port: 5672
                username: guest
                password: guest
      bindings: # 服务的整合处理
        input: # 这个名字是一个通道的名称
          destination: studyExchange # 表示要使用的Exchange名称定义
          content-type: application/json # 设置消息类型，本次为json，文本则设置“text/plain”
          binder: defaultRabbit  # 设置要绑定的消息服务的具体设置

eureka:
  client: # 客户端进行Eureka注册的配置
    service-url:
      defaultZone: http://localhost:7001/eureka,http://localhost:7002/eureka
  instance:
    lease-renewal-interval-in-seconds: 2 # 设置心跳的时间间隔（默认是30秒）
    lease-expiration-duration-in-seconds: 5 # 如果现在超过了5秒的间隔（默认是90秒）
    instance-id: receive-8802.com  # 在信息列表时显示主机名称
    prefer-ip-address: true     # 访问的路径变为IP地址
```

（4）启动类

```java
package com.ityj.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StreamMQConsumer8802Starter {

    public static void main(String[] args) {
        SpringApplication.run(StreamMQConsumer8802Starter.class, args);
    }

}
```

（5）业务编写

```java
package com.ityj.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Sink.class)
public class ReceiveMessageListenerService {

    @Value(value = "${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT)
    public void receiveMessage(Message<String> message) {
        System.out.println("消费者1号，接受："+message.getPayload()+"\t port:"+serverPort);
    }
}
```

（6）测试

启动7001、7002、8801、8802和rabbitMQ的服务端

多次访问生产者 http://localhost:8801/sendMessage，在rabbitMQ页面上看到波动情况。

其中8802的控制台可以看到有打印信息出现，即进行了消费。

![image-20210301190417655](D:\我的文件\gitRepository\cloud-image\img\image-20210301190417655.png)

#### 4、消息驱动之消费者8803

复制8802，再新建一个消费者模块。

cloud-stream-rabbitmq-consumer8803

测试：

再启动8083

并多次访问生产者 http://localhost:8801/sendMessage。

此时分别查看8002和8003的控制台打印情况：

8001生产者：

![image-20210301191820425](D:\我的文件\gitRepository\cloud-image\img\image-20210301191820425.png)

8002消费者：

![image-20210301191841155](D:\我的文件\gitRepository\cloud-image\img\image-20210301191841155.png)

8003消费者：

![image-20210301191901929](D:\我的文件\gitRepository\cloud-image\img\image-20210301191901929.png)

**发现，生产的两条数据，被8002和8003同时消费掉了。出现了重复消费的问题。**

#### 5、分组消费与持久化解决重复消费问题

生产实际案例:

![image-20210301192500890](D:\我的文件\gitRepository\cloud-image\img\image-20210301192500890.png)

##### （1）分组

> 微服务应用放置于同一个group中，就能够保证消息只会被其中一个应用消费一次。不同的组是可以消费的，同一个组内会发生竞争关系，只有其中一个可以消费。

同组竞争，不同组会重复消费。

8802和8803默认每一个微服务的组都是不同的：

![image-20210301193008064](D:\我的文件\gitRepository\cloud-image\img\image-20210301193008064.png)



修改8002和8003配置文件，显式地指定组名。

`group: ityjGroup8802`

```yml
server:
  port: 8802

spring:
  application:
    name: cloud-stream-consumer
  cloud:
    stream:
      binders: # 在此处配置要绑定的rabbitmq的服务信息；
        defaultRabbit: # 表示定义的名称，用于于binding整合
          type: rabbit # 消息组件类型
          environment: # 设置rabbitmq的相关的环境配置
            spring:
              rabbitmq:
                host: localhost
                port: 5672
                username: guest
                password: guest
      bindings: # 服务的整合处理
        input: # 这个名字是一个通道的名称
          destination: studyExchange # 表示要使用的Exchange名称定义
          content-type: application/json # 设置消息类型，本次为json，文本则设置“text/plain”
          binder: defaultRabbit  # 设置要绑定的消息服务的具体设置
          group: ityjGroup8802
eureka:
  client: # 客户端进行Eureka注册的配置
    service-url:
      defaultZone: http://localhost:7001/eureka,http://localhost:7002/eureka
  instance:
    lease-renewal-interval-in-seconds: 2 # 设置心跳的时间间隔（默认是30秒）
    lease-expiration-duration-in-seconds: 5 # 如果现在超过了5秒的间隔（默认是90秒）
    instance-id: receive-8802.com  # 在信息列表时显示主机名称
    prefer-ip-address: true     # 访问的路径变为IP地址
```

```yml
server:
  port: 8803

spring:
  application:
    name: cloud-stream-consumer
  cloud:
    stream:
      binders: # 在此处配置要绑定的rabbitmq的服务信息；
        defaultRabbit: # 表示定义的名称，用于于binding整合
          type: rabbit # 消息组件类型
          environment: # 设置rabbitmq的相关的环境配置
            spring:
              rabbitmq:
                host: localhost
                port: 5672
                username: guest
                password: guest
      bindings: # 服务的整合处理
        input: # 这个名字是一个通道的名称
          destination: studyExchange # 表示要使用的Exchange名称定义
          content-type: application/json # 设置消息类型，本次为json，文本则设置“text/plain”
          binder: defaultRabbit  # 设置要绑定的消息服务的具体设置
          group: ityjGroup8803

eureka:
  client: # 客户端进行Eureka注册的配置
    service-url:
      defaultZone: http://localhost:7001/eureka,http://localhost:7002/eureka
  instance:
    lease-renewal-interval-in-seconds: 2 # 设置心跳的时间间隔（默认是30秒）
    lease-expiration-duration-in-seconds: 5 # 如果现在超过了5秒的间隔（默认是90秒）
    instance-id: receive-8803.com  # 在信息列表时显示主机名称
    prefer-ip-address: true     # 访问的路径变为IP地址
```

此时8802和8803都还是在不同的组内，所以还是会出现重复消费的问题。



**把8003的group改为和8002一致：ityjGroup8802；**

再次重启，可以实现轮询的操作，并且不会出现重复消费问题。

![image-20210301195103866](D:\我的文件\gitRepository\cloud-image\img\image-20210301195103866.png)

![image-20210301195121994](D:\我的文件\gitRepository\cloud-image\img\image-20210301195121994.png)

![image-20210301195137290](D:\我的文件\gitRepository\cloud-image\img\image-20210301195137290.png)

##### （2）持久化

如果启动了8801生产者，此时消费者没有启动。生产者又生产了很多的数据，此时再启动消费者8802，如果消费者启动后能够发现8801生产的数据并进行消费，则说明可以持久化。否则不能；

此时如果配置了group属性，就可以持久化，没有配置就不行。

>  删除8802的group: ityjGroup8802配置，此时8803有group: ityjGroup8802配置

关闭8802和8803

多次访问：http://localhost:8801/sendMessage

启动8802服务，发现8802启动后不能消费8001已有的数据（没有配置group）

启动8803服务，发现8803启动后可以消费8001已有的数据。

![image-20210301205437675](D:\我的文件\gitRepository\cloud-image\img\image-20210301205437675.png)



### 十、SpringCloud Sleuth分布式请求链路追踪

为什么会出现这个技术？需要解决哪些问题？

![image-20210301212342572](D:\我的文件\gitRepository\cloud-image\img\image-20210301212342572.png)

是什么？

> Spring Cloud Sleuth提供了一套完整的服务跟踪的解决方案，在分布式系统中提供追踪解决方案并且兼容支持了zipkin。

![image-20210301212424848](D:\我的文件\gitRepository\cloud-image\img\image-20210301212424848.png)



#### 一、搭建链路监控步骤

##### 1、启动zipkin

```shell
cd D:\Java\zipkin-server-2.12.9-exec
java -jar zipkin-server-2.12.9-exec.jar
```

启动成功后，访问http://localhost:9411/zipkin/，正常显示即表示启动成功！

![image-20210301213819626](D:\我的文件\gitRepository\cloud-image\img\image-20210301213819626.png)

##### 2、运行控制台

![image-20210301214019704](D:\我的文件\gitRepository\cloud-image\img\image-20210301214019704.png)

![image-20210301213934749](D:\我的文件\gitRepository\cloud-image\img\image-20210301213934749.png)

> Trace:类似于树结构的Span集合，表示一条调用链路，存在唯一标识；span:表示调用链路来源，通俗的理解span就是一次请求信息

##### 3、修改8081提供者和80服务消费者

###### 1.修改8081

（1）改pom

添加依赖

```xml
<!--包含了sleuth+zipkin-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-zipkin</artifactId>
</dependency>
```

（2）改yml

```yml
spring:
  # 搭建链路监控
  zipkin:
    base-url: http://localhost:9411
  sleuth:
    sampler:
      # 采样值介于0到1之间，1表示全部采集
      probability: 1
```

（3）添加一个基本的控制器

```java
@GetMapping("/payment/zipkin")
public String paymentZipkin() {
    return "hi ,i'am paymentzipkin server fall back，welcome to IT，O(∩_∩)O哈哈~";
}
```

###### 2.修改80

（1）改pom

```xml
<!--包含了sleuth+zipkin-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-zipkin</artifactId>
</dependency>
```

（2）改yml

```yml
spring:
  # 搭建链路监控
  zipkin:
    base-url: http://localhost:9411
  sleuth:
    sampler:
      # 采样值介于0到1之间，1表示全部采集
      probability: 1
```

（3）编写控制器，调用8081的/payment/zipkin请求。

```java
private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE/payment/";   //服务提供者 集群版
@GetMapping("/payment/zipkin")
public String paymentZipkin()
{
    return restTemplate.getForObject(PAYMENT_URL + "zipkin/", String.class);
}
```

###### 3.测试

访问几次http://localhost:8081/payment/zipkin和http://localhost/consumer/payment/zipkin

并登录http://localhost:9411/zipkin/

![image-20210301223831045](D:\我的文件\gitRepository\cloud-image\img\image-20210301223831045.png)

可见，服务名有了注册的服务。

可以点击依赖进行查看微服务之间的关系，以及其他的细节内容。

![image-20210301223949240](D:\我的文件\gitRepository\cloud-image\img\image-20210301223949240.png)





# B、SpringCloud Alibaba

### 一、SpringCloud Alibaba Nacos服务注册和配置中心

#### 1、Nacos简介

Nacos：前四个字母分别为Naming和Configuration的前两个字母，最后的s为Service 

Nacos就是注册中心+配置中心的组合，一个更易于构建云原生应用的动态服务发现，配置管理和服务管理中心

> Nacos = Eureka+Config+Bus

能干嘛？

替代Eureka做服务注册中心，替代Config做服务配置中心。

![image-20210302083906157](D:\我的文件\gitRepository\cloud-image\img\image-20210302083906157.png)

#### 2、安装并运行Nacos

先从官网下载Nacos，nacos-server-1.2.0.zip解压运行bin目录下的startup.cmd即可。

![image-20210302084103107](D:\我的文件\gitRepository\cloud-image\img\image-20210302084103107.png)



启动后登录其前端页面：http://localhost:8848/nacos

![image-20210302084327128](D:\我的文件\gitRepository\cloud-image\img\image-20210302084327128.png)

#### 3、Nacos作为服务注册中心替代Eureka

##### 1、服务提供者cloudalibaba-provider-payment9001创建

（1）新建module

（2）改pom

* 确保父项目中有`<artifactId>spring-cloud-alibaba-dependencies</artifactId>`的依赖。

* 在payment9001中修改自己的pom，引入Nacos

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>springcloud-0219-00</artifactId>
        <groupId>com.ityj.springcloud</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloudalibaba-provider-payment9001</artifactId>

    <dependencies>
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>

        <dependency>
            <groupId>com.ityj.springcloud</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.62</version>
        </dependency>
    </dependencies>


</project>
```

（3）改yml

```yml
server:
  port: 9001

spring:
  application:
    name: nacos-payment-provider
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848 #配置Nacos地址

management:
  endpoints:
    web:
      exposure:
        include: '*'
```

（4）主启动

```java
@SpringBootApplication
@EnableDiscoveryClient
public class ProviderAli9001Starter {
    public static void main(String[] args) {
        SpringApplication.run(ProviderAli9001Starter.class, args);
    }
}
```

（5）业务代码

```java
@RestController
public class PaymentController
{
    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Integer id)
    {
        return "nacos registry, serverPort: "+ serverPort+"\t id"+id;
    }
}
```

（6）测试

启动Nacos服务以及9001

访问http://localhost:8848/nacos，发现9001已经在nacos上注册完毕。

![image-20210302085623683](D:\我的文件\gitRepository\cloud-image\img\image-20210302085623683.png)

调用controller链接：http://localhost:9001/payment/nacos/33

可正常访问。	

##### 2、模仿9001，新建模块9002

只有端口号不同。

新建cloudalibaba-provider-payment9002

> 或者取巧不想新建重复体力劳动，直接在idea中拷贝虚拟端口映射

![image-20210302090231461](D:\我的文件\gitRepository\cloud-image\img\image-20210302090231461.png)

![image-20210302090355754](D:\我的文件\gitRepository\cloud-image\img\image-20210302090355754.png)

保存，启动9011，访问http://localhost:9001/payment/nacos/33和http://localhost:9011/payment/nacos/33都可以正常。

![image-20210302090530921](D:\我的文件\gitRepository\cloud-image\img\image-20210302090530921.png)

但是不推荐使用。

模仿9001创建module9002。用于负载均衡测试。



##### 3、基于Nacos的服务消费者

（1）建module

cloudalibaba-consumer-nacos-order83

（2）改pom

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>springcloud-0219-00</artifactId>
        <groupId>com.ityj.springcloud</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloudalibaba-consumer-nacos-order83</artifactId>

    <dependencies>
        <!--SpringCloud ailibaba nacos -->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
        <dependency>
            <groupId>com.ityj.springcloud</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

</project>
```

nacos支持负载均衡，因为内部集成了ribbon。

![image-20210302091237551](D:\我的文件\gitRepository\cloud-image\img\image-20210302091237551.png)

（3）改yml

```yml
server:
  port: 83
  
spring:
  application:
    name: nacos-order-consumer
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848

service-url: 
  nacos-user-service: http://nacos-payment-provider       # 不是spring的配置，自定义的服务提供者接口uri 
```

（4）启动类

```java
@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerNacosAli83Starter {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerNacosAli83Starter.class, args);

    }
}
```

（5）业务类

作为一个消费端，暂且使用原始的restTemplate来实现和客户端的交互。

此时需要一个配置类来获取restTemplate

```java
@Configuration
public class ApplicationContextConfig {

    // 注册RestTemplate用于服务访问
    @Bean
    @LoadBalanced  // 负载均衡：默认轮询
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
```

业务层就是调用provider的/payment/nacos/{id}请求

```java
@RestController
@Slf4j
public class OrderNacosController {
    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serverURL;

    @GetMapping(value = "/consumer/payment/nacos/{id}")
    public String paymentInfo(@PathVariable("id") Long id) {
        return restTemplate.getForObject(serverURL+"/payment/nacos/"+id,String.class);
    }

}
```



（6）测试

启动9001,9002和83

现在Nacos页面上查看服务注册情况：三个都已经注册成功！

![image-20210302092556161](D:\我的文件\gitRepository\cloud-image\img\image-20210302092556161.png)



访问http://localhost:83/consumer/payment/nacos/33消费者调用情况：

发现可以正常调用，并且实现了负载均衡：轮询

##### 4、各种服务注册对比

![image-20210302092813962](D:\我的文件\gitRepository\cloud-image\img\image-20210302092813962.png)



**Nacos支持AP和CP模式的切换**

A：高可用，一定有返回

C：数据一致性

![image-20210302093350353](D:\我的文件\gitRepository\cloud-image\img\image-20210302093350353.png)

`curl -X PUT '$NACOS_SERVER:8848/nacos/v1/ns/operator/switches?entry=serverMode&value=CP'`



#### 4、Nacos作为配置中心替代Config+Bus

> config+bus的配置中心，需要一个server来获取远程仓库的配置，再将此配置分发到各个微服务，链路较长，而同步比较麻烦。
>
> Nacos的前端页面已经集成了配置中心，可以直接在上面修改，而修改后可以直接获取到。降低耦合

##### 1、搭建nacos的服务

（1）建module

cloudalibaba-config-nacos-client3377

（2）引pom

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>springcloud-0219-00</artifactId>
        <groupId>com.ityj.springcloud</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloudalibaba-config-nacos-client3377</artifactId>

    <dependencies>
        <!--nacos-config-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
        </dependency>
        <!--nacos-discovery-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
        <!--web + actuator-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <!--一般基础配置-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

</project>
```

（3）写yml（2个）

application.yml是获取Nacos配置中心的配置文件内容，而bootstrap.yml是自己个性化的内容。

yml的两个配置，为什么要配置两个？

![](D:\我的文件\gitRepository\cloud-image\img\image-20210302094607625.png)

application.yml

```yml
spring:
  profiles:
    active: dev
```

bootstrap.yml

```yml
server:
  port: 3377

spring:
  application:
    name: nacos-config-client
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848 #服务注册中心地址
      config:
        server-addr: localhost:8848 #配置中心地址
        file-extension: yaml #指定yaml格式的配置

# 读取的就是Nacos配置中心的 nacos-config-client-dev.yaml配置文件
#                           ${spring.application.name}-${spring.profiles.active}.${spring.cloud.nacos.config.file-extension}
#                           如果spring.profiles.active没有配置，就默认  ${spring.application.name}.${spring.cloud.nacos.config.file-extension}
```

（4）启动类

```java
@EnableDiscoveryClient
@SpringBootApplication
public class NacosConfigClient3377Starter {
    public static void main(String[] args) {
        SpringApplication.run(NacosConfigClient3377Starter.class, args);
    }
}
```

（5）controller业务层

```java
@RestController
@RefreshScope
@Slf4j
public class NacoConfigController {
    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/config/info")
    public String getConfigInfo() {
        return configInfo;
    }
}
```

（7）添加Nacos配置

登录http://localhost:8848/nacos，在配置列表里新增nacos-config-client-dev.yaml配置文件，文件名是根据上述两个配置中固定的。

![image-20210302100754240](D:\我的文件\gitRepository\cloud-image\img\image-20210302100754240.png)

![image-20210302100844621](D:\我的文件\gitRepository\cloud-image\img\image-20210302100844621.png)

（7）测试

启动3377服务，访问http://localhost:3377/config/info，看能否正常。

修改Nacos的配置，再次访问http://localhost:3377/config/info，发现已经实时更新。



##### 2、Nacos配置的具体化-分类配置

对于多环境多项目管理，可以通过Nacos简单地实现。

![image-20210302103609376](D:\我的文件\gitRepository\cloud-image\img\image-20210302103609376.png)

###### （1）Data Id

dataId是根据spring.profiles.active的不同来实现区分的：

![image-20210302104149238](D:\我的文件\gitRepository\cloud-image\img\image-20210302104149238.png)

![image-20210302104058466](D:\我的文件\gitRepository\cloud-image\img\image-20210302104058466.png)

修改application.yml的active属性即可

```yml
spring:
  profiles:
    active: dat
```

重启3377，访问http://localhost:3377/config/info，可实现切换。

![image-20210302104535016](D:\我的文件\gitRepository\cloud-image\img\image-20210302104535016.png)

###### （2）group实现分组

默认是 DEFAULT_GROUP

新建一个DAT的group：DAT_GROUP

![image-20210302105449638](D:\我的文件\gitRepository\cloud-image\img\image-20210302105449638.png)

此时修改bootstrap.yml的配置：添加group的属性

```yml
spring:
  application:
    name: nacos-config-client
  cloud:
    nacos:
      config:
        server-addr: localhost:8848 #配置中心地址
        file-extension: yaml #指定yaml格式的配置
        group: DAT_GROUP   # 指定group
```

上一步骤application.yml激活的还是dat

重启访问http://localhost:3377/config/info，可实现切换。

![image-20210302105433051](D:\我的文件\gitRepository\cloud-image\img\image-20210302105433051.png)

###### （3）通过namespace实现多项目的划分

通过namespace可以对不同的项目进行分组，比如ssm-learning和springboot-learning会拥有不同的配置文件，此时就可以通过命名空间来实现项目的划分。

![image-20210302105603006](D:\我的文件\gitRepository\cloud-image\img\image-20210302105603006.png)

![image-20210302105620881](D:\我的文件\gitRepository\cloud-image\img\image-20210302105620881.png)

自动生成了一个命名空间id，这个id需要配置在项目的配置文件中，实现分组查找。

![image-20210302105738663](D:\我的文件\gitRepository\cloud-image\img\image-20210302105738663.png)

多了一个命名空间ssm-learning

在ssm-learning命名空间中新建 nacos-config-client-dat.yaml的配置文件

组改为DAT_GROUP，这样项目中group就不需要修改了，而正常生产中也需要制定相应的group，避免default

![image-20210302110002354](D:\我的文件\gitRepository\cloud-image\img\image-20210302110002354.png)

修改yml

```yml
spring:
  application:
    name: nacos-config-client
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848 #服务注册中心地址
      config:
        server-addr: localhost:8848 #配置中心地址
        file-extension: yaml #指定yaml格式的配置
        group: DAT_GROUP
        namespace: b416401e-3db7-4920-b202-4f9829504695
```

重启，调用http://localhost:3377/config/info，可正常访问。

![image-20210302110233932](D:\我的文件\gitRepository\cloud-image\img\image-20210302110233932.png)

![image-20210302110305326](D:\我的文件\gitRepository\cloud-image\img\image-20210302110305326.png)



hostname -i

#### 5、Nacos集群和持久化配置（重要）



### 二、Sentinel

>  一句话解释，之前我们讲解过的Hystrix

#### 1、安装Sentinel控制台

其实就是有一个jar包，运行即可，默认端口为8080。

![image-20210302131312295](D:\我的文件\gitRepository\cloud-image\img\image-20210302131312295.png)



#### 2、创建项目，入驻Sentinel

（1）建module

cloudalibaba-sentinel-service8401

（2）改pom

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>springcloud-0219-00</artifactId>
        <groupId>com.ityj.springcloud</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloudalibaba-sentinel-service8401</artifactId>

    <dependencies>
        <dependency>
            <groupId>com.ityj.springcloud</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.version}</version>
        </dependency>

        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>

        <dependency>
            <groupId>com.alibaba.csp</groupId>
            <artifactId>sentinel-datasource-nacos</artifactId>
        </dependency>

        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
            <version>4.6.3</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

    </dependencies>

</project>
```

（3）写yml

```yml
server:
  port: 8401

spring:
  application:
    name: cloudalibaba-sentinel-service
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848
    sentinel:
      transport:
        dashboard: localhost:8080
        port: 8719  #默认8719，假如被占用了会自动从8719开始依次+1扫描。直至找到未被占用的端口

management:
  endpoints:
    web:
      exposure:
        include: '*'
```

（4）主启动

```java
@SpringBootApplication
@EnableDiscoveryClient
public class Sentinel8401Starter {
    public static void main(String[] args) {
        SpringApplication.run(Sentinel8401Starter.class, args);

    }
}
```

（5）业务方法

```java
@RestController
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA() {
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB() {

        return "------testB";
    }
}
```



（6）测试

启动Nacos，再启动Sentinel，最后启动8401

启动成功后，在Nacos服务注册中心可以看到8401的服务；

而sentinel的控制台并没有发现有信息：主要是sentinel是属于懒加载，只有服务访问了，才可以看到。

访问http://localhost:8401/testA和http://localhost:8401/testB后，可以看到控制台有对这两个接口的监控详细信息。

![image-20210302132619819](D:\我的文件\gitRepository\cloud-image\img\image-20210302132619819.png)





#### 3、流控模式

##### （1）QPS直接失败

QPS：每秒的访问量

![image-20210302143549378](D:\我的文件\gitRepository\cloud-image\img\image-20210302143549378.png)

表示每秒仅支持访问一次，超过一次将会放回错误信息！

![image-20210302143650443](D:\我的文件\gitRepository\cloud-image\img\image-20210302143650443.png)

##### （2）线程数直接失败

修改/testB的程序，休眠1分钟

```java
@GetMapping("/testB")
public String testB() {
    
    try {
        TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    return "------testB";
}
```

在Sentinel控制面板中进行配置：

![image-20210302143854604](D:\我的文件\gitRepository\cloud-image\img\image-20210302143854604.png)

线程数大于1将会直接报错。

此时调用多个线程访问，即多次F5刷新，此时会返回错误信息：

![image-20210302143943601](D:\我的文件\gitRepository\cloud-image\img\image-20210302143943601.png)

##### （3）关联模式

A关联B，如果B的QPS查过阈值，A将会限流报错。

订单模块关联支付模块，如果支付模块访问量过大，订单模块需要限流。

程序中去掉testB的休眠。

```java
@GetMapping("/testB")
public String testB() {

    /*try {
        TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }*/
    return "------testB";
}
```

配置sentinel

![image-20210302144811329](D:\我的文件\gitRepository\cloud-image\img\image-20210302144811329.png)

测试：

使用postman访问/testB，模拟高并发

![image-20210302145055760](D:\我的文件\gitRepository\cloud-image\img\image-20210302145055760.png)

![image-20210302145127906](D:\我的文件\gitRepository\cloud-image\img\image-20210302145127906.png)



先保证testA可以正常运行，在postman中调用testB的接口

同时调用A，看A的运行情况。

![image-20210302145306875](D:\我的文件\gitRepository\cloud-image\img\image-20210302145306875.png)

A直接被限流，当B执行完毕（没有高并发访问）时，A可以再次正常运行。

##### （4）流控效果-Warm Up

![image-20210302150430692](D:\我的文件\gitRepository\cloud-image\img\image-20210302150430692.png)

静置一段时间后初次访问开始计算

前5秒为预热时间，这段时间的请求阈值是    10  /   3   =  3；其中10是设置的，3是系统默认的coldFactor

超过5秒，回归正常的阈值设置10；查过10的QPS将会限流

![image-20210302151243157](D:\我的文件\gitRepository\cloud-image\img\image-20210302151243157.png)





![image-20210302151452841](D:\我的文件\gitRepository\cloud-image\img\image-20210302151452841.png)

**应用场景：**

![image-20210303082650279](D:\我的文件\gitRepository\cloud-image\img\image-20210303082650279.png)

#### 4、Sentinel降级

![image-20210303090210435](D:\我的文件\gitRepository\cloud-image\img\image-20210303090210435.png)

##### （1）RT

![image-20210303090307739](D:\我的文件\gitRepository\cloud-image\img\image-20210303090307739.png)

![image-20210303090314496](D:\我的文件\gitRepository\cloud-image\img\image-20210303090314496.png)

添加测试方法：

```java
@GetMapping("/testD")
public String testD() {
    try { 
        TimeUnit.SECONDS.sleep(1); 
    } catch (InterruptedException e) {
        e.printStackTrace(); 
    }
    log.info("testD 测试RT");

    return "------testD";
}
```

![image-20210303091501582](D:\我的文件\gitRepository\cloud-image\img\image-20210303091501582.png)

> RT是指平均响应时间，如果1秒内持续进入了5个及以上的线程请求，并且平均响应时间大于0.2秒，那么接下来的5秒钟，服务将会熔断，不可用。5秒后恢复正常。



使用Jmeter压测，1秒钟5次请求，并且一直执行，而testD方法的执行时间大于200ms，所以会进行熔断降级处理。

![image-20210303092106153](C:/Users/ayinj/AppData/Roaming/Typora/typora-user-images/image-20210303092106153.png)



![image-20210303092139751](D:\我的文件\gitRepository\cloud-image\img\image-20210303092139751.png)

![image-20210303093906803](D:\我的文件\gitRepository\cloud-image\img\image-20210303093906803.png)

##### （2）异常比例

![image-20210303094054649](C:/Users/ayinj/AppData/Roaming/Typora/typora-user-images/image-20210303094054649.png)

![image-20210303094105415](D:\我的文件\gitRepository\cloud-image\img\image-20210303094105415.png)

![image-20210303094354662](D:\我的文件\gitRepository\cloud-image\img\image-20210303094354662.png)

##### （3）异常数

![image-20210303094319222](D:\我的文件\gitRepository\cloud-image\img\image-20210303094319222.png)

![image-20210303094323432](D:\我的文件\gitRepository\cloud-image\img\image-20210303094323432.png)

![image-20210303094341479](D:\我的文件\gitRepository\cloud-image\img\image-20210303094341479.png)

#### 5、热点key降级

> 热点限流就是通过一个自定义的兜底方法，实现对于异常的处理。

之前配置的sentinel降级：如果程序出错，将会返回系统默认的规则。

这里的兜底就是Hystrix中的兜底方法（类似）。

注解这里是@SentinelResource，替代了豪猪哥的@HystrixCommand

##### 1、程序编写实现热点限流

（1）写业务

```java
@GetMapping("/testHotKey")
@SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                         @RequestParam(value = "p2",required = false) String p2) {
    int age = 1/0;
    return "------testHotKey";
}

//兜底方法
public String deal_testHotKey (String p1, String p2, BlockException exception){
    return "------deal_testHotKey,o(╥﹏╥)o";
}
```

（2）在sentinel配置页面配置

在访问带有p1参数的请求：http://localhost:8401/testHotKey?p1=22，如果一分钟请求数大于1，在接下来的统计窗口1秒中，会走兜底方法，而不是Whitelabel Error Page

![image-20210303100231518](D:\我的文件\gitRepository\cloud-image\img\image-20210303100231518.png)



##### 2、热点限流设置白名单

如果说http://localhost:8401/testHotKey?p1=666的请求p1=666为白名单，就是说访问http://localhost:8401/testHotKey?p1=666时，会有一个新的自定义的阈值条件，达到此条件会走兜底方法。

配置即可：

![image-20210303101113651](D:\我的文件\gitRepository\cloud-image\img\image-20210303101113651.png)

测试http://localhost:8401/testHotKey?p1=666和http://localhost:8401/testHotKey?p1=222

发现666不会走兜底策略（访问没到200阈值）

而p1=222直接很快进入兜底方法。

#### 6、系统规则

作为系统的总把关，例如网红小吃店，如果只能容纳50人，到50后就拒绝进入了。

![image-20210303103254176](D:\我的文件\gitRepository\cloud-image\img\image-20210303103254176.png)

http://localhost:8401/testHotKey?p1=666

![image-20210303103306004](D:\我的文件\gitRepository\cloud-image\img\image-20210303103306004.png)

最好不要使用。



#### 7、@SentinelResource

##### 1、按资源名称限流+后续处理

blockHandler 只针对sentinel控制台的配置的规则，比如限流这些条件达到了，会走handleException方法。

如果java报错，还是把错误信息给显示在了页面上，不和谐

```java
@RestController
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public CommonResult byResource() {
        return new CommonResult(200, "按资源名称限流测试OK", new Payment(2020L, "serial001"));
    }

    public CommonResult handleException(BlockException exception) {
        return new CommonResult(444, exception.getClass().getCanonicalName() + "\t 服务不可用");
    }
}
```

http://localhost:8401/byResource



![image-20210303105237144](D:\我的文件\gitRepository\cloud-image\img\image-20210303105237144.png)

![image-20210303105347348](D:\我的文件\gitRepository\cloud-image\img\image-20210303105347348.png)

多次访问

![image-20210303105254463](D:\我的文件\gitRepository\cloud-image\img\image-20210303105254463.png)



**问题：**

关闭8401服务，sentinel工作台上没有了相关的服务，请求也变为访问错误

![image-20210303105513412](D:\我的文件\gitRepository\cloud-image\img\image-20210303105513412.png)

![image-20210303105546371](D:\我的文件\gitRepository\cloud-image\img\image-20210303105546371.png)

说明注册没有持久化。

##### 2、按照Url地址限流+后续处理

```java
@GetMapping("/rateLimit/byUrl")
@SentinelResource(value = "byUrl")
public CommonResult byUrl()
{
    return new CommonResult(200,"按url限流测试OK",new Payment(2020L,"serial002"));
}
```



![image-20210303105829319](D:\我的文件\gitRepository\cloud-image\img\image-20210303105829319.png)

这个没有编写对应的兜底策略，返回的是系统默认提示。

http://localhost:8401/rateLimit/byUrl

![](D:\我的文件\gitRepository\cloud-image\img\image-20210303110004030.png)

**问题：**

![image-20210303105915372](D:\我的文件\gitRepository\cloud-image\img\image-20210303105915372.png)



##### 3、客户自定义限流处理逻辑

顶一一个全局的兜底策略，出问题走此逻辑。

（1）自定义的全局兜底策略

```java
public class CustomerBlockHandler {

    public static CommonResult handleException(BlockException exception) {
        return new CommonResult(2021, "自定义限流处理信息....CustomerBlockHandler");
    }
}
```

（2）普通的业务类

配置了@SentinelResource，以及其指向的兜底策略blockHandlerClass + blockHandler

```java
@GetMapping("/rateLimit/customerBlockHandler")
@SentinelResource(value = "customerBlockHandler",
        blockHandlerClass = CustomerBlockHandler.class,
        blockHandler = "handleException")
public CommonResult customerBlockHandler() {
    return new CommonResult(200, "按客戶自定义", new Payment(2020L, "serial003"));
}
```

（3）测试

![image-20210303111316920](D:\我的文件\gitRepository\cloud-image\img\image-20210303111316920.png)

http://localhost:8401/rateLimit/customerBlockHandler

![image-20210303111854207](D:\我的文件\gitRepository\cloud-image\img\image-20210303111854207.png)

多次访问，可见：走了自定义的全局处理策略。



### 三、Seata

![image-20210303152332757](D:\我的文件\gitRepository\cloud-image\img\image-20210303152332757.png)

**一次业务操作需要跨多个数据源或需要跨多个系统进行远程调用，就会产生分布式事务问题。**

**Seata是一款开源的分布式事务解决方案，致力于在微服务架构下提供高性能和简单易用的分布式事务服务。**

一个典型的分布式事务过程是由**ID+三组件模型**构成；

* Transaction ID（全局唯一的事务ID）
* Transaction Coordinator(TC)：事务协调器，维护全局事务的运行状态，负责协调并驱动全局事务的提交或回滚;
* Transaction  Manager(TM) ：控制全局事务的边界，负责开启一个全局事务，并最终发起全局提交或全局回滚的决议;
* Resource Manager(RM) ：控制分支事务，负责分支注册，状态汇报，并接收事务协调器的指令，驱动分支（本地）事务的提交和回滚；

处理过程：

![image-20210303152643881](D:\我的文件\gitRepository\cloud-image\img\image-20210303152643881.png)

![image-20210303152647818](D:\我的文件\gitRepository\cloud-image\img\image-20210303152647818.png)

#### 1、订单/库存/账户业务微服务准备

##### 业务需求：

> 下订单->减库存->扣余额->改（订单）状态

##### 1、新建订单Order-Module

seata-order-service2001模块





# C、开发中遇到的BUG

## 1、application.yml的样式未改变。

(1) 父项目是一个简单的maven项目，不需要引入springboot-parent. 之前直接引入了parent，导致子模块的jar包不能正常引入。==子模块pom文件没有正常引入==

(2) 配置文件名字错误：写成了**appliaction.yml**

## 2、jar包冲突

> 根目录下莫名多了一个lib包，里面有一些jar,导致和maven仓库里的jar包冲突掉，项目启动失败。删除Lib文件夹。重启解决。

## 3、无空参构造导致报错

> com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `com.ityj.springcloud.entity.model.CommonResult` (no Creators, like default constructor, exist): cannot deserialize from Object value (no delegate- or property-based Creator)

## 4、微服务Devtool热部署无法生效

> 原因是把spring-boot-devtools依赖放到commons模块。但是加了一个 <optional>true</optional>去掉就可以了。

==<optional>true</optional>表示两个项目之间依赖不传递；不设置optional或者optional是false，表示传递依赖。==

## 5、在zookeeper作为服务注册中心时，servicename大小写必须和配置文件中保持一致

```shell
{"code":-1,"msg":"java.lang.IllegalStateException: No instances available for CLOUD-PAYMENT-SERVICE","data":null}
```

> org.springframework.cloud.client.discovery.DiscoveryClient#getInstances

对于zookeeper，大小写敏感

```java
public ZookeeperDependency getDependencyForAlias(final String alias) {
    for (Map.Entry<String, ZookeeperDependency> zookeeperDependencyEntry : this.dependencies
         .entrySet()) {
        if (zookeeperDependencyEntry.getKey().equals(alias)) {
            return zookeeperDependencyEntry.getValue();
        }
    }
    return null;
}
```

对于eureka，大小写不敏感

```java
public List<InstanceInfo> getInstancesByVirtualHostName(String virtualHostName) {
    return Optional.ofNullable(this.virtualHostNameAppMap.get(virtualHostName.toUpperCase(Locale.ROOT)))
        .map(VipIndexSupport::getVipList)
        .map(AtomicReference::get)
        .orElseGet(Collections::emptyList); 
}
```



## 6、commons模块里引入了actuator的依赖，但是子项目无法访问/actuator/health。 一直404

原因是父项目的依赖添加错误。没有添加成starter...

原来：

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-actuator</artifactId>
</dependency>
```



==正确的是：==

```xml
 <dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```



## 7、hystrixDashboard无法正常监控其他应用

![image-20220622222254036](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220622222254.png)

接口是正常的

![image-20220622222323755](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220622222323.png)



解决方法：

在90001dashboard中添加配置：

```yml
hystrix:
  dashboard:
    proxy-stream-allow-list: "*"
```

重启即可。





## 8、java.lang.IllegalArgumentException: Source must not be null

原来的代码是：

```java
@Override
public PaymentDTO getPaymentById(Long id) {
    PaymentPO paymentPO = baseMapper.selectById(id);
    Assert.notNull(paymentPO, "Could not find related info.");
    BeanUtils.copyProperties(paymentPO, paymentDTO);
    return paymentDTO;
}
```

报错原因是paymentPO为null。导致BeanUtils.copyProperties出错。添加判断即可

```java
Assert.notNull(paymentPO, "Could not find related info.");
```



