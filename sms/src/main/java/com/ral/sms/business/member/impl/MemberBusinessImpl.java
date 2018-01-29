package com.ral.sms.business.member.impl;

import com.ral.model.auth.res.Manager;
import com.ral.model.dto.member.MemberDto;
import com.ral.model.entity.member.Member;
import com.ral.model.enums.HttpStatusEnum;
import com.ral.model.enums.logs.RalOperationResultEnum;
import com.ral.model.enums.logs.RalOperationTypeEnum;
import com.ral.model.enums.member.MemberStatusEnum;
import com.ral.model.query.Query;
import com.ral.model.query.member.MemberQuery;
import com.ral.model.res.Result;
import com.ral.service.member.IMemberService;
import com.ral.service.mongo.IRalOperationLogService;
import com.ral.sms.business.member.IMemberBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by victor on 2018/1/29.
 */
@Service
public class MemberBusinessImpl implements IMemberBusiness {

    @Autowired
    private IMemberService memberService;

    @Autowired
    private IRalOperationLogService ralOperationLogService;

    @Override
    public Result query(HttpServletRequest request, MemberQuery query) {
        query.setPageNow(query.getPageNow() == null || query.getPageNow()<= 0 ? 1 :query.getPageNow());
        query.setPageSize(query.getPageSize() == null || query.getPageSize()<= 0 ? Query.DEFAULT_PAGE_SIZE :query.getPageSize());
        List<MemberDto> dtos = memberService.query(query);
        query.setTotal(memberService.queryCount(query));
        return Result.initSuccessResult(dtos, query);
    }

    @Override
    public Result detail(HttpServletRequest request, Long id) {
        return Result.initSuccessResult(memberService.getMemberDtoById(id),null);
    }

    @Override
    public Result enable(HttpServletRequest request, Long id, Integer status, Manager manager) {
        Member member = memberService.getMemberById(id);
        if(member == null){
            return Result.initErrorResult(HttpStatusEnum.BAD_REQUEST,"Member id " + id + " is not exits");
        }
        MemberStatusEnum statusEnum = MemberStatusEnum.indexOf(status);
        if(statusEnum == null){
            return Result.initErrorResult(HttpStatusEnum.BAD_REQUEST,"Status " + status + " is not exits");
        }
        try {
            memberService.updateStatus(id,statusEnum);
            //add log
            String content = String.format("[%s],修改会员状态 :%s",manager.getName(),status);
            ralOperationLogService.logger(RalOperationTypeEnum.MEMBER_ENABLE_LOG,id.toString(),content, RalOperationResultEnum.INFO);
        }catch (Exception ex){
            return Result.initErrorResult("System error！");
        }
        return Result.initSuccessResult(null,null);
    }


}
