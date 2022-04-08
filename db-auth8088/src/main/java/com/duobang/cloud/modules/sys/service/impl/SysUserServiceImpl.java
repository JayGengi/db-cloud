package com.duobang.cloud.modules.sys.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.duobang.cloud.exception.impl.AuthenticatedException;
import com.duobang.cloud.exception.impl.BadRequestException;
import com.duobang.cloud.modules.sys.model.dto.LoginDto;
import com.duobang.cloud.modules.sys.model.dto.LoginResponseDto;
import com.duobang.cloud.modules.sys.model.entity.SysPermission;
import com.duobang.cloud.modules.sys.model.entity.SysUser;
import com.duobang.cloud.modules.sys.mapper.SysUserMapper;
import com.duobang.cloud.modules.sys.service.ISysPermissionService;
import com.duobang.cloud.modules.sys.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duobang.cloud.utils.Constant;
import com.duobang.cloud.utils.MyDateUtil;
import com.duobang.cloud.utils.TokenUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author JayGengi
 * @since 2022-04-02
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private ISysPermissionService permissionService;
    @Override
    public SysUser getUserByPhone(String phone) {
        return userMapper.getUserByPhone(phone);
    }

    @Override
    public void checkToken(String token) {
        // 缓存中是否存在该token
            TokenUtil.TokenCachedUser tokenCachedUser = TokenUtil.getCachedUserByToken(redisTemplate,token);
            if(ObjectUtil.isNull(tokenCachedUser)){
                throw new AuthenticatedException();
            }
    }

    @Override
    public LoginResponseDto login(LoginDto loginDto) {
        SysUser userBo = getUserByPhone(loginDto.getPhone());
        if(ObjectUtil.isNull(userBo) || !loginDto.getPassword().equals(userBo.getPassword())){
            throw new BadRequestException("用户不存在或密码错误");
        }
        // 返回
        LoginResponseDto responseDto = new LoginResponseDto();
        BeanUtils.copyProperties(userBo, responseDto);
        String token = TokenUtil.saveUserToken(redisTemplate, userBo.getId(), userBo.getPhone());
        responseDto.setToken(token);
        List<SysPermission> permissionList = permissionService.permListByUserId(userBo.getId());

        // 考虑到数据量有限，就不做细颗粒度更新缓存了
        if(CollUtil.isNotEmpty(permissionList)){
            responseDto.setPermList(permissionList);
            permissionList.forEach(item -> redisTemplate.boundSetOps(Constant.USER_PERMISSION + userBo.getId()).add(item.getPath()));
        }
        return responseDto;
    }
}
