package com.ral.service.sales;

import com.ral.model.dto.sales.PayMethodDto;
import com.ral.model.entity.sales.PayMethod;
import com.ral.model.entity.sales.PayMethodExample;
import com.ral.model.query.sales.PayMethodQuery;

import java.util.List;

/**
 * Created by victor on 2018/2/2.
 */
public interface IPayMethodService {

    List<PayMethod> selectByExample(PayMethodExample example);

    int countByExample(PayMethodExample example);

    List<PayMethodDto> query(PayMethodQuery query);

    int queryCount(PayMethodQuery query);

    PayMethodDto selectDtoById(Long id);

    PayMethod selectById(Long id);

    int save(PayMethod method);

    int updateSelective(PayMethod method);

    int update(PayMethod method);
}
