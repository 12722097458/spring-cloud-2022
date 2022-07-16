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

http://192.168.137.110:8500/
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



# 6、Nacos

> Dynamic *Na*ming and *Co*nfiguration *S*ervice
>
> 服务发现、服务配置 = Eureka + Config&Bus

## 1、Windows单机版

```shell
cd D:\Java\cloud-alibaba\nacos-server-2.1.0\nacos\bin
startup.cmd -m standalone    # 以单机模式启动

http://localhost:8848/nacos
```

## 2、Linux集群版

```shell
# 启动nacos
sh /app/tools/springcloud/nacos-scripts/nacos-cluster-startup.sh
# 启动nginx
/usr/local/nginx/sbin/nginx  -c /app/tools/springcloud/nginx-1.18.0/conf/nginx.conf

#linux配置的端口1111
http://192.168.137.110:1111/nacos
```



# 7、Sentinel

```shell
java -jar D:\Java\cloud-alibaba\sentinel-dashboard-1.8.4.jar

http://localhost:8080/    # sentinel/sentinel
```



# 8、jemter压测工具

```shell
D:\Java\tools-windows\apache-jmeter-5.4.1\bin\jmeter.sh
```

