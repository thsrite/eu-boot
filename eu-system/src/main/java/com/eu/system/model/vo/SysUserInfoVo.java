package com.eu.system.model.vo;

import com.eu.system.model.po.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

@Data
@ApiModel("用户权限详情")
public class SysUserInfoVo {

    @ApiModelProperty("用户")
    private SysUser sysUser;

    @ApiModelProperty("角色标识")
    private Set<String> roles;

    @ApiModelProperty("菜单权限")
    private Set<String> permissions;

}
