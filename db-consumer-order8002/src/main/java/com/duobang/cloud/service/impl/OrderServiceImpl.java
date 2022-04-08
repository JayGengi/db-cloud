package com.duobang.cloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duobang.cloud.dao.OrderMapper;
import com.duobang.cloud.entity.Payment;
import com.duobang.cloud.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Payment>  implements OrderService {
}
