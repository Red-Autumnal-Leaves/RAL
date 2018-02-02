package com.ral.dao.sales;

import com.ral.model.dto.sales.PayMethodDto;
import com.ral.model.entity.sales.PayMethod;
import com.ral.model.entity.sales.PayMethodExample;
import java.util.List;

import com.ral.model.query.sales.PayMethodQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PayMethodMapper {

    int countByExample(PayMethodExample example);

    int deleteByExample(PayMethodExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PayMethod record);

    int insertSelective(PayMethod record);

    List<PayMethod> selectByExample(PayMethodExample example);

    PayMethod selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PayMethod record, @Param("example") PayMethodExample example);


    int updateByExample(@Param("record") PayMethod record, @Param("example") PayMethodExample example);


    int updateByPrimaryKeySelective(PayMethod record);

    int updateByPrimaryKey(PayMethod record);

    PayMethodDto selectDtoById(@Param("id") Long methodId);

    List<PayMethodDto> query(PayMethodQuery query);

    int queryCount(PayMethodQuery query);


}