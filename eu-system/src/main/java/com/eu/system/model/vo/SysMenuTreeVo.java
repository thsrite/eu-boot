package com.eu.system.model.vo;

import com.eu.system.model.po.SysMenu;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单树形结构
 */
@Data
@ApiModel("菜单树形结构")
public class SysMenuTreeVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 节点ID
     */
    private Long menuId;

    /**
     * 节点名称
     */
    private String menuName;

    /**
     * 是否已授权
     */
    private Boolean isChecked;

    /**
     * 子节点
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<SysMenuTreeVo> children;

    public SysMenuTreeVo() {

    }

    public SysMenuTreeVo(SysMenu menu) {
        this.menuId = menu.getMenuId();
        this.menuName = menu.getMenuName();
        this.isChecked = menu.getIsChecked();
        this.children = menu.getChildren().stream().map(SysMenuTreeVo::new).collect(Collectors.toList());
    }

}
