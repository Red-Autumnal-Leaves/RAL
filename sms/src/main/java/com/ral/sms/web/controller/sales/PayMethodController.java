package com.ral.sms.web.controller.sales;

import com.ral.model.query.sales.PayMethodQuery;
import com.ral.model.res.Result;
import com.ral.sms.business.sales.IPayMethodBusiness;
import com.ral.sms.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by victor on 2018/2/2.
 */
@RestController
@RequestMapping("/payMethod/*")
public class PayMethodController extends BaseController{

    @Autowired
    private IPayMethodBusiness payMethodBusiness;

    //分页查询
    @RequestMapping(value = "query",method = RequestMethod.GET)
    public Result query(HttpServletRequest request, PayMethodQuery query){
        return payMethodBusiness.query(request,query);
    }

    //查询所有
    @RequestMapping(value = "all",method = RequestMethod.GET)
    public Result all(HttpServletRequest request, PayMethodQuery query){
        return payMethodBusiness.all(request,query);
    }

    //查询详情
    @RequestMapping(value = "detail/{methodId}",method = RequestMethod.GET)
    public Result detail(HttpServletRequest request,@PathVariable("methodId") Long methodId){
        return payMethodBusiness.detail(request,methodId);
    }

    //修改
    @RequestMapping(value = "update/{methodId}",method = RequestMethod.PUT)
    public Result update(HttpServletRequest request,@PathVariable("methodId") Long methodId, @RequestBody  String body){
        return payMethodBusiness.update(request,methodId,body,getUser());
    }


    //创建
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public Result save(HttpServletRequest request, @RequestBody  String body){
        return payMethodBusiness.save(request,body,getUser());
    }

}
