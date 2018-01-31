package com.ral.model.dto.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ral.util.date.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by victor on 2018/1/31.
 */
public class ImageDto implements Serializable{

    private Long id;

    private Integer type;

    private String size;

    private String name;

    private String url;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @JsonFormat(pattern = DateUtils.YYYY_MM_DD_HH_MM_SS)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
