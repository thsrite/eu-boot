package com.eu.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.eu.core.annotation.PreAuthorize;
import com.eu.core.enums.BusinessType;
import com.eu.system.aspect.Log;
import com.eu.system.model.dto.SysLogRemoveDto;
import com.eu.system.model.dto.SysOperLogListDto;
import com.eu.system.model.po.SysOperLog;
import com.eu.system.service.ISysOperLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 操作日志记录 前端控制器
 * </p>
 *
 * @author jiangxd
 * @since 2022-03-24
 */
@RestController
@RequestMapping("/sys/operlog")
@Api(tags = "总后台--操作日志")
public class SysOperLogController {

    @Autowired
    private ISysOperLogService operLogService;


    /**
     * 操作日志列表
     */
    @PreAuthorize("system:operlog:list")
    @GetMapping("list")
    @ApiOperation("操作日志列表")
    public IPage<SysOperLog> list(@Valid SysOperLogListDto dto) {
        return this.operLogService.operLogList(dto.getPageIndex(), dto.getPageSize(), dto.getTitle(), dto.getOperName(), dto.getStatus(), dto.getBusinessType(), dto.getBusinessTypes(), dto.getStartTime(), dto.getEndTime());
    }

    /**
     * 删除操作日志
     *
     * @param dto
     */
    @PreAuthorize("system:operlog:remove")
    @Log(title = "操作日志", businessType = BusinessType.DELETE)
    @DeleteMapping
    @ApiOperation("删除操作日志")
    @Transactional
    public void remove(@Valid @RequestBody SysLogRemoveDto dto) {
        this.operLogService.removeByIds(dto.getLogIds());
    }

    /**
     * 清空操作日志
     */
    @PreAuthorize("system:operlog:remove")
    @Log(title = "操作日志", businessType = BusinessType.CLEAN)
    @DeleteMapping("clean")
    @ApiOperation("清空操作日志")
    @Transactional
    public void clean() {
        this.operLogService.remove(new LambdaQueryWrapper<>());
    }

}
