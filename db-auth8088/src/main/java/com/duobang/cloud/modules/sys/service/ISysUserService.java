package com.duobang.cloud.modules.sys.service;

import com.duobang.cloud.modules.sys.model.dto.LoginDto;
import com.duobang.cloud.modules.sys.model.dto.LoginResponseDto;
import com.duobang.cloud.modules.sys.model.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author JayGengi
 * @since 2022-04-02
 */
public interface ISysUserService extends IService<SysUser> {

    SysUser getUserByPhone(String phone);

    void checkToken(String token);

    LoginResponseDto login(LoginDto loginDto);
}
