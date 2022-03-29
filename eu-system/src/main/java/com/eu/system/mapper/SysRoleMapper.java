package com.eu.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eu.system.model.po.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色信息表 Mapper 接口
 * </p>
 *
 * @author jiangxd
 * @since 2022-03-23
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 查询用户角色列表
     *
     * @param userId
     */
    List<SysRole> selectRolePermissionByUserId(@Param("userId") Long userId);

    /**
     * 角色列表
     *
     * @param page
     * @param roleName
     * @param roleKey
     * @param status
     * @param startTime
     * @param endTime
     */
    IPage<SysRole> roleList(@Param("page") Page page, @Param("roleName") String roleName,
                            @Param("roleKey") String roleKey, @Param("status") Integer status,
                            @Param("startTime") String startTime, @Param("endTime") String endTime);

}
