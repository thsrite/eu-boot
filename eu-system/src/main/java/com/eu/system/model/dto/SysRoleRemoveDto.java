package com.eu.system.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@ApiModel("删除角色")
public class SysRoleRemoveDto {

    @ApiModelProperty("角色id")
    @NotEmpty(message = "请选择删除的角色")
    private List<Long> roleIds;

}
