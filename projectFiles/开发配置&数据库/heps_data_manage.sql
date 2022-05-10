/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : heps_data_manage

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 09/04/2022 21:51:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(5) NOT NULL COMMENT '类型     0：目录   1：菜单   2：按钮',
  `icon` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `orderNum` int(11) NULL DEFAULT NULL COMMENT '排序',
  `created` datetime(0) NOT NULL,
  `updated` datetime(0) NULL DEFAULT NULL,
  `statu` int(5) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '系统管理', '', 'sys:manage', '', 0, 'el-icon-s-operation', 1, '2021-01-15 18:58:18', '2021-01-15 18:58:20', 1);
INSERT INTO `sys_menu` VALUES (2, 1, '用户管理', '/sys/users', 'sys:user:list', 'sys/User', 1, 'el-icon-s-custom', 1, '2021-01-15 19:03:45', '2021-01-15 19:03:48', 1);
INSERT INTO `sys_menu` VALUES (3, 1, '角色管理', '/sys/roles', 'sys:role:list', 'sys/Role', 1, 'el-icon-rank', 2, '2021-01-15 19:03:45', '2021-01-15 19:03:48', 1);
INSERT INTO `sys_menu` VALUES (4, 1, '菜单管理', '/sys/menus', 'sys:menu:list', 'sys/Menu', 1, 'el-icon-menu', 3, '2021-01-15 19:03:45', '2021-01-15 19:03:48', 1);
INSERT INTO `sys_menu` VALUES (5, 0, '系统工具', '', 'sys:tools', NULL, 0, 'el-icon-s-tools', 2, '2021-01-15 19:06:11', NULL, 1);
INSERT INTO `sys_menu` VALUES (6, 5, '数字字典', '/sys/dicts', 'sys:dict:list', 'sys/Dict', 1, 'el-icon-s-order', 1, '2021-01-15 19:07:18', '2021-01-18 16:32:13', 1);
INSERT INTO `sys_menu` VALUES (7, 3, '添加角色', '', 'sys:role:save', '', 2, '', 1, '2021-01-15 23:02:25', '2021-01-17 21:53:14', 0);
INSERT INTO `sys_menu` VALUES (9, 2, '添加用户', NULL, 'sys:user:save', NULL, 2, NULL, 1, '2021-01-17 21:48:32', NULL, 1);
INSERT INTO `sys_menu` VALUES (10, 2, '修改用户', NULL, 'sys:user:update', NULL, 2, NULL, 2, '2021-01-17 21:49:03', '2021-01-17 21:53:04', 1);
INSERT INTO `sys_menu` VALUES (11, 2, '删除用户', NULL, 'sys:user:delete', NULL, 2, NULL, 3, '2021-01-17 21:49:21', NULL, 1);
INSERT INTO `sys_menu` VALUES (12, 2, '分配角色', NULL, 'sys:user:role', NULL, 2, NULL, 4, '2021-01-17 21:49:58', NULL, 1);
INSERT INTO `sys_menu` VALUES (13, 2, '重置密码', NULL, 'sys:user:repass', NULL, 2, NULL, 5, '2021-01-17 21:50:36', NULL, 1);
INSERT INTO `sys_menu` VALUES (14, 3, '修改角色', NULL, 'sys:role:update', NULL, 2, NULL, 2, '2021-01-17 21:51:14', NULL, 1);
INSERT INTO `sys_menu` VALUES (15, 3, '删除角色', NULL, 'sys:role:delete', NULL, 2, NULL, 3, '2021-01-17 21:51:39', NULL, 1);
INSERT INTO `sys_menu` VALUES (16, 3, '分配权限', NULL, 'sys:role:perm', NULL, 2, NULL, 5, '2021-01-17 21:52:02', NULL, 1);
INSERT INTO `sys_menu` VALUES (17, 4, '添加菜单', NULL, 'sys:menu:save', NULL, 2, NULL, 1, '2021-01-17 21:53:53', '2021-01-17 21:55:28', 1);
INSERT INTO `sys_menu` VALUES (18, 4, '修改菜单', NULL, 'sys:menu:update', NULL, 2, NULL, 2, '2021-01-17 21:56:12', NULL, 1);
INSERT INTO `sys_menu` VALUES (19, 4, '删除菜单', NULL, 'sys:menu:delete', NULL, 2, NULL, 3, '2021-01-17 21:56:36', NULL, 1);
INSERT INTO `sys_menu` VALUES (20, 0, '模型管理', NULL, 'sys:model', NULL, 0, 'el-icon-menu', 3, '2022-03-23 18:41:11', '2022-03-23 18:41:14', 1);
INSERT INTO `sys_menu` VALUES (21, 0, '接口管理', NULL, 'sys:api', NULL, 0, 'el-icon-menu', 4, '2022-03-23 18:43:58', '2022-03-23 18:44:02', 1);
INSERT INTO `sys_menu` VALUES (22, 20, '字段库', '/model/fields', 'sys:model:field', 'model/Field', 1, 'el-icon-menu', 1, '2022-03-23 18:50:30', '2022-03-23 19:02:35', 1);
INSERT INTO `sys_menu` VALUES (24, 20, '字段树', '/model/fieldtrees', 'sys:model:fieldtree', 'model/FieldTree', 1, 'el-icon-tree', 2, '2022-03-24 15:21:32', '2022-03-24 15:22:26', 1);
INSERT INTO `sys_menu` VALUES (25, 20, '模型库', '/model/model', 'sys:model:model', 'model/Model', 1, 'el-icon-menu', 3, '2022-03-24 15:26:24', '2022-03-24 15:26:56', 1);
INSERT INTO `sys_menu` VALUES (26, 22, '查看字段', NULL, 'sys:field:view', NULL, 2, '', 1, '2022-04-01 13:27:55', NULL, 1);
INSERT INTO `sys_menu` VALUES (27, 22, '添加字段', NULL, 'sys:field:save', NULL, 2, NULL, 2, '2022-04-01 13:29:15', NULL, 1);
INSERT INTO `sys_menu` VALUES (28, 22, '修改字段', NULL, 'sys:field:update', NULL, 2, NULL, 3, '2022-04-01 13:29:44', NULL, 1);
INSERT INTO `sys_menu` VALUES (29, 22, '删除字段', NULL, 'sys:field:delete', NULL, 2, NULL, 4, '2022-04-01 13:30:21', NULL, 1);
INSERT INTO `sys_menu` VALUES (30, 24, '查看字段树', NULL, 'sys:fieldtree:view', NULL, 2, NULL, 1, '2022-04-01 13:31:40', NULL, 1);
INSERT INTO `sys_menu` VALUES (31, 24, '添加字段树', NULL, 'sys:fieldtree:save', NULL, 2, NULL, 2, '2022-04-01 13:32:07', NULL, 1);
INSERT INTO `sys_menu` VALUES (32, 24, '修改字段树', NULL, 'sys:fieldtree:update', NULL, 2, NULL, 3, '2022-04-01 13:32:29', NULL, 1);
INSERT INTO `sys_menu` VALUES (33, 24, '删除字段树', NULL, 'sys:fieldtree:delete', NULL, 2, NULL, 4, '2022-04-01 13:32:42', NULL, 1);
INSERT INTO `sys_menu` VALUES (34, 25, '查看模型', NULL, 'sys:model:view', NULL, 2, NULL, 1, '2022-04-01 13:33:19', NULL, 1);
INSERT INTO `sys_menu` VALUES (35, 25, '添加模型', NULL, 'sys:model:save', NULL, 2, NULL, 2, '2022-04-01 13:33:36', NULL, 1);
INSERT INTO `sys_menu` VALUES (36, 25, '修改模型', NULL, 'sys:model:update', NULL, 2, NULL, 3, '2022-04-01 13:33:50', NULL, 1);
INSERT INTO `sys_menu` VALUES (37, 25, '删除模型', NULL, 'sys:model:delete', NULL, 2, NULL, 4, '2022-04-01 13:34:01', NULL, 1);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `remark` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `created` datetime(0) NULL DEFAULT NULL,
  `updated` datetime(0) NULL DEFAULT NULL,
  `statu` int(5) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (3, '普通用户', 'normal', '只有基本查看功能', '2021-01-04 10:09:14', '2021-01-30 08:19:52', 1);
INSERT INTO `sys_role` VALUES (6, '超级管理员', 'admin', '系统默认最高权限，不可以编辑和任意修改', '2021-01-16 13:29:03', '2021-01-17 15:50:45', 1);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 213 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (177, 6, 1);
INSERT INTO `sys_role_menu` VALUES (178, 6, 2);
INSERT INTO `sys_role_menu` VALUES (179, 6, 9);
INSERT INTO `sys_role_menu` VALUES (180, 6, 10);
INSERT INTO `sys_role_menu` VALUES (181, 6, 11);
INSERT INTO `sys_role_menu` VALUES (182, 6, 12);
INSERT INTO `sys_role_menu` VALUES (183, 6, 13);
INSERT INTO `sys_role_menu` VALUES (184, 6, 3);
INSERT INTO `sys_role_menu` VALUES (185, 6, 7);
INSERT INTO `sys_role_menu` VALUES (186, 6, 14);
INSERT INTO `sys_role_menu` VALUES (187, 6, 15);
INSERT INTO `sys_role_menu` VALUES (188, 6, 16);
INSERT INTO `sys_role_menu` VALUES (189, 6, 4);
INSERT INTO `sys_role_menu` VALUES (190, 6, 17);
INSERT INTO `sys_role_menu` VALUES (191, 6, 18);
INSERT INTO `sys_role_menu` VALUES (192, 6, 19);
INSERT INTO `sys_role_menu` VALUES (193, 6, 5);
INSERT INTO `sys_role_menu` VALUES (194, 6, 6);
INSERT INTO `sys_role_menu` VALUES (195, 6, 20);
INSERT INTO `sys_role_menu` VALUES (196, 6, 22);
INSERT INTO `sys_role_menu` VALUES (197, 6, 26);
INSERT INTO `sys_role_menu` VALUES (198, 6, 27);
INSERT INTO `sys_role_menu` VALUES (199, 6, 28);
INSERT INTO `sys_role_menu` VALUES (200, 6, 29);
INSERT INTO `sys_role_menu` VALUES (201, 6, 24);
INSERT INTO `sys_role_menu` VALUES (202, 6, 30);
INSERT INTO `sys_role_menu` VALUES (203, 6, 31);
INSERT INTO `sys_role_menu` VALUES (204, 6, 32);
INSERT INTO `sys_role_menu` VALUES (205, 6, 33);
INSERT INTO `sys_role_menu` VALUES (206, 6, 25);
INSERT INTO `sys_role_menu` VALUES (207, 6, 34);
INSERT INTO `sys_role_menu` VALUES (208, 6, 35);
INSERT INTO `sys_role_menu` VALUES (209, 6, 36);
INSERT INTO `sys_role_menu` VALUES (210, 6, 37);
INSERT INTO `sys_role_menu` VALUES (211, 6, 21);
INSERT INTO `sys_role_menu` VALUES (212, 3, 21);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `city` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created` datetime(0) NULL DEFAULT NULL,
  `updated` datetime(0) NULL DEFAULT NULL,
  `last_login` datetime(0) NULL DEFAULT NULL,
  `statu` int(5) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_USERNAME`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$R7zegeWzOXPw871CmNuJ6upC0v8D373GuLuTw8jn6NET4BkPRZfgK', 'https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/5a9f48118166308daba8b6da7e466aab.jpg', '123@qq.com', '广州', '2021-01-12 22:13:53', '2021-01-16 16:57:32', '2020-12-30 08:38:37', 1);
INSERT INTO `sys_user` VALUES (2, 'test', '$2a$10$R7zegeWzOXPw871CmNuJ6upC0v8D373GuLuTw8jn6NET4BkPRZfgK', 'https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/5a9f48118166308daba8b6da7e466aab.jpg', 'test@qq.com', NULL, '2021-01-30 08:20:22', '2021-01-30 08:55:57', NULL, 1);
INSERT INTO `sys_user` VALUES (3, 'jim', '$2a$10$R7zegeWzOXPw871CmNuJ6upC0v8D373GuLuTw8jn6NET4BkPRZfgK', 'https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/5a9f48118166308daba8b6da7e466aab.jpg', 'lijiming@ihep.ac.cn', NULL, '2022-03-30 18:49:47', '2022-03-30 18:49:56', NULL, 1);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (4, 1, 6);
INSERT INTO `sys_user_role` VALUES (7, 1, 3);
INSERT INTO `sys_user_role` VALUES (13, 2, 3);
INSERT INTO `sys_user_role` VALUES (15, 3, 3);

-- ----------------------------
-- Table structure for tb_api
-- ----------------------------
DROP TABLE IF EXISTS `tb_api`;
CREATE TABLE `tb_api`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '接口名称标识（不能重复）',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接口描述',
  `http_type` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接口请求类型 post get (现在只支持post)',
  `op_type` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接口数据操作类型 put post delete get',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接口地址',
  `model` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作的集合名称，针对增删改操作有意义',
  `model_names` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接口涉及到的模型名称 逗号分割',
  `data` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接口数据参数格式',
  `filter` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接口操作条件',
  `page` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分页条件',
  `sort` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '排序条件',
  `status` int(1) NULL DEFAULT 1 COMMENT '接口状态',
  `return_data` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '返回字段信息  逗号隔开',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建接口用户ID',
  `access_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接口可访问用户ID列表  用逗号分割',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_api
-- ----------------------------
INSERT INTO `tb_api` VALUES (10, 'addUser', '这个接口是用来添加新用户', 'post', 'put', 'http://127.0.0.1:8081/api-server/user/put/addUser', 'user', 'user,dept', '[{\"param\":\"user\",\"id\":10000,\"type\":\"model\"}]', '#{user.dept_id} in dept.id', NULL, NULL, 1, NULL, 100000, '[100000,100001,200005]');

-- ----------------------------
-- Table structure for tb_api_acl
-- ----------------------------
DROP TABLE IF EXISTS `tb_api_acl`;
CREATE TABLE `tb_api_acl`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `api_id` bigint(20) NULL DEFAULT NULL COMMENT '接口ID',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_api_acl
-- ----------------------------
INSERT INTO `tb_api_acl` VALUES (1, 1, 1);
INSERT INTO `tb_api_acl` VALUES (2, 1, 2);

-- ----------------------------
-- Table structure for tb_condition
-- ----------------------------
DROP TABLE IF EXISTS `tb_condition`;
CREATE TABLE `tb_condition`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父节点ID',
  `type` int(1) NULL DEFAULT NULL COMMENT '节点类型 0root 1or 2and 3value',
  `value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '运算条件字符串',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2738 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_condition
-- ----------------------------
INSERT INTO `tb_condition` VALUES (2734, -1, 0, '');
INSERT INTO `tb_condition` VALUES (2735, 2734, 2, '');
INSERT INTO `tb_condition` VALUES (2736, 2735, 3, 'user.dept_id = dept.id');
INSERT INTO `tb_condition` VALUES (2737, 2735, 3, 'user.age > 20');

-- ----------------------------
-- Table structure for tb_data_source
-- ----------------------------
DROP TABLE IF EXISTS `tb_data_source`;
CREATE TABLE `tb_data_source`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `db_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据源类型',
  `host` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据源地址',
  `port` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '端口',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_data_source
-- ----------------------------
INSERT INTO `tb_data_source` VALUES (1, 'mysql', '127.0.0.1', '3306', 'root', '164411', 1, 'ceshi');

-- ----------------------------
-- Table structure for tb_field_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_field_info`;
CREATE TABLE `tb_field_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `field_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字段名称',
  `field_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据类型',
  `length` int(11) NULL DEFAULT NULL COMMENT '字段长度',
  `is_require` int(11) NULL DEFAULT 0 COMMENT '是否必填 1必填 0非必填',
  `is_unique` int(11) NULL DEFAULT 0 COMMENT '是否唯一 1唯一 0不唯一',
  `default_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '本字段默认值，需要按照field_type转换数据类型',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_field_info
-- ----------------------------
INSERT INTO `tb_field_info` VALUES (37, 'name', 'string', 11, 1, 0, '', '姓名');
INSERT INTO `tb_field_info` VALUES (38, 'province', 'string', 11, 0, 0, '', '省份');
INSERT INTO `tb_field_info` VALUES (39, 'city', 'string', 11, 0, 0, '北京', '城市');
INSERT INTO `tb_field_info` VALUES (40, 'phone', 'string', 11, 1, 1, '', '联系电话');

-- ----------------------------
-- Table structure for tb_field_node
-- ----------------------------
DROP TABLE IF EXISTS `tb_field_node`;
CREATE TABLE `tb_field_node`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `node_type` int(1) NULL DEFAULT NULL COMMENT '节点类型 1:根节点；2:中间节点（复合属性）；3:叶子节点；',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父节点id',
  `field_info_id` bigint(20) NULL DEFAULT NULL COMMENT '字段定义对象id',
  `default_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '允许对定义的字段进行重命名',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_tb_field_node_relation_1`(`field_info_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 71 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_field_node
-- ----------------------------
INSERT INTO `tb_field_node` VALUES (65, 1, -1, -1, 'root');
INSERT INTO `tb_field_node` VALUES (66, 3, 65, 37, '');
INSERT INTO `tb_field_node` VALUES (67, 2, 65, -1, 'address');
INSERT INTO `tb_field_node` VALUES (68, 3, 67, 38, '');
INSERT INTO `tb_field_node` VALUES (69, 3, 67, 39, '');
INSERT INTO `tb_field_node` VALUES (70, 3, 65, 40, '');

-- ----------------------------
-- Table structure for tb_model
-- ----------------------------
DROP TABLE IF EXISTS `tb_model`;
CREATE TABLE `tb_model`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `model_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模型名称',
  `index` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '索引信息',
  `parent_model_id` bigint(20) NULL DEFAULT NULL COMMENT '用于继承，父模型ID',
  `field_tree_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '字段树根节点ID',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `is_delete` int(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_tb_model_relation_1`(`field_tree_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_model
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
