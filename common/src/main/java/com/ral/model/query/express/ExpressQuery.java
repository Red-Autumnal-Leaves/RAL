package com.ral.model.query.express;

import com.ral.model.query.Query;

import java.io.Serializable;

/**
 * Created by victor on 2018/2/1.
 */
public class ExpressQuery extends Query implements Serializable{

    private String keywords;

    private String expressName;

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }
}
