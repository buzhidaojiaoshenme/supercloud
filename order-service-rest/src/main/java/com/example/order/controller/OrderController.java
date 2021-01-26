package com.example.order.controller;

import com.example.order.pojo.Order;
import com.example.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Value("${catalina.base}")
    private String catalinaBase;

    /**
     * 根据主键查询订单-调用商品服务 /product/list
     *
     * @param id
     * @return
     */
    @GetMapping("/product/list/{id}")
    public Order selectOrderById(@PathVariable("id") Integer id) {
        return orderService.selectOrderById(id);
    }

    /**
     * 测试sleuth调用链路
     *
     * @param id
     * @return
     */
    @GetMapping("/listByIds/{id}")
    public Order queryOrderById(@PathVariable("id") Integer id) {
        System.out.println("日志路径: " + catalinaBase);
        return orderService.queryOrderById(id);
    }

    /**
     * 根据主键查询订单-调用商品服务 /product/{id}
     *
     * @param id
     * @return
     */
    @GetMapping("/product/{id}")
    public Order searchOrderById(@PathVariable("id") Integer id) throws Exception {
        return orderService.searchOrderById(id);
    }

}