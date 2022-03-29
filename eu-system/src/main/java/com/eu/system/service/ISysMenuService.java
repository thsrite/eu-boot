package com.eu.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eu.system.model.po.SysMenu;
import com.eu.system.model.vo.SysMenuTreeVo;
import com.eu.system.model.vo.SysRouterVo;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author jiangxd
 * @since 2022-03-23
 */
public interface ISysMenuService extends IService<SysMenu> {

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    Set<String> selectMenuPermsByUserId(Long userId);

    /**
     * 根据用户ID查询菜单树信息
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    List<SysMenu> selectMenuTreeByUserId(Long userId);

    /**
     * 构建前端路由所需要的菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    List<SysRouterVo> buildMenus(List<SysMenu> menus);

    /**
     * 菜单列表
     *
     * @param userId 用户ID
     */
    List<SysMenu> menuList(String menuName, Integer visible, Integer status, Long userId);

    /**
     * 菜单树形结构
     *
     * @param menuName
     * @param visible
     * @param status
     * @param userId
     */
    List<SysMenuTreeVo> menuTree(String menuName, Integer visible, Integer status, Long userId);

    /**
     * 获取角色菜单树形列表
     *
     * @param roleId
     * @param userId
     */
    List<SysMenuTreeVo> roleMenuTree(Long roleId, Long userId);

    /**
     * 校验菜单名称是否唯一
     *
     * @param menu 菜单信息
     * @return 结果 true唯一 false不唯一
     */
    boolean checkMenuNameUnique(SysMenu menu);

    /**
     * 是否存在菜单子节点
     *
     * @param menuId 菜单ID
     * @return 结果 true 存在 false 不存在
     */
    boolean hasChildByMenuId(Long menuId);

    /**
     * 查询菜单是否存在角色
     *
     * @param menuId 菜单ID
     * @return 结果 true 存在 false 不存在
     */
    boolean checkMenuExistRole(Long menuId);

}
