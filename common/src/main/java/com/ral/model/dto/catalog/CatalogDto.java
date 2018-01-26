package com.ral.model.dto.catalog;

import com.ral.util.tree.IRalTreeDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 2018/1/26.
 */
public class CatalogDto implements IRalTreeDto<CatalogDto> {

    private Long id;

    private String name;

    private Long logo;

    private String logoUrl;

    private Integer levl;

    private Long parentId;

    private Boolean isEnable;

    private List<CatalogDto> childrens = new ArrayList<>();//子节点

    /**
     * 要求存在父级节点ID
     *
     * @return
     */
    @Override
    public String getParentKey() {
        return parentId.toString();
    }

    /**
     * 默认父级节点，如第一级的父级ID
     *
     * @return
     */
    @Override
    public String getDefaultParentKey() {
        return "0";
    }

    /**
     * 获取当前节点的KEY
     *
     * @return
     */
    @Override
    public String getKey() {
        return id.toString();
    }

    @Override
    public List<CatalogDto> getChildrens() {
        return childrens;
    }


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

    public Integer getLevl() {
        return levl;
    }

    public void setLevl(Integer levl) {
        this.levl = levl;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Boolean getEnable() {
        return isEnable;
    }

    public void setEnable(Boolean enable) {
        isEnable = enable;
    }

    public void setChildrens(List<CatalogDto> childrens) {
        this.childrens = childrens;
    }


}