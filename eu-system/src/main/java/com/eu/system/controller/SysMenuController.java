package com.eu.system.controller;

import com.eu.core.annotation.PreAuthorize;
import com.eu.core.constants.Constants;
import com.eu.core.constants.UserConstants;
import com.eu.core.enums.BusinessType;
import com.eu.core.exception.GlobalException;
import com.eu.core.exception.GlobalExceptionCode;
import com.eu.core.thread.CurrentUser;
import com.eu.system.aspect.Log;
import com.eu.system.model.dto.SysMenuListDto;
import com.eu.system.model.po.SysMenu;
import com.eu.system.model.vo.SysMenuTreeVo;
import com.eu.system.service.ISysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 菜单信息 前端控制器
 * </p>
 *
 * @author jiangxd
 * @since 2022-03-23
 */
@RestController
@RequestMapping("/sys/menu")
@Api(tags = "总后台--菜单管理")
public class SysMenuController {

    @Autowired
    private ISysMenuService sysMenuService;


    /**
     * 菜单列表
     *
     * @param dto
     */
    @PreAuthorize("system:menu:list")
    @GetMapping("list")
    @ApiOperation("菜单列表")
    public List<SysMenu> list(@Valid SysMenuListDto dto) {
        return this.sysMenuService.menuList(dto.getMenuName(), dto.getVisible(), dto.getStatus(), CurrentUser.getId());
    }

    /**
     * 菜单详情
     */
    @PreAuthorize("system:menu:query")
    @GetMapping("{menuId}")
    @ApiOperation("菜单详情")
    public SysMenu getInfo(@NotNull(message = "请选择菜单") @ApiParam("菜单id") @PathVariable Long menuId) {
        return this.sysMenuService.getById(menuId);
    }

    /**
     * 菜单树形结构
     *
     * @param dto
     */
    @GetMapping("tree")
    @ApiOperation("菜单树形结构")
    public List<SysMenuTreeVo> tree(SysMenuListDto dto) {
        return this.sysMenuService.menuTree(dto.getMenuName(), dto.getVisible(), dto.getStatus(), CurrentUser.getId());
    }

    /**
     * 角色菜单树形结构
     */
    @GetMapping("role/tree/{roleId}")
    @ApiOperation("角色菜单树形结构")
    public List<SysMenuTreeVo> roleMenuTree(@NotNull(message = "请选择角色") @ApiParam("角色id") @PathVariable Long roleId) {
        return this.sysMenuService.roleMenuTree(roleId, CurrentUser.getId());
    }

    /**
     * 新增菜单
     *
     * @param menu
     */
    @PreAuthorize("system:menu:add")
    @Log(title = "菜单管理", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增菜单")
    public void add(@RequestBody SysMenu menu) {
        // true唯一 false不唯一
        if (!this.sysMenuService.checkMenuNameUnique(menu)) {
            throw new GlobalException(GlobalExceptionCode.ERROR, "新增菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        } else if (menu.getMenuId().equals(menu.getParentId())) {
            throw new GlobalException(GlobalExceptionCode.ERROR, "新增菜单'" + menu.getMenuName() + "'失败，上级菜单不能选择自己");
        } else if (UserConstants.YES_FRAME.equals(menu.getIsFrame()) && !StringUtils.startsWithAny(menu.getPath(), Constants.HTTP, Constants.HTTPS)) {
            throw new GlobalException(GlobalExceptionCode.ERROR, "新增菜单'" + menu.getMenuName() + "'失败，地址必须以http(s)://开头");
        }

        this.sysMenuService.save(menu);
    }

    /**
     * 修改菜单
     *
     * @param menu
     */
    @PreAuthorize("system:menu:edit")
    @Log(title = "菜单管理", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改菜单")
    public void edit(@RequestBody SysMenu menu) {
        // true唯一 false不唯一
        if (!this.sysMenuService.checkMenuNameUnique(menu)) {
            throw new GlobalException(GlobalExceptionCode.ERROR, "修改菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        } else if (menu.getMenuId().equals(menu.getParentId())) {
            throw new GlobalException(GlobalExceptionCode.ERROR, "修改菜单'" + menu.getMenuName() + "'失败，上级菜单不能选择自己");
        } else if (UserConstants.YES_FRAME.equals(menu.getIsFrame()) && !StringUtils.startsWithAny(menu.getPath(), Constants.HTTP, Constants.HTTPS)) {
            throw new GlobalException(GlobalExceptionCode.ERROR, "修改菜单'" + menu.getMenuName() + "'失败，地址必须以http(s)://开头");
        }

        this.sysMenuService.updateById(menu);
    }

    /**
     * 删除菜单
     *
     * @param menuId
     */
    @PreAuthorize("system:menu:remove")
    @Log(title = "菜单管理", businessType = BusinessType.DELETE)
    @DeleteMapping("{menuId}")
    @ApiOperation("删除菜单")
    public void remove(@ApiParam("菜单id") @PathVariable Long menuId) {
        if (this.sysMenuService.hasChildByMenuId(menuId)) {
            throw new GlobalException(GlobalExceptionCode.ERROR, "存在子菜单,不允许删除");
        }

        if (this.sysMenuService.checkMenuExistRole(menuId)) {
            throw new GlobalException(GlobalExceptionCode.ERROR, "菜单已分配,不允许删除");
        }

        this.sysMenuService.removeById(menuId);
    }

}
