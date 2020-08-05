# Java8-NewFeatures

java 练习的小例子


springcloud01
1、启动eureka服务
http://localhost:8081/

2、启动server-a服务 8083端口;8084端口
http://localhost:8083/testA
http://localhost:8084/testA

3、启动server-b服务，测试feign调用
http://localhost:8082/call

4、启动server-zuui服务，测试网关
http://localhost:8085/service-b/call

5、关闭server-a服务，测试Hystrix
http://localhost:8085/service-b/call
