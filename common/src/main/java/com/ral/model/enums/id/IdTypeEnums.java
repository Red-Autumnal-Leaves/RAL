package com.ral.model.enums.id;

import com.google.common.collect.ImmutableMap;
import com.ral.model.enums.IndexedEnum;
import com.ral.model.enums.IndexedEnumUtil;

/**
 * @author victor
 * @desc
 * @date 2018/1/6 18:47
 */
public enum IdTypeEnums implements IndexedEnum<IdTypeEnums> {

    DEFAULT(1,"默认",""),
    ITEM(2,"商品",""),
    SKU(3,"SKU",""),
    BARCODE(4,"条码",""),
    ORDER(5,"订单","SO"),
    REQUEST(6,"HTTP 请求",""),
    ;

    private int index;

    private String name;

    private String prefix;

    IdTypeEnums(int index,String name,String prefix){
        this.index = index;
        this.name = name;
        this.prefix = prefix;
    }

    private static final ImmutableMap<Integer, IdTypeEnums> INDEXS = IndexedEnumUtil.toIndexes(values());

    public static IdTypeEnums indexOf(int index) {
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

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
