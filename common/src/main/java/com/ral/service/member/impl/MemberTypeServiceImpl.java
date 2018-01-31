package com.ral.service.member.impl;

import com.ral.dao.member.MemberTypeMapper;
import com.ral.model.dto.member.MemberTypeDto;
import com.ral.model.entity.member.MemberType;
import com.ral.model.entity.member.MemberTypeExample;
import com.ral.model.query.member.MemberTypeQuery;
import com.ral.service.member.IMemberTypeService;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by victor on 2018/1/31.
 */
@Service
public class MemberTypeServiceImpl implements IMemberTypeService {

    @Autowired
    private MemberTypeMapper memberTypeMapper;

    @Override
    public List<MemberType> selectByExample(MemberTypeExample example) {
        example = example == null ? new MemberTypeExample() : example;
        List<MemberType> memberTypes = memberTypeMapper.selectByExample(example);
        return memberTypes == null ? new ArrayList<>() : memberTypes;
    }

    @Override
    public MemberType selectById(Long id) {
        return memberTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<MemberTypeDto> query(MemberTypeQuery query) {
        query = query == null  ? new MemberTypeQuery() : query;
        List<MemberTypeDto> dtos = memberTypeMapper.query(query);
        return dtos == null ? new ArrayList<>() : dtos;
    }

    @Override
    public int queryCount(MemberTypeQuery query) {
        query = query == null  ? new MemberTypeQuery() : query;
        return memberTypeMapper.queryCount(query);
    }

    @Override
    public List<MemberTypeDto> getAll() {
        List<MemberTypeDto> dtos = memberTypeMapper.getAll();
        return dtos == null ? new ArrayList<>() : dtos;
    }

    @Override
    public MemberTypeDto selectDtoById(Long id) {
        return memberTypeMapper.getDtoById(id);
    }

    @Override
    public int update(MemberType memberType) {
        return memberTypeMapper.updateByPrimaryKeySelective(memberType);
    }

    @Override
    public int save(MemberType memberType) {
        return memberTypeMapper.insertSelective(memberType);
    }

    @Override
    public int del(Long id) {
        return memberTypeMapper.deleteByPrimaryKey(id);
    }
}
