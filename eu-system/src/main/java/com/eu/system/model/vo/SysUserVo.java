package com.eu.system.model.vo;

import com.eu.system.model.po.SysRole;
import com.eu.system.model.po.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("用户详情")
public class SysUserVo {

    @ApiModelProperty("用户")
    private SysUser sysUser;

    @ApiModelProperty("角色")
    private List<SysRole> roles;

}
