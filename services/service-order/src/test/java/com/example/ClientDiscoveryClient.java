package com.example;

import org.example.OrderMainApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;

@SpringBootTest(classes = OrderMainApplication.class)
public class ClientDiscoveryClient {

    @Autowired
    DiscoveryClient discoveryClient;
    @Test
    void testDiscoveryClient(){
        // 1.获取微服务名集合
        List<String> services = discoveryClient.getServices();
        // 2.遍历微服务名字集合，然后获取实例
        for (String service : services) {
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            // 3.获取实例后，获得每一个实例的ip+端口
            for (ServiceInstance instance : instances) {
                System.out.println("url: " + instance.getHost() + ":" + instance.getPort());
            }
        }

    }


}
