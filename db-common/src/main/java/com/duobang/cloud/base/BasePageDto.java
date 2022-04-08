package com.duobang.cloud.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
  * @des　  分页
  * @author JayGengi
  * @time　 2021/5/10 10:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "分页")
public class BasePageDto implements Serializable {

    @ApiModelProperty(value = "第几页", example = "1",required = true)
    @NotNull(message="分页不能为空")
    private Long currPage;

    @ApiModelProperty(value = "数量", example = "10",required = true)
    @Max(50)
    @NotNull(message="分页不能为空")
    private Long pageSize;
}
