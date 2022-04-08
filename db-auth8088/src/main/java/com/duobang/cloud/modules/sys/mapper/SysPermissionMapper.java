package com.duobang.cloud.modules.sys.mapper;

import com.duobang.cloud.modules.sys.model.entity.SysPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 权限 Mapper 接口
 * </p>
 *
 * @author JayGengi
 * @since 2022-04-02
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    @Select("select * from sys_permission")
    List<SysPermission> permList();

    @Select("SELECT\n" +
            "p.* \n" +
            "FROM\n" +
            "\tsys_permission p\n" +
            "\tINNER JOIN sys_role_permission rp ON p.id = rp.permission_id\n" +
            "\tINNER JOIN sys_user_role r ON r.role_id = rp.role_id \n" +
            "WHERE\n" +
            "\tr.user_id =${userId}")
    List<SysPermission> permListByUserId(@Param("userId") Long userId);
}
