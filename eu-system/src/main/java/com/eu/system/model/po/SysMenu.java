package com.eu.system.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.eu.datasource.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单权限表
 * </p>
 *
 * @author jiangxd
 * @since 2022-03-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_menu")
@ApiModel(value = "SysMenu对象", description = "菜单权限表")
public class SysMenu extends BaseModel {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单ID")
    @TableId("menu_id")
    private Long menuId;

    @ApiModelProperty(value = "菜单名称")
    @TableField("menu_name")
    private String menuName;

    @ApiModelProperty(value = "父菜单ID")
    @TableField("parent_id")
    private Long parentId = 0L;

    @ApiModelProperty(value = "显示顺序")
    @TableField("order_num")
    private Integer orderNum;

    @ApiModelProperty(value = "路由地址")
    @TableField("path")
    private String path;

    @ApiModelProperty(value = "组件路径")
    @TableField("component")
    private String component;

    @ApiModelProperty(value = "路由参数")
    @TableField("query")
    private String query;

    @ApiModelProperty("是否为外链（0是 1否）")
    @TableField("is_frame")
    private Integer isFrame;

    @ApiModelProperty(value = "菜单类型（M目录 C菜单 F按钮）")
    @TableField("menu_type")
    private String menuType;

    @ApiModelProperty(value = "菜单状态（0显示 1隐藏）")
    @TableField("visible")
    private Integer visible;

    @ApiModelProperty(value = "菜单状态（0正常 1停用）")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "权限标识")
    @TableField("perms")
    private String perms;

    @ApiModelProperty(value = "菜单图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    /**
     * 是否角色选择
     */
    @TableField(exist = false)
    private Boolean isChecked = Boolean.FALSE;

    /**
     * 子菜单
     */
    @TableField(exist = false)
    private List<SysMenu> children = new ArrayList<SysMenu>();

}
