package com.example.consumer.ribbon.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * ribbon负载均衡规则的配置
 */
//@Configuration
//@AvoidScan
public class RibbonRuleConfig {

    //@Resource
    //private IClientConfig iClientConfig;

    /**
     * 针对所有ribbon请求的全局配置
     * <p>
     * 默认情况下，bean的名称由方法名决定
     *
     * @return
     */
    //@Bean
    public IRule ribbonRule1() {
        return new RandomRule();
    }

    /**
     * 针对单个RibbonClient的config
     *
     * @param iClientConfig
     * @return
     */
    //@Bean
    public IRule ribbonRule2(IClientConfig iClientConfig) {
        System.out.println(iClientConfig);
        return new RandomRule();
    }

}
