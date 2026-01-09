package com.example;

import net.bytebuddy.asm.Advice;
import net.bytebuddy.utility.nullability.AlwaysNull;
import org.example.OrderMainApplication;
import org.example.config.OrderConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = OrderMainApplication.class)
public class ConfigTest {

    private OrderConfig orderConfig = new OrderConfig();

    @Test
    public void testOrderConfig(){
        System.out.println(orderConfig.getTimeout());
    }

}
