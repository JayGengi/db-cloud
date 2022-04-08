package com.duobang.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/** 
  * @des　  nacos 配置客户端
  * @author JayGengi
  * @time　 2022/3/25 14:02
 */
@RestController
@RefreshScope //支持nacos的动态刷新功能
public class ConfigClientController {
    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")
    public String getConfigInfo() {
        return configInfo;
    }
}
