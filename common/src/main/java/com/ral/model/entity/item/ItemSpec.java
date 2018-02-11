package com.ral.model.entity.item;

import java.io.Serializable;
import java.util.Date;

public class ItemSpec implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item_spec.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item_spec.item_code
     *
     * @mbggenerated
     */
    private String itemCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item_spec.spec_id
     *
     * @mbggenerated
     */
    private Long specId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item_spec.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item_spec.last_updatetime
     *
     * @mbggenerated
     */
    private Date lastUpdatetime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table item_spec
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item_spec.id
     *
     * @return the value of item_spec.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item_spec.id
     *
     * @param id the value for item_spec.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item_spec.item_code
     *
     * @return the value of item_spec.item_code
     *
     * @mbggenerated
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item_spec.item_code
     *
     * @param itemCode the value for item_spec.item_code
     *
     * @mbggenerated
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode == null ? null : itemCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item_spec.spec_id
     *
     * @return the value of item_spec.spec_id
     *
     * @mbggenerated
     */
    public Long getSpecId() {
        return specId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item_spec.spec_id
     *
     * @param specId the value for item_spec.spec_id
     *
     * @mbggenerated
     */
    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item_spec.create_time
     *
     * @return the value of item_spec.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item_spec.create_time
     *
     * @param createTime the value for item_spec.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item_spec.last_updatetime
     *
     * @return the value of item_spec.last_updatetime
     *
     * @mbggenerated
     */
    public Date getLastUpdatetime() {
        return lastUpdatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item_spec.last_updatetime
     *
     * @param lastUpdatetime the value for item_spec.last_updatetime
     *
     * @mbggenerated
     */
    public void setLastUpdatetime(Date lastUpdatetime) {
        this.lastUpdatetime = lastUpdatetime;
    }
}