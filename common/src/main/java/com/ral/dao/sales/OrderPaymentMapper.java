package com.ral.dao.sales;

import java.util.List;

import com.ral.model.entity.sales.OrderPayment;
import com.ral.model.entity.sales.OrderPaymentExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderPaymentMapper {

    int countByExample(OrderPaymentExample example);

    int deleteByExample(OrderPaymentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderPayment record);

    int insertSelective(OrderPayment record);

    List<OrderPayment> selectByExample(OrderPaymentExample example);

    OrderPayment selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderPayment record, @Param("example") OrderPaymentExample example);

    int updateByExample(@Param("record") OrderPayment record, @Param("example") OrderPaymentExample example);

    int updateByPrimaryKeySelective(OrderPayment record);

    int updateByPrimaryKey(OrderPayment record);
}