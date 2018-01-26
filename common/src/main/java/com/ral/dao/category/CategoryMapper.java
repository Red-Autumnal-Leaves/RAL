package com.ral.dao.category;

import java.util.List;

import com.ral.model.dto.category.CategoryDto;
import com.ral.model.entity.category.Category;
import com.ral.model.entity.category.CategoryExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryMapper {

    int countByExample(CategoryExample example);

    int deleteByExample(CategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Category record);

    int insertSelective(Category record);

    List<Category> selectByExample(CategoryExample example);

    Category selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByExample(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    List<CategoryDto> queryAllDtos();

    CategoryDto getDtoByCategoryId(@Param("categoryId")Long categoryId);

}