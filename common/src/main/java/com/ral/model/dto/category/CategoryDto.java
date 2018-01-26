package com.ral.model.dto.category;

import com.ral.util.tree.IRalTreeDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author victor
 * @desc
 * @date 2018/1/26 20:54
 */
public class CategoryDto implements IRalTreeDto<CategoryDto> {

    private Long id;

    private String name;

    private Long logo;

    private Integer levl;

    private Long parentId;

    private Boolean isEnable;

    private List<CategoryDto> childrens = new ArrayList<>();

    @Override
    public String getKey() {
        return id.toString();
    }

    @Override
    public String getParentKey() {
        return parentId == null ? "0" : parentId.toString();
    }

    @Override
    public String getDefaultParentKey() {
        return "0";
    }

    @Override
    public List<CategoryDto> getChildrens() {
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

    public void setChildrens(List<CategoryDto> childrens) {
        this.childrens = childrens;
    }
}
