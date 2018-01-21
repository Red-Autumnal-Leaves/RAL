package com.ral.dao.sales;

import java.util.List;

import com.ral.model.entity.sales.SalesOrder;
import com.ral.model.entity.sales.SalesOrderExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesOrderMapper {
    int countByExample(SalesOrderExample example);

    int deleteByExample(SalesOrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SalesOrder record);

    int insertSelective(SalesOrder record);

    List<SalesOrder> selectByExample(SalesOrderExample example);

    SalesOrder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SalesOrder record, @Param("example") SalesOrderExample example);

    int updateByExample(@Param("record") SalesOrder record, @Param("example") SalesOrderExample example);

    int updateByPrimaryKeySelective(SalesOrder record);

    int updateByPrimaryKey(SalesOrder record);
}