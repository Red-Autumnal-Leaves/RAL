package com.ral.dao.item;

import java.util.List;

import com.ral.model.entity.item.ItemImage;
import com.ral.model.entity.item.ItemImageExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemImageMapper {

    int countByExample(ItemImageExample example);

    int deleteByExample(ItemImageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ItemImage record);

    int insertSelective(ItemImage record);

    List<ItemImage> selectByExample(ItemImageExample example);

    ItemImage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ItemImage record, @Param("example") ItemImageExample example);

    int updateByExample(@Param("record") ItemImage record, @Param("example") ItemImageExample example);

    int updateByPrimaryKeySelective(ItemImage record);

    int updateByPrimaryKey(ItemImage record);
}