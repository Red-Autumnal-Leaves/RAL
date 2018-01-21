package com.ral.dao.sku;

import java.util.List;

import com.ral.model.entity.sku.SkuSpec;
import com.ral.model.entity.sku.SkuSpecExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SkuSpecMapper {
    int countByExample(SkuSpecExample example);

    int deleteByExample(SkuSpecExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SkuSpec record);

    int insertSelective(SkuSpec record);

    List<SkuSpec> selectByExample(SkuSpecExample example);

    SkuSpec selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SkuSpec record, @Param("example") SkuSpecExample example);

    int updateByExample(@Param("record") SkuSpec record, @Param("example") SkuSpecExample example);

    int updateByPrimaryKeySelective(SkuSpec record);

    int updateByPrimaryKey(SkuSpec record);
}