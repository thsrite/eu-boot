package com.eu.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eu.system.model.po.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author jiangxd
 * @since 2022-03-23
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    SysUser selectUserByUserName(@Param("userName") String userName);

    /**
     * 用户列表
     *
     * @param page
     * @param userName
     * @param phone
     * @param status
     * @param startTime
     * @param endTime
     */
    IPage<SysUser> userList(@Param("page") Page page, @Param("userName") String userName,
                            @Param("phone") String phone, @Param("status") Integer status,
                            @Param("startTime") String startTime, @Param("endTime") String endTime);

}
