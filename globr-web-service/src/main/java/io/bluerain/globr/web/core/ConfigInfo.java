package io.bluerain.globr.web.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by hentai_mew on 15-10-27.
 * 配置信息注入类
 */
@Component("configInfo")
public class ConfigInfo {

    @Value("${baseAppUrl}")
    private String baseAppUrl;

    @Value("${localProxy}")
    private Boolean localProxy;

    private String docsUrl;

    public String getBaseAppUrl() {
        return baseAppUrl;
    }

    public void setBaseAppUrl(String baseAppUrl) {
        this.baseAppUrl = baseAppUrl;
    }

    public Boolean getLocalProxy() {
        return localProxy;
    }

    public void setLocalProxy(Boolean localProxy) {
        this.localProxy = localProxy;
    }

    public void setDocsUrl(String docsUrl) {
        this.docsUrl = docsUrl;
    }

    public String getDocsUrl() {
        return getBaseAppUrl() + "/api-docs";
    }
}
