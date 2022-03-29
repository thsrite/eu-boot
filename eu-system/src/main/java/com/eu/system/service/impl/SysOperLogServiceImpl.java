package com.eu.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eu.system.mapper.SysOperLogMapper;
import com.eu.system.model.po.SysOperLog;
import com.eu.system.service.ISysOperLogService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 操作日志记录 服务实现类
 * </p>
 *
 * @author jiangxd
 * @since 2022-03-24
 */
@Service
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogMapper, SysOperLog> implements ISysOperLogService {

    /**
     * 新增操作日志
     *
     * @param operLog 操作日志对象
     */
    @Override
    public void insertOperlog(SysOperLog operLog) {
        this.save(operLog);
    }

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
    @Override
    public IPage<SysOperLog> operLogList(Integer pageIndex, Integer pageSize, String title, String operName, Integer status, Integer businessType, List<Integer> businessTypes, String startTime, String endTime) {
        return this.getBaseMapper().operLogList(new Page<>(pageIndex, pageSize), title, operName, status, businessType, businessTypes, startTime, endTime);
    }
}
