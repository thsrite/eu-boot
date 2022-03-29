package com.eu.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eu.core.exception.GlobalException;
import com.eu.core.exception.GlobalExceptionCode;
import com.eu.system.mapper.SysRoleMapper;
import com.eu.system.model.po.SysRole;
import com.eu.system.model.po.SysRoleMenu;
import com.eu.system.model.po.SysUser;
import com.eu.system.model.po.SysUserRole;
import com.eu.system.service.ISysRoleMenuService;
import com.eu.system.service.ISysRoleService;
import com.eu.system.service.ISysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author jiangxd
 * @since 2022-03-23
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    private ISysUserRoleService userRoleService;

    @Autowired
    private ISysRoleMenuService roleMenuService;


    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectRolePermissionByUserId(Long userId) {
        List<SysRole> perms = this.getBaseMapper().selectRolePermissionByUserId(userId);

        Set<String> permsSet = new HashSet<>();
        for (SysRole perm : perms) {
            if (ObjectUtil.isNotNull(perm)) {
                permsSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
            }
        }
        return permsSet;
    }

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
    @Override
    public IPage<SysRole> roleList(Page page, String roleName, String roleKey, Integer status, String startTime, String endTime) {
        return this.getBaseMapper().roleList(page, roleName, roleKey, status, startTime, endTime);
    }

    /**
     * 校验角色是否允许操作
     *
     * @param role 角色信息
     */
    @Override
    public boolean checkRoleAllowed(SysRole role) {
        if (ObjectUtil.isNotNull(role.getRoleId()) && role.isAdmin()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * 校验角色名称是否唯一
     *
     * @param role 角色信息
     * @return 结果 true唯一 false不唯一
     */
    @Override
    public boolean checkRoleNameUnique(SysRole role) {
        Long roleId = ObjectUtil.isNull(role.getRoleId()) ? -1L : role.getRoleId();
        SysRole info = this.getOne(new LambdaQueryWrapper<SysRole>().eq(SysRole::getRoleName, role.getRoleName()));
        if (ObjectUtil.isNotNull(info) && info.getRoleId().longValue() != roleId.longValue()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * 校验角色权限是否唯一
     *
     * @param role 角色信息
     * @return 结果 true唯一 false不唯一
     */
    @Override
    public boolean checkRoleKeyUnique(SysRole role) {
        Long roleId = ObjectUtil.isNull(role.getRoleId()) ? -1L : role.getRoleId();
        SysRole info = this.getOne(new LambdaQueryWrapper<SysRole>().eq(SysRole::getRoleKey, role.getRoleKey()));
        if (ObjectUtil.isNotNull(info) && info.getRoleId().longValue() != roleId.longValue()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * 新增保存角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    @Transactional
    public void insertRole(SysRole role) {
        // 新增角色信息
        this.save(role);

        this.insertRoleMenu(role);
    }

    /**
     * 修改保存角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    @Transactional
    public void updateRole(SysRole role) {
        // 修改角色信息
        this.updateById(role);

        this.roleMenuService.remove(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId, role.getRoleId()));

        this.insertRole(role);
    }

    /**
     * 删除角色信息
     *
     * @param roleIds
     */
    @Override
    @Transactional
    public void removeRole(List<Long> roleIds) {
        roleIds.forEach(roleId -> {
            if (!this.checkRoleAllowed(new SysRole(roleId))) {
                throw new GlobalException(GlobalExceptionCode.ERROR, "不允许操作超级管理员角色");
            }

            // 判断角色是否分配
            int urCnt = this.userRoleService.count(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getRoleId, roleId));
            if (urCnt > 0) {
                throw new GlobalException(GlobalExceptionCode.ERROR, "角色已被分配，不能删除");
            }
        });

        // 删除角色菜单关系
        this.roleMenuService.remove(new LambdaQueryWrapper<SysRoleMenu>().in(SysRoleMenu::getRoleId, roleIds));

        // 删除角色
        this.removeByIds(roleIds);
    }

    /**
     * 新增角色菜单信息
     *
     * @param role 角色对象
     */
    public void insertRoleMenu(SysRole role) {
        // 新增用户与角色管理
        List<SysRoleMenu> rmList = new ArrayList<>();
        for (Long menuId : role.getMenuIds()) {
            SysRoleMenu rm = new SysRoleMenu();
            rm.setRoleId(role.getRoleId());
            rm.setMenuId(menuId);
            rmList.add(rm);
        }

        if (CollUtil.isNotEmpty(rmList)) {
            this.roleMenuService.saveBatch(rmList);
        }
    }

}
