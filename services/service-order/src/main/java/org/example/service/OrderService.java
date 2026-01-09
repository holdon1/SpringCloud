package org.example.service;

import org.example.Result;
import org.example.service_order.entity.Order;
import org.example.service_order.vo.OrderInfo;

import java.util.List;

public interface OrderService {

    /**
     * 查询用户订单信息
     * @param userId 用户id
     * @param productIds 商品信息id
     * @return
     */
    Result<OrderInfo> getOrder(Long userId, List<Long> productIds);
}
