package com.example.product.feign.controller;


import com.example.product.feign.api.FeignHystrixApi;
import com.example.product.feign.api.HelloFeignApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("feign")
@RestController
public class FeignController {

    @Resource
    private HelloFeignApi helloFeignApi;

    @Resource
    private FeignHystrixApi feignHystrixApi;

    @GetMapping("normal")
    public String getRepoByFeignClient(@RequestParam("q") String queryStr) {
        String repo = helloFeignApi.getRepo(queryStr);
        System.out.println(repo);
        return repo;
    }

    @GetMapping("compress")
    public ResponseEntity<byte[]> getCompressRepoByFeignClient(@RequestParam("q") String queryStr) {
        ResponseEntity<byte[]> repo = helloFeignApi.getRepoCompress(queryStr);
        return repo;
    }

    /**
     * 测试feign的断路器
     * @param a
     * @param b
     * @return
     */
    @GetMapping("testFallBack")
    public String testFallBack(Integer a, Integer b) {
        String info = feignHystrixApi.add(a, b);
        return info;
    }
}
