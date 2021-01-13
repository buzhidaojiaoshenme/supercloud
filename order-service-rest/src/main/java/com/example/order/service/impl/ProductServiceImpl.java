package com.example.order.service.impl;

import com.example.order.pojo.Product;
import com.example.order.service.ProductService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 商品管理(请求降级)
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * 查询商品列表
	 *
	 * @return
	 */
	@Override
	public List<Product> selectProductList() {
		// ResponseEntity: 封装了返回数据
		return restTemplate.exchange(
				"http://product-service/product/list",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<Product>>() {
				}).getBody();
	}


	/**
	 * 根据多个主键查询商品
	 *
	 * @param ids
	 * @return
	 */
	@Override
	public List<Product> selectProductListByIds(List<Integer> ids) {
		StringBuffer sb = new StringBuffer();
		ids.forEach(id -> sb.append("id=" + id + "&"));
		return restTemplate.getForObject("http://product-service/product/listByIds?" + sb.toString(), List.class);
	}

	/**
	 * 根据主键查询商品
	 *
	 * @param id
	 * @return
	 */
	// 声明需要服务容错的方法
	// 服务降级
	@HystrixCommand(commandProperties = {
			// 请求错误率大于 50% 就启动熔断器，然后 for 循环发起重试请求，当请求符合熔断条件触发 fallbackMethod
			@HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE, value = "50"),
			// 熔断多少秒后去重试请求，默认 5s
 			@HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS, value = "5000")
	}, fallbackMethod = "selectProductByIdFallback")
	@Override
	public Product selectProductById(Integer id) {
		return restTemplate.getForObject("http://product-service/product/" + id, Product.class);
	}


	// 托底数据
	private Product selectProductByIdFallback(Integer id) {
		return new Product(id, "托底数据", 1, 2666D);
	}

}