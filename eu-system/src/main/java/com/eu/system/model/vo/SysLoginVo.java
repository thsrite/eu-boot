package com.eu.system.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("总后台登录返回")
public class SysLoginVo extends SysUserInfoVo {

    @ApiModelProperty("用户token(放入header:Authorization)")
    private String token;

}
