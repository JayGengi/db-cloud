package com.duobang.cloud.exception.impl;


import com.duobang.cloud.exception.MyException;
import com.duobang.cloud.response.MyResponseCode;

/**
 * @author: DiegoSun
 * @time: 2022/3/11 上午11:19
 * @description:
 */
public class AuthenticatedException extends MyException {
    public AuthenticatedException(){
        this.code = MyResponseCode.UNAUTHENTICATED;
    }
}
