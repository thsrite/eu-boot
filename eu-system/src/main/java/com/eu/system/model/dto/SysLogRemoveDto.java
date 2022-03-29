package com.eu.system.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@ApiModel("删除日志")
public class SysLogRemoveDto {

    @ApiModelProperty("日志id")
    @NotEmpty(message = "请选择删除的日志")
    private List<Long> logIds;

}
