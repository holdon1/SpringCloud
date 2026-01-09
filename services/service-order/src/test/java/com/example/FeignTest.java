package com.example;

import org.example.OrderMainApplication;
import org.example.feign.WeatherFeignClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = OrderMainApplication.class)
public class FeignTest {
    @Autowired
    WeatherFeignClient weatherFeignClient;
    @Test
    public void weatherFeignClientTest(){

        String weather = weatherFeignClient.getWeather("","50b53ff8dd7d9fa320d3d3ca32cf8ed1","2182");


    }

}
