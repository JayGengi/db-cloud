package com.duobang.cloud;

import com.duobang.cloud.utils.AutowiredBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/** 
  * @des　  gateway
  * @author JayGengi
  * @time　 2022/3/31 15:37
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class GateWayMain9527 {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(GateWayMain9527.class, args);
        AutowiredBean.setApplicationContext(run);
    }
}
