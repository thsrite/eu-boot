package com.eu.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.eu.system.model.po.SysOperLog;

import java.util.List;

/**
 * <p>
 * 操作日志记录 服务类
 * </p>
 *
 * @author jiangxd
 * @since 2022-03-24
 */
public interface ISysOperLogService extends IService<SysOperLog> {

    /**
     * 新增操作日志
     *
     * @param operLog 操作日志对象
     */
    void insertOperlog(SysOperLog operLog);

    /**
     * 操作记录列表
     *
     * @param pageIndex
     * @param pageSize
     * @param title
     * @param operName
     * @param status
     * @param businessType
     * @param startTime
     * @param endTime
     */
    IPage<SysOperLog> operLogList(Integer pageIndex, Integer pageSize, String title, String operName, Integer status, Integer businessType, List<Integer> businessTypes, String startTime, String endTime);

}
