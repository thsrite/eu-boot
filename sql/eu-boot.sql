/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.123
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : 192.168.1.123:3306
 Source Schema         : eu-boot

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 25/03/2022 17:46:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor` (
  `info_id` bigint NOT NULL COMMENT '访问ID',
  `user_name` varchar(50) DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(128) DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `status` char(1) DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) DEFAULT '' COMMENT '提示消息',
  `login_time` datetime DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统访问记录';

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------
BEGIN;
INSERT INTO `sys_logininfor` VALUES (667232086851059712, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '1', '密码输入错误', '2022-03-25 15:08:56');
INSERT INTO `sys_logininfor` VALUES (667232100084088832, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '1', '密码输入错误', '2022-03-25 15:08:59');
INSERT INTO `sys_logininfor` VALUES (667237699408625664, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 15:31:14');
INSERT INTO `sys_logininfor` VALUES (667241331013189632, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 15:45:40');
INSERT INTO `sys_logininfor` VALUES (667242384941121536, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 15:49:52');
INSERT INTO `sys_logininfor` VALUES (667245002220371968, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:00:16');
INSERT INTO `sys_logininfor` VALUES (667245024156581888, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:00:21');
INSERT INTO `sys_logininfor` VALUES (667245026178236416, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:00:21');
INSERT INTO `sys_logininfor` VALUES (667245026941599744, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:00:21');
INSERT INTO `sys_logininfor` VALUES (667245027700768768, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:00:22');
INSERT INTO `sys_logininfor` VALUES (667245028438966272, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:00:22');
INSERT INTO `sys_logininfor` VALUES (667245029097472000, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:00:22');
INSERT INTO `sys_logininfor` VALUES (667245029852446720, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:00:22');
INSERT INTO `sys_logininfor` VALUES (667245030557089792, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:00:22');
INSERT INTO `sys_logininfor` VALUES (667245031303675904, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:00:23');
INSERT INTO `sys_logininfor` VALUES (667245031974764544, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:00:23');
INSERT INTO `sys_logininfor` VALUES (667245032784265216, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:00:23');
INSERT INTO `sys_logininfor` VALUES (667245035418288128, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:00:23');
INSERT INTO `sys_logininfor` VALUES (667245043202916352, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:00:25');
INSERT INTO `sys_logininfor` VALUES (667245044004028416, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:00:26');
INSERT INTO `sys_logininfor` VALUES (667245044712865792, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:00:26');
INSERT INTO `sys_logininfor` VALUES (667245045417508864, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:00:26');
INSERT INTO `sys_logininfor` VALUES (667245160836366336, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:00:53');
INSERT INTO `sys_logininfor` VALUES (667245336615452672, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:01:35');
INSERT INTO `sys_logininfor` VALUES (667245340826533888, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:01:36');
INSERT INTO `sys_logininfor` VALUES (667245343661883392, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:01:37');
INSERT INTO `sys_logininfor` VALUES (667245344358137856, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:01:37');
INSERT INTO `sys_logininfor` VALUES (667245345113112576, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:01:37');
INSERT INTO `sys_logininfor` VALUES (667245345775812608, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:01:37');
INSERT INTO `sys_logininfor` VALUES (667245346430124032, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:01:38');
INSERT INTO `sys_logininfor` VALUES (667245347130572800, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:01:38');
INSERT INTO `sys_logininfor` VALUES (667249130627661824, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:16:40');
INSERT INTO `sys_logininfor` VALUES (667249137296605184, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:16:41');
INSERT INTO `sys_logininfor` VALUES (667249141960671232, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:16:43');
INSERT INTO `sys_logininfor` VALUES (667249144368201728, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:16:43');
INSERT INTO `sys_logininfor` VALUES (667249145110593536, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:16:43');
INSERT INTO `sys_logininfor` VALUES (667249145832013824, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:16:43');
INSERT INTO `sys_logininfor` VALUES (667249146482130944, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:16:44');
INSERT INTO `sys_logininfor` VALUES (667249147094499328, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:16:44');
INSERT INTO `sys_logininfor` VALUES (667249965831028736, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:19:59');
INSERT INTO `sys_logininfor` VALUES (667249970184716288, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:20:00');
INSERT INTO `sys_logininfor` VALUES (667249984189497344, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:20:03');
INSERT INTO `sys_logininfor` VALUES (667249986446032896, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:20:04');
INSERT INTO `sys_logininfor` VALUES (667249987150675968, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:20:04');
INSERT INTO `sys_logininfor` VALUES (667249987872096256, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:20:04');
INSERT INTO `sys_logininfor` VALUES (667250130084167680, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:20:38');
INSERT INTO `sys_logininfor` VALUES (667251159504781312, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:24:44');
INSERT INTO `sys_logininfor` VALUES (667252418521595904, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:29:44');
INSERT INTO `sys_logininfor` VALUES (667252422585876480, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:29:45');
INSERT INTO `sys_logininfor` VALUES (667252425748381696, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:29:45');
INSERT INTO `sys_logininfor` VALUES (667252455628603392, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:29:53');
INSERT INTO `sys_logininfor` VALUES (667252622561902592, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:30:32');
INSERT INTO `sys_logininfor` VALUES (667252625820876800, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:30:33');
INSERT INTO `sys_logininfor` VALUES (667252626479382528, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:30:33');
INSERT INTO `sys_logininfor` VALUES (667252627137888256, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:30:33');
INSERT INTO `sys_logininfor` VALUES (667252627842531328, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:30:34');
INSERT INTO `sys_logininfor` VALUES (667258421237514240, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:53:35');
INSERT INTO `sys_logininfor` VALUES (667258701983252480, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:54:42');
INSERT INTO `sys_logininfor` VALUES (667258706160779264, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:54:43');
INSERT INTO `sys_logininfor` VALUES (667258708752859136, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:54:43');
INSERT INTO `sys_logininfor` VALUES (667258709453307904, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:54:44');
INSERT INTO `sys_logininfor` VALUES (667258710149562368, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 16:54:44');
INSERT INTO `sys_logininfor` VALUES (667261639073988608, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 17:06:22');
INSERT INTO `sys_logininfor` VALUES (667262627222650880, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 17:10:18');
INSERT INTO `sys_logininfor` VALUES (667263971287367680, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 17:15:38');
INSERT INTO `sys_logininfor` VALUES (667264634876592128, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Mac OS X', '0', '登录成功', '2022-03-25 17:18:16');
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` bigint DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int DEFAULT '0' COMMENT '显示顺序',
  `path` varchar(200) DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `query` varchar(255) DEFAULT NULL COMMENT '路由参数',
  `is_frame` int DEFAULT '1' COMMENT '是否为外链（0是 1否）',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_delete` int DEFAULT '0' COMMENT '是否删除',
  `version` int DEFAULT '0' COMMENT '乐观锁',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单权限表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 1, 'system', NULL, '', 1, 'M', '0', '0', '', 'system', '系统管理目录', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (2, '系统监控', 0, 2, 'monitor', NULL, '', 1, 'M', '0', '0', '', 'monitor', '系统监控目录', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (3, '系统工具', 0, 3, 'tool', NULL, '', 1, 'M', '0', '0', '', 'tool', '系统工具目录', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (4, '若依官网', 0, 4, 'http://ruoyi.vip', NULL, '', 0, 'M', '0', '0', '', 'guide', '若依官网地址', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (100, '用户管理', 1, 1, 'user', 'system/user/index', '', 1, 'C', '0', '0', 'system:user:list', 'user', '用户管理菜单', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (101, '角色管理', 1, 2, 'role', 'system/role/index', '', 1, 'C', '0', '0', 'system:role:list', 'peoples', '角色管理菜单', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (102, '菜单管理', 1, 3, 'menu', 'system/menu/index', '', 1, 'C', '0', '0', 'system:menu:list', 'tree-table', '菜单管理菜单', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (103, '部门管理', 1, 4, 'dept', 'system/dept/index', '', 1, 'C', '0', '0', 'system:dept:list', 'tree', '部门管理菜单', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (104, '岗位管理', 1, 5, 'post', 'system/post/index', '', 1, 'C', '0', '0', 'system:post:list', 'post', '岗位管理菜单', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (105, '字典管理', 1, 6, 'dict', 'system/dict/index', '', 1, 'C', '0', '0', 'system:dict:list', 'dict', '字典管理菜单', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (106, '参数设置', 1, 7, 'config', 'system/config/index', '', 1, 'C', '0', '0', 'system:config:list', 'edit', '参数设置菜单', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (107, '通知公告', 1, 8, 'notice', 'system/notice/index', '', 1, 'C', '0', '0', 'system:notice:list', 'message', '通知公告菜单', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (108, '日志管理', 1, 9, 'log', '', '', 1, 'M', '0', '0', '', 'log', '日志管理菜单', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (109, '在线用户', 2, 1, 'online', 'monitor/online/index', '', 1, 'C', '0', '0', 'monitor:online:list', 'online', '在线用户菜单', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (110, '定时任务', 2, 2, 'job', 'monitor/job/index', '', 1, 'C', '0', '0', 'monitor:job:list', 'job', '定时任务菜单', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (111, '数据监控', 2, 3, 'druid', 'monitor/druid/index', '', 1, 'C', '0', '0', 'monitor:druid:list', 'druid', '数据监控菜单', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (112, '服务监控', 2, 4, 'server', 'monitor/server/index', '', 1, 'C', '0', '0', 'monitor:server:list', 'server', '服务监控菜单', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (113, '缓存监控', 2, 5, 'cache', 'monitor/cache/index', '', 1, 'C', '0', '0', 'monitor:cache:list', 'redis', '缓存监控菜单', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (114, '表单构建', 3, 1, 'build', 'tool/build/index', '', 1, 'C', '0', '0', 'tool:build:list', 'build', '表单构建菜单', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (115, '代码生成', 3, 2, 'gen', 'tool/gen/index', '', 1, 'C', '0', '0', 'tool:gen:list', 'code', '代码生成菜单', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (116, '系统接口', 3, 3, 'swagger', 'tool/swagger/index', '', 1, 'C', '0', '0', 'tool:swagger:list', 'swagger', '系统接口菜单', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (500, '操作日志', 108, 1, 'operlog', 'monitor/operlog/index', '', 1, 'C', '0', '0', 'monitor:operlog:list', 'form', '操作日志菜单', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (501, '登录日志', 108, 2, 'logininfor', 'monitor/logininfor/index', '', 1, 'C', '0', '0', 'monitor:logininfor:list', 'logininfor', '登录日志菜单', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1001, '用户查询', 100, 1, '', '', '', 1, 'F', '0', '0', 'system:user:query', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1002, '用户新增', 100, 2, '', '', '', 1, 'F', '0', '0', 'system:user:add', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1003, '用户修改', 100, 3, '', '', '', 1, 'F', '0', '0', 'system:user:edit', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1004, '用户删除', 100, 4, '', '', '', 1, 'F', '0', '0', 'system:user:remove', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1005, '用户导出', 100, 5, '', '', '', 1, 'F', '0', '0', 'system:user:export', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1006, '用户导入', 100, 6, '', '', '', 1, 'F', '0', '0', 'system:user:import', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1007, '重置密码', 100, 7, '', '', '', 1, 'F', '0', '0', 'system:user:resetPwd', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1008, '角色查询', 101, 1, '', '', '', 1, 'F', '0', '0', 'system:role:query', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1009, '角色新增', 101, 2, '', '', '', 1, 'F', '0', '0', 'system:role:add', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1010, '角色修改', 101, 3, '', '', '', 1, 'F', '0', '0', 'system:role:edit', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1011, '角色删除', 101, 4, '', '', '', 1, 'F', '0', '0', 'system:role:remove', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1012, '角色导出', 101, 5, '', '', '', 1, 'F', '0', '0', 'system:role:export', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1013, '菜单查询', 102, 1, '', '', '', 1, 'F', '0', '0', 'system:menu:query', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1014, '菜单新增', 102, 2, '', '', '', 1, 'F', '0', '0', 'system:menu:add', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1015, '菜单修改', 102, 3, '', '', '', 1, 'F', '0', '0', 'system:menu:edit', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1016, '菜单删除', 102, 4, '', '', '', 1, 'F', '0', '0', 'system:menu:remove', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1017, '部门查询', 103, 1, '', '', '', 1, 'F', '0', '0', 'system:dept:query', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1018, '部门新增', 103, 2, '', '', '', 1, 'F', '0', '0', 'system:dept:add', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1019, '部门修改', 103, 3, '', '', '', 1, 'F', '0', '0', 'system:dept:edit', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1020, '部门删除', 103, 4, '', '', '', 1, 'F', '0', '0', 'system:dept:remove', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1021, '岗位查询', 104, 1, '', '', '', 1, 'F', '0', '0', 'system:post:query', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1022, '岗位新增', 104, 2, '', '', '', 1, 'F', '0', '0', 'system:post:add', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1023, '岗位修改', 104, 3, '', '', '', 1, 'F', '0', '0', 'system:post:edit', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1024, '岗位删除', 104, 4, '', '', '', 1, 'F', '0', '0', 'system:post:remove', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1025, '岗位导出', 104, 5, '', '', '', 1, 'F', '0', '0', 'system:post:export', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1026, '字典查询', 105, 1, '#', '', '', 1, 'F', '0', '0', 'system:dict:query', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1027, '字典新增', 105, 2, '#', '', '', 1, 'F', '0', '0', 'system:dict:add', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1028, '字典修改', 105, 3, '#', '', '', 1, 'F', '0', '0', 'system:dict:edit', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1029, '字典删除', 105, 4, '#', '', '', 1, 'F', '0', '0', 'system:dict:remove', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1030, '字典导出', 105, 5, '#', '', '', 1, 'F', '0', '0', 'system:dict:export', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1031, '参数查询', 106, 1, '#', '', '', 1, 'F', '0', '0', 'system:config:query', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1032, '参数新增', 106, 2, '#', '', '', 1, 'F', '0', '0', 'system:config:add', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1033, '参数修改', 106, 3, '#', '', '', 1, 'F', '0', '0', 'system:config:edit', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1034, '参数删除', 106, 4, '#', '', '', 1, 'F', '0', '0', 'system:config:remove', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1035, '参数导出', 106, 5, '#', '', '', 1, 'F', '0', '0', 'system:config:export', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1036, '公告查询', 107, 1, '#', '', '', 1, 'F', '0', '0', 'system:notice:query', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1037, '公告新增', 107, 2, '#', '', '', 1, 'F', '0', '0', 'system:notice:add', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1038, '公告修改', 107, 3, '#', '', '', 1, 'F', '0', '0', 'system:notice:edit', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1039, '公告删除', 107, 4, '#', '', '', 1, 'F', '0', '0', 'system:notice:remove', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1040, '操作查询', 500, 1, '#', '', '', 1, 'F', '0', '0', 'monitor:operlog:query', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1041, '操作删除', 500, 2, '#', '', '', 1, 'F', '0', '0', 'monitor:operlog:remove', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1042, '日志导出', 500, 4, '#', '', '', 1, 'F', '0', '0', 'monitor:operlog:export', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1043, '登录查询', 501, 1, '#', '', '', 1, 'F', '0', '0', 'monitor:logininfor:query', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1044, '登录删除', 501, 2, '#', '', '', 1, 'F', '0', '0', 'monitor:logininfor:remove', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1045, '日志导出', 501, 3, '#', '', '', 1, 'F', '0', '0', 'monitor:logininfor:export', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1046, '在线查询', 109, 1, '#', '', '', 1, 'F', '0', '0', 'monitor:online:query', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1047, '批量强退', 109, 2, '#', '', '', 1, 'F', '0', '0', 'monitor:online:batchLogout', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1048, '单条强退', 109, 3, '#', '', '', 1, 'F', '0', '0', 'monitor:online:forceLogout', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1049, '任务查询', 110, 1, '#', '', '', 1, 'F', '0', '0', 'monitor:job:query', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1050, '任务新增', 110, 2, '#', '', '', 1, 'F', '0', '0', 'monitor:job:add', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1051, '任务修改', 110, 3, '#', '', '', 1, 'F', '0', '0', 'monitor:job:edit', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1052, '任务删除', 110, 4, '#', '', '', 1, 'F', '0', '0', 'monitor:job:remove', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1053, '状态修改', 110, 5, '#', '', '', 1, 'F', '0', '0', 'monitor:job:changeStatus', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1054, '任务导出', 110, 7, '#', '', '', 1, 'F', '0', '0', 'monitor:job:export', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1055, '生成查询', 115, 1, '#', '', '', 1, 'F', '0', '0', 'tool:gen:query', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1056, '生成修改', 115, 2, '#', '', '', 1, 'F', '0', '0', 'tool:gen:edit', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1057, '生成删除', 115, 3, '#', '', '', 1, 'F', '0', '0', 'tool:gen:remove', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1058, '导入代码', 115, 2, '#', '', '', 1, 'F', '0', '0', 'tool:gen:import', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1059, '预览代码', 115, 4, '#', '', '', 1, 'F', '0', '0', 'tool:gen:preview', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
INSERT INTO `sys_menu` VALUES (1060, '生成代码', 115, 5, '#', '', '', 1, 'F', '0', '0', 'tool:gen:code', '#', '', 1, '2022-03-24 17:14:32', 1, '2022-03-24 17:14:32', 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log` (
  `oper_id` bigint NOT NULL COMMENT '日志主键',
  `title` varchar(50) DEFAULT '' COMMENT '模块标题',
  `business_type` int DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) DEFAULT '' COMMENT '请求方式',
  `operator_type` int DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) DEFAULT '' COMMENT '操作人员',
  `oper_url` varchar(255) DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(128) DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) DEFAULT '' COMMENT '返回参数',
  `status` int DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='操作日志记录';

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `role_sort` int DEFAULT NULL COMMENT '角色排序',
  `status` int NOT NULL COMMENT '角色状态（0正常 1停用）',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_delete` int DEFAULT '0' COMMENT '是否删除',
  `version` int DEFAULT '0' COMMENT '乐观锁',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色信息表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin', 1, 0, '超级管理员', 1, '2022-03-24 17:11:00', 1, '2022-03-24 17:11:02', 0, 0);
INSERT INTO `sys_role` VALUES (2, '普通角色', 'common', 2, 0, '普通角色', 1, '2022-03-24 17:11:06', 1, '2022-03-24 17:11:05', 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色和菜单关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` VALUES (2, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2);
INSERT INTO `sys_role_menu` VALUES (2, 3);
INSERT INTO `sys_role_menu` VALUES (2, 4);
INSERT INTO `sys_role_menu` VALUES (2, 100);
INSERT INTO `sys_role_menu` VALUES (2, 101);
INSERT INTO `sys_role_menu` VALUES (2, 102);
INSERT INTO `sys_role_menu` VALUES (2, 103);
INSERT INTO `sys_role_menu` VALUES (2, 104);
INSERT INTO `sys_role_menu` VALUES (2, 105);
INSERT INTO `sys_role_menu` VALUES (2, 106);
INSERT INTO `sys_role_menu` VALUES (2, 107);
INSERT INTO `sys_role_menu` VALUES (2, 108);
INSERT INTO `sys_role_menu` VALUES (2, 109);
INSERT INTO `sys_role_menu` VALUES (2, 110);
INSERT INTO `sys_role_menu` VALUES (2, 111);
INSERT INTO `sys_role_menu` VALUES (2, 112);
INSERT INTO `sys_role_menu` VALUES (2, 113);
INSERT INTO `sys_role_menu` VALUES (2, 114);
INSERT INTO `sys_role_menu` VALUES (2, 115);
INSERT INTO `sys_role_menu` VALUES (2, 116);
INSERT INTO `sys_role_menu` VALUES (2, 500);
INSERT INTO `sys_role_menu` VALUES (2, 501);
INSERT INTO `sys_role_menu` VALUES (2, 1000);
INSERT INTO `sys_role_menu` VALUES (2, 1001);
INSERT INTO `sys_role_menu` VALUES (2, 1002);
INSERT INTO `sys_role_menu` VALUES (2, 1003);
INSERT INTO `sys_role_menu` VALUES (2, 1004);
INSERT INTO `sys_role_menu` VALUES (2, 1005);
INSERT INTO `sys_role_menu` VALUES (2, 1006);
INSERT INTO `sys_role_menu` VALUES (2, 1007);
INSERT INTO `sys_role_menu` VALUES (2, 1008);
INSERT INTO `sys_role_menu` VALUES (2, 1009);
INSERT INTO `sys_role_menu` VALUES (2, 1010);
INSERT INTO `sys_role_menu` VALUES (2, 1011);
INSERT INTO `sys_role_menu` VALUES (2, 1012);
INSERT INTO `sys_role_menu` VALUES (2, 1013);
INSERT INTO `sys_role_menu` VALUES (2, 1014);
INSERT INTO `sys_role_menu` VALUES (2, 1015);
INSERT INTO `sys_role_menu` VALUES (2, 1016);
INSERT INTO `sys_role_menu` VALUES (2, 1017);
INSERT INTO `sys_role_menu` VALUES (2, 1018);
INSERT INTO `sys_role_menu` VALUES (2, 1019);
INSERT INTO `sys_role_menu` VALUES (2, 1020);
INSERT INTO `sys_role_menu` VALUES (2, 1021);
INSERT INTO `sys_role_menu` VALUES (2, 1022);
INSERT INTO `sys_role_menu` VALUES (2, 1023);
INSERT INTO `sys_role_menu` VALUES (2, 1024);
INSERT INTO `sys_role_menu` VALUES (2, 1025);
INSERT INTO `sys_role_menu` VALUES (2, 1026);
INSERT INTO `sys_role_menu` VALUES (2, 1027);
INSERT INTO `sys_role_menu` VALUES (2, 1028);
INSERT INTO `sys_role_menu` VALUES (2, 1029);
INSERT INTO `sys_role_menu` VALUES (2, 1030);
INSERT INTO `sys_role_menu` VALUES (2, 1031);
INSERT INTO `sys_role_menu` VALUES (2, 1032);
INSERT INTO `sys_role_menu` VALUES (2, 1033);
INSERT INTO `sys_role_menu` VALUES (2, 1034);
INSERT INTO `sys_role_menu` VALUES (2, 1035);
INSERT INTO `sys_role_menu` VALUES (2, 1036);
INSERT INTO `sys_role_menu` VALUES (2, 1037);
INSERT INTO `sys_role_menu` VALUES (2, 1038);
INSERT INTO `sys_role_menu` VALUES (2, 1039);
INSERT INTO `sys_role_menu` VALUES (2, 1040);
INSERT INTO `sys_role_menu` VALUES (2, 1041);
INSERT INTO `sys_role_menu` VALUES (2, 1042);
INSERT INTO `sys_role_menu` VALUES (2, 1043);
INSERT INTO `sys_role_menu` VALUES (2, 1044);
INSERT INTO `sys_role_menu` VALUES (2, 1045);
INSERT INTO `sys_role_menu` VALUES (2, 1046);
INSERT INTO `sys_role_menu` VALUES (2, 1047);
INSERT INTO `sys_role_menu` VALUES (2, 1048);
INSERT INTO `sys_role_menu` VALUES (2, 1049);
INSERT INTO `sys_role_menu` VALUES (2, 1050);
INSERT INTO `sys_role_menu` VALUES (2, 1051);
INSERT INTO `sys_role_menu` VALUES (2, 1052);
INSERT INTO `sys_role_menu` VALUES (2, 1053);
INSERT INTO `sys_role_menu` VALUES (2, 1054);
INSERT INTO `sys_role_menu` VALUES (2, 1055);
INSERT INTO `sys_role_menu` VALUES (2, 1056);
INSERT INTO `sys_role_menu` VALUES (2, 1057);
INSERT INTO `sys_role_menu` VALUES (2, 1058);
INSERT INTO `sys_role_menu` VALUES (2, 1059);
INSERT INTO `sys_role_menu` VALUES (2, 1060);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `user_name` varchar(30) NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户昵称',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '手机号码',
  `sex` int DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) DEFAULT '' COMMENT '密码',
  `status` int DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `login_ip` varchar(128) DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_delete` int DEFAULT '0' COMMENT '是否删除',
  `version` int DEFAULT '0' COMMENT '乐观锁',
  PRIMARY KEY (`user_id`) USING BTREE,
  KEY `idx_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户信息表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 'admin', 'Eu', 'eu@163.com', '15666666666', 1, '', '0192023a7bbd73250516f069df18b500', 0, '127.0.0.1', '2022-03-24 17:06:20', 1, '2022-03-24 17:09:52', 1, '2022-03-24 17:09:54', 0, 0);
INSERT INTO `sys_user` VALUES (2, 'eu', 'Eu', 'eu@qq.com', '15666666666', 1, '', '0192023a7bbd73250516f069df18b500', 0, '127.0.0.1', '2022-03-24 17:06:20', 1, '2022-03-24 17:09:58', 1, '2022-03-24 17:09:57', 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户和角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
