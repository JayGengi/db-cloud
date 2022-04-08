package com.duobang.cloud.utils;

/**
 * 常量
 */
public class Constant {
    public static final String SESSION_USER_ID = "userId";

    public static final String NUMBER_CODE_KEY = "blast-server:number:code:";
    public static final String MOBILE_CODE_KEY = "blast-server:mobile:code:";
    public static final String AUTHENTICATION_TOKEN = "blast-server:token:";

    public static final String TOKEN = "token";

    /**用户客户端登录信息（Ex Window10-Chrome ...）*/
    public static final String USER_AGENT = "user-agent:user:";

    public static final String TOKEN_LOGIN_URL = "/token/login";
    public static final String SMS_LOGIN_URL = "/sms/login";
    public static final String TOKEN_LOGOUT_URL = "/token/logout";
    public static final int TOKEN_EXPIRE = 60 * 60 * 24 * 7;
    /**短信失效时间，单位：秒*/
    public static final int SMS_EXPIRE = 60 * 3;


    /** AES算法生成密码用的固定key，请不要修改，修改后数据库中保存的密码都会失效 **/
    public static final String AES_KEY = "dbBlast";

    public static final String USER_PERMISSION = "user:permission:";
}
