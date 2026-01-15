package org.example.feign;
import jakarta.servlet.http.HttpServletRequest;
import org.example.feign.fallback.ProductFeignClientFallback;
import org.example.service_product.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.example.Result;
@FeignClient(value = "service-product",fallback = ProductFeignClientFallback.class)
public interface ProductFeignClient {
    @GetMapping("/dev-api/product/{id}")
    Result<Product> getProductById(@PathVariable Long id);
}
