package com.ral.model.dto;

import java.util.List;
/**
 * 通用树形接口
 * Created by victor on 2018/1/26.
 */
public interface IRalTreeDto<T extends IRalTreeDto> {

    /**
     * 获取当前节点的KEY
     * @return
     */
    String getKey();

    /**
     * 要求存在父级节点 key
     * @return
     */
    String getParentKey();

    /**
     * 默认父级节点，如第一级的父级ID
     * @return
     */
    String getDefaultParentKey();


    /**
     * 存储子级节点
     * @return
     */
    List<T> getChildrens();



}
