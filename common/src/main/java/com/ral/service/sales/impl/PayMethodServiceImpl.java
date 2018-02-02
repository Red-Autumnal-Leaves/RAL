package com.ral.service.sales.impl;

import com.ral.dao.sales.PayMethodMapper;
import com.ral.model.dto.sales.PayMethodDto;
import com.ral.model.entity.sales.PayMethod;
import com.ral.model.entity.sales.PayMethodExample;
import com.ral.model.query.sales.PayMethodQuery;
import com.ral.service.sales.IPayMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 2018/2/2.
 */
@Service
public class PayMethodServiceImpl implements IPayMethodService {

    @Autowired
    private PayMethodMapper payMethodMapper;

    @Override
    public List<PayMethod> selectByExample(PayMethodExample example) {
        example =  example == null ? new PayMethodExample() : example;
        List<PayMethod> methods = payMethodMapper.selectByExample(example);
        return methods == null ? new ArrayList<>() : methods;
    }

    @Override
    public int countByExample(PayMethodExample example) {
        example =  example == null ? new PayMethodExample() : example;
        return payMethodMapper.countByExample(example);
    }

    @Override
    public List<PayMethodDto> query(PayMethodQuery query) {
        List<PayMethodDto> dtos = payMethodMapper.query(query);
        return dtos == null ? new ArrayList<>() : dtos;
    }

    @Override
    public int queryCount(PayMethodQuery query) {
        return payMethodMapper.queryCount(query);
    }

    @Override
    public PayMethodDto selectDtoById(Long id) {
        return payMethodMapper.selectDtoById(id);
    }

    @Override
    public PayMethod selectById(Long id) {
        return payMethodMapper.selectByPrimaryKey(id);
    }

    @Override
    public int save(PayMethod method) {
        return payMethodMapper.insertSelective(method);
    }

    @Override
    public int update(PayMethod method) {
        return payMethodMapper.updateByPrimaryKeySelective(method);
    }
}
