package com.duobang.cloud.modules.sys.model.dto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author: DiegoSun
 * @time: 2022/3/9 下午5:18
 * @description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户创建")
public class LoginDto {
    @ApiModelProperty(value = "手机号", required = true)
    @NotBlank(message = "手机号不能为空" )
    private String phone;

    @Length(min = 6, max = 32 )
    @ApiModelProperty(value = "用户密码", required = true)
    private String password;
}
