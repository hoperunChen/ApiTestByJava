package com.luckyrui.apitest.service.entity;

import java.util.Date;

public class Header extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8263609005931551961L;
	


	public Header() {

	}

	/**
	 * 通过键值对创建param
	 * @param key
	 * @param value
	 */
	public Header(String key, String value) {
		this.name = key;
		this.value = value;
	}

	/**
	 * id
	 */
	private long id;

	/**
	 * apiInfo.id
	 */
	private long apiId;

	/**
	 * header的键
	 */
	private String name;

	/**
	 * header的值
	 */
	private String value;

	/**
	 * 说明
	 */
	private String explain;

	/**
	 * 创建时间
	 */
	private Date ctime;

	/**
	 * 备注
	 */
	private String remark;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getApiId() {
		return apiId;
	}

	public void setApiId(long apiId) {
		this.apiId = apiId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	

}
