package com.luckyrui.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.luckyrui.exception.HttpClientException;

/**
 * httpClientUtils
 * 
 * @author chenrui
 * @date 2016年8月29日下午2:03:54
 * @version 1.0
 */
public class HttpClientUtil {
	static Log logger = LogFactory.getLog("HttpClientUtil.class");
	private static final String PRO_FILE_PATH = "httpclient.properties";

	/**
	 * 超时时间,默认5000
	 */
	private static Integer timeout = 5000;
	/**
	 * 重试次数,默认3次
	 */
	private static Integer retryTimes = 3;

	/**
	 * 初始化超时时间和重试次数
	 */
	static {
		ProfileUtil profileUtil = ProfileUtil.getInstance();
		String _timeout = profileUtil.readInProject(PRO_FILE_PATH, "client.timeout");
		String _retryTimes = profileUtil.readInProject(PRO_FILE_PATH, "client.retry_times");
		if (!CheckUtil.isEmpty(_timeout)) {
			timeout = Integer.parseInt(_timeout);
		}
		if (!CheckUtil.isEmpty(_retryTimes)) {
			retryTimes = Integer.parseInt(_retryTimes);
		}
	}

	/**
	 * 创建httpClient,创建时设置超时时间和重试次数
	 * 
	 * @return
	 */
	private static CloseableHttpClient createClient() {
		HttpClientBuilder clientBuilder = HttpClients.custom();
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(timeout)
				.setConnectionRequestTimeout(timeout).setSocketTimeout(timeout).build();
		// 设置重试次数
		clientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(retryTimes, true));
		// 设置超时时间
		clientBuilder.setDefaultRequestConfig(requestConfig);
		return clientBuilder.build();
	}
	
	/**
	 * 执行post请求,不需要header
	 * @param url
	 * @param params
	 * @return
	 * @author chenrui
	 * @date 2016年8月29日 下午2:41:05
	 * @version 
	 * @throws HttpClientException 
	 * @throws IOException 
	 */
	public static String post(String url, Map<String, String> params) throws IOException, HttpClientException{
		return post(url,null,params);
	}

	/**
	 * 执行post请求,返回String类型的返回值
	 * 
	 * @param url
	 * @param params
	 *            <String,String>的Map类型参数
	 * @return
	 * @throws IOException
	 * @throws HttpClientException
	 */
	public static String post(String url, Map<String, String> headers, Map<String, String> params)
			throws IOException, HttpClientException {
		CloseableHttpClient httpclient = createClient();

		String body = null;

		logger.info("create httppost:" + url);
		HttpPost post = postForm(url, headers, params);
		try {
			body = invoke(httpclient, post);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpclient.close();
		}

		return body;
	}

	/**
	 * 执行get请求,返回String类型的请求
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 * @throws HttpClientException
	 */
	public static String get(String url) throws IOException, HttpClientException {
		CloseableHttpClient httpclient = createClient();
		String body = null;
		logger.info("create httpget:" + url);
		HttpGet get = new HttpGet(url);
		try {
			body = invoke(httpclient, get);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpclient.close();
		}

		return body;
	}

	/**
	 * 调用请求
	 * 
	 * @param httpclient
	 * @param httpost
	 * @return
	 * @throws IOException
	 * @throws HttpClientException
	 */
	private static String invoke(CloseableHttpClient httpclient, HttpUriRequest httpost)
			throws IOException, HttpClientException {
		// 发送请求
		CloseableHttpResponse response = sendRequest(httpclient, httpost);
		if(response == null){
			logger.error("请求失败");
			throw new HttpClientException(-1,"请求失败:"+httpost.getURI());
		}
		String body = null;
		try {
			// 解析返回
			body = paseResponse(response);
			response.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			response.close();
		}
		return body;
	}

	/**
	 * 解析返回结果
	 * 
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws HttpClientException
	 */
	private static String paseResponse(HttpResponse response) throws IOException, HttpClientException {
		logger.info("get response from http server..");
		String body = null;
		HttpEntity entity = response.getEntity();
		logger.info("response status: " + response.getStatusLine());
		// 获取状态码
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode == 200) {
			body = EntityUtils.toString(entity);
			logger.info(body);
			EntityUtils.consume(entity);
		} else {
			throw new HttpClientException(statusCode);
		}
		return body;
	}

	/**
	 * 发送请求
	 * 
	 * @param httpclient
	 * @param httpost
	 * @return
	 */
	private static CloseableHttpResponse sendRequest(CloseableHttpClient httpclient, HttpUriRequest httpost) {
		logger.info("execute post...");
		CloseableHttpResponse response = null;

		try {
			response = httpclient.execute(httpost);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ConnectTimeoutException e){
			logger.error("connect timed out");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * 构建HttpPost
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	private static HttpPost postForm(String url, Map<String, String> headers, Map<String, String> params) {

		HttpPost httpost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();

		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			nvps.add(new BasicNameValuePair(key, params.get(key)));
		}

		if (null != headers) {
			for (String key : headers.keySet()) {
				httpost.setHeader(key, headers.get(key));
			}

		}

		try {
			logger.info("set utf-8 form entity to httppost");
			httpost.setEntity(new UrlEncodedFormEntity(nvps));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return httpost;
	}
}
