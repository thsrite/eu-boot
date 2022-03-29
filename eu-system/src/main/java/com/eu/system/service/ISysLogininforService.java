package com.eu.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.eu.system.model.po.SysLogininfor;

/**
 * <p>
 * 系统访问记录 服务类
 * </p>
 *
 * @author jiangxd
 * @since 2022-03-24
 */
public interface ISysLogininforService extends IService<SysLogininfor> {

    /**
     * 新增系统登录日志
     *
     * @param logininfor 访问日志对象
     */
    void insertLogininfor(SysLogininfor logininfor);

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
    IPage<SysLogininfor> logininforList(Integer pageIndex, Integer pageSize, String ipaddr, String userName, Integer status, String startTime, String endTime);

}
