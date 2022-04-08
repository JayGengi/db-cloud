package com.duobang.cloud.feign;

import cn.hutool.json.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
  * @des　  feign api 调用
  * @author JayGengi
  * @time　 2022/4/1 14:40
 */
@FeignClient("nacos-payment-service") //服务名
public interface PaymentFeignService {

    @GetMapping("/payment/{id}")
    JSONObject get(@PathVariable("id") Integer id);
}
