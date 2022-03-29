package com.eu.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eu.system.mapper.SysLogininforMapper;
import com.eu.system.model.po.SysLogininfor;
import com.eu.system.service.ISysLogininforService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统访问记录 服务实现类
 * </p>
 *
 * @author jiangxd
 * @since 2022-03-24
 */
@Service
public class SysLogininforServiceImpl extends ServiceImpl<SysLogininforMapper, SysLogininfor> implements ISysLogininforService {

    /**
     * 新增系统登录日志
     *
     * @param logininfor 访问日志对象
     */
    @Override
    public void insertLogininfor(SysLogininfor logininfor) {
        this.save(logininfor);
    }

    /**
     * 登录日志列表
     *
     * @param pageIndex
     * @param pageSize
     * @param ipaddr
     * @param userName
     * @param status
     * @param startTime
     * @param endTime
     */
    @Override
    public IPage<SysLogininfor> logininforList(Integer pageIndex, Integer pageSize, String ipaddr, String userName, Integer status, String startTime, String endTime) {
        return this.getBaseMapper().logininforList(new Page<>(pageIndex, pageSize), ipaddr, userName, status, startTime, endTime);
    }
}
