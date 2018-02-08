package com.ral.service.brand.impl;

import com.ral.dao.brand.BrandMapper;
import com.ral.model.dto.brand.BrandDto;
import com.ral.model.entity.brand.Brand;
import com.ral.model.entity.brand.BrandExample;
import com.ral.model.query.brand.BrandQuery;
import com.ral.service.brand.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 2018/2/8.
 */
@Service
public class BrandServiceImpl implements IBrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> selectByExample(BrandExample example) {
        example = example == null ? new BrandExample() : example;
        List<Brand> brands =  brandMapper.selectByExample(example);
        return brands == null ? new ArrayList<>() : brands;
    }

    @Override
    public int countByExample(BrandExample example) {
        example = example == null ? new BrandExample() : example;
        return brandMapper.countByExample(example);
    }

    @Override
    public int insert(Brand brand) {
        return brandMapper.insert(brand);
    }

    @Override
    public int insertSelective(Brand brand) {
        return brandMapper.insertSelective(brand);
    }

    @Override
    public int update(Brand brand) {
        return brandMapper.updateByPrimaryKey(brand);
    }

    @Override
    public int updateSelective(Brand brand) {
        return brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public BrandDto selectDtoById(Long brandId) {
        return brandMapper.selectDtoById(brandId);
    }

    @Override
    public Brand selectById(Long brandId) {
        return brandMapper.selectByPrimaryKey(brandId);
    }

    @Override
    public List<BrandDto> query(BrandQuery query) {
        List<BrandDto> dtos = brandMapper.query(query);
        return dtos == null ? new ArrayList<>() : dtos;
    }

    @Override
    public int queryCount(BrandQuery query) {
        return brandMapper.queryCount(query);
    }
}
