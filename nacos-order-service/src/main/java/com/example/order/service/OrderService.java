package com.example.order.service;


import com.example.order.pojo.Order;

import java.util.List;

public interface OrderService {

    /**
     * 三种不同的方式分别组装order信息
     * @param id
     * @return
     */
    List<Order> listOrderById(Integer id);

}