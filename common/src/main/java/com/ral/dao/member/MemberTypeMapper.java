package com.ral.dao.member;

import java.util.List;

import com.ral.model.dto.member.MemberTypeDto;
import com.ral.model.entity.member.MemberType;
import com.ral.model.entity.member.MemberTypeExample;
import com.ral.model.query.member.MemberTypeQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberTypeMapper {

    int countByExample(MemberTypeExample example);

    int deleteByExample(MemberTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MemberType record);

    int insertSelective(MemberType record);

    List<MemberType> selectByExample(MemberTypeExample example);

    MemberType selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MemberType record, @Param("example") MemberTypeExample example);

    int updateByExample(@Param("record") MemberType record, @Param("example") MemberTypeExample example);

    int updateByPrimaryKeySelective(MemberType record);

    int updateByPrimaryKey(MemberType record);

    int queryCount(MemberTypeQuery query);

    List<MemberTypeDto> query(MemberTypeQuery query);

    MemberTypeDto getDtoById(@Param("id")Long id);

    List<MemberTypeDto> getAll();
}