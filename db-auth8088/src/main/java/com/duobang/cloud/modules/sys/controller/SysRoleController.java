package com.duobang.cloud.modules.sys.controller;


import com.duobang.cloud.modules.sys.service.ISysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色 前端控制器
 * </p>
 *
 * @author JayGengi
 * @since 2022-04-02
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping("/sys/role")
public class SysRoleController {


}

