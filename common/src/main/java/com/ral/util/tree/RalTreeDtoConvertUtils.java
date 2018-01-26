package com.ral.util.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 2018/1/26.
 */
public class RalTreeDtoConvertUtils {


    /**
     * 将List转换为tree
     * @param nodes
     * @return
     */
    public static <T extends IRalTreeDto> List<T> convertToTree(List<T> nodes){
        List<T> tree = new ArrayList<>();
        if(nodes == null || nodes.isEmpty()){
            return tree;
        }
        nodes.parallelStream().forEach(node->{
            if(node.getParentKey().equals(node.getDefaultParentKey())){
                queryTreeByParentKey(node,nodes);
                tree.add(node);
            }
        });
        return tree;
    }

    /**
     * 查询某个父级节点下所有节点，返回树形结构
     * @return
     */
    public static <T extends IRalTreeDto> void queryTreeByParentKey(T parent ,List<T> nodes){
        for(T node : nodes){
            if(node.getParentKey().equals(parent.getKey())){
                queryTreeByParentKey(node,nodes);
                parent.getChildrens().add(node);
            }
        }
    }


}
