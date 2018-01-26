package com.ral.service.category;

import com.ral.model.dto.category.CategoryDto;
import com.ral.model.entity.category.Category;
import com.ral.model.entity.category.CategoryExample;

import java.util.List;

/**
 * @author victor
 * @desc
 * @date 2018/1/26 20:53
 */
public interface ICategoryService {


    List<Category> selectByExample(CategoryExample example);

    List<CategoryDto> getAllTree();

    List<CategoryDto> getAllDto();

    CategoryDto detail(Long catalogId);

    int delete(Long categoryId);

    int update(Category category);

}
