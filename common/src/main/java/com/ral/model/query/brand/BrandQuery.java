package com.ral.model.query.brand;

import com.ral.model.query.Query;

import java.io.Serializable;

/**
 * Created by victor on 2018/2/7.
 */
public class BrandQuery extends Query implements Serializable {

    private Long id;

    private String name;

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
}
