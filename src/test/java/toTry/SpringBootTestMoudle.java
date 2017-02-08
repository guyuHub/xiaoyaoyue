package toTry;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Enumeration;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.ContextLoader;

import cn.com.doit.AppStart;
import cn.com.doit.util.ApplicationFactoryUtil;
import cn.com.doit.util.JiaMiUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=AppStart.class,webEnvironment = WebEnvironment.DEFINED_PORT)
public class SpringBootTestMoudle {
	@Autowired
	@Qualifier("applicationFactoryUtil")
   private ApplicationFactoryUtil applicationFactoryUtil;
	@Autowired
	@Qualifier("jiaMiUtil")
   private JiaMiUtil jiaMiUtil;
   @Autowired
	private TomcatEmbeddedServletContainer container;
	
//测试加密工具的MessageDigest   
   @Test
   public void testOne() throws MalformedURLException, ServletException{
	   //添加servlet 类似dispatchServlet
	   //servletContext.addServlet(name, this.servlet);
	   JiaMiUtil sb=new JiaMiUtil();
	   System.out.println("=====================");
	//  System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
	 ;

		 System.out.println(applicationFactoryUtil.getServletContext().getRealPath(""));
	   File toread=new File("./crypyo.properties"); 
		System.out.println(sb.MessageDigest(toread));
	  System.out.println("=====================");
	  
   }

public ApplicationFactoryUtil getApplicationFactoryUtil() {
	return applicationFactoryUtil;
}

public void setApplicationFactoryUtil(
		ApplicationFactoryUtil applicationFactoryUtil) {
	this.applicationFactoryUtil = applicationFactoryUtil;
}

public JiaMiUtil getJiaMiUtil() {
	return jiaMiUtil;
}

public void setJiaMiUtil(JiaMiUtil jiaMiUtil) {
	this.jiaMiUtil = jiaMiUtil;
}
   
   
}
