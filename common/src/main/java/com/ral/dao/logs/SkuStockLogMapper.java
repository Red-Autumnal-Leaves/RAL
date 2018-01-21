package com.ral.dao.logs;

import java.util.List;

import com.ral.model.entity.logs.SkuStockLog;
import com.ral.model.entity.logs.SkuStockLogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SkuStockLogMapper {

    int countByExample(SkuStockLogExample example);

    int deleteByExample(SkuStockLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SkuStockLog record);

    int insertSelective(SkuStockLog record);

    List<SkuStockLog> selectByExample(SkuStockLogExample example);

    SkuStockLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SkuStockLog record, @Param("example") SkuStockLogExample example);

    int updateByExample(@Param("record") SkuStockLog record, @Param("example") SkuStockLogExample example);

    int updateByPrimaryKeySelective(SkuStockLog record);

    int updateByPrimaryKey(SkuStockLog record);
}