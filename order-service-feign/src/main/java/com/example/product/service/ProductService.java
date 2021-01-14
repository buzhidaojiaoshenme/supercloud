package com.example.product.service;

import com.example.product.fallback.ProductServiceFallbackFactory;
import com.example.product.pojo.Product;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
//熔断降级
// 声明需要调用的服务和服务熔断处理类
@FeignClient(value = "product-service",fallbackFactory = ProductServiceFallbackFactory.class)
public interface ProductService {

    /**
     * 查询商品列表
     *
     * @return
     */
    @GetMapping("/product/list")
    List<Product> selectProductList();

    /**
     * 根据多个主键查询商品
     *
     * @param ids
     * @return
     */
    @GetMapping("/product/listByIds")
    List<Product> selectProductListByIds(@RequestParam("id") List<Integer> ids);

    /**
     * 根据主键查询商品
     *
     * @param id
     * @return
     */
    /**
     * 测试服务熔断
     * 根据主键查询商品
     * 10秒内发起了至少circuitBreaker.requestVolumeThreshold 次数的请求，
     * 失败率超过circuitBreaker.errorThresholdPercentage，
     * 从熔断开启后，
     * 后续circuitBreaker.sleepWindowInMilliseconds之内的请求，都不会发起到远程服务端
     * @param id
     * @return
     */
    @HystrixCommand(commandProperties = {
            // 打开熔断器开关
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ENABLED, value = "false"),
            // 设置单个请求的超时时间,默认是1s,也可以在application.yml中设置全局的
            @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "15000"),
            // 时间窗
            @HystrixProperty(name = HystrixPropertiesManager.METRICS_ROLLING_STATS_TIME_IN_MILLISECONDS, value = "10000"),
            // 请求次数
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD, value = "5"),
            // 失败百分比
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE, value = "50"),
            // 熔断时间 跟上边的(时间窗,最小请求次数,失败百分比)配合,10秒内,请求次数达到5次,失败率在50%,熔断5秒。
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS, value = "5000")
    })
    @GetMapping("/product/{id}")
    Product selectProductById(@PathVariable("id") Integer id);

}