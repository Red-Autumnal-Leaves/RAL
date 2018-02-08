package com.ral.model.dto.spec;

import com.ral.model.entity.spec.SpecValue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 2018/2/8.
 */
public class SpecDto implements Serializable {

    private Long id;

    private Long categoryId;

    private String name;

    private String alias;

    private List<SpecValue> values = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<SpecValue> getValues() {
        return values;
    }

    public void setValues(List<SpecValue> values) {
        this.values = values;
    }
}
