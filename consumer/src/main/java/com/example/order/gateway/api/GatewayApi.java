package com.example.order.gateway.api;

import com.example.order.feign.fallback.FeignHystrixApiFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "provider", fallback = FeignHystrixApiFallBack.class)
public interface GatewayApi {

    @GetMapping("/gateway/hello")
    String hello(@RequestParam("userName") String userName);
}
