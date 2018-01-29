package com.ral.model.auth.res;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ral.util.date.DateUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Manager implements Serializable{

	private static final long serialVersionUID = 56465432132156431L;

	// 用户id
	private String userId;

	// 用户名
	private String userName;

	// 姓名
	private String name;

	// 性别
	private Integer sex;

	// 电话
	private String tel;

	// 状态
	private Integer status;

	// 创建时间
	private Date createTime;

	// 最后修改时间
	private Date lastUpdateTime;

	// 备注
	private String desc;

	// 角色
	private Role role;

	// 权限
	private List<Project> projects = new ArrayList<Project>();

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@JsonFormat(pattern = DateUtils.YYYY_MM_DD_HH_MM_SS)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@JsonFormat(pattern = DateUtils.YYYY_MM_DD_HH_MM_SS)
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	
}
