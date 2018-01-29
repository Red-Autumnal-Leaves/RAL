package com.ral.sms.web.controller.member;

import com.ral.model.auth.res.Manager;
import com.ral.model.query.member.MemberQuery;
import com.ral.model.res.Result;
import com.ral.sms.business.member.IMemberBusiness;
import com.ral.sms.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by victor on 2018/1/29.
 */
@RestController
@RequestMapping("/member/*")
public class MemberController extends BaseController{

    @Autowired
    private IMemberBusiness memberBusiness;

    /**
     * 分页查询
     * @param request
     * @param query
     * @return
     */
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public Result query(HttpServletRequest request, MemberQuery query){
        return memberBusiness.query(request,query);
    }

    /**
     * 查询单个详情
     * @param request
     * @param memberId
     * @return
     */
    @RequestMapping(value = "/detail/{memberId}",method = RequestMethod.GET)
    public Result detail(HttpServletRequest request,@PathVariable("memberId") Long memberId){
        return memberBusiness.detail(request,memberId);
    }



    @RequestMapping(value = "/{memberId}/enable/{status}",method = RequestMethod.PUT)
    public Result enable(HttpServletRequest request,@PathVariable("memberId") Long memberId,@PathVariable("status") Integer status){
        Manager manager = getUser();
        return memberBusiness.enable(request,memberId,status,manager);
    }

}
