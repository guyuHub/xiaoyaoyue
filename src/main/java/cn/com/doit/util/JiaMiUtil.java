package cn.com.doit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import javax.crypto.SecretKeyFactory;
import javax.servlet.annotation.WebServlet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

//加密，解密工具类
@Component("jiaMiUtil")
@PropertySource("file:${user.dir}/crypyo.properties")
public class JiaMiUtil {
	
  private String algorithmName;   //算法名称
  private String defaultAlgorithmKey;    //算法key
  private HashMap<String,SecretKeyFactory> alreadyHave=new HashMap<String, SecretKeyFactory>();
  private  SecretKeyFactory instanse;
  
  //摘要MessageDigest 
  @Value("${crypto.MessageDigest.AlgorithmName:MD5}")
  private  String MessageDigestAlgorithmName="MD5";
  @Value("${crypto.MessageDigest.FileOniceReadSize}")
  private  int MessageDigestFileOniceReadSize=1024;
  
  @Value("${crypto.MessageDigest.CharsetName:UTF-8}")
  private  String charsetName="UTF-8";
  public String MessageDigest(File file){
	  try {
		MessageDigest md=MessageDigest.getInstance(MessageDigestAlgorithmName);
		 FileInputStream files=new FileInputStream(file);
		 byte[] rerders=new byte[MessageDigestFileOniceReadSize];
		 byte[] result=new byte[30];
		 boolean cando=true;
		 while (cando) {
			 if(files.read(rerders)==-1){
				 cando=false; 
			 }
			md.update(rerders);
		}
		 result= md.digest();
//		 return new String(result,charsetName);
		 return stringToHexString(new String(result,charsetName));
	  } catch (NoSuchAlgorithmException e) {
		
		return "NoSuchAlgorithmException";
	} catch (FileNotFoundException e) {
		
		e.printStackTrace();
		return "FileNotFoundException";
	} catch (IOException e) {
	
		e.printStackTrace();
		return "IOException";
	}
	   }
  //DES块加密算法
  public Object DEScrypTion(Object date,String key,boolean mode) throws Exception{
	  MessageDigest md=MessageDigest.getInstance("MD5");
   return null;
  }
  public  String stringToHexString(String s) {  
      String str = "";  
      for (int i = 0; i < s.length(); i++) {  
          int ch = (int) s.charAt(i);  
          String s4 = Integer.toHexString(ch);  
          str = str + s4;  
      }  
      return str;  
  }  
public String getAlgorithmName() {
	return algorithmName;
}
public void setAlgorithmName(String algorithmName) {
	this.algorithmName = algorithmName;
}
public String getDefaultAlgorithmKey() {
	return defaultAlgorithmKey;
}
public void setDefaultAlgorithmKey(String defaultAlgorithmKey) {
	this.defaultAlgorithmKey = defaultAlgorithmKey;
}
public HashMap<String, SecretKeyFactory> getAlreadyHave() {
	return alreadyHave;
}
public void setAlreadyHave(HashMap<String, SecretKeyFactory> alreadyHave) {
	this.alreadyHave = alreadyHave;
}
public SecretKeyFactory getInstanse() {
	return instanse;
}
public void setInstanse(SecretKeyFactory instanse) {
	this.instanse = instanse;
}
public String getMessageDigestAlgorithmName() {
	return MessageDigestAlgorithmName;
}
public void setMessageDigestAlgorithmName(String messageDigestAlgorithmName) {
	MessageDigestAlgorithmName = messageDigestAlgorithmName;
}
public int getMessageDigestFileOniceReadSize() {
	return MessageDigestFileOniceReadSize;
}
public void setMessageDigestFileOniceReadSize(int messageDigestFileOniceReadSize) {
	MessageDigestFileOniceReadSize = messageDigestFileOniceReadSize;
}
public String getCharsetName() {
	return charsetName;
}
public void setCharsetName(String charsetName) {
	this.charsetName = charsetName;
}
 
}
