package com.ral.sms.web.controller.sales;

import com.ral.model.query.sales.PayMethodQuery;
import com.ral.model.res.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by victor on 2018/2/2.
 */
@RestController
@RequestMapping("/payMethod/*")
public class PayMethodController {

    //分页查询
    @RequestMapping(value = "query",method = RequestMethod.GET)
    public Result query(HttpServletRequest request, PayMethodQuery query){
        return null;
    }

    //查询所有
    @RequestMapping(value = "all",method = RequestMethod.GET)
    public Result all(HttpServletRequest request){
        return null;
    }

    //查询详情
    @RequestMapping(value = "detail/{methodId}",method = RequestMethod.GET)
    public Result detail(HttpServletRequest request,@PathVariable("methodId") Long methodId){
        return null;
    }

    //修改
    @RequestMapping(value = "update/{methodId}",method = RequestMethod.PUT)
    public Result update(HttpServletRequest request,@PathVariable("methodId") Long methodId, @RequestBody  String body){
        return null;
    }

}
