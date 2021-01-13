package com.example.order.feign.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "helloFeignApi", url = "https://api.github.com")
public interface HelloFeignApi {

    @GetMapping("/search/repositories")
    String getRepo(@RequestParam("q") String queryStr);

    @GetMapping("/search/repositories")
    ResponseEntity<byte[]> getRepoCompress(@RequestParam("q") String queryStr);
}
