package com.eu.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eu.core.constants.Constants;
import com.eu.core.constants.UserConstants;
import com.eu.system.mapper.SysMenuMapper;
import com.eu.system.model.po.SysMenu;
import com.eu.system.model.po.SysRole;
import com.eu.system.model.po.SysRoleMenu;
import com.eu.system.model.po.SysUser;
import com.eu.system.model.vo.SysMenuTreeVo;
import com.eu.system.model.vo.SysMetaVo;
import com.eu.system.model.vo.SysRouterVo;
import com.eu.system.service.ISysMenuService;
import com.eu.system.service.ISysRoleMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author jiangxd
 * @since 2022-03-23
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Autowired
    private ISysRoleMenuService roleMenuService;

    /**
     * 所有权限标识
     */
    private static final String ALL_PERMISSION = "*:*:*";


    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectMenuPermsByUserId(Long userId) {
        List<String> perms;
        if (SysUser.isAdmin(userId)) {
//            perms = this.list().parallelStream().map(SysMenu::getPerms).collect(Collectors.toList());
            perms = Collections.singletonList(ALL_PERMISSION);
        } else {
            perms = this.getBaseMapper().selectMenuPermsByUserId(userId);
        }

        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotEmpty(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 根据用户ID查询菜单
     *
     * @param userId 用户名称
     * @return 菜单列表
     */
    @Override
    public List<SysMenu> selectMenuTreeByUserId(Long userId) {
        List<SysMenu> menus;
        if (SysUser.isAdmin(userId)) {
            menus = this.getBaseMapper().selectMenuTreeAll();
        } else {
            menus = this.getBaseMapper().selectMenuTreeByUserId(userId);
        }
        return this.getChildPerms(menus, 0);
    }

    /**
     * 构建前端路由所需要的菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    @Override
    public List<SysRouterVo> buildMenus(List<SysMenu> menus) {
        List<SysRouterVo> routers = new LinkedList<>();
        for (SysMenu menu : menus) {
            SysRouterVo router = new SysRouterVo();
            router.setHidden(1 == menu.getVisible());
            router.setName(this.getRouteName(menu));
            router.setPath(this.getRouterPath(menu));
            router.setComponent(this.getComponent(menu));
            router.setQuery(menu.getQuery());
            router.setMeta(new SysMetaVo(menu.getMenuName(), menu.getIcon(), menu.getPath()));
            List<SysMenu> cMenus = menu.getChildren();
            if (CollUtil.isNotEmpty(cMenus) && UserConstants.TYPE_DIR.equals(menu.getMenuType())) {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(cMenus));
            } else if (isMenuFrame(menu)) {
                router.setMeta(null);
                List<SysRouterVo> childrenList = new ArrayList<>();
                SysRouterVo children = new SysRouterVo();
                children.setPath(menu.getPath());
                children.setComponent(menu.getComponent());
                children.setName(StringUtils.capitalize(menu.getPath()));
                children.setMeta(new SysMetaVo(menu.getMenuName(), menu.getIcon(), menu.getPath()));
                children.setQuery(menu.getQuery());
                childrenList.add(children);
                router.setChildren(childrenList);
            } else if (menu.getParentId().intValue() == 0 && isInnerLink(menu)) {
                router.setMeta(new SysMetaVo(menu.getMenuName(), menu.getIcon()));
                router.setPath("/");
                List<SysRouterVo> childrenList = new ArrayList<>();
                SysRouterVo children = new SysRouterVo();
                String routerPath = innerLinkReplaceEach(menu.getPath());
                children.setPath(routerPath);
                children.setComponent(UserConstants.INNER_LINK);
                children.setName(StringUtils.capitalize(routerPath));
                children.setMeta(new SysMetaVo(menu.getMenuName(), menu.getIcon(), menu.getPath()));
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            routers.add(router);
        }
        return routers;
    }

    /**
     * 查询系统菜单列表
     *
     * @param userId 用户ID
     */
    @Override
    public List<SysMenu> menuList(String menuName, Integer visible, Integer status, Long userId) {
        List<SysMenu> menuList;
        // 管理员显示所有菜单信息
        if (SysUser.isAdmin(userId)) {
            menuList = this.list(new LambdaQueryWrapper<SysMenu>()
                    .apply(StrUtil.isNotBlank(menuName), "menu_name like '%" + menuName + "%'")
                    .apply(null != visible, "visible = " + visible)
                    .apply(null != status, "status = " + status));
        } else {
            menuList = this.getBaseMapper().selectMenuListByUserId(menuName, visible, status, userId);
        }

        return menuList;
    }

    /**
     * 菜单树形结构
     *
     * @param menuName
     * @param visible
     * @param status
     * @param userId
     */
    @Override
    public List<SysMenuTreeVo> menuTree(String menuName, Integer visible, Integer status, Long userId) {
        List<SysMenu> menuList = this.menuList(menuName, visible, status, userId);

        List<SysMenu> menuTrees = this.buildMenuTree(menuList, null);
        return menuTrees.stream().map(SysMenuTreeVo::new).collect(Collectors.toList());
    }

    /**
     * 获取角色菜单树形列表
     *
     * @param roleId
     * @param userId
     */
    @Override
    public List<SysMenuTreeVo> roleMenuTree(Long roleId, Long userId) {
        List<SysMenu> menuList = this.menuList(null, null, null, userId);

        // 角色已授权的菜单
        List<Long> checkedList = this.checkedMenu(roleId);
        List<SysMenu> menuTrees = this.buildMenuTree(menuList, checkedList);
        return menuTrees.stream().map(SysMenuTreeVo::new).collect(Collectors.toList());
    }

    /**
     * 校验菜单名称是否唯一
     *
     * @param menu 菜单信息
     * @return 结果 true唯一 false不唯一
     */
    @Override
    public boolean checkMenuNameUnique(SysMenu menu) {
        Long menuId = ObjectUtil.isNull(menu.getMenuId()) ? -1L : menu.getMenuId();
        SysMenu info = this.getOne(new LambdaQueryWrapper<SysMenu>()
                .eq(SysMenu::getMenuName, menu.getMenuName())
                .eq(SysMenu::getParentId, menu.getParentId()));
        if (ObjectUtil.isNotNull(info) && info.getMenuId().longValue() != menuId.longValue()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * 是否存在菜单子节点
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    public boolean hasChildByMenuId(Long menuId) {
        int result = this.count(new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getParentId, menuId));
        return result > 0;
    }

    /**
     * 查询菜单使用数量
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    public boolean checkMenuExistRole(Long menuId) {
        int result = this.roleMenuService.count(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getMenuId, menuId));
        return result > 0;
    }

    /**
     * 构建前端所需要树结构
     *
     * @param menus 菜单列表
     * @return 树结构列表
     */
    private List<SysMenu> buildMenuTree(List<SysMenu> menus, List<Long> checkedList) {
        List<SysMenu> returnList = new ArrayList<>();
        List<Long> tempList = new ArrayList<>();

        for (SysMenu menu : menus) {
            if ((CollUtil.isNotEmpty(checkedList) && checkedList.contains(menu.getMenuId()))) {
                menu.setIsChecked(Boolean.TRUE);
            }
            tempList.add(menu.getMenuId());
        }

        Iterator<SysMenu> iterator = menus.iterator();
        while (iterator.hasNext()) {
            SysMenu menu = iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(menu.getParentId())) {
                this.recursionFn(menus, menu);
                returnList.add(menu);
            }
        }

        if (returnList.isEmpty()) {
            returnList = menus;
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private void recursionFn(List<SysMenu> list, SysMenu t) {
        // 得到子节点列表
        List<SysMenu> childList = this.getChildList(list, t);
        t.setChildren(childList);
        for (SysMenu tChild : childList) {
            if (this.hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<SysMenu> getChildList(List<SysMenu> list, SysMenu t) {
        List<SysMenu> tlist = new ArrayList<>();
        Iterator<SysMenu> it = list.iterator();
        while (it.hasNext()) {
            SysMenu n = it.next();
            if (n.getParentId().longValue() == t.getMenuId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysMenu> list, SysMenu t) {
        return this.getChildList(list, t).size() > 0;
    }

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list     分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public List<SysMenu> getChildPerms(List<SysMenu> list, int parentId) {
        List<SysMenu> returnList = new ArrayList<>();
        Iterator<SysMenu> iterator = list.iterator();
        while (iterator.hasNext()) {
            SysMenu t = iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == parentId) {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 获取路由名称
     *
     * @param menu 菜单信息
     * @return 路由名称
     */
    public String getRouteName(SysMenu menu) {
        String routerName = StringUtils.capitalize(menu.getPath());
        // 非外链并且是一级目录（类型为目录）
        if (this.isMenuFrame(menu)) {
            routerName = StringUtils.EMPTY;
        }
        return routerName;
    }

    /**
     * 是否为菜单内部跳转
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isMenuFrame(SysMenu menu) {
        return menu.getParentId().intValue() == 0
                && UserConstants.TYPE_MENU.equals(menu.getMenuType())
                && menu.getIsFrame().equals(UserConstants.NO_FRAME);
    }

    /**
     * 是否为内链组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isInnerLink(SysMenu menu) {
        return menu.getIsFrame().equals(UserConstants.NO_FRAME)
                && StringUtils.startsWithAny(menu.getPath(), Constants.HTTP, Constants.HTTPS);
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(SysMenu menu) {
        String routerPath = menu.getPath();
        // 内链打开外网方式
        if (menu.getParentId().intValue() != 0 && this.isInnerLink(menu)) {
            routerPath = this.innerLinkReplaceEach(routerPath);
        }
        // 非外链并且是一级目录（类型为目录）
        if (0 == menu.getParentId().intValue()
                && UserConstants.TYPE_DIR.equals(menu.getMenuType())
                && UserConstants.NO_FRAME.equals(menu.getIsFrame())) {
            routerPath = "/" + menu.getPath();
        }
        // 非外链并且是一级目录（类型为菜单）
        else if (isMenuFrame(menu)) {
            routerPath = "/";
        }
        return routerPath;
    }

    /**
     * 内链域名特殊字符替换
     */
    public String innerLinkReplaceEach(String path) {
        return StringUtils.replaceEach(path, new String[]{Constants.HTTP, Constants.HTTPS},
                new String[]{"", ""});
    }

    /**
     * 获取组件信息
     *
     * @param menu 菜单信息
     * @return 组件信息
     */
    public String getComponent(SysMenu menu) {
        String component = UserConstants.LAYOUT;
        if (StringUtils.isNotEmpty(menu.getComponent()) && !isMenuFrame(menu)) {
            component = menu.getComponent();
        } else if (StringUtils.isEmpty(menu.getComponent()) && menu.getParentId().intValue() != 0 && isInnerLink(menu)) {
            component = UserConstants.INNER_LINK;
        } else if (StringUtils.isEmpty(menu.getComponent()) && this.isParentView(menu)) {
            component = UserConstants.PARENT_VIEW;
        }
        return component;
    }

    /**
     * 是否为parent_view组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isParentView(SysMenu menu) {
        return menu.getParentId().intValue() != 0 && UserConstants.TYPE_DIR.equals(menu.getMenuType());
    }

    /**
     * 获取角色已授权的menuId
     *
     * @param roleId
     */
    private List<Long> checkedMenu(Long roleId) {
        if (SysRole.isAdmin(roleId)) {
            return this.list().parallelStream().map(SysMenu::getMenuId).collect(Collectors.toList());
        } else {
            return this.getBaseMapper().selectMenuListByRoleId(roleId);
        }
    }

}
