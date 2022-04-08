package com.duobang.cloud.exception.impl;

import com.duobang.cloud.exception.MyException;
import com.duobang.cloud.response.MyResponseCode;
import lombok.Data;

/**
 * @author: DiegoSun
 * @time: 2022/3/11 上午11:29
 * @description: 开这个口容易造成混乱，不开这个口使用起来又不方便
 */
@Data
public class BadRequestException extends MyException {
    public BadRequestException(){
        this.code = MyResponseCode.BAD_REQUEST;
    }
    public BadRequestException(String message){
        this.code = MyResponseCode.BAD_REQUEST;
        this.message = message;
    }
}
