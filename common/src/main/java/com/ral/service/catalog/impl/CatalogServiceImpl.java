package com.ral.service.catalog.impl;

import com.google.common.collect.Lists;
import com.ral.constants.redis.RedisKeyConstants;
import com.ral.dao.base.ImageMapper;
import com.ral.dao.catalog.CatalogMapper;
import com.ral.model.dto.catalog.CatalogDto;
import com.ral.model.entity.base.Image;
import com.ral.model.entity.base.ImageExample;
import com.ral.model.entity.catalog.Catalog;
import com.ral.model.entity.catalog.CatalogExample;
import com.ral.service.catalog.ICatalogService;
import com.ral.service.redis.IRedisService;
import com.ral.util.codec.JSONUtils;
import com.ral.util.codec.StringUtils;
import com.ral.util.tree.RalTreeDtoConvertUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by victor on 2018/1/26.
 */
@Service
public class CatalogServiceImpl implements ICatalogService {

    @Autowired
    private CatalogMapper catalogMapper;

    @Autowired
    private IRedisService redisService;

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public List<Catalog> selectByExample(CatalogExample example) {
        example = example == null ? new CatalogExample() : example;
        List<Catalog> catalogs = catalogMapper.selectByExample(example);
        return catalogs == null ? new ArrayList<>() : catalogs;
    }

    @Override
    public CatalogDto detail(Long catalogId) {
        String value = redisService.hget(RedisKeyConstants.CATALOG_MAP,catalogId.toString());
        if(StringUtils.isNullOrEmpty(value)){
            CatalogDto dto = convertToDto(catalogMapper.selectByPrimaryKey(catalogId));
            redisService.hset(RedisKeyConstants.CATALOG_MAP,catalogId.toString(),JSONUtils.toJson(dto));
            return dto;
        }else{
            return JSONUtils.toBean(value,CatalogDto.class);
        }
    }

    @Override
    public int delete(Long catalogId) {
        int res = catalogMapper.deleteByPrimaryKey(catalogId);
        if(res > 0){
            redisService.hdel(RedisKeyConstants.CATALOG_MAP,catalogId.toString());
        }
        return res;
    }

    @Override
    public int update( Catalog catalog) {
        int res = catalogMapper.updateByPrimaryKeySelective(catalog);
        if(res > 0){
            CatalogDto dto = detail(catalog.getId());
            if(dto != null){
                redisService.hset(RedisKeyConstants.CATALOG_MAP,catalog.getId().toString(),JSONUtils.toJson(dto));
            }
        }
        return res;
    }

    @Override
    public List<CatalogDto> getAllTree(CatalogExample example) {
        String value = redisService.get(RedisKeyConstants.CATALOG_TREE_KEY);
        if(StringUtils.isNullOrEmpty(value)){

        }
        List<Catalog> catalogs = selectByExample(example);
        return RalTreeDtoConvertUtils.convertToTree(convertToDto(catalogs));
    }

    @Override
    public List<CatalogDto> convertToDto(List<Catalog> catalogs) {
        //images
        List<Long> imageIds = new ArrayList<>();
        catalogs.forEach(catalog -> {
            imageIds.add(catalog == null || catalog.getLogo() == null ? 0 : catalog.getLogo());
        });
        Map<Long,String> imageUrlMap = new HashMap<>();
        if(!imageIds.isEmpty()){
            ImageExample example = new ImageExample();
            example.createCriteria().andIdIn(imageIds);
            List<Image> images = imageMapper.selectByExample(example);
            if(images != null && !images.isEmpty()){
                imageUrlMap = images.stream().collect(Collectors.toMap(Image::getId, Image::getUrl));
            }
        }
        List<CatalogDto> dtos = new ArrayList<>();
        for(Catalog catalog : catalogs){
            if(catalog == null){
                return null;
            }
            CatalogDto dto = new CatalogDto();
            BeanUtils.copyProperties(catalog,dto);
            dto.setLogoUrl(imageUrlMap.get(dto.getLogo()));
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public CatalogDto convertToDto(Catalog catalog) {
        List<Catalog> catalogs = Lists.newArrayList(catalog);
        List<CatalogDto> dtos = convertToDto(catalogs);
        return dtos == null || dtos.isEmpty() ? null : dtos.get(0);
    }




}
