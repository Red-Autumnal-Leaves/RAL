package com.ral.model.dto.brand;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ral.util.date.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by victor on 2018/2/8.
 */
public class BrandDto  implements Serializable{

    private Long id;

    private String name;

    private Long logo;

    private String logoUrl;

    private String createUser;

    private Date createTime;

    private String lastUpdateUser;

    private Date lastUpdateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLogo() {
        return logo;
    }

    public void setLogo(Long logo) {
        this.logo = logo;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @JsonFormat(pattern = DateUtils.YYYY_MM_DD_HH_MM_SS)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    @JsonFormat(pattern = DateUtils.YYYY_MM_DD_HH_MM_SS)
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
