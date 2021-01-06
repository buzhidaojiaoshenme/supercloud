package com.example.consumer.feign.fallback;

import com.example.consumer.feign.api.FeignHystrixApi;
import org.springframework.stereotype.Service;

@Service
public class FeignHystrixApiFallBack implements FeignHystrixApi {

    @Override
    public String add(Integer a, Integer b) {
        return "出错了,这是fallback信息, a = " + a +  ", b = " + b;
    }
}
