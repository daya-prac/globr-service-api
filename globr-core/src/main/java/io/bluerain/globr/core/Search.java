package io.bluerain.globr.core;

import io.bluerain.aclient.attach.IndieParam;
import io.bluerain.aclient.core.Response;
import io.bluerain.aclient.core.Result;
import io.bluerain.aclient.core.method.HttpGet;
import io.bluerain.aclient.ua.Chrome;
import io.bluerain.core.Str;
import io.bluerain.globr.enties.bean.io.bluerain.globr.enties.SearchResult;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

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

    public static List<SearchResult> by(String keyword) {
        Search search = new Search();
        return search.byKeyword(keyword);
    }

    public List<SearchResult> byKeyword(String keyword) {
        final List<SearchResult> srs = new ArrayList<>();
        System.setProperty("proxySet", "true");
        System.setProperty("socksProxyHost", "127.0.0.1");
        System.setProperty("socksProxyPort", "1080");
        String url = "https://www.google.com/search?q=" +
                keyword +
                "&sourceid=chrome&ie=UTF-8";
        HttpGet get = HttpGet.create(url);
        IndieParam ps = IndieParam.builder();
        get
                .userAgent(Chrome.WINDOWS_PHONE)
//                .param(ps)
                .request()
                .result(new Result() {
                    @Override
                    public void success(String body, Response response) {
                        Document dom = response.readDom();
                        Element domBody = dom.body();
                        SearchResult sr = null;
                        for (Element e : domBody.getElementsByAttributeValue("style", "clear:both")) {
                            if (e.children().size() == 0)
                                continue;
                            Element titleDom = e.child(0);
                            String link = titleDom.attr("href");
                            String title = titleDom.html();
                            sr = new SearchResult();
                            sr.setTitle(title);
                            sr.setLink(link);
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

    private String getRealLink(String href) {

        String source;
        try {
            source = URLDecoder.decode(href, "UTF-8");
            href = URLDecoder.decode(href, "UTF-8");
            //匹配真实url
            href = Str.match(href, "q=http://www.google.com/gwt/x\\?hl=en&u=((.(?!&source))*.)", 1);

            //截取encoder之后的参数
            if (!Str.notNullOrEmpty(href)) { //如果匹配为空
                href = Str.match(source, "q=http://googleweblight.com/?lite_url=((.(?!&ei))*.)", 1);
                if (href == null) {
                    href = Str.match(source, "q=((.(?!&sa))*.)", 1);
                }
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
