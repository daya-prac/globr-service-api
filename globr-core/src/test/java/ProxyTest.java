import io.bluerain.core.Obj;
import io.bluerain.core.Str;
import io.bluerain.http.core.UserAgent;
import io.bluerain.http.response.HttpResponse;
import io.bluerain.http.rest.handler.ResultHandler;
import io.bluerain.http.rest.method.HttpGet;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by foredawn on 15-8-21.
 * 代理测试
 */
public class ProxyTest {

    @Test
    public void testProxy() {
        String keyword = "呵呵哒";
        System.setProperty("proxySet", "true");
        System.setProperty("socksProxyHost", "127.0.0.1");
        System.setProperty("socksProxyPort", "1080");
        String url = "https://www.google.com/search?" +
                "q=" + keyword +
                "&safe=off&prmd=ivns" +
                "&start=0" +
                "&gws_rd=cr";
        /*HttpGet get = HttpGet.create(url);
        IndieParam ps = IndieParam.builder();
        get
                .userAgent(UA.WindowsPhone7_5)
                .param(ps)
                .request()
                .result(new Result() {
                    @Override
                    public void success(String body, Response response) {
                    }

                    @Override
                    public void error(String body, javax.ws.rs.core.Response response) {
                        System.out.printf(body);
                    }
                });*/
        HttpGet.create(url)
                .userAgent(UserAgent.WP_75)
                .exe()
                .handle(new ResultHandler() {
                    @Override
                    public void success(HttpResponse response, String body) {
                        Document doc = response.readDom();
                        Elements rs = doc.getElementsByClass("g");
                        for (Element r : rs) {
                            Element titleDom = r.getElementsByTag("h3").get(0).getElementsByTag("a").get(0);
                            Elements contentTemp = r.getElementsByClass("st");
                            if (!Obj.notNullOrEmpty(contentTemp)) //图片结果[略过]
                                continue;
                            Element contentDom = contentTemp.get(0);
                            System.out.println(getRealLink(titleDom.attr("href")));
                            System.out.println(titleDom.html());
                            System.out.println(contentDom.html());
                        }
                    }

                    @Override
                    public void error(HttpResponse response, int code, String body) {

                    }
                });
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

    @Test
    public void test() throws UnsupportedEncodingException {
        String url = "/url?q=https://zh.wikipedia.org/zh/User_talk:%E6%88%91%E6%98%AF%E4%BD%A0%E7%9A%84%E5%91%B5%E5%91%B5%E5%93%92&sa=U&ved=0CCIQFjAKahUKEwiw_Z3JqLzHAhVEGo4KHaL4C3c&usg=AFQjCNHvv7MLMvQJ6z0C6LJXTrfNTSijBg:";
        url = Str.match(url, "q=((.(?!&sa))*.)", 1);
        System.out.println(url);

        if (!Str.notNullOrEmpty(url)) { //如果匹配为空
        } else {
            String p = URLEncoder.encode("?", "UTF-8");
            if (url.contains(p)) {
                String ps = url.substring(url.indexOf(p), url.length());
                url = url.substring(0, url.indexOf(p)) + URLDecoder.decode(ps, "UTF-8");
            }
        }
        System.out.println(url);
    }
}
