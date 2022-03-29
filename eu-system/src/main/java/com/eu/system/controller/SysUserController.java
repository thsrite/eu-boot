package com.eu.system.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eu.core.annotation.PreAuthorize;
import com.eu.core.enums.BusinessType;
import com.eu.core.exception.GlobalException;
import com.eu.core.exception.GlobalExceptionCode;
import com.eu.core.thread.CurrentUser;
import com.eu.system.aspect.Log;
import com.eu.system.model.dto.SysAuthRoleDto;
import com.eu.system.model.dto.SysUserListDto;
import com.eu.system.model.dto.SysUserRemoveDto;
import com.eu.system.model.dto.SysUserResetPwdDto;
import com.eu.system.model.po.SysUser;
import com.eu.system.model.vo.SysUserVo;
import com.eu.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author jiangxd
 * @since 2022-03-23
 */
@RestController
@RequestMapping("/sys/user")
@Api(tags = "总后台--用户管理")
public class SysUserController {

    @Autowired
    private ISysUserService userService;


    /**
     * 用户列表
     *
     * @param dto
     */
    @PreAuthorize("system:user:list")
    @GetMapping("list")
    @ApiOperation("用户列表")
    public IPage<SysUser> list(@Valid SysUserListDto dto) {
        return this.userService.userList(new Page<>(dto.getPageIndex(), dto.getPageSize()), dto.getUserName(), dto.getPhone(), dto.getStatus(), dto.getStartTime(), dto.getEndTime());
    }

    /**
     * 用户详情
     *
     * @param userId
     */
    @PreAuthorize("system:user:query")
    @GetMapping("{userId}")
    @ApiOperation("用户详情")
    public SysUserVo getInfo(@NotNull(message = "用户id不能为空") @ApiParam("用户id") @PathVariable Long userId) {
        return this.userService.getInfo(userId);
    }

    /**
     * 新增用户
     *
     * @param user
     */
    @PreAuthorize("system:user:add")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增用户")
    public void add(@RequestBody SysUser user) {
        if (!this.userService.checkUserNameUnique(user)) {
            throw new GlobalException(GlobalExceptionCode.ERROR, "新增用户'" + user.getUserName() + "'失败，登录账号已存在");
        }

        if (StrUtil.isNotBlank(user.getPhone()) && !this.userService.checkPhoneUnique(user)) {
            throw new GlobalException(GlobalExceptionCode.ERROR, "新增用户'" + user.getUserName() + "'失败，手机号码已存在");
        }

        if (StrUtil.isNotBlank(user.getEmail()) && !this.userService.checkEmailUnique(user)) {
            throw new GlobalException(GlobalExceptionCode.ERROR, "新增用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }

        user.setPassword(SecureUtil.md5(user.getPassword()));

        this.userService.insertUser(user);
    }

    /**
     * 修改用户
     *
     * @param user
     */
    @PreAuthorize("system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改用户")
    public void edit(@RequestBody SysUser user) {
        if (!this.userService.checkUserAllowed(user)) {
            throw new GlobalException(GlobalExceptionCode.ERROR, "不允许操作超级管理员用户");
        }

        if (!this.userService.checkUserNameUnique(user)) {
            throw new GlobalException(GlobalExceptionCode.ERROR, "新增用户'" + user.getUserName() + "'失败，登录账号已存在");
        }

        if (StrUtil.isNotBlank(user.getPhone()) && !this.userService.checkPhoneUnique(user)) {
            throw new GlobalException(GlobalExceptionCode.ERROR, "新增用户'" + user.getUserName() + "'失败，手机号码已存在");
        }

        if (StrUtil.isNotBlank(user.getEmail()) && !this.userService.checkEmailUnique(user)) {
            throw new GlobalException(GlobalExceptionCode.ERROR, "新增用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }

        user.setPassword(SecureUtil.md5(user.getPassword()));

        this.userService.updateUser(user);
    }

    /**
     * 删除用户
     *
     * @param dto
     */
    @PreAuthorize("system:user:remove")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @DeleteMapping
    @ApiOperation("删除用户")
    public void remove(@Valid @RequestBody SysUserRemoveDto dto) {
        if (dto.getUserIds().contains(CurrentUser.getId())) {
            throw new GlobalException(GlobalExceptionCode.ERROR, "当前用户不能删除");
        }

        this.userService.removeUser(dto.getUserIds());
    }

    /**
     * 重置密码
     */
    @PreAuthorize("system:user:resetPwd")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("resetPwd")
    @ApiOperation("重置密码")
    public void resetPwd(@Valid @RequestBody SysUserResetPwdDto dto) {
        SysUser user = new SysUser(dto.getUserId());
        if (!this.userService.checkUserAllowed(user)) {
            throw new GlobalException(GlobalExceptionCode.ERROR, "不允许操作超级管理员用户");
        }

        if (!dto.getPwd().equals(dto.getComfirmPwd())) {
            throw new GlobalException(GlobalExceptionCode.ERROR, "两次密码不一致");
        }

        user.setPassword(SecureUtil.md5(user.getPassword()));
        this.userService.updateById(user);
    }

    /**
     * 修改状态
     */
    @PreAuthorize("system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PatchMapping("{userId}")
    @ApiOperation("修改状态")
    public void changeStatus(@NotNull(message = "请选择用户") @ApiParam("用户id") @PathVariable Long userId) {
        SysUser user = this.userService.getById(userId);

        if (!this.userService.checkUserAllowed(user)) {
            throw new GlobalException(GlobalExceptionCode.ERROR, "不允许操作超级管理员角色");
        }

        user.setStatus(user.getStatus() == 0 ? 1 : 0);
        this.userService.updateById(user);
    }

    /**
     * 用户授权角色
     *
     * @param dto
     */
    @PreAuthorize("system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.GRANT)
    @PutMapping("authRole")
    @ApiOperation("用户授权角色")
    public void authRole(@Valid @RequestBody SysAuthRoleDto dto) {
        this.userService.authRole(dto.getUserId(), dto.getRoleIds());
    }

}
