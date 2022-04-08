package com.duobang.cloud.feign;

import cn.hutool.json.JSONObject;
import com.duobang.cloud.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
  * @des　  feign api 调用
  * @author JayGengi
  * @time　 2022/4/1 14:40
 */
@FeignClient(value = "nacos-auth",path = "/sys",configuration = FeignConfig.class) //服务名
public interface AuthFeignClient {

    @GetMapping("/permission/checkAuth")
    JSONObject checkAuth(@RequestParam String token, @RequestParam String path);

    @GetMapping("/user/checkToken")
    JSONObject checkToken(@RequestParam String token);
}
