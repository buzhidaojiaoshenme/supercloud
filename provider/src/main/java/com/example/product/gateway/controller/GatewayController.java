package com.example.product.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("gateway")
@RestController
public class GatewayController {

    @GetMapping("/hello")
    public String add(String userName, HttpServletRequest request) throws InterruptedException {
        //Thread.sleep(10000);
        return "from " + request.getServerPort() + " hello,world:  " + userName;
    }


}
