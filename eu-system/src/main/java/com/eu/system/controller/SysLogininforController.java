package com.eu.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.eu.core.annotation.PreAuthorize;
import com.eu.core.enums.BusinessType;
import com.eu.system.aspect.Log;
import com.eu.system.model.dto.SysLogRemoveDto;
import com.eu.system.model.dto.SysLogininforListDto;
import com.eu.system.model.po.SysLogininfor;
import com.eu.system.service.ISysLogininforService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 登录日志记录 前端控制器
 * </p>
 *
 * @author jiangxd
 * @since 2022-03-24
 */
@RestController
@RequestMapping("/sys/logininfor")
@Api(tags = "总后台--登录日志")
public class SysLogininforController {

    @Autowired
    private ISysLogininforService logininforService;


    /**
     * 登录日志列表
     */
    @PreAuthorize("system:logininfor:list")
    @GetMapping("list")
    @ApiOperation("登录日志列表")
    public IPage<SysLogininfor> list(@Valid SysLogininforListDto dto) {
        return this.logininforService.logininforList(dto.getPageIndex(), dto.getPageSize(), dto.getIpaddr(), dto.getUserName(), dto.getStatus(), dto.getStartTime(), dto.getEndTime());
    }

    /**
     * 删除登录日志
     *
     * @param dto
     */
    @PreAuthorize("system:logininfor:remove")
    @Log(title = "登录日志", businessType = BusinessType.DELETE)
    @DeleteMapping
    @ApiOperation("删除登录日志")
    @Transactional
    public void remove(@Valid @RequestBody SysLogRemoveDto dto) {
        this.logininforService.removeByIds(dto.getLogIds());
    }

    /**
     * 清空登录日志
     */
    @PreAuthorize("system:logininfor:remove")
    @Log(title = "登录日志", businessType = BusinessType.CLEAN)
    @DeleteMapping("clean")
    @ApiOperation("清空登录日志")
    @Transactional
    public void clean() {
        this.logininforService.remove(new LambdaQueryWrapper<>());
    }

}
