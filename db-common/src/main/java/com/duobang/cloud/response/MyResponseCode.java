package com.duobang.cloud.response;

/**
 * 定义返回的错误码，及说明信息
 * 用5位数来表示，前3位与http码一致，后2位可以表达更细的子类
 */
public enum MyResponseCode {
    OK(20000, "成功"),
    UNAUTHENTICATED(40100, "未登录"),
    FORBIDDEN(40300, "无权操作"),
    NOT_FOUND(40400, "未找到内容"),
    BAD_REQUEST(40000, "用户请求错误"),
    FAILED(50000, "操作失败"),
    MAX(99999, "未知错误");


    private Integer code;
    private String message;
    private MyResponseCode(Integer code, String message){
        this.code = code;
        this.message = message;
    }
    public Integer getCode(){
        return this.code;
    }
    public String getMessage(){
        return this.message;
    }
}
