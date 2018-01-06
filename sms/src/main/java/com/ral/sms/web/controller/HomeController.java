package com.ral.sms.web.controller;

import com.ral.dao.member.MemberMapper;
import com.ral.model.entity.member.MemberExample;
import com.ral.model.enums.id.IdTypeEnums;
import com.ral.model.res.Result;
import com.ral.service.file.IFileStoreService;
import com.ral.service.id.IdGeneraterService;
import com.ral.service.redis.IRedisService;
import com.ral.service.session.IRedisSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author victor
 * @desc
 * @date 2018/1/5 0:37
 */
@RestController
public class HomeController {


    @Autowired
    private IRedisService redisService;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private IRedisSessionService redisSessionService;

    @Autowired
    private IFileStoreService fileStoreService;

    @Autowired
    private IdGeneraterService idGeneraterService;

    @RequestMapping("/")
    public Result home(){
        redisService.set("test","111");
        System.out.println(redisSessionService.getKey());
        fileStoreService.testConfig();
        return Result.initSuccessResult(memberMapper.selectByExample(new MemberExample()),null);
    }

    @RequestMapping("/id")
    public Result id(){
        return Result.initSuccessResult(idGeneraterService.generate(IdTypeEnums.ORDER),null);
    }

    @RequestMapping("/id/{count}")
    public Result ids(@PathVariable("count") int count){
        return Result.initSuccessResult(idGeneraterService.generate(IdTypeEnums.DEFAULT,count),null);
    }
}
