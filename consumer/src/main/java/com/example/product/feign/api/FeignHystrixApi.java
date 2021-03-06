package com.example.product.feign.api;

import com.example.product.feign.fallback.FeignHystrixApiFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "provider", fallback = FeignHystrixApiFallBack.class)
public interface FeignHystrixApi {

    @GetMapping("/ribbon/add")
    String add(@RequestParam("a") Integer a, @RequestParam("b") Integer b);
}
