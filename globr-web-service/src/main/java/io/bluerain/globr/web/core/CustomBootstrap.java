package io.bluerain.globr.web.core;

import com.wordnik.swagger.config.ConfigFactory;

import javax.servlet.http.HttpServlet;

public class CustomBootstrap extends HttpServlet {

	private static final long serialVersionUID = 100L;
	static {
		ConfigFactory.config().setBasePath("http://localhost/esse");
		// ConfigFactory.config().setBasePath("http://api.essential.cc");

		// use the following as another option
		/*
		 * BeanConfig bean = new BeanConfig(); bean.setScan(true);
		 * bean.setResourcePackage("org.resteasy.rest");
		 * bean.setBasePath("http://localhost:8080/spring");
		 * bean.setVersion("1.0");
		 */
	}
}
