package com.ral.dao.sales;

import java.util.List;

import com.ral.model.entity.sales.SalesOrderLine;
import com.ral.model.entity.sales.SalesOrderLineExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesOrderLineMapper {

    int countByExample(SalesOrderLineExample example);

    int deleteByExample(SalesOrderLineExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SalesOrderLine record);

    int insertSelective(SalesOrderLine record);

    List<SalesOrderLine> selectByExample(SalesOrderLineExample example);

    SalesOrderLine selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SalesOrderLine record, @Param("example") SalesOrderLineExample example);

    int updateByExample(@Param("record") SalesOrderLine record, @Param("example") SalesOrderLineExample example);

    int updateByPrimaryKeySelective(SalesOrderLine record);

    int updateByPrimaryKey(SalesOrderLine record);
}