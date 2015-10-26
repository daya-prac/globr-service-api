GloBr Api
====

####提供Google API的服务端应用

```markdown
读法:GloBr -> GloB(e)r
```

被额外依赖项目:(需要自行导入项目或者添加本地jar)

[bluerain-commons](https://github.com/HentaiMew/bluerain-commons) |
[rebr-client](https://github.com/HentaiMew/rebr-client.git)

如果要导入项目的话，需要包含的是以下本地依赖项:
```xml
<dependency>
    <groupId>io.bluerain.utils</groupId>
    <artifactId>commons</artifactId>
    <version>1.0.1-SNAPSHOT</version>
</dependency>
<dependency>
    <groupId>io.bluerain.http</groupId>
    <artifactId>rebr-client</artifactId>
    <version>0.1.0-SNAPSHOT</version>
</dependency>
```
这里下载WAR文件以后可以直接丢进Tomcat/Jetty中运行。

访问 http://yourdomain/globr/

进入Swagger文档界面，在线测试以及参数/返回结构阅读等。
