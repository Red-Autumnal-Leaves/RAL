package com.ral.service.member;

import com.ral.model.dto.member.MemberDto;
import com.ral.model.entity.member.Member;
import com.ral.model.entity.member.MemberExample;
import com.ral.model.enums.member.MemberStatusEnum;
import com.ral.model.query.member.MemberQuery;
import java.util.List;
/**
 * Created by victor on 2018/1/29.
 */
public interface IMemberService {

    List<MemberDto> query(MemberQuery query);

    int queryCount(MemberQuery query);

    MemberDto getMemberDtoById(Long id);

    int updateStatus(Long id, MemberStatusEnum status);

    Member getMemberById(Long id);

    List<Member> selectByExample(MemberExample example);

}
