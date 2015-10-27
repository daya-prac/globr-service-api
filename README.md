GloBr Api
====

####GloBr是提供Google检索数据的服务端API应用

主要包括 谷歌搜索和谷歌学术搜索

```markdown
读法:GloBr -> GloB(e)r
```

被额外依赖项目:(需要自行导入项目或者添加本地jar)

[bluerain-commons](https://github.com/HentaiMew/bluerain-commons) |
[rebr-client](https://github.com/HentaiMew/rebr-client.git)


[这里下载WAR文件](http://7xl780.com1.z0.glb.clouddn.com/apps/globr/last/globr.war)以后可以直接丢进Tomcat/Jetty或者其他Servlet容器中运行。

##### 部署步骤：

1. 域名绑定：修改globr.war中的/WEB-INF/classes/conf.properties文件
````properties
# 应用访问路径(不需要后缀"/")，如果globr直接绑定了域名，则直接填写域名：http://www.xxx.com
# 本地开发一般是：
#baseAppUrl=http://localhost:8080/globr
# 官方部署上线的应用域名
baseAppUrl=http://globr-api.bluerain.io
# 是否开启本地代理（适用于本地开发以及国内服务器部署）
#localProxy=true
localProxy=false
````

2.启动容器，访问绑定好的域名，例如globr官方的:http://globr-api.bluerain.io/index

3.在首页的SwaggerAPI文档界面，验证在线测试以及参数/返回结构阅读等是否正常工作。

##### 客户端开发的一些描述：

负责储存结果的JavaBean结构:

1.SearchResult
````java
public class SearchResult {

    /**
     * 当前页码
     */
    private Integer curPage;
    /**
     * 结果列表
     */
    private List<SingleResult> list = new ArrayList<>();
    /**
     * 有联系的关键字列表
     */
    private List<String> relatedKeys = new ArrayList<>();


    public Integer getCurPage() {
        return curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public List<String> getRelatedKeys() {
        return relatedKeys;
    }

    public void setRelatedKeys(List<String> relatedKeys) {
        this.relatedKeys = relatedKeys;
    }

    public List<SingleResult> getList() {
        return list;
    }

    public void setList(List<SingleResult> list) {
        this.list = list;
    }
}
````
2.SingleResult
````java
public class SingleResult {

    /**
     * 结果标题
     */
    private String title;
    /**
     * 结果链接
     */
    private String link;
    /**
     * 结果内容
     */
    private String content;
    /**
     * 结果域名
     */
    private String domain;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
````

#### 官方API简述(可以自行去：http://globr-api.bluerain.io/index 测试)

NAME     |  URL
-------  | -----------------------------------------------
网页搜索 | http://globr-api.bluerain.io/api/search    
学术搜索 | http://globr-api.bluerain.io/api/search/scholar


参数  | 命名 | 详细信息
----- | ---- | ------------
请求方式 | GET | 全部都是GET请求方方式
页码  | pagNum | 页码，默认:1；为负数或者为0时也表示1
关键字  | keyword | 搜索关键字

##### 响应结构和说明参见上述JavaBean

下述是JSON结构示例：
````JSON
{
  "curPage": 1,
  "list": [
    {
      "title": "<b>呵呵哒</b>是什么意思_拜仁慕尼黑吧_百度贴吧",
      "link": "http://tieba.baidu.com/p/3336262950",
      "content": "<b>呵呵哒</b>是什么意思 .... <b>呵呵哒</b>高冷萌，和么么哒反义词 ... 呵呵是鄙视加去死的意思~~<br> <b>呵呵哒</b>简单粗暴点来说是你麻痹的意思~~因为你麻痹是骂人的就用<b>呵呵哒</b>来代替&nbsp;...",
      "domain": "tieba.baidu.com"
    }
  ],
  "relatedKeys": [
    "我是关键字1",
  ]
}
````
