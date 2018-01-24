package com.ral.model.enums.logs;

import com.google.common.collect.ImmutableMap;
import com.ral.model.enums.IndexedEnum;
import com.ral.model.enums.IndexedEnumUtil;

/**
 * Created by victor on 2018/1/24.
 */
public enum  RalOperationResultEnum implements IndexedEnum<RalOperationResultEnum> {

    DEBUG(10,"DEBUG"),
    INFO(20,"INFO"),
    WARN(30,"WARN"),
    ERROR(40,"ERROR"),
    ;
    private int index;

    private String name;

    RalOperationResultEnum(int index,String name){
        this.index = index;
        this.name = name;
    }

    @Override
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

    private static final ImmutableMap<Integer, RalOperationResultEnum> INDEXS = IndexedEnumUtil.toIndexes(values());

    public static RalOperationResultEnum indexOf(int index) {
        return INDEXS.get(index);
    }
}
