package org.example.service.Impl;

import jakarta.servlet.http.HttpServletRequest;
import org.example.Result;
import org.example.service_product.entity.Product;
import org.example.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Result<Product> getProduct(Long id) {


        // 构造一个Product数据
        Product product = new Product();
        product.setId(id);
        product.setCategory(1L);
        product.setDescription("进口香蕉");
        product.setPrice(new BigDecimal("12.5"));
        product.setName("香蕉");
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
