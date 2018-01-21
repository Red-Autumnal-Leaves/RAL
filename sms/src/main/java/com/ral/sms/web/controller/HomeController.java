package com.ral.sms.web.controller;

import com.ral.model.res.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author victor
 * @desc
 * @date 2018/1/5 0:37
 */
@RestController
public class HomeController {


    @RequestMapping("/")
    public Result home(){

        return Result.initSuccessResult(null,null);
    }

}
