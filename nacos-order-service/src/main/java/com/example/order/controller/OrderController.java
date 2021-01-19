package com.example.order.controller;

import com.example.order.service.OrderService;
import com.example.order.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    /**
     * 根据主键查询订单-调用商品服务 /product/{id}
     *
     * @param id
     * @return
     */
    @GetMapping("/product/{id}")
    public List<Order> searchOrderById(@PathVariable("id") Integer id) throws Exception {
        return orderService.listOrderById(id);
    }

}