package com.example.order.service.impl;

import com.example.order.pojo.Product;
import com.example.order.service.OrderService;
import com.example.order.service.ProductService;
import com.example.order.pojo.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private ProductService productService;

    /**
     * 三种不同的方式分别组装order信息
     *
     * @param id
     * @return
     */
    @Override
    public List<Order> listOrderById(Integer id) {

        List<String> services = discoveryClient.getServices();
        services.forEach(System.out::println);

        System.out.println("========================================================================");

        ServiceInstance choose = loadBalancerClient.choose("nacos-product-service");
        System.out.println(choose);

        List<Order> orderList = new ArrayList<>();
        //feignClient的形式
        Order order1 = new Order(id, "feignClient-order-001", "中国", 22788D, productService.selectProductList());
        orderList.add(order1);
        Order order2 = new Order(id, "restTemplate-order-001", "中国", 22788D, selectProductListByRestTemplate());
        orderList.add(order2);
        return orderList;
    }

    /**
     * 使用resttemplate调用服务
     * @return
     */
    public List<Product> selectProductListByRestTemplate() {
        String url = "http://nacos-product-service/product/list";
        ResponseEntity<List<Product>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {
                }
        );
        return response.getBody();
    }

    /**
     * 使用webClient调用服务
     * @return
     */
    public List<Product> selectProductListByWebClient() {
        String url = "http://nacos-product-service/product/list";
        ResponseEntity<List<Product>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {
                }
        );
        return response.getBody();
    }


}