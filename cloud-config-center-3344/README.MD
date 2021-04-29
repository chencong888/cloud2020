##加入了bus消息总线后,修改配置文件，客户端动态获取##
1.github上面修改了springcloud-config文件下config-xxx.yml文件内容，只需要往配置中心3344发送post请求
客户端会自动刷新配置文件

2.需要运维人员发送post请求到3344(必须是post请求)
```
curl -X POST "http://localhost:3344/actuator/bus-refresh"
```
