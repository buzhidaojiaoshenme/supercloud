package com.example.product.hystrix.controller;


import com.example.product.hystrix.service.HystrixService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("hystrix")
@RestController
public class HystrixController {

    @Resource
    private HystrixService hystrixService;

    @GetMapping("hello")
    public String helloWorld(String userName) throws Exception {
        String user = hystrixService.getUser(userName);
        return user;
    }
}
