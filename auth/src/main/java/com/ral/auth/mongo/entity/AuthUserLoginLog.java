package com.ral.auth.mongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @author victor
 * @desc auth 用户登录日志
 * @date 2018/1/24 20:20
 */
@Document(collection = AuthUserLoginLog.COLLECTION_NAME)
public class AuthUserLoginLog implements Serializable {

    static final String COLLECTION_NAME = "auth_user_login_log";

    @Id
    private String id;//主键

    private String tenantId;

    private String user;

    private String ip;

    private Date createTime;//创建时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
