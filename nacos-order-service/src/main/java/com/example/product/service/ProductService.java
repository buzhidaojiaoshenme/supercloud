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
@FeignClient(value = "product-service")
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
    @GetMapping("/product/{id}")
    Product selectProductById(@PathVariable("id") Integer id);

}