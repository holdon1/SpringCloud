package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.ProductConfig;
import org.example.service_product.entity.Product;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.example.Result;

@RestController
@RequestMapping("/dev-api/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductConfig  productConfig;

    /**
     * 根据商品id获取商品信息
     * @param id 商品id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Product> getProductInfo(@PathVariable("id") Long id,HttpServletRequest request) {
        System.out.println("token: "+request.getHeader("token"));

        return productService.getProduct(id);
    }

    @GetMapping("/config")
    public Result<String> getConfig(){
//        String res = "order attr: " + this.timeout + ","  + this.autoConfirm;
        String res = "order attr: " + productConfig.getTimeout();
        return Result.success(res);
    }
}
