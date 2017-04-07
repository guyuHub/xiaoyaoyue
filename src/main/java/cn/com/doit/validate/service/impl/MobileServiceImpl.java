/**
 * 
 */
package cn.com.doit.validate.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import cn.com.doit.validate.service.MobileService;

/**
 * @author Administrator
 *
 */
public class MobileServiceImpl implements MobileService {
	private CloseableHttpClient httpClient;
	public void init(){

			httpClient = HttpClients.createDefault();
			RequestConfig requestConfig = RequestConfig.custom()
					.setSocketTimeout(60000).setConnectTimeout(5000).build();
			HttpPost request = new HttpPost("");
			request.setConfig(requestConfig);
			// cursor并不为空时处理
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			// 传入查询起始时间与结束时间
			nvps.add(new BasicNameValuePair("startTime", new Date().toString()));
			nvps.add(new BasicNameValuePair("endTime", new Date().toString()));
			try {
				request.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// Create a custom response handler 后续返回类型应该是一组普通类
			ResponseHandler<List<String>> responseHandler = new ResponseHandler<List<String>>() {

				public List<String> handleResponse(HttpResponse response)
						throws ClientProtocolException, IOException {
					// TODO Auto-generated method stub
					return null;
				}
//				public List<SimpleUser> handleResponse(
//						final HttpResponse response)throws ClientProtocolException, IOException {
//					int status = response.getStatusLine().getStatusCode();
//					if (status >= 200 && status < 300) {
//						HttpEntity entity = response.getEntity();
//						String jsonString = EntityUtils.toString(entity);
////						if(jsonString!=null&&(!"".equals(jsonString))){
////							jsonMapper = new ObjectMapper();
////							JavaType javaType = getCollectionType(ArrayList.class,
////									SimpleUser.class);
////							@SuppressWarnings("unchecked")
////							List<SimpleUser> lst = (List<SimpleUser>) jsonMapper
////									.readValue(jsonString, javaType);
//							return null;
//						}else{
//							return null;
//						}
//					
//					} 
//					return null;
//
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
	
		}
   public String sendMsg(String phoneNum,String msg){
	return msg;
	   
   }
}
