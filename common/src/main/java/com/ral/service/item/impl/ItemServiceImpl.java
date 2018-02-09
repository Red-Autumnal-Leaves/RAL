package com.ral.service.item.impl;

import com.ral.dao.item.ItemMapper;
import com.ral.model.dto.item.ItemDto;
import com.ral.model.entity.item.Item;
import com.ral.model.entity.item.ItemExample;
import com.ral.model.query.item.ItemQuery;
import com.ral.service.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 2018/2/9.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public List<Item> selectByExample(ItemExample example) {
        example = example == null ? new ItemExample() : example;
        List<Item> items = itemMapper.selectByExample(example);
        return items == null ? new ArrayList<>() : items;
    }

    @Override
    public int countByExample(ItemExample example) {
        example = example == null ? new ItemExample() : example;
        return itemMapper.countByExample(example);
    }

    @Override
    public int insertSelective(Item item) {
        return itemMapper.insertSelective(item);
    }

    @Override
    public int updateSelective(Item item) {
        return itemMapper.updateByPrimaryKeySelective(item);
    }

    @Override
    public int update(Item item) {
        return itemMapper.updateByPrimaryKey(item);
    }

    @Override
    public Item selectById(Long itemId) {
        return itemMapper.selectByPrimaryKey(itemId);
    }

    @Override
    public Item selectByItemCode(String itemCode) {
        ItemExample example = new ItemExample();
        example.createCriteria().andItemCodeEqualTo(itemCode).andIsDisableEqualTo(false);
        List<Item> items = selectByExample(example);
        return items == null || items.isEmpty() ? null : items.get(0);
    }

    @Override
    public List<ItemDto> query(ItemQuery query) {
        List<ItemDto> dtos = itemMapper.query(query);
        return dtos == null ? new ArrayList<>() : dtos;
    }

    @Override
    public int queryCount(ItemQuery query) {
        return itemMapper.queryCount(query);
    }

    @Override
    public ItemDto selectDtoById(Long itemId) {
        return itemMapper.selectDtoById(itemId);
    }

    @Override
    public ItemDto selectDtoByItemCode(String itemCode) {
        return itemMapper.selectDtoByItemCode(itemCode);
    }
}
