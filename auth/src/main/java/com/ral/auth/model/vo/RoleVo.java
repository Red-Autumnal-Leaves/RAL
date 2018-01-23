package com.ral.auth.model.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author victor
 * 角色信息载体
 */
public class RoleVo implements Serializable{

	private static final long serialVersionUID = 12353434566167481L;
	
	private String roleId;
	
	private String name;
	
	private Date createTime;
	
	private String desc;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
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

}
