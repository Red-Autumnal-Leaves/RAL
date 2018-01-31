package com.ral.sms.web.controller.member;

import com.ral.model.query.member.MemberTypeQuery;
import com.ral.model.res.Result;
import com.ral.sms.business.member.IMemberTypeBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 会员类型controller
 * Created by victor on 2018/1/31.
 */
@RestController
@RequestMapping("/memberType/*")
public class MemberTypeController {

    @Autowired
    private IMemberTypeBusiness memberTypeBusiness;

    /**
     * 查询所有
     * @param request
     * @return
     */
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public Result all(HttpServletRequest request){
        return memberTypeBusiness.all(request);
    }

    /**
     * 分页查询
     * @param request
     * @param query
     * @return
     */
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public Result query(HttpServletRequest request, MemberTypeQuery query){
        return memberTypeBusiness.query(request,query);
    }


    /**
     * 创建
     * @param request
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result save(HttpServletRequest request, @RequestBody String body){
        return memberTypeBusiness.save(request,body);
    }

    /**
     * 修改
     * @param request
     * @return
     */
    @RequestMapping(value = "/update/{id}",method = RequestMethod.PUT)
    public Result update(HttpServletRequest request, @PathVariable("id") Long id,  @RequestBody String body){
        return memberTypeBusiness.update(request,id,body);
    }

    /**
     * 查询单个详情
     * @param request
     * @return
     */
    @RequestMapping(value = "/detail/{memberTypeId}",method = RequestMethod.GET)
    public Result detail(HttpServletRequest request, @PathVariable("memberTypeId") Long memberTypeId){
        return memberTypeBusiness.detail(request,memberTypeId);
    }

    /**
     * 修改
     * @param request
     * @return
     */
    @RequestMapping(value = "/del/{memberTypeId}",method = RequestMethod.DELETE)
    public Result del(HttpServletRequest request, @PathVariable("memberTypeId") Long memberTypeId){
        return memberTypeBusiness.del(request,memberTypeId);
    }



}
