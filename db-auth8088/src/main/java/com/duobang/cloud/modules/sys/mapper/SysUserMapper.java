package com.duobang.cloud.modules.sys.mapper;

import com.duobang.cloud.modules.sys.model.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author JayGengi
 * @since 2022-04-02
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    @Select("select * from sys_user where phone = ${phone}")
    SysUser getUserByPhone(@Param("phone") String phone);
}
