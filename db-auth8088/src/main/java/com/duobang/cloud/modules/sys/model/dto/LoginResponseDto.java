package com.duobang.cloud.modules.sys.model.dto;

import com.duobang.cloud.modules.sys.model.entity.SysPermission;
import com.duobang.cloud.modules.sys.model.entity.SysRole;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: DiegoSun
 * @time: 2021/5/8 下午2:35
 * @description:
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {

    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机
     */
    private String phone;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 昵称
     */
    private String nickname;

    private String token;

    @ApiModelProperty("用户权限列表")
    private List<SysPermission> permList;


    @ApiModelProperty("用户角色列表")
    private List<SysRole> roleList;


}
