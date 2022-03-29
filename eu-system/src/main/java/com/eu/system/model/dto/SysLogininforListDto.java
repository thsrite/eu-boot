package com.eu.system.model.dto;

import com.eu.datasource.model.PageQueryDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("登录记录列表查询")
public class SysLogininforListDto extends PageQueryDto {

    @ApiModelProperty(value = "登录地址")
    private String ipaddr;

    @ApiModelProperty(value = "登录用户")
    private String userName;

    @ApiModelProperty("操作状态（0正常 1异常）")
    private Integer status;

    @ApiModelProperty("开始时间")
    private String startTime;

    @ApiModelProperty("结束时间")
    private String endTime;

}
