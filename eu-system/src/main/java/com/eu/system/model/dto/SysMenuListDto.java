package com.eu.system.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("菜单列表查询")
public class SysMenuListDto {

    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "菜单状态（0显示 1隐藏）")
    private Integer visible;

    @ApiModelProperty("菜单状态（0正常 1停用）")
    private Integer status;

}
