package io.bluerain.globr.core;

import io.bluerain.core.Obj;
import io.bluerain.core.Str;
import io.bluerain.globr.enties.bean.io.bluerain.globr.enties.SearchResult;
import io.bluerain.globr.enties.bean.io.bluerain.globr.enties.SingleResult;
import io.bluerain.http.core.UserAgent;
import io.bluerain.http.response.HttpResponse;
import io.bluerain.http.rest.handler.ResultHandler;
import io.bluerain.http.rest.method.HttpGet;
import io.bluerain.type.RefInt;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by foredawn on 15-8-22.
 * 抓取谷歌数据的核心类
 */
public class Search {

    public static SearchResult by(String keyword, int pagNum) {
        Search search = new Search();
        return search.byKeyword(keyword, pagNum);
    }

    public SearchResult byKeyword(String keyword, Integer pagNum) {
        RefInt pn = RefInt.newInstance(pagNum);
        int start = getStart(pn);

        //创建返回结果Bean结构
        final SearchResult result = new SearchResult();
        final List<SingleResult> srs = new ArrayList<>();
        result.setList(srs);
        //是否开启代理(本地开发)
//        startProxy();

        String url = "https://www.google.com/search?" +
                "q=" + keyword +
                "&safe=off&prmd=ivns" +
                "&start=" + start +
                "&gws_rd=cr" +
                "&hl=zh_CN";
        HttpGet.create(url)
                .userAgent(UserAgent.WP_75)
                .exe()
                .handle(new ResultHandler() {
                    @Override
                    public void success(HttpResponse response, String body) {
                        Document doc = response.readDom();
                        Elements rs = doc.getElementsByClass("g");
                        SingleResult sr = null;
                        for (Element r : rs) { //获取条目列表
                            sr = new SingleResult();
                            Elements tempH3Dom, tempADom;
                            if ((tempH3Dom = r.getElementsByTag("h3")).size() == 0 || (tempADom = tempH3Dom.get(0).getElementsByTag("a")).size() == 0)
                                continue;//如果出现非标准结构[略过]
                            Element titleDom = tempADom.get(0);
                            Elements contentTemp = r.getElementsByClass("st");
                            if (!Obj.notNullOrEmpty(contentTemp)) //图片结果[略过]
                                continue;
                            Element contentDom = contentTemp.get(0);
                            sr.setTitle(titleDom.html());
                            sr.setLink(getRealLink(titleDom.attr("href")));
                            sr.setContent(contentDom.html());
                            if (sr.getLink() == null)
                                continue;
                            sr.setDomain(Str.match(sr.getLink(), "https?://([^/]+)", 1));
                            srs.add(sr);
                        }
                        //获取相关关键字
                        Elements relatedKeys = doc.select("td[valign=top] > ._Bmc");
                        if (Obj.notNullOrEmpty(relatedKeys)) {
                            int index = 1;
                            for (Element keyStr : relatedKeys) {
                                if (index != relatedKeys.size())
                                    result.getRelatedKeys().add(keyStr.text());
                                index++;
                            }
                        }
                    }

                    @Override
                    public void error(HttpResponse response, int code, String body) {

                    }
                });
        result.setCurPage(pn.getValue());
        return result;
    }

    private void startProxy() {
        System.setProperty("proxySet", "true");
        System.setProperty("socksProxyHost", "127.0.0.1");
        System.setProperty("socksProxyPort", "1080");
    }

    private int getStart(RefInt pn) {
        int pagNum = pn.getValue();
        int start;
        if (pagNum <= 1)
            pagNum = 1;
        if (pagNum == 1)
            start = 0;
        else
            start = (pagNum - 1) * 10;
        pn.setValue(pagNum);
        return start;
    }

    private String getRealLink(String href) {

        try {
            href = URLDecoder.decode(href, "UTF-8");
            //匹配真实url
            href = Str.match(href, "q=((.(?!&sa))*.)", 1);

            //截取encoder之后的参数
            if (!Str.notNullOrEmpty(href)) { //如果匹配为空
            } else {
                String p = URLEncoder.encode("?", "UTF-8");
                if (href.contains(p)) {
                    String ps = href.substring(href.indexOf(p), href.length());
                    href = href.substring(0, href.indexOf(p)) + URLDecoder.decode(ps, "UTF-8");
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return href;

    }
}
