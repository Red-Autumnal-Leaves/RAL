package com.ral.service.express.impl;

import com.ral.dao.express.ExpressMapper;
import com.ral.model.dto.express.ExpressDto;
import com.ral.model.entity.express.Express;
import com.ral.model.entity.express.ExpressExample;
import com.ral.service.express.IExpressService;
import org.apache.tools.ant.taskdefs.EchoXML;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 2018/2/1.
 */
@Service
public class ExpressServiceImpl implements IExpressService {

    @Autowired
    private ExpressMapper expressMapper;

    @Override
    public List<Express> selectByExample(ExpressExample example) {
        example = example == null ? new  ExpressExample() : example;
        return expressMapper.selectByExample(example);
    }

    @Override
    public int countByExample(ExpressExample example) {
        example = example == null ? new  ExpressExample() : example;
        return expressMapper.countByExample(example);
    }

    @Override
    public Express selectById(Long expressId) {
        return expressMapper.selectByPrimaryKey(expressId);
    }

    @Override
    public int save(Express express) {
        return expressMapper.insertSelective(express);
    }

    @Override
    public int update(Express express) {
        return expressMapper.updateByPrimaryKeySelective(express);
    }

    @Override
    public List<ExpressDto> convertToDto(List<Express> expresses) {
        List<ExpressDto> dtos = new ArrayList<>();
        if(expresses != null && !expresses.isEmpty()){
            expresses.forEach(express -> {
                ExpressDto dto = convertToDto(express);
                if(dto != null){
                    dtos.add(dto);
                }
            });
        }
        return dtos;
    }

    @Override
    public ExpressDto convertToDto(Express express) {
        if(express != null){
            ExpressDto dto = new ExpressDto();
            BeanUtils.copyProperties(express,dto);
            return dto;
        }
        return null;
    }

    @Override
    public int countByExpressName(String expressName) {
        ExpressExample example = new ExpressExample();
        example.createCriteria().andExpressNameEqualTo(expressName);
        return expressMapper.countByExample(example);
    }
}
