package com.eu.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.eu.system.model.po.SysUser;
import com.eu.system.model.vo.SysLoginVo;
import com.eu.system.model.vo.SysUserInfoVo;
import com.eu.system.model.vo.SysUserVo;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author jiangxd
 * @since 2022-03-23
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    SysUser selectUserByUserName(String userName);

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
    IPage<SysUser> userList(Page page, String userName, String phone, Integer status, String startTime, String endTime);

    /**
     * 用户详情
     *
     * @param userId
     */
    SysUserVo getInfo(Long userId);

    /**
     * 校验用户名称是否唯一
     *
     * @param user 用户信息
     * @return 结果 true唯一 false不唯一
     */
    boolean checkUserNameUnique(SysUser user);

    /**
     * 校验手机号码是否唯一
     *
     * @param user 用户信息
     * @return 结果 true唯一 false不唯一
     */
    boolean checkPhoneUnique(SysUser user);

    /**
     * 校验email是否唯一
     *
     * @param user 用户信息
     * @return 结果 true唯一 false不唯一
     */
    boolean checkEmailUnique(SysUser user);

    /**
     * 校验用户是否允许操作
     *
     * @param user 用户信息
     * @return 结果 true唯一 false不唯一
     */
    boolean checkUserAllowed(SysUser user);

    /**
     * 新增用户
     *
     * @param user
     */
    void insertUser(SysUser user);

    /**
     * 修改用户
     *
     * @param user
     */
    void updateUser(SysUser user);

    /**
     * 删除用户
     *
     * @param userIds
     */
    void removeUser(List<Long> userIds);

    /**
     * 用户授权角色
     *
     * @param userId
     * @param roleIds
     */
    void authRole(Long userId, List<Long> roleIds);

    /**
     * 用户登录
     *
     * @param userName
     * @param password
     */
    SysLoginVo login(String userName, String password);

    /**
     * 用户信息
     */
    SysUserInfoVo info(SysUser user);

}
