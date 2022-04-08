package com.duobang.cloud.modules.sys.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author JayGengi
 * @since 2022-04-02
 */
@Data
@Builder
@TableName("sys_user")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
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

    /**
     * 状态:0待审,1正常,2禁用
     */
    private Integer state;

    /**
     * 类型:0运维、1用户
     */
    private Integer type;

    private LocalDateTime createTime;

    private LocalDateTime lastLoginTime;

    private String lastLoginIp;


    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", username=" + username +
                ", password=" + password +
                ", phone=" + phone +
                ", avatar=" + avatar +
                ", nickname=" + nickname +
                ", state=" + state +
                ", type=" + type +
                ", createTime=" + createTime +
                ", lastLoginTime=" + lastLoginTime +
                ", lastLoginIp=" + lastLoginIp +
                "}";
    }
}
