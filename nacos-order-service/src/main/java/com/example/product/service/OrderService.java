package com.example.product.service;


import com.example.product.pojo.Order;

public interface OrderService {

    /**
     * 根据主键查询订单
     *
     * @param id
     * @return
     */
    Order selectOrderById(Integer id);

    /**
     * 根据主键查询订单
     *
     * @param id
     * @return
     */
    Order queryOrderById(Integer id);

    /**
     * 根据主键查询订单
     *
     * @param id
     * @return
     */
    Order searchOrderById(Integer id) throws Exception;

}