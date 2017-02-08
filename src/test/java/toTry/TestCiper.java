package toTry;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.security.Signature;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import org.junit.Test;
import org.slf4j.LoggerFactory;

import sun.security.jca.GetInstance;
import sun.security.jca.ServiceId;

public class TestCiper {
	  public   String[] tokenizeTransformation(String paramString)
			    throws NoSuchAlgorithmException
			  {
			    if (paramString == null) {
			      throw new NoSuchAlgorithmException("No transformation given");
			    }
			    String[] arrayOfString = new String[3];
			    int i = 0;
			    StringTokenizer localStringTokenizer = new StringTokenizer(paramString, "/");
			    try
			    {
			      while ((localStringTokenizer.hasMoreTokens()) && (i < 3)) {
			        arrayOfString[(i++)] = localStringTokenizer.nextToken().trim();
			      }
			      if ((i == 0) || (i == 2) || (localStringTokenizer.hasMoreTokens())) {
			        throw new NoSuchAlgorithmException("Invalid transformation format:" + paramString);
			      }
			    }
			    catch (NoSuchElementException localNoSuchElementException)
			    {
			      throw new NoSuchAlgorithmException("Invalid transformation format:" + paramString);
			    }
			    if ((arrayOfString[0] == null) || (arrayOfString[0].length() == 0)) {
			      throw new NoSuchAlgorithmException("Invalid transformation:algorithm not specified-" + paramString);
			    }
			    return arrayOfString;
			  }
	  @Test
	 public void testOne() throws NoSuchAlgorithmException, IOException{
		  String STATIC_LOGGER_BINDER_PATH = "org/slf4j/impl/StaticLoggerBinder.class";
		  ClassLoader loggerFactoryClassLoader = LoggerFactory.class
	              .getClassLoader();
	      Enumeration<URL> paths;
	      
	        paths = loggerFactoryClassLoader
	                .getResources(STATIC_LOGGER_BINDER_PATH);
	      
	      while (paths.hasMoreElements()) {
	        URL path = (URL) paths.nextElement();
	     System.out.println(path);
	      }
		  
		//  System.out.println(System.getProperty("java.security.properties"));
		//  System.out.println(tokenizeTransformation("AES")[0]);
	 }
	  @Test
		 public void testTwo() throws NoSuchAlgorithmException{
		  ArrayList list = new ArrayList<ServiceId>(1);
		  list.add(new ServiceId("Cipher", "AES"));
		  List<Provider.Service> bb= GetInstance.getServices((List<ServiceId>)list);
		  System.out.println(bb);
		  
		 }
	  
}
