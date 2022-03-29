package com.eu.system.service.impl;

import com.eu.system.model.po.SysUserRole;
import com.eu.system.mapper.SysUserRoleMapper;
import com.eu.system.service.ISysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户和角色关联表 服务实现类
 * </p>
 *
 * @author jiangxd
 * @since 2022-03-23
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

}
