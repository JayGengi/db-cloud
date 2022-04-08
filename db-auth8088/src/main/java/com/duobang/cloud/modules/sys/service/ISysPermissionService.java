package com.duobang.cloud.modules.sys.service;

import com.duobang.cloud.modules.sys.model.entity.SysPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限 服务类
 * </p>
 *
 * @author JayGengi
 * @since 2022-04-02
 */
public interface ISysPermissionService extends IService<SysPermission> {

    void checkAuth(String token, String path);

    List<SysPermission> permListByUserId(Long userId);
}
