package com.duobang.cloud.modules.sys.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 权限
 * </p>
 *
 * @author JayGengi
 * @since 2022-04-02
 */
@Data
@Builder
@TableName("sys_permission")
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long pid;

    private String name;

    private String path;

    private String method;

    @Override
    public String toString() {
        return "SysPermission{" +
                "id=" + id +
                "pid=" + pid +
                ", name=" + name +
                ", path=" + path +
                ", method=" + method +
                "}";
    }
}
