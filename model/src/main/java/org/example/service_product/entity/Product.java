package org.example.service_product.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class Product {

    /**
     * 主键
     */
    private Long id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 类别
     */
    private Long category;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 描述信息
     */
    private String description;
    /**
     * 创建信息
     */
    private LocalDateTime creationDate;
    /**
     * 更新信息学
     */
    private LocalDateTime updateDate;
}
