package com.example.product.gateway.controller;


import com.example.product.gateway.api.GatewayApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("gateway")
@RestController
public class GatewayController {

    @Resource
    private GatewayApi gatewayapi;

    @GetMapping("test")
    public String test(String userName) {
        String hello = gatewayapi.hello(userName);
        return hello;
    }
}
