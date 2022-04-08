package com.duobang.cloud.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

/**
 * @author: DiegoSun
 * @time: 2022/3/11 上午10:41
 * @description: 定义返回的结构体
 * code，一定是MyResponseCode中指定的code
 * message，可以是MyResponseCode中的Message，也可以自己指定
 * data，成功有data，失败暂无data
 */
@Data
@Builder
public class MyResponseBody {
    private Integer code;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    /**
     * 成功，无返回data
     * @return
     */
    public static MyResponseBody ok(){
        return MyResponseBody.builder()
                .code(MyResponseCode.OK.getCode())
                .message(MyResponseCode.OK.getMessage())
                .build();
    }

    /**
     * 成功，有返回data
     * @param data
     * @return
     */
    public static MyResponseBody ok(Object data){
        return MyResponseBody.builder()
                .code(MyResponseCode.OK.getCode())
                .message(MyResponseCode.OK.getMessage())
                .data(data)
                .build();
    }

    /**
     * 操作失败，使用code中的message
     * @param code
     * @return
     */
    public static MyResponseBody fail(MyResponseCode code){
        return MyResponseBody.builder()
                .code(code.getCode())
                .message(code.getMessage())
                .build();
    }

    /**
     * 操作失败，使用自定义的message
     * @param code
     * @param message
     * @return
     */
    public static MyResponseBody fail(MyResponseCode code, String message){
        return MyResponseBody.builder()
                .code(code.getCode())
                .message(message)
                .build();
    }

}
