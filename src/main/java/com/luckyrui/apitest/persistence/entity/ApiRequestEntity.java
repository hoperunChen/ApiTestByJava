package com.luckyrui.apitest.persistence.entity;

import java.io.IOException;

import com.luckyrui.exception.HttpClientException;
import com.luckyrui.utils.HttpClientUtil;

/**
 * 请求实体类
 * 
 * @author chenrui
 * @date 2016年8月30日上午10:22:42
 * @version persistence
 */
public class ApiRequestEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5034999870233819039L;

	public ApiRequestEntity() {

	}

	/**
	 * 构造:所有参数
	 * 
	 * @param uri
	 * @param post
	 * @param method
	 * @param headers
	 * @param params
	 */
	public ApiRequestEntity(String uri, String post, int method, Headers headers, Params params) {
		setUri(uri);
		setPost(post);
		setMethod(method);
		setHeaders(headers);
		setParams(params);
	}

	/**
	 * 构造:必须参数
	 * 
	 * @param uri
	 * @param headers
	 * @param params
	 */
	public ApiRequestEntity(String uri, Headers headers, Params params) {
		setUri(uri);
		setHeaders(headers);
		setParams(params);
	}

	/**
	 * 构造:只需要地址和参数
	 * 
	 * @param uri
	 * @param params
	 */
	public ApiRequestEntity(String uri, Params params) {
		setUri(uri);
		setParams(params);
	}

	/**
	 * 构造:最简参数
	 * 
	 * @param uri
	 */
	public ApiRequestEntity(String uri) {
		setUri(uri);
		setParams(params);
	}

	/**
	 * 请求方式:GET
	 */
	public static final Integer REQUEST_METHOD_GET = 0;
	/**
	 * 请求方式:POST
	 */
	public static final Integer REQUEST_METHOD_POST = 1;

	private String responseStr;

	/**
	 * 请求地址
	 */
	private String uri;
	/**
	 * 请求端口
	 */
	private String post = "80";
	/**
	 * 请求方式
	 */
	private Integer method = REQUEST_METHOD_GET;
	/**
	 * 请求头
	 */
	private Headers headers;
	/**
	 * 请求参数
	 */
	private Params params;

	/************* getter/setter ****************/

	public String getUri() {
		return uri;
	}

	public String getResponseStr() {
		return responseStr;
	}

	public void setResponseStr(String responseStr) {
		this.responseStr = responseStr;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public Integer getMethod() {
		return method;
	}

	public void setMethod(Integer method) {
		this.method = method;
	}

	public Headers getHeaders() {
		return headers;
	}

	public void setHeaders(Headers headers) {
		this.headers = headers;
	}

	public Params getParams() {
		return params;
	}

	public void setParams(Params params) {
		this.params = params;
	}

	/************* getter/setter ****************/

	/**
	 * 发送请求
	 * 
	 * @return
	 * @author chenrui
	 * @date 2016年8月30日 下午10:05:30
	 * @version persistence
	 */
	public String sendRequest() {
		try {
			if (method == REQUEST_METHOD_GET) {
				responseStr = __sendGetRequest();
			} else if (method == REQUEST_METHOD_POST) {
				responseStr = __sendPostRequest();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return responseStr;
		}
		return responseStr;
	}

	/**
	 * 发送post请求
	 * 
	 * @return
	 * @throws IOException
	 * @throws HttpClientException
	 * @author chenrui
	 * @date 2016年8月30日 下午10:05:37
	 * @version persistence
	 */
	private String __sendPostRequest() throws IOException, HttpClientException {
		if (null == headers) {
			return HttpClientUtil.post(uri, params.getPostParams());
		} else {
			return HttpClientUtil.post(uri, headers.getHeaders(), params.getPostParams());
		}
	}

	/**
	 * 发送get请求
	 * 
	 * @return
	 * @author chenrui
	 * @date 2016年8月30日 下午10:05:45
	 * @version persistence
	 * @throws HttpClientException
	 * @throws IOException
	 */
	private String __sendGetRequest() throws IOException, HttpClientException {
		return HttpClientUtil.get(uri + params.getGetParams());
	}

}
