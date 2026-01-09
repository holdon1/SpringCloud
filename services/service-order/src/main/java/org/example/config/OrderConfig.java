package org.example.config;

import feign.Retryer;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "order")
@Component
@Data
public class OrderConfig {
    private String timeout;
//    private String autoConfirm;
    @Bean
    public Retryer retryer(){
        return new Retryer.Default();
    }
}
