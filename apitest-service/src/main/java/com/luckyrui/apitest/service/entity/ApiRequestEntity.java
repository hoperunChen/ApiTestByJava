package com.luckyrui.apitest.service.entity;

import java.io.IOException;
import java.util.HashMap;

import com.luckyrui.base.callback.CallbackAble;
import com.luckyrui.base.exception.HttpClientException;
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
	 * @param port
	 * @param method
	 * @param headers
	 * @param params
	 */
	public ApiRequestEntity(long id,String apiName, String uri, String port, int method, Headers headers, Params params) {
		setApiName(apiName);
		setUri(uri);
		setPort(port);
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
	}

	/**
	 * 请求方式:GET
	 */
	public static final Integer REQUEST_METHOD_GET = 0;
	/**
	 * 请求方式:POST
	 */
	public static final Integer REQUEST_METHOD_POST = 1;

	/**
	 * 响应文本
	 */
	private String responseStr;
	
	/**
	 * 接口id
	 */
	private long id;

	/**
	 * 接口名称
	 */
	private String apiName;

	/**
	 * 请求地址
	 */
	private String uri;
	/**
	 * 请求端口
	 */
	private String port = "80";
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
	
	
	

	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getUri() {
		return uri;
	}

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
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

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
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
	 * @param cb 回调函数,不需要回调可以传空
	 * @return
	 * @author chenrui
	 * @date 2016年8月30日 下午10:05:30
	 * @version persistence
	 */
	public void sendRequest(@SuppressWarnings("rawtypes") final CallbackAble cb) {
		new Thread() {
			@SuppressWarnings("unchecked")
			@Override
			public void run() {
				try {
					if (method == REQUEST_METHOD_GET) {
						responseStr = __sendGetRequest();
					} else if (method == REQUEST_METHOD_POST) {
						responseStr = __sendPostRequest();
					}
					System.out.println(responseStr);
					if(null != cb){
						cb.call(responseStr);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
	
	/**
	 * 发送请求
	 * 
	 * @author chenrui
	 * @date 2016年11月3日 上午10:55:55
	 * @version 201611
	 */
	public void sendRequest() {
		sendRequest(null);
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
			return HttpClientUtil.post(uri, null == params ? new HashMap<String, String>() : params.getPostParams());
		} else {
			return HttpClientUtil.post(uri, headers.getHeaders(),
					null == params ? new HashMap<String, String>() : params.getPostParams());
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
		String rqUrl =uri + (params == null ? "" : params.getGetParams());
		System.out.println(rqUrl);
		return HttpClientUtil.get(rqUrl);
	}

	@Override
	public String toString() {
		String rtn = String.format("{\n名称:%s\nurl:%s\nmethod:%s\nheaders:%s\nparams:%s\n}\n", apiName, uri, method,
				headers == null ? "" : headers.toString(), params == null ? "" : params.toString());
		return rtn;

	}

}
