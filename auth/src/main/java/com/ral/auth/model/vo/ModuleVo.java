package com.ral.auth.model.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ModuleVo implements Serializable{

	private static final long serialVersionUID = 73313289631344101L;

	private String moduleId;
	
	private String name;
	
	private String code;
	
	private Date createTime;
	
	private List<ActionVo> actions = new ArrayList<ActionVo>();
	
	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
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

	public List<ActionVo> getActions() {
		return actions;
	}

	public void setActions(List<ActionVo> actions) {
		this.actions = actions;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
