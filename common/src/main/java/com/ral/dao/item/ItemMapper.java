package com.ral.dao.item;

import java.util.List;

import com.ral.model.dto.item.ItemDto;
import com.ral.model.entity.item.Item;
import com.ral.model.entity.item.ItemExample;
import com.ral.model.query.item.ItemQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemMapper {

    int countByExample(ItemExample example);

    int deleteByExample(ItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Item record);

    int insertSelective(Item record);

    List<Item> selectByExample(ItemExample example);

    Item selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Item record, @Param("example") ItemExample example);

    int updateByExample(@Param("record") Item record, @Param("example") ItemExample example);

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKey(Item record);

    List<ItemDto> query(ItemQuery query);

    int queryCount(ItemQuery query);

    ItemDto selectDtoById(@Param("id")Long id);

    ItemDto selectDtoByItemCode(@Param("itemCode")String itemCode);

}