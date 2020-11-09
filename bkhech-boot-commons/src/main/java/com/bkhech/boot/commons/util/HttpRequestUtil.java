package com.bkhech.boot.commons.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

/**
 * @author guowm
 * @date 2019/7/4
 */
public class HttpRequestUtil {
	
	private static final Log logger = LogFactory.getLog(HttpRequestUtil.class);
	
	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url 发送请求的URL
	 * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			logger.error("发送GET请求出现异常！" + e);
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	/**  
     * 向指定URL发送POST方法的请求  
     * @param url 发送请求的URL  
     * @param params 请求参数，请求参数应该是name1=value1&name2=value2的形式。  
     * @return URL 所代表远程资源的响应  
     * 使用jdk自带的HttpURLConnection向URL发送POST请求并输出响应结果
	 * 参数使用流传递，并且硬编码为字符串"name=aaa"的格式
	 * @throws Exception
     */  
	public static String sendPost(String url, String params) {
		StringBuffer buffer = new StringBuffer();
		try {
			// 建立连接
			URL urlPath = new URL(url);

			HttpURLConnection httpConn = (HttpURLConnection) urlPath.openConnection();

			// 设置参数
			httpConn.setDoOutput(true); // 需要输出
			httpConn.setDoInput(true); // 需要输入
			httpConn.setUseCaches(false); // 不允许缓存
			httpConn.setRequestMethod("POST"); // 设置POST方式连接

			// 设置请求属性
			httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
			httpConn.setRequestProperty("Charset", "UTF-8");

			// 连接,也可以不用明文connect，使用下面的httpConn.getOutputStream()会自动connect
			httpConn.connect();

			// 建立输入流，向指向的URL传入参数
			DataOutputStream dos = new DataOutputStream(httpConn.getOutputStream());

			dos.writeBytes(params);
			dos.flush();
			dos.close();

			// 获得响应状态
			int resultCode = httpConn.getResponseCode();
			if (HttpURLConnection.HTTP_OK == resultCode) {
				BufferedReader responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
				String readLine = "";
				while ((readLine = responseReader.readLine()) != null) {
					buffer.append(readLine);
				}
				responseReader.close();
				return buffer.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return buffer.toString();
	}
	
	/**
	 * http json
	 * 
	 * @param url
	 * @param body
	 * @return
	 */
	public static String sendPostJson(String url, String body) {
		String responseContent = "";
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		try {
			httpClient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			httpPost.addHeader("Content-Type", "application/json");
			httpPost.setEntity(new StringEntity(body, Charset.forName("UTF-8")));
			
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(10000).build();
			httpPost.setConfig(requestConfig);
			
			response = httpClient.execute(httpPost);
			//System.out.println(response.getStatusLine().getStatusCode() + "\n");
			HttpEntity entity = response.getEntity();
			responseContent = EntityUtils.toString(entity, "UTF-8");
			//System.out.println(responseContent);

			response.close();
			httpClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return responseContent;
	}
	
}
