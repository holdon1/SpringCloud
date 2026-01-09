package com.example;

import cn.hutool.core.util.RandomUtil;
import org.example.ProductMainApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.DiscoveryClient;

@SpringBootTest(classes = ProductMainApplication.class)
public class ClientDiscoveryClientTest {

    @Test
    public void testRandom() {
        System.out.println(Math.random());
        System.out.println(String.valueOf((int)(Math.random() * 9 + 1) * 100000));
        String code = RandomUtil.randomNumbers(6);
        System.out.println(code );
    }
}
