package com.ral.model.entity.item;

import java.util.Date;

public class Evaluate {
    private Long id;

    private String itemCode;

    private Integer priceStar;

    private Integer qualityStar;

    private Integer matchStar;

    private Integer logisticsStar;

    private String content;

    private Boolean isAnonymous;

    private Boolean isSslide;

    private Long memberId;

    private String createUser;

    private Date createTime;

    private String lastUpdateUser;

    private Date lastUpdateTime;

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
        this.itemCode = itemCode == null ? null : itemCode.trim();
    }

    public Integer getPriceStar() {
        return priceStar;
    }

    public void setPriceStar(Integer priceStar) {
        this.priceStar = priceStar;
    }

    public Integer getQualityStar() {
        return qualityStar;
    }

    public void setQualityStar(Integer qualityStar) {
        this.qualityStar = qualityStar;
    }

    public Integer getMatchStar() {
        return matchStar;
    }

    public void setMatchStar(Integer matchStar) {
        this.matchStar = matchStar;
    }

    public Integer getLogisticsStar() {
        return logisticsStar;
    }

    public void setLogisticsStar(Integer logisticsStar) {
        this.logisticsStar = logisticsStar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Boolean getIsAnonymous() {
        return isAnonymous;
    }

    public void setIsAnonymous(Boolean isAnonymous) {
        this.isAnonymous = isAnonymous;
    }

    public Boolean getIsSslide() {
        return isSslide;
    }

    public void setIsSslide(Boolean isSslide) {
        this.isSslide = isSslide;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
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

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser == null ? null : lastUpdateUser.trim();
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}