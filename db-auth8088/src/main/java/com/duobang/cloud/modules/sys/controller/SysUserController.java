package com.duobang.cloud.modules.sys.controller;


import com.duobang.cloud.modules.sys.service.ISysPermissionService;
import com.duobang.cloud.modules.sys.service.ISysUserService;
import com.duobang.cloud.response.MyResponseBody;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author JayGengi
 * @since 2022-04-02
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/sys/user")
public class SysUserController {
    @Autowired
    ISysUserService userService;


    @GetMapping("/checkToken")
    public MyResponseBody checkToken(@RequestParam("token") String token) {
        userService.checkToken(token);
        return MyResponseBody.ok();
    }

    @GetMapping("/ignore")
    public MyResponseBody ignore() {
        return MyResponseBody.ok("ignore");
    }

    @GetMapping("/test")
    public String test() {
        return "This is starter test";
    }

    @GetMapping("/test2")
    public String test2() {
        return "This is starter test2";
    }

    @GetMapping("/exception")
    public String exception() throws Exception {
        throw new Exception("TestException");
    }
}

