package com.duobang.cloud.exception;


import com.duobang.cloud.response.MyResponseCode;

/**
 * @author: DiegoSun
 * @time: 2022/3/11 上午11:16
 * @description: 自定义异常，需要定义返回的code
 *
 */
public abstract class MyException extends RuntimeException{
    protected MyResponseCode code;
    protected String message;

    public MyResponseCode getCode(){
        return this.code;
    };

    public String getMessage(){
        return this.message;
    }
}
