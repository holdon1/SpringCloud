package org.example.feign.fallback;

import org.example.Result;
import org.example.feign.ProductFeignClient;
import org.example.service_product.entity.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Component
public class ProductFeignClientFallback implements ProductFeignClient {
    @Override
    public Result<Product> getProductById(Long id) {
        Product product = new Product();
        product.setId(id);
        product.setCategory(1L);
        product.setDescription("无法显示商品信息");
        product.setPrice(new BigDecimal("0"));
        product.setName("无");
        product.setCreationDate(LocalDateTime.now());
        product.setUpdateDate(LocalDateTime.now());

        // 模拟超时机制
//        try {
//            TimeUnit.SECONDS.sleep(100);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        return Result.success(product);
    }
}
