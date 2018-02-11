package com.ral.model.enums.sku;

import com.google.common.collect.ImmutableMap;
import com.ral.model.enums.IndexedEnum;
import com.ral.model.enums.IndexedEnumUtil;

/**
 * Created by victor on 2018/2/11.
 */
public enum  SkuStatusEnum implements IndexedEnum<SkuStatusEnum> {

    DEFAULT(10,"默认"),
    ACTIVE(20,"活跃的");

    private int index;

    private String name;

    SkuStatusEnum(int index,String name){
        this.index = index;
        this.name = name;
    }


    private static final ImmutableMap<Integer, SkuStatusEnum> INDEXS = IndexedEnumUtil.toIndexes(values());

    public static SkuStatusEnum indexOf(int index){
        return INDEXS.get(index);
    }

    @Override
    public int getIndex() {
        return index;
    }
}
