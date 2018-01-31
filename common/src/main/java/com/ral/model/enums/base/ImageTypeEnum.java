package com.ral.model.enums.base;

import com.google.common.collect.ImmutableMap;
import com.ral.model.enums.IndexedEnum;
import com.ral.model.enums.IndexedEnumUtil;

/**
 * 图片类型枚举
 * Created by victor on 2018/1/31.
 */
public enum ImageTypeEnum implements IndexedEnum<ImageTypeEnum> {

    CATALOG_LOGO(101, "前端目录LOGO", ""),
    CATEGORY_LOGO(102, "商品基础类目LOGO", ""),
    BRAND_LOGO(103, "品牌LOGO", ""),
    ITEM_FACE_IMAGE(201, "商品封面图", ""),
    ITEM_DETAIL_IMAGE(202, "商品橱窗图", ""),
    SKU_FACE_IMAGE(301, "SKU封面图", ""),
    OTHERS(999, "其它", "");

    private int index;

    private String name;

    private String image;

    ImageTypeEnum(int index, String name, String image) {
        this.index = index;
        this.name = name;
        this.image = image;
    }

    private static final ImmutableMap<Integer, ImageTypeEnum> INDEXS = IndexedEnumUtil.toIndexes(values());

    public static ImageTypeEnum indexOf(int index) {
        return INDEXS.get(index);
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
}
