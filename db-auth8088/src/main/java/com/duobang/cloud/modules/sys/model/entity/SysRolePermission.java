package com.duobang.cloud.modules.sys.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 角色与权限对应关系
 * </p>
 *
 * @author JayGengi
 * @since 2022-04-02
 */
@Data
@Builder
@TableName("sys_role_permission")
public class SysRolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 权限ID
     */
    private Long permissionId;


    @Override
    public String toString() {
        return "SysRolePermission{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", permissionId=" + permissionId +
                "}";
    }
}
