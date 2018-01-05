package com.ral.task.web.controller;

import com.ral.dao.member.MemberMapper;
import com.ral.model.entity.member.MemberExample;
import com.ral.model.res.Result;
import com.ral.service.file.IFileStoreService;
import com.ral.service.redis.IRedisService;
import com.ral.service.session.IRedisSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by victor on 2018/1/5.
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

    @RequestMapping("/")
    public Result home(){
        redisService.set("test","111");
        System.out.println(redisSessionService.getKey());
        fileStoreService.testConfig();
        return Result.initSuccessResult(memberMapper.selectByExample(new MemberExample()),null);

    }


}
