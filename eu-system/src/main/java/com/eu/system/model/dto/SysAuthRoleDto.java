package com.eu.system.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel("用户授权角色")
public class SysAuthRoleDto {

    @ApiModelProperty("用户id")
    @NotNull(message = "请选择授权用户")
    private Long userId;

    @ApiModelProperty("角色")
    @NotEmpty(message = "请选择授权角色")
    private List<Long> roleIds;

}
