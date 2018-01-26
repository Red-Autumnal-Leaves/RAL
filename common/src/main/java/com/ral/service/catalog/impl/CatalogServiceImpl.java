package com.ral.service.catalog.impl;

import com.ral.constants.redis.RedisKeyConstants;
import com.ral.dao.catalog.CatalogMapper;
import com.ral.model.dto.catalog.CatalogDto;
import com.ral.model.entity.catalog.Catalog;
import com.ral.model.entity.catalog.CatalogExample;
import com.ral.service.catalog.ICatalogService;
import com.ral.service.redis.IRedisService;
import com.ral.util.codec.JSONUtils;
import com.ral.util.codec.StringUtils;
import com.ral.util.tree.RalTreeDtoConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 2018/1/26.
 */
@Service
public class CatalogServiceImpl implements ICatalogService {

    @Autowired
    private CatalogMapper catalogMapper;

    @Autowired
    private IRedisService redisService;

    @Override
    public List<Catalog> selectByExample(CatalogExample example) {
        example = example == null ? new CatalogExample() : example;
        List<Catalog> catalogs = catalogMapper.selectByExample(example);
        return catalogs == null ? new ArrayList<>() : catalogs;
    }

    @Override
    public CatalogDto detail(Long catalogId) {
        return catalogMapper.queryDtoById(catalogId);
    }

    @Override
    public int delete(Long catalogId) {
        int res = catalogMapper.deleteByPrimaryKey(catalogId);
        if(res > 0){
            redisService.del(RedisKeyConstants.CATALOG_TREE_KEY);
        }
        return res;
    }

    @Override
    public int update( Catalog catalog) {
        int res = catalogMapper.updateByPrimaryKeySelective(catalog);
        if(res > 0){
            redisService.del(RedisKeyConstants.CATALOG_TREE_KEY);
        }
        return res;
    }

    @Override
    public List<CatalogDto> getAllTree() {
        String value = redisService.get(RedisKeyConstants.CATALOG_TREE_KEY);
        if(StringUtils.isNullOrEmpty(value)){
            List<CatalogDto> tree = RalTreeDtoConvertUtils.convertToTree(catalogMapper.queryAllDto());
            redisService.set(RedisKeyConstants.CATALOG_TREE_KEY, JSONUtils.toJson(tree));
            return tree;
        }
        return JSONUtils.toList(value,CatalogDto.class);
    }


    @Override
    public List<CatalogDto> getAllDto() {
        List<CatalogDto> dtos = catalogMapper.queryAllDto();
        return dtos == null ? new ArrayList<>() : dtos;
    }


}
