package com.ral.dao.sku;

import java.util.List;

import com.ral.model.dto.sku.SkuDto;
import com.ral.model.entity.sku.Sku;
import com.ral.model.entity.sku.SkuExample;
import com.ral.model.query.sku.SkuQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SkuMapper {

    int countByExample(SkuExample example);

    int deleteByExample(SkuExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Sku record);

    int insertSelective(Sku record);

    List<Sku> selectByExample(SkuExample example);

    Sku selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Sku record, @Param("example") SkuExample example);

    int updateByExample(@Param("record") Sku record, @Param("example") SkuExample example);

    int updateByPrimaryKeySelective(Sku record);

    int updateByPrimaryKey(Sku record);

    List<SkuDto> query(SkuQuery query);

    int queryCount(SkuQuery query);

    List<SkuDto> selectDtoByItemCodes(@Param("itemCodes")List<String> itemCodes);

}