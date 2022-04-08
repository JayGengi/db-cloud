package com.duobang.cloud.modules.sys.controller;


import com.duobang.cloud.modules.sys.model.dto.LoginDto;
import com.duobang.cloud.modules.sys.service.ISysUserService;
import com.duobang.cloud.response.MyResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/sys/login")
public class LoginController {
    @Autowired
    ISysUserService userService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public MyResponseBody login(@RequestBody LoginDto loginDto){
        return MyResponseBody.ok(userService.login(loginDto));
    }

}

