package com.ral.service.express.impl;

import com.ral.dao.express.ExpressMapper;
import com.ral.model.entity.express.Express;
import com.ral.model.entity.express.ExpressExample;
import com.ral.service.express.IExpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return null;
    }

    @Override
    public int save(Express express) {
        return 0;
    }

    @Override
    public int update(Express express) {
        return 0;
    }
}
