package com.ral.auth.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by victor on 2018/1/23.
 */
@RestController
public class ExampleController {

    @RequestMapping("/")
    public String home(){
        return "hello";
    }

}
