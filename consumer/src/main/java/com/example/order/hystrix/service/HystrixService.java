package com.example.order.hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

@Service
public class HystrixService {

    @HystrixCommand(fallbackMethod="defaultUser")
    public String getUser(String userName) throws Exception {
        if("spring".equalsIgnoreCase(userName)) {
            return "this is a real user";
        } else {
            throw new  Exception();
        }
    }


    /**
     * 出错则调用此方法返回预设友好方法
     * @return
     */
    public String defaultUser(String userName) {
        return "hello, i am default user";
    }
}
