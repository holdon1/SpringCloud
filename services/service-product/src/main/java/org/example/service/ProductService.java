package org.example.service;

import jakarta.servlet.http.HttpServletRequest;
import org.example.Result;
import org.example.service_product.entity.Product;
import org.springframework.stereotype.Service;

public interface ProductService {
    /**
     * 根据商品id获取商品信息
     * @param id 商品id
     * @return
     */
    Result<Product> getProduct(Long id);
}
