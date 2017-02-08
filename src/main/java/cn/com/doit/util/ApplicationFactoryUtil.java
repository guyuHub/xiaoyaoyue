package cn.com.doit.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.beans.BeansException;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component("applicationFactoryUtil")
public class ApplicationFactoryUtil implements ApplicationContextAware,ServletContextInitializer  {
	private static ApplicationContext ctx;
	private static ServletContext servletContext;

	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		ApplicationFactoryUtil.setCtx(ctx);
	}

	public static ApplicationContext getCtx() {
		return ctx;
	}

	public static void setCtx(ApplicationContext ctx) {
		ApplicationFactoryUtil.ctx = ctx;
	}

	public void onStartup(ServletContext servletContext)
			throws ServletException {
		this.servletContext=servletContext;
	}

	public static ServletContext getServletContext() {
		return servletContext;
	}

	public static void setServletContext(ServletContext servletContext) {
		ApplicationFactoryUtil.servletContext = servletContext;
	}

}
