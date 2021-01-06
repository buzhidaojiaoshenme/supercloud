package com.example.consumer.ribbon.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("ribbon")
@RestController
public class RibbonProviderController {

    @GetMapping("/add")
    public String add(Integer a, Integer b, HttpServletRequest request) throws InterruptedException {
        //Thread.sleep(10000);
        return "from " + request.getServerPort() + " result: " + a + " " + b;
    }


}
