//package com.duobang.cloud.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.reactive.CorsWebFilter;
//import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
//import org.springframework.web.util.pattern.PathPatternParser;
//
///**
//  * @des　  Spring-GateWay WebFlux的跨域配置
//  * @author JayGengi
//  * @time　 2022/4/6 13:31
// */
//@Configuration
//public class CorsConfig {
//    @Bean
//    public CorsWebFilter corsFilter() {
//        CorsConfiguration config = new CorsConfiguration();
//        config.addAllowedMethod("*");
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader("*");
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
//        source.registerCorsConfiguration("/**", config);
//
//        return new CorsWebFilter(source);
//    }
//}
