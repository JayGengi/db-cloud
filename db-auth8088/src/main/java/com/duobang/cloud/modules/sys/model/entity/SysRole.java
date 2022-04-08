package com.duobang.cloud.modules.sys.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author JayGengi
 * @since 2022-04-02
 */
@Data
@Builder
@TableName("sys_role")
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    /**
     * 类型
     */
    private String code;


    @Override
    public String toString() {
        return "SysRole{" +
                "id=" + id +
                ", name=" + name +
                ", code=" + code +
                "}";
    }
}
