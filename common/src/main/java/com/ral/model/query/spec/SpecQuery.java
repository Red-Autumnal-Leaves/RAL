package com.ral.model.query.spec;

import com.ral.model.query.Query;

import java.io.Serializable;

/**
 * Created by victor on 2018/2/8.
 */
public class SpecQuery extends Query implements Serializable {

    private Long categoryId;

    private String name;

    private String alias;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
