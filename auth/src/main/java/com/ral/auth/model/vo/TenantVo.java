package com.ral.auth.model.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author victor
 * @desc 租户载体
 */
public class TenantVo implements Serializable{

	private static final long serialVersionUID = 899616561123345L;
	
	private String tenantId;
	
	private String name;
	
	private Byte isEnable;
	
	private Date createTime;
	
	private Date lastUpdateTime;
	
	private Date authorizeTime;
	
	private String desc;

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Byte getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Byte isEnable) {
		this.isEnable = isEnable;
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

	public Date getAuthorizeTime() {
		return authorizeTime;
	}

	public void setAuthorizeTime(Date authorizeTime) {
		this.authorizeTime = authorizeTime;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
