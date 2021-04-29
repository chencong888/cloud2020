## github上面修改了springcloud-config文件下config-xxx.yml文件内容，configserver能获取到，config-client获取不到##
1.需要再config-client项目bootstrap.yml文件中配置
```
#暴露端点信息
management:
  endpoints:
    web:
      exposure:
        include: "*"
```
2.需要在业务类加上@RefreshScope注解

3.需要运维人员发送post请求刷新3355(必须是post请求)
```
curl -X POST "http://localhost:3355/actuator/refresh"
```
