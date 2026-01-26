package org.example.pridicate;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.cloud.gateway.handler.predicate.QueryRoutePredicateFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
@Component
public class VIPRoutePredicateFactory extends AbstractRoutePredicateFactory<VIPRoutePredicateFactory.Config> {

    public VIPRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("param","value");
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return new GatewayPredicate(){
            @Override
            /**
             * serverWebExchange: 网路交互参数
             */
            public boolean test(ServerWebExchange serverWebExchange) {
                // 获取网络请求
                ServerHttpRequest request = serverWebExchange.getRequest();
                // 得到请求参数
                MultiValueMap<String, String> queryParams = request.getQueryParams();
                String first = queryParams.getFirst(config.param);

                // 验证请求参数值是否为 “haha"，如果true代表VIP
//                return StringUtils.hasText(first) && first.equals(config.value);
                return false;
            }
        };
    }


    @Override
    public String name() {
        return super.name();
    }
    // 配置静态类
    @Validated
    public static class Config {
        private @NotEmpty String param;
        private String value;

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }

        public String getRegexp() {
            return value;
        }

        public void setRegexp(String regexp) {
            this.value = regexp;
        }
    }



}
