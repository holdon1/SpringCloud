package org.example.service_order.vo;

import lombok.Data;
import org.example.service_product.entity.Product;

import java.util.List;

@Data
public class OrderInfo {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 用户地址
     */
    private String address;

    /**
     * 商品信息集合
     */
    private List<Product>productList;

}
