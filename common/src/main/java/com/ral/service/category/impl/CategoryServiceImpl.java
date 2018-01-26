package com.ral.service.category.impl;

import com.ral.constants.redis.RedisKeyConstants;
import com.ral.dao.category.CategoryMapper;
import com.ral.model.dto.category.CategoryDto;
import com.ral.model.entity.category.Category;
import com.ral.model.entity.category.CategoryExample;
import com.ral.service.category.ICategoryService;
import com.ral.service.redis.IRedisService;
import com.ral.util.codec.JSONUtils;
import com.ral.util.codec.StringUtils;
import com.ral.util.tree.RalTreeDtoConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author victor
 * @desc
 * @date 2018/1/26 20:53
 */
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private IRedisService redisService;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> selectByExample(CategoryExample example) {
        example = example == null ? new CategoryExample(): example;
        List<Category> categories = categoryMapper.selectByExample(example);
        return categories == null ? new ArrayList<>() : categories;
    }

    @Override
    public List<CategoryDto> getAllTree() {
        String value = redisService.get(RedisKeyConstants.CATEGORY_TREE_KEY);
        if(StringUtils.isNullOrEmpty(value)){
            List<CategoryDto> tree = RalTreeDtoConvertUtils.convertToTree(categoryMapper.queryAllDtos());
            redisService.set(RedisKeyConstants.CATEGORY_TREE_KEY, JSONUtils.toJson(tree));
            return tree;
        }
        return JSONUtils.toList(value,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllDto() {
        List<CategoryDto> dtos = categoryMapper.queryAllDtos();
        return dtos == null ? new ArrayList<>() : dtos;
    }

    @Override
    public CategoryDto detail(Long categoryId) {
        return categoryMapper.getDtoByCategoryId(categoryId);
    }

    @Override
    public int delete(Long categoryId) {
        int res = categoryMapper.deleteByPrimaryKey(categoryId);
        if(res > 0){
            redisService.del(RedisKeyConstants.CATEGORY_TREE_KEY);
        }
        return res;
    }

    @Override
    public int update(Category category) {
        int res = categoryMapper.updateByPrimaryKeySelective(category);
        if(res > 0){
            redisService.del(RedisKeyConstants.CATEGORY_TREE_KEY);
        }
        return res;
    }


}
