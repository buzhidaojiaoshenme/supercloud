package com.example.order.service.impl;

import com.example.order.pojo.Product;
import com.example.order.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 商品服务
 */
@Service
public class ProductServiceImpl implements ProductService {

    /**
     * 查询商品列表
     *
     * @return
     */
    @Override
    public List<Product> selectProductList() {
        return Arrays.asList(
                new Product(1, "华为手机", 2, 5888D),
                new Product(2, "联想笔记本", 1, 6888D),
                new Product(3, "小米平板", 5, 2666D)
        );
    }

    /**
     * 根据多个主键查询商品
     *
     * @param ids
     * @return
     */
    @Override
    public List<Product> selectProductListByIds(List<Integer> ids) {
        List<Product> products = new ArrayList<>();
        ids.forEach(id -> products.add(new Product(id, "电视机" + id, 1, 5800D)));
        return products;
    }

    /**
     * 根据主键查询商品
     *
     * @param id
     * @return
     */
    @Override
    public Product selectProductById(Integer id) {
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new Product(id, "冰箱", 1, 2666D);
    }

}