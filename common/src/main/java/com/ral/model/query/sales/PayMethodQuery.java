package com.ral.model.query.sales;

import com.ral.model.query.Query;

import java.io.Serializable;

/**
 * Created by victor on 2018/2/2.
 */
public class PayMethodQuery extends Query implements Serializable {

    private String name;

    private Boolean isEnable;

    private String appId;

    private String mchId;

    private String key;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getEnable() {
        return isEnable;
    }

    public void setEnable(Boolean enable) {
        isEnable = enable;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
