package com.ral.model.enums.logs;

import com.google.common.collect.ImmutableMap;
import com.ral.model.enums.IndexedEnum;
import com.ral.model.enums.IndexedEnumUtil;

/**
 * @author victor
 * @desc 日志系统枚举
 * @date 2018/1/23 21:38
 */
public enum RalOperationSourceEnum implements IndexedEnum<RalOperationSourceEnum> {

    RAL_AUTH(1,"RAL-AUTH","AUTH授权项目"),
    RAL_SHOP_SMS(2,"RAL-SHOP-SMS","商城后台管理系统SMS"),
    ;


    RalOperationSourceEnum(int index,String code,String name){
        this.index = index;
        this.code = code;
        this.name = name;
    }

    private int index;

    private String code;

    private String name;

    @Override
    public int getIndex() {
        return index;
    }


    private static final ImmutableMap<Integer, RalOperationSourceEnum> INDEXS = IndexedEnumUtil.toIndexes(values());

    public static RalOperationSourceEnum indexOf(int index) {
        return INDEXS.get(index);
    }


    public void setIndex(int index) {
        this.index = index;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
