package com.ral.sms.web.controller;

import com.ral.model.enums.HttpStatusEnum;
import com.ral.model.res.Result;
import com.ral.service.mail.IJMailService;
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

    @Autowired
    private IJMailService jMailService;

    @RequestMapping("/")
    public Result home(){
        return Result.initSuccessResult(null,null);
    }


    @RequestMapping("/log")
    public Result log(){
        return Result.initSuccessResult(ralOperationLogService.findAll(),null);
    }

    @RequestMapping("/send/main")
    public Result sendMail(){
        try {
            jMailService.send("378961642@qq.com","测试","hello",null);
            return Result.initSuccessResult(null,null);
        }catch (Exception ex){
            return Result.initErrorResult(HttpStatusEnum.ERROR,ex.getMessage());
        }
    }


}
