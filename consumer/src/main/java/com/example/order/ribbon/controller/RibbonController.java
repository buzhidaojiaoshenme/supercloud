package com.example.order.ribbon.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RequestMapping("ribbon")
@RestController
public class RibbonController {

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("helloWorld")
    public ResponseEntity<String> helloWorld(HttpServletRequest request) {
        //ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:8081/ribbon/add?a=15&b=20", String.class, request);
        ResponseEntity<String> entity = restTemplate.getForEntity("http://PROVIDER/ribbon/add?a=15&b=22", String.class);
        System.out.println(entity);
        return entity;
    }
}
