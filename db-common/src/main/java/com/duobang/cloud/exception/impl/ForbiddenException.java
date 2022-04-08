package com.duobang.cloud.exception.impl;


import com.duobang.cloud.exception.MyException;
import com.duobang.cloud.response.MyResponseCode;

/**
 * @author: DiegoSun
 * @time: 2022/3/3 下午3:42
 * @description:
 */
public class ForbiddenException extends MyException {
    public ForbiddenException(){
        this.code = MyResponseCode.FORBIDDEN;
    }
}
