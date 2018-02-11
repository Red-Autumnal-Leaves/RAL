package com.ral.model.dto.item;

import com.ral.model.dto.spec.SpecDto;

/**
 * Created by victor on 2018/2/11.
 */
public class ItemSpecDto {

    private Long id;

    private String itemCode;

    private Long specId;

    private SpecDto spec;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Long getSpecId() {
        return specId;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    public SpecDto getSpec() {
        return spec;
    }

    public void setSpec(SpecDto spec) {
        this.spec = spec;
    }
}
