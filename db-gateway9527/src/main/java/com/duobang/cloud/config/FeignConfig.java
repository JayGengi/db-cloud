package com.duobang.cloud.config;

import feign.Logger;
import feign.codec.Decoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
/** 
  * feign中对返回的数据进行解析时，缺少依赖对象导致。
  * 增加HttpMessageConverters
  * @author JayGengi
  * @time　 2022/4/6 16:08
 */
@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feignLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public Decoder decoder() {
        return new ResponseEntityDecoder(new SpringDecoder(feignHttpMessageConverter()));
    }

    private ObjectFactory<HttpMessageConverters> feignHttpMessageConverter() {
        HttpMessageConverters httpMessageConverters = new HttpMessageConverters
                (new MappingJackson2HttpMessageConverter());
        return () -> httpMessageConverters;
    }

}
