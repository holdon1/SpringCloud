package org.example.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.example.config.OrderConfig;
import org.example.service.OrderService;
import org.example.service_order.entity.Order;
import org.example.service_order.vo.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.example.Result;

import java.util.List;

@RestController
@RequestMapping("/dev-api/order")
public class OrderController {
    @Autowired
    OrderService orderService;


    @Autowired
    OrderConfig orderConfig;
    /**
     * 查询用户订单信息
     * @param userId
     * @param productIds
     * @return
     */
    @SentinelResource(value = "getOrder")
    @GetMapping("/query")
    public Result<OrderInfo> getOrder(@RequestParam("userId") Long userId,
                                      @RequestParam("productIds") List<Long> productIds) {

        return orderService.getOrder(userId,productIds);
    }

    @GetMapping("/config")
    public Result<String> getConfig(){
//        String res = "order attr: " + this.timeout + ","  + this.autoConfirm;
        String res = "order attr: " + orderConfig.getTimeout();
        return Result.success(res);
    }

}
