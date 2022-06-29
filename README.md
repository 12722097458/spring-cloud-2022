# spring-cloud-2022
Learning Spring Cloud 

项目依赖：

# 1、Mysql

```shell
需要启动：Linux(192.168.137.110):root/root，mysql是直接安装在centos上，开机自启的。
```



# 2、zookeeper

```shell
通过docker安装并启动。
已经有了name=zookeeper-standalone的应用，直接启动即可
docker start zookeeper-standalone
```



# 3、consul

本机IP：192.168.1.8

```shell
通过docker安装并启动。
已经有了name=consul-dev的应用，直接启动即可
docker start consul-dev
```



# 4、rabbitMQ

Windows：D:\Java\RabbitMQ\rabbitmq-server-3.7.15

```shell
直接双击start启动即可。默认UI--> http://localhost:15672/直接双击start启动即可。
server默认端口5672
```

![image-20220629234453624](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220629234453.png)



# 5、Sleuth & Zipkin

Sleuth通过Zipkin提供的jar包可以实现链路追踪

```shell
cd D:\Java\zipkin-server-2.12.9-exec
java -jar zipkin-server-2.12.9-exec.jar
默认URL： http://localhost:9411/zipkin/
```





