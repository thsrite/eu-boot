package com.eu.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eu.system.model.po.SysOperLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 操作日志记录 Mapper 接口
 * </p>
 *
 * @author jiangxd
 * @since 2022-03-24
 */
public interface SysOperLogMapper extends BaseMapper<SysOperLog> {

    /**
     * 操作记录列表
     *
     * @param page
     * @param title
     * @param operName
     * @param status
     * @param businessType
     * @param startTime
     * @param endTime
     */
    IPage<SysOperLog> operLogList(@Param("page") Page page, @Param("title") String title,
                                  @Param("operName") String operName, @Param("status") Integer status,
                                  @Param("businessType") Integer businessType, @Param("businessTypes") List<Integer> businessTypes,
                                  @Param("startTime") String startTime, @Param("endTime") String endTime);

}
