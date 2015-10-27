package io.bluerain.globr.web.servlet;

import io.bluerain.globr.web.core.ConfigInfo;
import io.bluerain.proxy.Socks5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hentai_mew on 15-10-27.
 * 主页映射的servlet对象
 */
@Component
public class IndexServlet extends HttpServlet {

    private ApplicationContext applicationContext;
    private ConfigInfo configInfo;
    boolean isProxy = false;

    @Override
    public void init() throws ServletException {
        super.init();
        applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        configInfo = (ConfigInfo) applicationContext.getBean("configInfo");

        if (configInfo.getLocalProxy() && !isProxy) {
            Socks5.proxyLocalShadowsocks();
            isProxy = true;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("url", configInfo.getDocsUrl());
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
