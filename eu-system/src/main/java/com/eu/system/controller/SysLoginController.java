package com.eu.system.controller;

import com.eu.core.thread.CurrentUser;
import com.eu.system.model.dto.SysLoginDto;
import com.eu.system.model.po.SysMenu;
import com.eu.system.model.po.SysUser;
import com.eu.system.model.vo.SysLoginVo;
import com.eu.system.model.vo.SysRouterVo;
import com.eu.system.model.vo.SysUserInfoVo;
import com.eu.system.service.ISysMenuService;
import com.eu.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 登录
 */
@RestController
@RequestMapping("/sys")
@Api(tags = "总后台--登录")
public class SysLoginController {

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysMenuService menuService;


    /**
     * 用户登录
     *
     * @param dto
     */
    @PostMapping("login")
    @ApiOperation("用户登录")
    public SysLoginVo login(@Valid @RequestBody SysLoginDto dto) {
        return this.userService.login(dto.getUserName(), dto.getPassword());
    }

    /**
     * 用户信息
     */
    @GetMapping("info")
    @ApiOperation("用户信息")
    public SysUserInfoVo getInfo() {
        SysUser user = this.userService.getById(CurrentUser.getId());
        return this.userService.info(user);
    }

    /**
     * 路由信息
     */
    @GetMapping("routers")
    @ApiOperation("路由信息")
    public List<SysRouterVo> getRouters() {
        Long userId = CurrentUser.getId();
        List<SysMenu> menus = this.menuService.selectMenuTreeByUserId(userId);

        return this.menuService.buildMenus(menus);
    }

}
