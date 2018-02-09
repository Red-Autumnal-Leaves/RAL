package com.ral.service.item;

import com.ral.model.dto.item.ItemDto;
import com.ral.model.entity.item.Item;
import com.ral.model.entity.item.ItemExample;
import com.ral.model.query.item.ItemQuery;

import java.util.List;

/**
 * Created by victor on 2018/2/9.
 */
public interface ItemService {

    List<Item> selectByExample(ItemExample example);

    int countByExample(ItemExample example);

    int insertSelective(Item item);

    int updateSelective(Item item);

    int update(Item item);

    Item selectById(Long itemId);

    Item selectByItemCode(String itemCode);

    List<ItemDto> query(ItemQuery query);

    int queryCount(ItemQuery query);

    ItemDto selectDtoById(Long itemId);

    ItemDto selectDtoByItemCode(String itemCode);

}
