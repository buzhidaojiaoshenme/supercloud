package com.example.product.feign.fallback;

import com.example.product.feign.api.FeignHystrixApi;
import org.springframework.stereotype.Service;

@Service
public class FeignHystrixApiFallBack implements FeignHystrixApi {

    @Override
    public String add(Integer a, Integer b) {
        return "出错了,这是fallback信息, a = " + a +  ", b = " + b;
    }
}
