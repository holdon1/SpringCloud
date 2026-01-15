package org.example.service.serviceImpl;

import com.alibaba.csp.sentinel.adapter.spring.webmvc_v6x.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.example.Result;
import org.example.feign.ProductFeignClient;
import org.example.service.OrderService;
import org.example.service_order.entity.Order;
import org.example.service_order.vo.OrderInfo;
import org.example.service_product.entity.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private ProductFeignClient productFeignClient;
    /**
     * 获取用户订单信息
     * @param userId 用户id
     * @param productIds 商品信息id
     * @return
     */
    @Override
    @SentinelResource(value = "getOrder",fallback = "getOrderFallback")
    public Result<OrderInfo> getOrder(Long userId, List<Long> productIds) {

        OrderInfo orderInfo = new OrderInfo();

        // 构造假数据
        // todo 手动设置一个异常，检查是否调用兜底函数

        Order order = new Order();
        order.setUserId(userId);
        order.setAddress("街道");
        order.setNickName("爱吃");
        order.setId(1L);

        BeanUtils.copyProperties(order,orderInfo);
        List<Product> products = new ArrayList<>();
        for (Long productId : productIds) {
            // 远程调用product接口，获取用户信息
            // Product product =  getProudtFromRemote(productId);
            Result<Product> productResult = productFeignClient.getProductById(productId);
            if(productResult !=null && productResult.getData()!=null){
                products.add(productResult.getData());
            }
        }
        orderInfo.setProductList(products);

        return Result.success(orderInfo);

    }
    public Result<OrderInfo> getOrderFallback(Long userId, List<Long> productIds, BlockException e) {
        Order order = new Order();
        order.setUserId(userId);
        order.setAddress("");
        order.setNickName("");
        order.setId(0L);
        return Result.error(500,"未知商品信息");
    }

    private Product getProudtFromRemote(Long productId) {
        // 1.通过注册中心名字，获取注册中的服务ip+port
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        ServiceInstance serviceInstance = instances.get(0);
        // 2.拼接字符串获取目标url
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/dev-api/product/" + productId;
        System.out.println("url: " + url);
        log.info("url: " + url);
        // 3.对url发起请求获取product,并且返回
        ParameterizedTypeReference<Result<Product>> typeRef = new ParameterizedTypeReference<>() {};
        // Result<Product> result = restTemplate.getForObject(url, Result.class);
        ResponseEntity<Result<Product>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, typeRef);
        Result<Product> result = responseEntity.getBody();
        if(result == null || result.getData() == null){
            log.error("获取数据失败或返回数据为空");
            return new Product();
        }
        log.info("product: " + result.getData());
        return result.getData();

    }

}
