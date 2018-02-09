package com.ral.service.sku.impl;

import com.google.common.collect.Lists;
import com.ral.dao.sku.SkuMapper;
import com.ral.model.dto.sku.SkuDto;
import com.ral.model.entity.sku.Sku;
import com.ral.model.entity.sku.SkuExample;
import com.ral.model.query.sku.SkuQuery;
import com.ral.service.sku.ISkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 2018/2/9.
 */
@Service
public class SkuServiceImpl implements ISkuService{

    @Autowired
    private SkuMapper skuMapper;



    @Override
    public List<Sku> selectByExample(SkuExample example) {
        example = example == null ? new SkuExample() : example;
        List<Sku> skus = skuMapper.selectByExample(example);
        return skus == null ? new ArrayList<>() : skus;
    }

    @Override
    public int countByExample(SkuExample example) {
        return skuMapper.countByExample(example);
    }

    @Override
    public List<SkuDto> query(SkuQuery query) {
        List<SkuDto> dtos = skuMapper.query(query);
        return dtos == null ? new ArrayList<>() : dtos;
    }

    @Override
    public int queryCount(SkuQuery query) {
        return skuMapper.queryCount(query);
    }

    @Override
    public List<SkuDto> selectDtoByItemCodes(List<String> itemCodes) {
        if(itemCodes == null || itemCodes.isEmpty()){
            return new ArrayList<>();
        }
        List<SkuDto> dtos = skuMapper.selectDtoByItemCodes(itemCodes);
        return dtos == null ? new ArrayList<>() : dtos;
    }

    @Override
    public List<SkuDto> selectDtoByItemCode(String itemCode) {
        return selectDtoByItemCodes(Lists.newArrayList(itemCode));
    }
}
