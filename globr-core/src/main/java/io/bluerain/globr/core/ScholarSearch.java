package io.bluerain.globr.core;

import io.bluerain.globr.core.utils.GooglePagin;
import io.bluerain.globr.enties.bean.io.bluerain.globr.enties.SearchResult;
import io.bluerain.globr.enties.bean.io.bluerain.globr.enties.SingleResult;
import io.bluerain.http.core.Language;
import io.bluerain.http.core.LinkedHttpMap;
import io.bluerain.http.core.UserAgent;
import io.bluerain.http.response.HttpResponse;
import io.bluerain.http.rest.handler.ResultHandler;
import io.bluerain.http.rest.method.HttpGet;
import io.bluerain.proxy.Socks5;
import io.bluerain.regex.SampleMatcher;
import io.bluerain.type.RefInt;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by hentai_mew on 15-10-26.
 * 学术搜索数据处理核心类
 */
public class ScholarSearch {

    public static SearchResult by(String keyword, int pagNum) {
        final SearchResult result = new SearchResult();
        RefInt refInt = RefInt.newInstance(pagNum);
        String url = "https://scholar.google.com/scholar";
        LinkedHttpMap ps =
                LinkedHttpMap.builder()
                        .put("start", GooglePagin.getStart(refInt))
                        .put("hl", "zh-CN")
                        .put("btnG", "")
                        .put("q", keyword);
        String body = HttpGet.create(url)
                .queryParams(ps)
                .language(Language.zh_CN)
                .userAgent(UserAgent.WP_75)
                .exe()
                .handle(new ResultHandler() {
                    @Override
                    public void success(HttpResponse response, String body) {
                        Document dom = response.readDom();
                        SingleResult sr = null;
                        //
                        for (Element elem : dom.select("div.gs_r")) {
                            sr = new SingleResult();
                            //分两部分解析
                            //1. 头部（包含标题和链接）
                            Elements tes = elem.select("h3.gs_rt");
                            if (tes.size() <= 0)
                                continue;
                            Element titleDom = tes.get(0);
                            sr.setTitle(titleDom.text());
                            sr.setLink(titleDom.child(0).attr("href"));
                            //2. 身体（包含内容）
                            Elements ces = elem.select(".gs_rs");
                            if (ces.size() <= 0) //如果不存在内容.gs_rs样式则跳过
                                continue;
                            Element contentDom = ces.get(0);
                            sr.setContent(contentDom.html());
                            //3. 从链接中提取域名
                            sr.setDomain(SampleMatcher.marchDoaminFromUrl(sr.getLink()));
                            result.getList().add(sr);
                        }
                    }

                    @Override
                    public void error(HttpResponse response, int code, String body) {

                    }
                }).readBody();
        result.setCurPage(refInt.getValue());
        SingleResult sr = new SingleResult();
        sr.setTitle("输出HTML");
        sr.setContent(body);
        result.getList().add(sr);
        return result;
    }
}
