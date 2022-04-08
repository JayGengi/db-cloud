package com.duobang.cloud.runner;

import com.duobang.cloud.modules.sys.model.entity.SysPermission;
import com.duobang.cloud.modules.sys.model.entity.SysRole;
import com.duobang.cloud.modules.sys.model.entity.SysRolePermission;
import com.duobang.cloud.modules.sys.model.entity.SysUser;
import com.duobang.cloud.modules.sys.service.ISysPermissionService;
import com.duobang.cloud.modules.sys.service.ISysRolePermissionService;
import com.duobang.cloud.modules.sys.service.ISysRoleService;
import com.duobang.cloud.modules.sys.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: DiegoSun
 * @time: 2022/3/9 下午4:11
 * @description:
 */
@Slf4j
@Component
public class DataRunner implements ApplicationRunner {

    @Value("${auth.prepare-data}")
    private boolean prepareData;

    @Autowired
    ISysPermissionService permissionService;

    @Autowired
    ISysRoleService roleService;

    @Autowired
    ISysRolePermissionService rolePermissionService;
    @Autowired
    ISysUserService userService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(!prepareData){
            return;
        }
        System.out.println("---start prepare permission---");
        preparePermission();
        System.out.println("---start prepare role---");
        prepareRole();
        System.out.println("---start prepare user---");
        prepareUser();
        System.out.println("---end prepare---");
    }
    private void preparePermission(){
        SysPermission dto = SysPermission.builder()
                .pid(0L)
                .name("test")
                .path("/sys/user/test")
                .method("GET")
                .build();
        permissionService.save(dto);

        SysRole role1 = SysRole.builder()
                .name("创建者")
                .code("OWNER")
                .build();
        roleService.save(role1);
        SysRolePermission sysRolePermission = SysRolePermission.builder().roleId(role1.getId()).permissionId(dto.getId()).build();
        rolePermissionService.save(sysRolePermission);

        SysRole role2 = SysRole.builder()
                .name("管理员")
                .code("ADMIN")
                .build();
        roleService.save(role2);

        SysRole role3 = SysRole.builder()
                .name("成员")
                .code("USER")
                .build();
        roleService.save(role3);
        List<SysPermission> permissionList = permissionService.list();
        log.debug("permissionList---{}",permissionList);
    }

    private void prepareRole(){
        List<SysRole> roleList = roleService.list();
        log.debug("roleList---{}",roleList);
    }

    private void prepareUser(){
        SysUser user = SysUser.builder()
                .username("JayGengi")
                .nickname("JayGengi")
                .phone("18888888888")
                .password("123456")
                .build();
        userService.save(user);
    }
}
