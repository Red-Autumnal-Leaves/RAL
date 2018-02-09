package com.ral.model.dto.sku;

import com.ral.model.entity.spec.Spec;
import com.ral.model.entity.spec.SpecValue;

import java.io.Serializable;

/**
 * Created by victor on 2018/2/9.
 */
public class SkuSpecDto implements Serializable {

    private Long id;

    private String skuCode;

    private Long specId;

    private Long specValueId;

    private Spec spec;

    private SpecValue value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public Long getSpecId() {
        return specId;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    public Long getSpecValueId() {
        return specValueId;
    }

    public void setSpecValueId(Long specValueId) {
        this.specValueId = specValueId;
    }

    public Spec getSpec() {
        return spec;
    }

    public void setSpec(Spec spec) {
        this.spec = spec;
    }

    public SpecValue getValue() {
        return value;
    }

    public void setValue(SpecValue value) {
        this.value = value;
    }
}
