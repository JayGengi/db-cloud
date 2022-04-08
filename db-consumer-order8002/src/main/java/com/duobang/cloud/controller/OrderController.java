package com.duobang.cloud.controller;

import cn.hutool.json.JSONObject;
import com.duobang.cloud.feign.PaymentFeignService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/consumer")
public class OrderController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/payment/{id}")
    public JSONObject get(@PathVariable("id")Integer id){
        return paymentFeignService.get(id);
    }
}
