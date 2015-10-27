package io.bluerain.globr.enties.bean.io.bluerain.globr.enties;

/**
 * Created by hewtai_mew on 15-8-24.
 * 单个结果条目
 */
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

    @Override
    public String toString() {
        return "SingleResult{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", content='" + content + '\'' +
                ", domain='" + domain + '\'' +
                '}';
    }
}
