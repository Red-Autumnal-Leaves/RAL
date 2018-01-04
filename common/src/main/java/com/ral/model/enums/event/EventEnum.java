package com.ral.model.enums.event;

import com.google.common.collect.ImmutableMap;
import com.ral.model.enums.IndexedEnum;
import com.ral.model.enums.IndexedEnumUtil;

/**
 * @author victor
 * @desc 事件枚举接口
 * @date 2018/1/2 23:59
 */
public enum  EventEnum implements IndexedEnum<EventEnum> {
    EXAMPLE_EVENT(1,"测试事件");

    EventEnum(int index,String name){
        this.index = index;
        this.name = name;
    }

    private int index;

    private String name;

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getIndex() {
        return 0;
    }

    private static final ImmutableMap<Integer, EventEnum> INDEXS = IndexedEnumUtil.toIndexes(values());

    public static EventEnum indexOf(int index) {
        return INDEXS.get(index);
    }
}
