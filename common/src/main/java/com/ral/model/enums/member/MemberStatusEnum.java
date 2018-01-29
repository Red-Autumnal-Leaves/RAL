package com.ral.model.enums.member;

import com.google.common.collect.ImmutableMap;
import com.ral.model.enums.IndexedEnum;
import com.ral.model.enums.IndexedEnumUtil;

/**
 * Created by victor on 2018/1/29.
 */
public enum MemberStatusEnum implements IndexedEnum<MemberStatusEnum> {

    ACTIVE(10,"活跃中"),
    DISABLE(99,"禁用中");

    private int index;

    private String name;

    MemberStatusEnum(int index,String name){
        this.index = index;
        this.name = name;
    }

    private static final ImmutableMap<Integer, MemberStatusEnum> INDEXS = IndexedEnumUtil.toIndexes(values());

    public static MemberStatusEnum indexOf(int index) {
        return INDEXS.get(index);
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
}
