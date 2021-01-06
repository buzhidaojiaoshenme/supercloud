package com.example.gateway;

import com.example.gateway.filter.CustomGatewayFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder
//                .routes()
//                .route(r -> r.path("/jd").uri("https://www.baidu.com/").id("jd_route"))
//                .build();
//
//    }

//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        //生成一个比当前时间早一个小时的UTC时间
//        ZonedDateTime zonedDateTime = LocalDateTime.now().minusHours(1L).atZone(ZoneId.systemDefault());
//
//        return builder
//                .routes()
//                .route("after_route", r-> r.after(zonedDateTime).uri("http://baidu.com"))
//                .build();
//
//    }

    /**
     * 将Gateway Filter配置到路由上
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(
                        r -> r.path("/test")
                                .filters(f -> f.filter(new CustomGatewayFilter()))
                                .uri("http://www.baidu.com/")
                                .order(0)
                                .id("custom_filter")
                )
                .build();
    }

}
