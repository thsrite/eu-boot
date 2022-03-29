package com.eu.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eu.core.annotation.PreAuthorize;
import com.eu.core.enums.BusinessType;
import com.eu.core.exception.GlobalException;
import com.eu.core.exception.GlobalExceptionCode;
import com.eu.system.aspect.Log;
import com.eu.system.model.dto.SysRoleListDto;
import com.eu.system.model.dto.SysRoleRemoveDto;
import com.eu.system.model.po.SysRole;
import com.eu.system.service.ISysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 角色信息表 前端控制器
 * </p>
 *
 * @author jiangxd
 * @since 2022-03-23
 */
@RestController
@RequestMapping("/sys/role")
@Api(tags = "总后台--角色管理")
public class SysRoleController {

    @Autowired
    private ISysRoleService sysRoleService;


    /**
     * 角色列表
     *
     * @param dto
     */
    @PreAuthorize("system:role:list")
    @GetMapping("list")
    @ApiOperation("角色列表")
    public IPage<SysRole> list(@Valid SysRoleListDto dto) {
        return this.sysRoleService.roleList(new Page<>(dto.getPageIndex(), dto.getPageSize()), dto.getRoleName(), dto.getRoleKey(), dto.getStatus(), dto.getStartTime(), dto.getEndTime());
    }

    /**
     * 角色详情
     */
    @PreAuthorize("system:role:query")
    @GetMapping("{roleId}")
    @ApiOperation("角色详情")
    public SysRole getInfo(@NotNull(message = "请选择角色") @ApiParam("角色id") Long roleId) {
        return this.sysRoleService.getById(roleId);
    }

    /**
     * 角色新增
     *
     * @param role
     */
    @PreAuthorize("system:role:add")
    @Log(title = "角色管理", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("角色新增")
    public void add(@RequestBody SysRole role) {
        // true唯一 false不唯一
        if (!this.sysRoleService.checkRoleNameUnique(role)) {
            throw new GlobalException(GlobalExceptionCode.ERROR, "新增角色'" + role.getRoleName() + "'失败，角色名称已存在");
        }

        if (!this.sysRoleService.checkRoleKeyUnique(role)) {
            throw new GlobalException(GlobalExceptionCode.ERROR, "新增角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }

        this.sysRoleService.insertRole(role);
    }

    /**
     * 修改角色
     *
     * @param role
     */
    @PreAuthorize("system:role:edit")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改角色")
    public void edit(@RequestBody SysRole role) {
        if (!this.sysRoleService.checkRoleAllowed(role)) {
            throw new GlobalException(GlobalExceptionCode.ERROR, "不允许操作超级管理员角色");
        }
        // true唯一 false不唯一
        if (!this.sysRoleService.checkRoleNameUnique(role)) {
            throw new GlobalException(GlobalExceptionCode.ERROR, "修改角色'" + role.getRoleName() + "'失败，角色名称已存在");
        }

        if (!this.sysRoleService.checkRoleKeyUnique(role)) {
            throw new GlobalException(GlobalExceptionCode.ERROR, "修改角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }

        this.sysRoleService.updateRole(role);
    }

    /**
     * 修改状态
     */
    @PreAuthorize("system:role:edit")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PatchMapping("{roleId}")
    @ApiOperation("修改状态")
    public void changeStatus(@NotNull(message = "请选择角色") @ApiParam("角色id") @PathVariable Long roleId) {
        SysRole role = this.sysRoleService.getById(roleId);

        if (!this.sysRoleService.checkRoleAllowed(role)) {
            throw new GlobalException(GlobalExceptionCode.ERROR, "不允许操作超级管理员角色");
        }

        role.setStatus(role.getStatus() == 0 ? 1 : 0);
        this.sysRoleService.updateById(role);
    }

    /**
     * 删除角色
     *
     * @param dto
     */
    @PreAuthorize("system:role:remove")
    @Log(title = "角色管理", businessType = BusinessType.DELETE)
    @DeleteMapping
    @ApiOperation("删除角色")
    public void remove(@Valid @RequestBody SysRoleRemoveDto dto) {
        this.sysRoleService.removeRole(dto.getRoleIds());
    }


}
