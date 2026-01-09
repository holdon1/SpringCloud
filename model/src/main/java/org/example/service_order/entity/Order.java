package org.example.service_order.entity;

import lombok.Data;

import java.util.List;

@Data
public class Order {
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
     * 商品集合
     */
    private List<Long>productIdList;
}
