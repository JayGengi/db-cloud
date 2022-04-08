package com.duobang.cloud.controller;

import com.duobang.cloud.entity.Payment;
import com.duobang.cloud.response.MyResponseBody;
import com.duobang.cloud.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/payment")
public class PaymentController {

    private PaymentService paymentService;

    @PostMapping()
    public MyResponseBody sava(@RequestBody Payment payment){
        return MyResponseBody.ok(paymentService.save(payment));
    }

    @GetMapping()
    public MyResponseBody list(){
        return MyResponseBody.ok(paymentService.list());
    }

    @GetMapping(value = "/{id}")
    public MyResponseBody get(@PathVariable("id")Integer id){
        return MyResponseBody.ok(paymentService.getById(id));
    }
}
