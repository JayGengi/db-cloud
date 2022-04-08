package com.duobang.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
  * @author JayGengi
  * @time　 2022/3/25 14:09
 */
@EnableDiscoveryClient
@SpringBootApplication
public class NacosConfigClient9101 {
    public static void main(String[] args) {
        SpringApplication.run(NacosConfigClient9101.class,args);
    }
}
