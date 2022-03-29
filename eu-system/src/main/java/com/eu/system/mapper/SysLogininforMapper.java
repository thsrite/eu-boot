package com.eu.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eu.system.model.po.SysLogininfor;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 系统访问记录 Mapper 接口
 * </p>
 *
 * @author jiangxd
 * @since 2022-03-24
 */
public interface SysLogininforMapper extends BaseMapper<SysLogininfor> {

    /**
     * 登录日志列表
     *
     * @param page
     * @param ipaddr
     * @param userName
     * @param status
     * @param startTime
     * @param endTime
     */
    IPage<SysLogininfor> logininforList(@Param("page") Page page, @Param("ipaddr") String ipaddr,
                                        @Param("userName") String userName, @Param("status") Integer status,
                                        @Param("startTime") String startTime, @Param("endTime") String endTime);

}
