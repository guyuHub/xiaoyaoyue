/**
 * 
 */
package cn.com.doit.validate.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.com.doit.util.CheckSumBuilder;
import cn.com.doit.validate.service.EmailService;
import cn.com.doit.validate.service.MobileService;

/**
 * @author Administrator
 *
 */
@Configuration(value = "emailService")
public class EmailServiceImpl implements EmailService {
	private CloseableHttpClient httpClient;
	private HttpPost request;
	private ResponseResult lst=null;
	@Value("${email.service.sendHost}")
	private String sendHost="https://api.netease.im/sms/sendcode.action";
	@Value("${email.service.username}")
	private String username="1712473752";
	@Value("${email.service.password}")
	private String password="guyu717250";
	@Value("${email.service.fromMailAddress}")
	private String fromMailAddress="guyu_1992@foxmail.com";
	@Value("${email.service.toMailAddress}")
	private String toMailAddress="guyu_1992@foxmail.com";
	
	
	@PostConstruct
	@Before
	public void init() throws AddressException, MessagingException{
		Properties props = initProperties();
		Authenticator auth=new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {  
		        return new PasswordAuthentication(username, password);  
		    }  
		};
		 Session session = initSession(props,auth);
		 initMessage(session);
	}


	private void initMessage( Session session) throws AddressException, MessagingException {
        // 创建MIME邮件对象  
        MimeMessage mimeMessage = new MimeMessage(session);  
        mimeMessage.setFrom(new InternetAddress(fromMailAddress));// 发件人  
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(  
                toMailAddress));// 收件人  
        mimeMessage.setSubject("测试邮件验证");  //主题
        mimeMessage.setSentDate(new Date());// 发送日期  
        MimeMessage message = new MimeMessage(session);    
        

		
	}


	private Session initSession(Properties props,Authenticator auth) {
	return Session.getDefaultInstance(props,auth);
		
	}

	private Properties initProperties() {
		 Properties properties = new Properties();
		   //设置发送和接收协议  
	        properties.put("mail.transport.protocal", "smtp");  
	        //设置发送邮件服务器  
	        properties.put("mail.smtp.host",sendHost);
	        properties.put("mail.smtp.auth", "true");  
		return null;
	}
public ResponseResult sendMsg(String phoneNum,String msg){
	   List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		// 传入手机号码和短信内容
		nvps.add(new BasicNameValuePair("mobile", phoneNum));
		try {
			request.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		// Create a custom response handler 后续返回类型应该是一组普通类
					ResponseHandler<ResponseResult> responseHandler = new ResponseHandler<ResponseResult>() {

						public ResponseResult handleResponse(HttpResponse response)
								throws ClientProtocolException, IOException {
							int status = response.getStatusLine().getStatusCode();
							if (status >= 200 && status < 300) {
								HttpEntity entity = response.getEntity();
								String jsonString = EntityUtils.toString(entity);
								System.out.println("=========="+jsonString);
								if(jsonString!=null&&(!"".equals(jsonString))){
									ObjectMapper		jsonMapper = new ObjectMapper();						
									 lst = jsonMapper
											.readValue(jsonString, ResponseResult.class);
									return  lst;
								}else{
									return null;
								}
						}

							return null;}
		
					};
					try {
						httpClient.execute(request, responseHandler);
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	return lst;
	   
   }

   @Test
   public void test(){
	   sendMsg("18655528392","你好帅啊");
   }
}
