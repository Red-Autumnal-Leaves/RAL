package com.ral.model.query.member;

import com.ral.model.query.Query;

import java.io.Serializable;

/**
 * Created by victor on 2018/1/31.
 */
public class MemberTypeQuery extends Query implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
