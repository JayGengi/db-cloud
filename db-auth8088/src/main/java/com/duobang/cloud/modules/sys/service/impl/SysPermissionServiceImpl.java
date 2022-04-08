package com.duobang.cloud.modules.sys.service.impl;

import com.duobang.cloud.exception.impl.ForbiddenException;
import com.duobang.cloud.modules.sys.model.entity.SysPermission;
import com.duobang.cloud.modules.sys.mapper.SysPermissionMapper;
import com.duobang.cloud.modules.sys.service.ISysPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duobang.cloud.utils.Constant;
import com.duobang.cloud.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author JayGengi
 * @since 2022-04-02
 */
@Service
@Slf4j
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SysPermissionMapper permissionMapper;

    @PostConstruct
    public void loadRoleUriToRedis() {
        log.info("---loadRoleUriToRedis --- PostConstruct");
    }

    @Override
    public void checkAuth(String token, String path) {
        TokenUtil.TokenCachedUser cachedUser = TokenUtil.getCachedUserByToken(redisTemplate,token);
        boolean hasAuth = redisTemplate.boundSetOps(Constant.USER_PERMISSION + cachedUser.getUserId()).isMember(path);
        if (hasAuth) {
            return;
        }
        throw new ForbiddenException();
    }

    @Override
    public List<SysPermission> permListByUserId(Long userId) {
        return permissionMapper.permListByUserId(userId);
    }
}
