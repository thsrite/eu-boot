package com.eu.system.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel("重置密码")
public class SysUserResetPwdDto {

    @ApiModelProperty("用户id")
    @NotNull(message = "请选择重置用户")
    private Long userId;

    @ApiModelProperty("新密码")
    @NotBlank(message = "请输入新密码")
    private String pwd;

    @ApiModelProperty("确认密码")
    @NotBlank(message = "请输入确认密码")
    private String comfirmPwd;

}
