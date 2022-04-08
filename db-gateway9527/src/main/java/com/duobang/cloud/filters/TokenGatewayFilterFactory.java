package com.duobang.cloud.filters;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.duobang.cloud.config.AuthExclusion;
import com.duobang.cloud.service.AuthService;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

/** 
  * @des　  token filter    
  * @author JayGengi
  * @time　 2022/4/7 10:58
 */
@Component
@Slf4j
public class TokenGatewayFilterFactory extends AbstractGatewayFilterFactory<TokenGatewayFilterFactory.Config> implements Ordered {

    @Autowired
    private AuthExclusion exclusion;

    @Autowired
    private AuthService authService;
    /**
     * TODO 待完善
     * db-common {@link com.duobang.cloud.response.MyResponseCode.UNAUTHENTICATED}
     */
    private final String UNAUTHENTICATED = "40100";

    public TokenGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Lists.newArrayList("token");
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpResponse response = exchange.getResponse();
            String path = exchange.getRequest().getPath().toString();
            log.info("进入了全局过滤器,path:[{}]", path);
            //1、判断是否是过滤的路径， 是的话就放行
//            if (exclusion.isExclusionUrl(path) ){
//                return chain.filter(exchange);
//            }
//            String tokenValue = exchange.getRequest().getHeaders().getFirst(config.getToken());
//            log.info("从请求中获取到的token value 是:[{}]", tokenValue);
//            // token是否存在
//            if(StrUtil.isBlank(tokenValue) || !authService.checkToken(tokenValue)){
//                return getVoidMono(response, UNAUTHENTICATED, "未登录");
//            }
            return chain.filter(exchange);
        };
    }

    @Override
    public int getOrder() {
        return 1;
    }

    @Data
    public static class Config {
        private String token;
    }

    private Mono<Void> getVoidMono(ServerHttpResponse response, String code, String msg) {
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        response.setStatusCode(HttpStatus.OK);

        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);

        byte[] bits = json.toJSONString().getBytes();
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        return response.writeWith(Mono.just(buffer));
    }
}
