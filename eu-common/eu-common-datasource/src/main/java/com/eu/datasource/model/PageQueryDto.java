package com.eu.datasource.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 分页查询
 */
@Data
@ApiModel("公共分页参数")
public class PageQueryDto {

    /**
     * 页数
     */
    @ApiModelProperty(value = "页数", example = "1")
    @NotNull(message = "分页页数不能为空")
    private Integer pageIndex = 1;

    /**
     * 每页条数
     */
    @ApiModelProperty(value = "每页条数", example = "10")
    @NotNull(message = "分页条数不能为空")
    private Integer pageSize = 10;

}
