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
import java.util.TimeZone;

import javax.annotation.PostConstruct;

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
import cn.com.doit.validate.service.MobileService;

/**
 * @author Administrator
 *
 */
@Configuration(value = "mobileService")
public class MobileServiceImpl implements MobileService {
	private CloseableHttpClient httpClient;
	private HttpPost request;
	private ResponseResult lst=null;
	@Value("${mobile.service.url}")
	private String ur="https://api.netease.im/sms/sendcode.action";
	@Value("${mobile.service.AppKey}")
	private String AppKey="d45ff7e44e703aa65be01d7714277982";
	@Value("${mobile.service.AppSecret}")
	private String appSecret="b12e9b107fa1";
	@PostConstruct
	@Before
	public void init(){
			httpClient = HttpClients.createDefault();

			RequestConfig requestConfig = RequestConfig.custom()
					.setSocketTimeout(60000).setConnectTimeout(5000).build();
			 request = new HttpPost(ur);
			request.setConfig(requestConfig);
			request.addHeader("AppKey", AppKey);
			request.addHeader("Nonce", "FreeRead"); //随机数;
			request.addHeader("CurTime", Long.valueOf(
					Calendar.getInstance().getTimeInMillis()/1000
					).toString()
					);
			request.addHeader("CheckSum", CheckSumBuilder.getCheckSum("b12e9b107fa1", "FreeRead", Long.valueOf(
					Calendar.getInstance().getTimeInMillis()/1000).toString()));
			request.addHeader("Content-Type",
                "application/x-www-form-urlencoded; charset=UTF-8");
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
