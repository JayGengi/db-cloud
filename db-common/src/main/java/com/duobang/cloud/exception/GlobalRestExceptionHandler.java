package com.duobang.cloud.exception;

import com.duobang.cloud.response.MyResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

/**
 * @author: DiegoSun
 * @time: 2022/3/3 下午3:44
 * @description: 对错误的拦截处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalRestExceptionHandler {

    /**
     * 对全部的自定义的异常进行捕获，使用者需要在异常中定义返回的code
     * @param e
     * @return 如果自己指定的message，使用自己指定的message，否则使用code中自定义的
     */
    @ExceptionHandler(value = MyException.class)
    public ResponseEntity<MyResponseBody> commonExceptionHandler(MyException e){
        String message = Optional.ofNullable(e.getMessage())
                            .orElse(e.getCode().getMessage());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(MyResponseBody.fail(e.getCode(), message));
    }

}
