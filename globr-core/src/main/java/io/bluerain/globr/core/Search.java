package io.bluerain.globr.core;

import io.bluerain.aclient.attach.IndieParam;
import io.bluerain.aclient.core.Response;
import io.bluerain.aclient.core.Result;
import io.bluerain.aclient.core.method.HttpGet;
import io.bluerain.aclient.ua.UA;
import io.bluerain.core.Obj;
import io.bluerain.core.Str;
import io.bluerain.globr.enties.bean.io.bluerain.globr.enties.SearchResult;
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

    public static List<SearchResult> by(String keyword, int pagNum) {
        Search search = new Search();
        return search.byKeyword(keyword, pagNum);
    }

    public List<SearchResult> byKeyword(String keyword, int pagNum) {
        int start = getStart(pagNum);
        final List<SearchResult> srs = new ArrayList<>();
        System.setProperty("proxySet", "true");
        System.setProperty("socksProxyHost", "127.0.0.1");
        System.setProperty("socksProxyPort", "1080");

        String url = "https://www.google.com/search?" +
                "q=" + keyword +
                "&safe=off&prmd=ivns" +
                "&start=" + start +
                "&gws_rd=cr";
        HttpGet get = HttpGet.create(url);
        IndieParam ps = IndieParam.builder();
        get
                .userAgent(UA.WindowsPhone7_5)
                .param(ps)
                .request()
                .result(new Result() {
                    @Override
                    public void success(String body, Response response) {
                        Document doc = response.readDom();
                        Elements rs = doc.getElementsByClass("g");
                        SearchResult sr = null;
                        for (Element r : rs) {
                            sr = new SearchResult();
                            Element titleDom = r.getElementsByTag("h3").get(0).getElementsByTag("a").get(0);
                            Elements contentTemp = r.getElementsByClass("st");
                            if (!Obj.notNullOrEmpty(contentTemp)) //图片结果[略过]
                                continue;
                            Element contentDom = contentTemp.get(0);
                            sr.setTitle(titleDom.html());
                            sr.setLink(getRealLink(titleDom.attr("href")));
                            sr.setContent(contentDom.html());
                            srs.add(sr);
                        }
                    }

                    @Override
                    public void error(String body, javax.ws.rs.core.Response response) {
                        System.out.printf(body);
                    }
                });
        return srs;
    }

    private int getStart(int pagNum) {
        int start;
        if (pagNum <= 1)
            pagNum = 1;
        if (pagNum == 1)
            start = 0;
        else
            start = (pagNum - 1) * 10;
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
