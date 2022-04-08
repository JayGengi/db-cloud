package com.duobang.cloud.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author JayGengi
 * @des　 TokenUtil
 * @time　 2021/6/1 14:25
 */
public class TokenUtil {

    /**
     * @param redisTemplate
     * @param userId
     * @param username
     */
    public static String saveUserToken(RedisTemplate redisTemplate, Long userId, String username) {
        String token = TokenUtil.generateToken(username);
        int tokenExpire = Constant.TOKEN_EXPIRE;
        TokenCachedUser user = new TokenCachedUser(userId, username);
        redisTemplate.opsForValue().set(getToken(token), user, tokenExpire, TimeUnit.SECONDS);
        redisTemplate.opsForValue().set(userId, token, tokenExpire, TimeUnit.SECONDS);

        return token;
    }

    public static void delUserToken(RedisTemplate redisTemplate, Long userId, String username, String token){
        redisTemplate.delete(getToken(token));
        redisTemplate.delete(userId);
    }

    private static String generateToken(String username) {
        String token = SecureUtil.md5(username + System.currentTimeMillis());
        return token;
    }

    private static String getToken(String rawToken) {
        return Constant.AUTHENTICATION_TOKEN + rawToken;
    }

    public static TokenCachedUser getCachedUserByToken(RedisTemplate redisTemplate, String token) {
        TokenCachedUser tokenCachedUser = (TokenCachedUser) redisTemplate.opsForValue().get(getToken(token));

        return tokenCachedUser;
    }


    /**
     * @param redisTemplate
     * @param userId        场景:用户踢人(挤掉前一个用户)、用户被禁用 删除token
     */
    public static void removeUserToken(RedisTemplate redisTemplate, Long userId) {
        // 实现用户(踢人下线)
        String userOldToken = (String) redisTemplate.opsForValue().get(userId);
        if (StrUtil.isNotEmpty(userOldToken)) {
            redisTemplate.delete(getToken(userOldToken));
            redisTemplate.delete(Constant.USER_AGENT + getToken(userOldToken));
            redisTemplate.delete(userId);
        }
    }

    public static void removeUserTokenByToken(RedisTemplate redisTemplate, String token) {
        TokenCachedUser cachedUser = getCachedUserByToken(redisTemplate, token);
        if (ObjectUtil.isNotNull(cachedUser)) {
            redisTemplate.delete(getToken(token));
            redisTemplate.delete(Constant.USER_AGENT + getToken(token));
            redisTemplate.delete(cachedUser.getUserId());
        }

    }

    /**
     * @param redisTemplate
     * @param key
     */
    public static void removeRedisKey(RedisTemplate redisTemplate, String key) {
        redisTemplate.delete(key);
    }
    /**
     * @param redisTemplate
     * @param userId        用户是否在线
     */
    public static boolean userIsLogin(RedisTemplate redisTemplate, Long userId) {
        // 用户是否在线
        try {
            Object userOldToken = redisTemplate.opsForValue().get(userId);
            return ObjectUtil.isNotNull(userOldToken);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    /**
     * @param redisTemplate
     * @param token         保存或更新用户客户端登录信息（Ex Window10-Chrome ...） 存储于token
     */
    public static void savaOrUpUserAgent(RedisTemplate redisTemplate, UserAgent ua, String token) {
        redisTemplate.opsForValue().set(Constant.USER_AGENT + token, ua, Constant.TOKEN_EXPIRE, TimeUnit.SECONDS);
    }

    @Data
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class TokenCachedUser implements Serializable {
        private Long userId;
        private String username;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    public static class UserAgent implements Serializable {
        //操作系统
        private String operatingSystem;
        //浏览器
        private String browser;
        //ip
        private String remoteAddr;
    }
}
