package com.ral.sms.web.controller.express;

import com.ral.model.query.express.ExpressQuery;
import com.ral.model.res.Result;
import com.ral.sms.business.express.IExpressBusiness;
import com.ral.sms.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by victor on 2018/2/1.
 */
@RestController
@RequestMapping("/express/*")
public class ExpressController extends BaseController {

    @Autowired
    private IExpressBusiness expressBusiness;

    //查询所有
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Result all(HttpServletRequest request){
        return expressBusiness.all(request);
    }

    //分页查询
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public Result query(HttpServletRequest request, ExpressQuery query){
        return expressBusiness.query(request,query);
    }

    //查询单个
    @RequestMapping(value = "/detail/{expressId}", method = RequestMethod.GET)
    public Result detail(HttpServletRequest request,@PathVariable("expressId") Long expressId){
        return expressBusiness.detail(request,expressId);
    }

    //创建
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(HttpServletRequest request, @RequestBody String body){
        return expressBusiness.save(request,body,getUser());
    }

    //修改
    @RequestMapping(value = "/update/{expressId}", method = RequestMethod.PUT)
    public Result update(HttpServletRequest request,@PathVariable("expressId") Long expressId, @RequestBody String body){
        return expressBusiness.update(request,expressId,body,getUser());
    }
}
