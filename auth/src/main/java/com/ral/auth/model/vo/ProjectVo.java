package com.ral.auth.model.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectVo implements Serializable {

	private static final long serialVersionUID = 123546546132156L;

	private String projectId;

	private String name;
	
	private String code;

	private Date createTime;

	private String desc;
	
	//模块
	private List<ModuleVo> services = new ArrayList<ModuleVo>();

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
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

	public List<ModuleVo> getServices() {
		return services;
	}

	public void setServices(List<ModuleVo> services) {
		this.services = services;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
