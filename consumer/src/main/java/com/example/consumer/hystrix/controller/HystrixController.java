package com.example.consumer.hystrix.controller;


import com.example.consumer.hystrix.service.HystrixService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
