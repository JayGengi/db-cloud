package com.duobang.cloud.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * @author: DiegoSun
 * @time: 2021/5/8 下午12:53
 * @description:
 */
public class UserDetailUtil {

    public static Long getUserId() {
        Long userId = (Long)getSession().getAttribute(Constant.SESSION_USER_ID);
        return userId;
    }

    public static HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    public static HttpSession getSession(){
        HttpServletRequest request = getRequest();
        HttpSession session = request.getSession();
        return session;
    }
}
