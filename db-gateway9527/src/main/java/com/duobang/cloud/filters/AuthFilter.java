package com.duobang.cloud.filters;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.duobang.cloud.config.AuthExclusion;
import com.duobang.cloud.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/** 
  * @des　  全局过滤器    
  * @author JayGengi
  * @time　 2022/4/1 15:51
 */
@Slf4j
@Component
public class AuthFilter implements GlobalFilter, Ordered {

    @Autowired
    private AuthExclusion exclusion;

    @Autowired
    private AuthService authService;
    /**
     * TODO 待完善
     * db-common {@link com.duobang.cloud.response.MyResponseCode.UNAUTHENTICATED}
     */
    private final String UNAUTHENTICATED = "40100";
    /**
     * TODO 待完善
     * db-common {@link com.duobang.cloud.response.MyResponseCode.FORBIDDEN}
     */
    private final String NO_PERMISSION = "40300";
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String path = exchange.getRequest().getPath().toString();
        log.info("进入了全局过滤器,path:[{}]", path);
        //1、判断是否是过滤的路径， 是的话就放行
        if (exclusion.isExclusionUrl(path) ){
            return chain.filter(exchange);
        }
        String tokenValue = exchange.getRequest().getHeaders().getFirst("token");
        log.info("从请求中获取到的token value 是:[{}]", tokenValue);
        // 2.token是否存在
        if(StrUtil.isBlank(tokenValue) || !authService.checkToken(tokenValue)){
            return getVoidMono(response, UNAUTHENTICATED, "未登录");
        }
        //3、判断请求的URL是否有权限
        if(!authService.checkAuth(request)){
            return getVoidMono(response, NO_PERMISSION, "无访问权限");
        }
        return chain.filter(exchange.mutate().request(request).build());
    }

    /**
     * 指定全局过滤器执行的顺序
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 3;
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
