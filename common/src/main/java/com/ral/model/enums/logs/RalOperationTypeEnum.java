package com.ral.model.enums.logs;

import com.google.common.collect.ImmutableMap;
import com.ral.model.enums.IndexedEnum;
import com.ral.model.enums.IndexedEnumUtil;

/**
 * @author victor
 * @desc
 * @date 2018/1/23 21:44
 */
public enum RalOperationTypeEnum implements IndexedEnum<RalOperationTypeEnum> {

    AUTH_USER_LOGIN(1,"AUTH用户登录日志",RalOperationSourceEnum.RAL_AUTH);

    RalOperationTypeEnum(int index,String name,RalOperationSourceEnum source){
        this.index  = index;
        this.name = name;
        this.source = source;
    }

    private int index;

    private String name;

    private RalOperationSourceEnum source;


    private static final ImmutableMap<Integer, RalOperationTypeEnum> INDEXS = IndexedEnumUtil.toIndexes(values());

    public static RalOperationTypeEnum indexOf(int index) {
        return INDEXS.get(index);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RalOperationSourceEnum getSource() {
        return source;
    }

    public void setSource(RalOperationSourceEnum source) {
        this.source = source;
    }
}
