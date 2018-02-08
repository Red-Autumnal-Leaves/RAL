package com.ral.sms.web.controller.spec;

import com.ral.model.query.spec.SpecQuery;
import com.ral.model.res.Result;
import com.ral.sms.business.spec.ISpecBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by victor on 2018/2/8.
 */
@RestController
@RequestMapping("/spec/*")
public class SpecController {

    @Autowired
    private ISpecBusiness specBusiness;

    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public Result query(HttpServletRequest request, SpecQuery query){
        return specBusiness.query(request,query);
    }

    @RequestMapping(value = "/query/{categoryId}",method = RequestMethod.GET)
    public Result queryByCategory(HttpServletRequest request,@PathVariable("categoryId") Long categoryId){
        return specBusiness.query(request,categoryId);
    }

    @RequestMapping(value = "/detail/{specId}",method = RequestMethod.GET)
    public Result detail(HttpServletRequest request, @PathVariable("specId") Long specId){
        return specBusiness.detail(request,specId);
    }

    @RequestMapping(value = "/delete/{specId}",method = RequestMethod.DELETE)
    public Result delete(HttpServletRequest request, @PathVariable("specId") Long specId){
        return null;
    }

    @RequestMapping(value = "/update/specId}",method = RequestMethod.PUT)
    public Result update(HttpServletRequest request, @PathVariable("specId") Long specId,@RequestBody  String body){
        return null;
    }

    @RequestMapping(value = "/save/{categoryId}",method = RequestMethod.POST)
    public Result save(HttpServletRequest request,@PathVariable("categoryId") Long categoryId,  @RequestBody  String body){
        return null;
    }

    @RequestMapping(value = "/remove/{specId}/value/{valueId}",method = RequestMethod.DELETE)
    public Result removeValue(HttpServletRequest request, @PathVariable("specId") Long specId,@PathVariable("valueId") Long valueId){
        return null;
    }

}
