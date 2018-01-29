package com.ral.service.member.impl;

import com.ral.dao.member.MemberMapper;
import com.ral.model.dto.member.MemberDto;
import com.ral.model.entity.member.Member;
import com.ral.model.entity.member.MemberExample;
import com.ral.model.enums.member.MemberStatusEnum;
import com.ral.model.query.member.MemberQuery;
import com.ral.service.member.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 2018/1/29.
 */
@Service
public class MemberServiceImpl implements IMemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public List<MemberDto> query(MemberQuery query) {
        List<MemberDto> dtos = memberMapper.query(query);
        return dtos == null ? new ArrayList<>() : dtos;
    }

    @Override
    public int queryCount(MemberQuery query) {
        return memberMapper.queryCount(query);
    }

    @Override
    public MemberDto getMemberDtoById(Long id) {
        return memberMapper.getMemberDto(id);
    }

    @Override
    public int updateStatus(Long id, MemberStatusEnum status) {
        Member member  = new Member();
        member.setId(id);
        member.setStatus(status.getIndex());
        return memberMapper.updateByPrimaryKeySelective(member);
    }

    @Override
    public Member getMemberById(Long id) {
        return memberMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Member> selectByExample(MemberExample example) {
        return memberMapper.selectByExample(example);
    }
}
