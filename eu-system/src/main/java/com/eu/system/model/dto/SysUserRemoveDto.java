package com.eu.system.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@ApiModel("删除用户")
public class SysUserRemoveDto {

    @ApiModelProperty("用户id")
    @NotEmpty(message = "请选择删除的用户")
    private List<Long> userIds;

}
