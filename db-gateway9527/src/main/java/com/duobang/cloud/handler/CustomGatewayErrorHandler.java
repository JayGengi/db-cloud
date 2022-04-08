//package com.duobang.cloud.handler;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.common.collect.Maps;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.gateway.support.NotFoundException;
//import org.springframework.core.annotation.Order;
//import org.springframework.core.io.buffer.DataBufferFactory;
//import org.springframework.http.MediaType;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ResponseStatusException;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.server.WebExceptionHandler;
//import reactor.core.publisher.Mono;
//
//import java.util.Map;
//
///**
//  * 网关错误处理
//  * 1、处理网关程序发生的错误，比如：网关Controller发生的错误
//  * 2、处理没有路由错误
//  * 3、下游微服务抛出异常，无法处理
//  * @author JayGengi
//  * @time　 2022/4/1 16:39
// */
//@Slf4j
//@Component
//@Order(Integer.MIN_VALUE)
//public class CustomGatewayErrorHandler implements WebExceptionHandler {
//
//    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
//
//    @Override
//    public Mono<Void> handle(ServerWebExchange exchange, Throwable throwable) {
//        Map<String, Object> json = Maps.newHashMap();
//        ServerHttpRequest request = exchange.getRequest();
//        ServerHttpResponse response = exchange.getResponse();
//        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
//
//        if (exchange.getResponse().isCommitted()) {
//            return Mono.error(throwable);
//        }
//
//        log.info("{} 网关层发生的异常类型为:[{}],url:[{}]", exchange.getLogPrefix(), throwable.getClass().getName(),
//                request.getURI().toString());
//
//        if (throwable instanceof NotFoundException) {
//            json.put("code", 404);
//            json.put("msg", "对应的微服务没有找到");
//        } else if (throwable instanceof ResponseStatusException) {
//            json.put("code", ((ResponseStatusException) throwable).getStatus().value());
//            json.put("msg", "网关层发生了业务异常");
//        } else if (throwable instanceof Exception) {
//            json.put("code", 500);
//            json.put("msg", "网关层发生了服务器内部异常");
//        }
//
//        return response
//                .writeWith(Mono.fromSupplier(() -> {
//                    DataBufferFactory bufferFactory = response.bufferFactory();
//                    try {
//                        return bufferFactory.wrap(OBJECT_MAPPER.writeValueAsBytes(json));
//                    } catch (Exception ex) {
//                        log.warn("writing response error", ex);
//                        return bufferFactory.wrap(new byte[0]);
//                    }
//                }));
//    }
//}
