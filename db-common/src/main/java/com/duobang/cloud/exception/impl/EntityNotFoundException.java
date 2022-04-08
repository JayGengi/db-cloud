package com.duobang.cloud.exception.impl;


import com.duobang.cloud.exception.MyException;
import com.duobang.cloud.response.MyResponseCode;

/**
 * @author: DiegoSun
 * @time: 2022/3/11 下午2:26
 * @description:
 */
public class EntityNotFoundException extends MyException {
    public EntityNotFoundException(){
        this.code = MyResponseCode.NOT_FOUND;
    }
}
