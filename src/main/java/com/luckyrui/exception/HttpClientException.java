package com.luckyrui.exception;

/**
 * httpClient请求异常
 * @author chenrui
 *
 */
public class HttpClientException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer errcode = null;

	public HttpClientException() {
	}

	public HttpClientException(int errCode, String msg) {
		super(msg);
		this.errcode = errCode;
	}
	
	public HttpClientException(int errCode) {
		super();
		this.errcode = errCode;
	}

	@Override
	public String getMessage() {
		String msg = "httpclient invoke err : " + this.errcode + ";" + super.getMessage();
		return msg;
	}

}
