package com.eu.system.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 系统访问记录
 * </p>
 *
 * @author jiangxd
 * @since 2022-03-24
 */
@Data
@TableName("sys_logininfor")
@ApiModel(value = "SysLogininfor对象", description = "系统访问记录")
public class SysLogininfor {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "访问ID")
    @TableId("info_id")
    private Long infoId;

    @ApiModelProperty(value = "用户账号")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "登录IP地址")
    @TableField("ipaddr")
    private String ipaddr;

    @ApiModelProperty(value = "登录地点")
    @TableField("login_location")
    private String loginLocation;

    @ApiModelProperty(value = "浏览器类型")
    @TableField("browser")
    private String browser;

    @ApiModelProperty(value = "操作系统")
    @TableField("os")
    private String os;

    @ApiModelProperty(value = "登录状态（0成功 1失败）")
    @TableField("status")
    private String status;

    @ApiModelProperty(value = "提示消息")
    @TableField("msg")
    private String msg;

    @ApiModelProperty(value = "访问时间")
    @TableField("login_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime loginTime;

}
