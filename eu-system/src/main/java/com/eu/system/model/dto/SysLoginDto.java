package com.eu.system.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel("总后台登录")
public class SysLoginDto {

    @ApiModelProperty("用户账号")
    @NotBlank(message = "请输入用户账号")
    private String userName;

    @ApiModelProperty("用户密码")
    @NotBlank(message = "请输入用户密码")
    private String password;

}
