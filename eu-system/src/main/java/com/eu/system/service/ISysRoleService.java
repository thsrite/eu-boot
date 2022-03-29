package com.eu.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.eu.system.model.po.SysRole;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author jiangxd
 * @since 2022-03-23
 */
public interface ISysRoleService extends IService<SysRole> {

    /**
     * 根据用户ID查询角色权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    Set<String> selectRolePermissionByUserId(Long userId);

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
    IPage<SysRole> roleList(Page page, String roleName, String roleKey, Integer status, String startTime, String endTime);

    /**
     * 校验角色是否允许操作
     *
     * @param role 角色信息
     */
    boolean checkRoleAllowed(SysRole role);

    /**
     * 校验角色名称是否唯一
     *
     * @param role 角色信息
     * @return 结果 true唯一 false不唯一
     */
    boolean checkRoleNameUnique(SysRole role);

    /**
     * 校验角色权限是否唯一
     *
     * @param role 角色信息
     * @return 结果 true唯一 false不唯一
     */
    boolean checkRoleKeyUnique(SysRole role);

    /**
     * 新增角色信息
     *
     * @param role
     */
    void insertRole(SysRole role);

    /**
     * 修改角色信息
     *
     * @param role
     */
    void updateRole(SysRole role);

    /**
     * 删除角色信息
     *
     * @param roleIds
     */
    void removeRole(List<Long> roleIds);

}
