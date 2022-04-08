package com.duobang.cloud.modules.sys.controller;


import com.duobang.cloud.modules.sys.service.ISysPermissionService;
import com.duobang.cloud.response.MyResponseBody;
import com.duobang.cloud.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 权限 前端控制器
 * </p>
 *
 * @author JayGengi
 * @since 2022-04-02
 */
@RestController
@RequestMapping("/sys/permission")
public class SysPermissionController {


    @Autowired
    ISysPermissionService permissionService;

    @GetMapping("/checkAuth")
    public MyResponseBody checkAuth(@RequestParam("token") String token,@RequestParam("path") String path) {

        permissionService.checkAuth(token, path);
        return MyResponseBody.ok();
    }
}

