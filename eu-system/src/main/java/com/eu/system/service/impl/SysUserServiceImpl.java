package com.eu.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eu.core.constants.Constants;
import com.eu.core.enums.UserStatus;
import com.eu.core.exception.GlobalException;
import com.eu.core.exception.GlobalExceptionCode;
import com.eu.core.thread.AsyncManager;
import com.eu.core.utils.JWTUtil;
import com.eu.system.async.AsyncFactory;
import com.eu.system.mapper.SysRoleMapper;
import com.eu.system.mapper.SysUserMapper;
import com.eu.system.model.po.SysRole;
import com.eu.system.model.po.SysUser;
import com.eu.system.model.po.SysUserRole;
import com.eu.system.model.vo.SysLoginVo;
import com.eu.system.model.vo.SysUserInfoVo;
import com.eu.system.model.vo.SysUserVo;
import com.eu.system.service.ISysMenuService;
import com.eu.system.service.ISysRoleService;
import com.eu.system.service.ISysUserRoleService;
import com.eu.system.service.ISysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author jiangxd
 * @since 2022-03-23
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private ISysUserRoleService userRoleService;

    @Value("${jwt.sign}")
    private String jwtSign;

    @Value("${jwt.expiration}")
    private Long jwtExpiration;


    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserByUserName(String userName) {
        return this.getOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUserName, userName));
    }

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
    @Override
    public IPage<SysUser> userList(Page page, String userName, String phone, Integer status, String startTime, String endTime) {
        return this.getBaseMapper().userList(page, userName, phone, status, startTime, endTime);
    }

    /**
     * 用户详情
     *
     * @param userId
     */
    @Override
    public SysUserVo getInfo(Long userId) {
        SysUserVo vo = new SysUserVo();
        // 所有角色
        List<SysRole> allRoleList = this.roleService.list();

        // 用户角色
        List<SysRole> userRoleList = ((SysRoleMapper) this.roleService.getBaseMapper()).selectRolePermissionByUserId(userId);
        vo.setRoles(SysUser.isAdmin(userId) ? allRoleList : userRoleList);

        SysUser user = this.getById(userId);
        vo.setSysUser(user);

        return vo;
    }

    /**
     * 校验用户名称是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public boolean checkUserNameUnique(SysUser user) {
        int count = this.count(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUserName, user.getUserName()));
        if (count > 0) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * 校验手机号码是否唯一
     *
     * @param user 用户信息
     */
    @Override
    public boolean checkPhoneUnique(SysUser user) {
        Long userId = ObjectUtil.isNull(user.getUserId()) ? -1L : user.getUserId();
        SysUser info = this.getOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getPhone, user.getPhone()));
        if (ObjectUtil.isNotNull(info) && info.getUserId().longValue() != userId.longValue()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * 校验email是否唯一
     *
     * @param user 用户信息
     */
    @Override
    public boolean checkEmailUnique(SysUser user) {
        Long userId = ObjectUtil.isNull(user.getUserId()) ? -1L : user.getUserId();
        SysUser info = this.getOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getEmail, user.getEmail()));
        if (ObjectUtil.isNotNull(info) && info.getUserId().longValue() != userId.longValue()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * 校验用户是否允许操作
     *
     * @param user 用户信息
     */
    @Override
    public boolean checkUserAllowed(SysUser user) {
        if (ObjectUtil.isNotNull(user.getUserId()) && user.isAdmin()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * 新增用户
     *
     * @param user
     */
    @Override
    @Transactional
    public void insertUser(SysUser user) {
        this.save(user);

        // 新增用户角色信息
        this.insertUserRole(user.getUserId(), user.getRoleIds());
    }

    /**
     * 修改用户
     *
     * @param user
     */
    @Override
    @Transactional
    public void updateUser(SysUser user) {
        this.updateById(user);

        // 删除用户与角色关联
        this.userRoleService.remove(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, user.getUserId()));

        // 新增用户角色信息
        this.insertUserRole(user.getUserId(), user.getRoleIds());
    }

    /**
     * 删除用户
     *
     * @param userIds
     */
    @Override
    @Transactional
    public void removeUser(List<Long> userIds) {
        userIds.forEach(userId -> {
            if (!this.checkUserAllowed(new SysUser(userId))) {
                throw new GlobalException(GlobalExceptionCode.ERROR, "不允许操作超级管理员用户");
            }
        });

        // 删除用户与角色关联
        this.userRoleService.remove(new LambdaQueryWrapper<SysUserRole>().in(SysUserRole::getUserId, userIds));

        // 删除用户
        this.removeByIds(userIds);
    }

    /**
     * 用户授权角色
     *
     * @param userId
     * @param roleIds
     */
    @Override
    @Transactional
    public void authRole(Long userId, List<Long> roleIds) {
        // 删除用户与角色关联
        this.userRoleService.remove(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, userId));

        // 新增用户角色信息
        this.insertUserRole(userId, roleIds);
    }

    /**
     * 用户登录
     *
     * @param userName
     * @param password
     */
    @Override
    public SysLoginVo login(String userName, String password) {
        SysUser user = this.selectUserByUserName(userName);
        if (null == user) {
            AsyncManager.INSTANCE.execute(AsyncFactory.recordLogininfor(userName, Constants.LOGIN_FAIL, GlobalExceptionCode.USERNAME_IS_NOT_FOUNT.getMsg()));
            throw new GlobalException(GlobalExceptionCode.USERNAME_IS_NOT_FOUNT);
        }

        if (!SecureUtil.md5(password).equals(user.getPassword())) {
            AsyncManager.INSTANCE.execute(AsyncFactory.recordLogininfor(userName, Constants.LOGIN_FAIL, GlobalExceptionCode.PASSWORD_IS_WRONG.getMsg()));
            throw new GlobalException(GlobalExceptionCode.PASSWORD_IS_WRONG);
        }

        if (user.getStatus().equals(UserStatus.DISABLE.getCode())) {
            AsyncManager.INSTANCE.execute(AsyncFactory.recordLogininfor(userName, Constants.LOGIN_FAIL, GlobalExceptionCode.ACCOUNT_IS_DISABLED.getMsg()));
            throw new GlobalException(GlobalExceptionCode.ACCOUNT_IS_DISABLED);
        }

        SysUserInfoVo infoVo = this.info(user);

        // 登录token
        String token = JWTUtil.INSTANCE.generate(user.getUserId(), userName, user.getPhone(), null, this.jwtSign, jwtExpiration, infoVo.getPermissions(), infoVo.getRoles());

        SysLoginVo vo = new SysLoginVo();
        BeanUtils.copyProperties(infoVo, vo);
        vo.setToken(token);

        AsyncManager.INSTANCE.execute(AsyncFactory.recordLogininfor(userName, Constants.LOGIN_SUCCESS, "登录成功"));
        return vo;
    }

    /**
     * 用户信息
     */
    @Override
    public SysUserInfoVo info(SysUser user) {
        // 角色权限标识
        Set<String> rolePerms = this.roleService.selectRolePermissionByUserId(user.getUserId());

        // 菜单权限标识
        Set<String> menuPerms = this.menuService.selectMenuPermsByUserId(user.getUserId());
        SysUserInfoVo vo = new SysUserInfoVo();

        vo.setSysUser(user);
        vo.setPermissions(menuPerms);
        vo.setRoles(rolePerms);

        return vo;
    }

    /**
     * 新增用户角色信息
     *
     * @param roleIds 角色id
     */
    public void insertUserRole(Long userId, List<Long> roleIds) {
        if (CollUtil.isNotEmpty(roleIds)) {
            // 新增用户与角色管理
            List<SysUserRole> list = new ArrayList<>();
            for (Long roleId : roleIds) {
                SysUserRole ur = new SysUserRole();
                ur.setUserId(userId);
                ur.setRoleId(roleId);
                list.add(ur);
            }
            if (list.size() > 0) {
                this.userRoleService.saveBatch(list);
            }
        }
    }

}
