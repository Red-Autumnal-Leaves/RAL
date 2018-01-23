package com.ral.mongo.entity.logs;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * @author victor
 * @desc
 * @date 2018/1/23 21:30
 */
public class RalOperationLog {

    @Id
    private Long id;//主键

    private String source;//系统code，如auth ,sms

    private Integer type;//类型，如登录

    private String name; //日志描述

    private String content;//记录内容

    private String data;//存储的业务数据

    private Integer result;//操作结果  用于区分错误日志还是正常日志

    private Date createTime;//创建时间

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}


