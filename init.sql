/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50018
Source Host           : localhost:3306
Source Database       : test1

Target Server Type    : MYSQL
Target Server Version : 50018
File Encoding         : 65001

Date: 2018-03-28 15:20:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_acl
-- ----------------------------
DROP TABLE IF EXISTS `sys_acl`;
CREATE TABLE `sys_acl` (
  `id` int(11) NOT NULL auto_increment COMMENT '权限id',
  `code` varchar(20) NOT NULL default '' COMMENT '权限码',
  `name` varchar(20) NOT NULL default '' COMMENT '权限名称',
  `acl_module_id` int(11) NOT NULL default '0' COMMENT '权限所在的权限模块id',
  `url` varchar(100) NOT NULL default '' COMMENT '请求的url, 可以填正则表达式',
  `type` int(11) NOT NULL default '3' COMMENT '类型，1：菜单，2：按钮，3：其他',
  `status` int(11) NOT NULL default '1' COMMENT '状态，1：正常，0：冻结',
  `seq` int(11) NOT NULL default '0' COMMENT '权限在当前模块下的顺序，由小到大',
  `remark` varchar(200) default '' COMMENT '备注',
  `operator` varchar(20) NOT NULL default '' COMMENT '操作者',
  `operate_time` datetime NOT NULL COMMENT '最后一次更新时间',
  `operate_ip` varchar(20) NOT NULL default '' COMMENT '最后一个更新者的ip地址',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_acl
-- ----------------------------
INSERT INTO `sys_acl` VALUES ('1', '20171015095130_26', '进入产品管理界面', '1', '/sys/product/product.page', '1', '1', '1', '', 'Admin', '2017-10-15 09:51:30', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES ('2', '20171015095322_14', '查询产品列表', '1', '/sys/product/page.json', '2', '1', '2', '', 'Admin', '2017-10-15 09:53:22', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES ('3', '20171015095350_69', '产品上架', '1', '/sys/product/online.json', '2', '1', '3', '', 'Admin', '2017-10-15 09:53:51', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES ('4', '20171015095420_7', '产品下架', '1', '/sys/product/offline.json', '2', '1', '4', '', 'Admin', '2017-10-15 10:11:28', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES ('5', '20171015212626_63', '进入订单页', '2', '/sys/order/order.page', '1', '1', '1', '', 'Admin', '2017-10-15 21:26:27', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES ('6', '20171015212657_12', '查询订单列表', '2', '/sys/order/list.json', '2', '1', '2', '', 'Admin', '2017-10-15 21:26:57', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES ('7', '20171015212907_36', '进入权限管理页', '7', '/sys/aclModule/acl.page', '1', '1', '1', '', 'Admin', '2017-10-15 21:29:07', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES ('8', '20171015212938_27', '进入角色管理页', '8', '/sys/role/role.page', '1', '1', '1', '', 'Admin', '2017-10-16 17:49:38', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES ('9', '20171015213009_0', '进入用户管理页', '9', '/sys/dept/dept.page', '1', '1', '1', '', 'Admin', '2017-10-15 21:30:09', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES ('10', '20171016230429_8', '进入权限更新记录页面', '11', '/sys/log/log.page', '1', '1', '1', '', 'Admin', '2017-10-16 23:04:49', '0:0:0:0:0:0:0:1');

-- ----------------------------
-- Table structure for sys_acl_module
-- ----------------------------
DROP TABLE IF EXISTS `sys_acl_module`;
CREATE TABLE `sys_acl_module` (
  `id` int(11) NOT NULL auto_increment COMMENT '权限模块id',
  `name` varchar(20) NOT NULL default '' COMMENT '权限模块名称',
  `parent_id` int(11) NOT NULL default '0' COMMENT '上级权限模块id',
  `level` varchar(200) NOT NULL default '' COMMENT '权限模块层级',
  `seq` int(11) NOT NULL default '0' COMMENT '权限模块在当前层级下的顺序，由小到大',
  `status` int(11) NOT NULL default '1' COMMENT '状态，1：正常，0：冻结',
  `remark` varchar(200) default '' COMMENT '备注',
  `operator` varchar(20) NOT NULL default '' COMMENT '操作者',
  `operate_time` datetime NOT NULL COMMENT '最后一次操作时间',
  `operate_ip` varchar(20) NOT NULL default '' COMMENT '最后一次更新操作者的ip地址',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_acl_module
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` int(11) NOT NULL auto_increment COMMENT '部门id',
  `name` varchar(20) NOT NULL default '' COMMENT '部门名称',
  `parent_id` int(11) NOT NULL default '0' COMMENT '上级部门id',
  `level` varchar(200) NOT NULL default '' COMMENT '部门层级',
  `seq` int(11) NOT NULL default '0' COMMENT '部门在当前层级下的顺序，由小到大',
  `remark` varchar(200) default '' COMMENT '备注',
  `operator` varchar(20) NOT NULL default '' COMMENT '操作者',
  `operate_time` datetime NOT NULL COMMENT '最后一次操作时间',
  `operate_ip` varchar(20) NOT NULL default '' COMMENT '最后一次更新操作者的ip地址',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` int(11) NOT NULL auto_increment,
  `type` int(11) NOT NULL default '0' COMMENT '权限更新的类型，1：部门，2：用户，3：权限模块，4：权限，5：角色，6：角色用户关系，7：角色权限关系',
  `target_id` int(11) NOT NULL COMMENT '基于type后指定的对象id，比如用户、权限、角色等表的主键',
  `old_value` text COMMENT '旧值',
  `new_value` text COMMENT '新值',
  `operator` varchar(20) NOT NULL default '' COMMENT '操作者',
  `operate_time` datetime NOT NULL COMMENT '最后一次更新的时间',
  `operate_ip` varchar(20) NOT NULL default '' COMMENT '最后一次更新者的ip地址',
  `status` int(11) NOT NULL default '0' COMMENT '当前是否复原过，0：没有，1：复原过',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL auto_increment COMMENT '角色id',
  `name` varchar(20) NOT NULL,
  `type` int(11) NOT NULL default '1' COMMENT '角色的类型，1：管理员角色，2：其他',
  `status` int(11) NOT NULL default '1' COMMENT '状态，1：可用，0：冻结',
  `remark` varchar(200) default '' COMMENT '备注',
  `operator` varchar(20) NOT NULL default '' COMMENT '操作者',
  `operate_time` datetime NOT NULL COMMENT '最后一次更新的时间',
  `operate_ip` varchar(20) NOT NULL default '' COMMENT '最后一次更新者的ip地址',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_acl
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_acl`;
CREATE TABLE `sys_role_acl` (
  `id` int(11) NOT NULL auto_increment,
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `acl_id` int(11) NOT NULL COMMENT '权限id',
  `operator` varchar(20) NOT NULL default '' COMMENT '操作者',
  `operate_time` datetime NOT NULL COMMENT '最后一次更新的时间',
  `operate_ip` varchar(200) NOT NULL default '' COMMENT '最后一次更新者的ip',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_acl
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `id` int(11) NOT NULL auto_increment,
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `operator` varchar(20) NOT NULL default '' COMMENT '操作者',
  `operate_time` datetime NOT NULL COMMENT '最后一次更新的时间',
  `operate_ip` varchar(20) NOT NULL default '' COMMENT '最后一次更新者的ip地址',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL auto_increment COMMENT '用户id',
  `username` varchar(20) NOT NULL default '' COMMENT '用户名称',
  `telephone` varchar(13) NOT NULL default '' COMMENT '手机号',
  `mail` varchar(20) NOT NULL default '' COMMENT '邮箱',
  `password` varchar(40) NOT NULL default '' COMMENT '加密后的密码',
  `dept_id` int(11) NOT NULL default '0' COMMENT '用户所在部门的id',
  `status` int(11) NOT NULL default '1' COMMENT '状态，1：正常，0：冻结状态，2：删除',
  `remark` varchar(200) default '' COMMENT '备注',
  `operator` varchar(20) NOT NULL default '' COMMENT '操作者',
  `operate_time` datetime NOT NULL COMMENT '最后一次更新时间',
  `operate_ip` varchar(20) NOT NULL default '' COMMENT '最后一次更新者的ip地址',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
