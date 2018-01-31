package com.ral.service.member;

import com.ral.model.dto.member.MemberTypeDto;
import com.ral.model.entity.member.MemberType;
import com.ral.model.entity.member.MemberTypeExample;
import com.ral.model.query.member.MemberTypeQuery;
import java.util.List;
/**
 * Created by victor on 2018/1/31.
 */
public interface IMemberTypeService  {

    List<MemberType> selectByExample(MemberTypeExample example);

    MemberType selectById(Long id);

    List<MemberTypeDto> query(MemberTypeQuery query);

    int queryCount(MemberTypeQuery query);

    List<MemberTypeDto> getAll();

    MemberTypeDto selectDtoById(Long id);

    int update(MemberType memberType);

    int save(MemberType memberType);

    int del(Long id);
}
