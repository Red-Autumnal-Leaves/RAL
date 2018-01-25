package com.ral.model.auth.res;

import java.io.Serializable;
import java.util.Date;

public class Action implements Serializable{

	private static final long serialVersionUID = 123455105896312L;

	private String actionId;
	
	private String name;
	
	private String code;
	
	private Date createTime;
	
	private String desc;

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
