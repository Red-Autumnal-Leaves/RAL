package com.ral.dao.stock;

import java.util.List;

import com.ral.model.entity.stock.SkuStock;
import com.ral.model.entity.stock.SkuStockExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SkuStockMapper {

    int countByExample(SkuStockExample example);

    int deleteByExample(SkuStockExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SkuStock record);

    int insertSelective(SkuStock record);

    List<SkuStock> selectByExample(SkuStockExample example);

    SkuStock selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SkuStock record, @Param("example") SkuStockExample example);

    int updateByExample(@Param("record") SkuStock record, @Param("example") SkuStockExample example);

    int updateByPrimaryKeySelective(SkuStock record);

    int updateByPrimaryKey(SkuStock record);
}