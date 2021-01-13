package com.example.order.service.impl;

import com.example.order.pojo.Product;
import com.example.order.service.ProductService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 商品管理(请求降级)
 */
@Log4j2
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
	@Override
	public Product selectProductById(Integer id) throws Exception {
		return restTemplate.getForObject("http://product-service/product/" + id, Product.class);
	}

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
			@HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ENABLED, value = "true"),
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
	@Override
	public Product selectProductByIdTestCircuitBreaker(Integer id) throws Exception {
		log.info("=======================断路器熔断测试: " + LocalDateTime.now());
		if(id == 2) {
			//模拟发生错误的情况,这个错误可以是在本地方法发生的,也可以是调用远程方法时,远程方法内发生的
			//(时间窗,最小请求次数,失败百分比,熔断时间),10秒内,请求次数达到5次,失败率在50%,熔断5秒
			throw new Exception("查询主键为 2 的商品信息导致异常");
		}
		return restTemplate.getForObject("http://product-service/product/" + id, Product.class);
	}


	// 托底数据
	private Product selectProductByIdFallback(Integer id) {
		return new Product(id, "托底数据", 1, 2666D);
	}

}