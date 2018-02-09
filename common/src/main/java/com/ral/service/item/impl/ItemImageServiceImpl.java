package com.ral.service.item.impl;

import com.google.common.collect.Lists;
import com.ral.dao.item.ItemImageMapper;
import com.ral.model.dto.item.ItemImageDto;
import com.ral.model.entity.item.ItemImage;
import com.ral.model.entity.item.ItemImageExample;
import com.ral.service.item.ItemImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 2018/2/9.
 */
@Service
public class ItemImageServiceImpl implements ItemImageService {

    @Autowired
    private ItemImageMapper itemImageMapper;

    @Override
    public List<ItemImage> selectByExample(ItemImageExample example) {
        example = example == null ? new ItemImageExample() : example;
        List<ItemImage> images = itemImageMapper.selectByExample(example);
        return images == null ? new ArrayList<>() : images;
    }

    @Override
    public int countByExample(ItemImageExample example) {
        return itemImageMapper.countByExample(example);
    }

    @Override
    public int batchInsert(List<ItemImage> images) {
        return itemImageMapper.batchInsert(images);
    }

    @Override
    public int delete(List<Long> ids) {
        ItemImageExample example = new ItemImageExample();
        example.createCriteria().andIdIn(ids);
        return itemImageMapper.deleteByExample(example);
    }

    @Override
    public List<ItemImageDto> selectDtosByItemCodes(List<String> itemCodes) {
        if(itemCodes == null || itemCodes.isEmpty()){
            return new ArrayList<>();
        }
        List<ItemImageDto> dtos = itemImageMapper.selectDtosByItemCodes(itemCodes);
        return dtos == null ? new ArrayList<>() : dtos;
    }

    @Override
    public List<ItemImageDto> selectDtoByItemCode(String itemCode) {
        return selectDtosByItemCodes(Lists.newArrayList(itemCode));
    }
}
