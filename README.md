# swaggercxf
Swagger with Apache CXF

Swaggercxf是对Dropwizard的模仿。It is one copy of Dropwizard.

使用CXF替换了Dropwizard中的Jersey。But replace Jersey with CXF.

##工程依赖(Dependence)：

```
   Dropwizard
   Jetty
   Freemarker
   Swagger
   Apache CXF
```   
   
##你可以：

###复制

  Clone Swaggercxf： git clone https://github.com/neticeydh/swaggercxf.git

###运行

```
  执行以下命令：
  mvn exec:exec
  
  浏览器内访问：
  http://localhost:8080/app/swagger
  
```  

###增加自己的接口定义（register resource）

   请参考 `com.ydh935.swaggercxf.App` Main。
   
##License

MIT License

