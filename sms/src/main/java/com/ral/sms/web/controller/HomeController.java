package com.ral.sms.web.controller;

import com.ral.model.res.Result;
import com.ral.service.mongo.IRalOperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author victor
 * @desc
 * @date 2018/1/5 0:37
 */
@RestController
public class HomeController {

    @Autowired
    private IRalOperationLogService ralOperationLogService;

    @RequestMapping("/")
    public Result home(){
        return Result.initSuccessResult(ralOperationLogService.findAll(),null);
    }



}
