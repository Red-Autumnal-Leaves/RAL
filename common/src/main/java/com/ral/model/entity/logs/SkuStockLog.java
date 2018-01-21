package com.ral.model.entity.logs;

import java.util.Date;

public class SkuStockLog {
    private Long id;

    private Long version;

    private Integer type;

    private String itemCode;

    private String skuCode;

    private Integer stock;

    private Integer orderOccupancy;

    private Integer activityOccupancy;

    private String createUser;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode == null ? null : itemCode.trim();
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode == null ? null : skuCode.trim();
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getOrderOccupancy() {
        return orderOccupancy;
    }

    public void setOrderOccupancy(Integer orderOccupancy) {
        this.orderOccupancy = orderOccupancy;
    }

    public Integer getActivityOccupancy() {
        return activityOccupancy;
    }

    public void setActivityOccupancy(Integer activityOccupancy) {
        this.activityOccupancy = activityOccupancy;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}