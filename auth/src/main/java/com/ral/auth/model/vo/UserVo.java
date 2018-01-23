package com.ral.auth.model.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author victor
 * @desc 用户载体
 */
public class UserVo implements Serializable {

	private static final long serialVersionUID = 65456432131644331L;

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
	
	//角色
	private RoleVo role;
	
	//权限
	private List<ProjectVo> projects = new ArrayList<ProjectVo>();
	

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public RoleVo getRole() {
		return role;
	}

	public void setRole(RoleVo role) {
		this.role = role;
	}

	public List<ProjectVo> getProjects() {
		return projects;
	}

	public void setProjects(List<ProjectVo> projects) {
		this.projects = projects;
	}

}
