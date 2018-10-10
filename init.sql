/*
Navicat MySQL Data Transfer

Source Server         : zhangpk
Source Server Version : 50143
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50099
File Encoding         : 65001

Date: 2018-10-10 10:36:21
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `t_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
`id`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' ,
`text`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`url`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`pid`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`perms`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`type`  int(1) NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
BEGIN;
INSERT INTO t_menu VALUES ('1', '系统管理', null, '0', null, '0'), ('10', '修改角色', null, '3', 'role:update', '1'), ('11', '删除角色', null, '3', 'role:delete', '1'), ('2', '用户管理', '../user/toUserList', '1', 'user:list', '0'), ('3', '角色管理', null, '1', 'role:list', '0'), ('4', '生产部管理', null, '0', null, '0'), ('5', '生产部营销状况', null, '4', 'test:list', '0'), ('6', '新增用户', null, '2', 'user:add', '1'), ('7', '修改用户', null, '2', 'user:update', '1'), ('8', '删除用户', null, '2', 'user:delete', '1'), ('9', '新增角色', null, '3', 'role:add', '1');
COMMIT;

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
`id`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' ,
`name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`createTime`  datetime NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of t_role
-- ----------------------------
BEGIN;
INSERT INTO t_role VALUES ('1', '管理员', '2018-10-09 15:58:38'), ('2', '用户管理员', null), ('3', '角色管理员', null);
COMMIT;

-- ----------------------------
-- Table structure for `t_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu` (
`id`  int(15) NOT NULL AUTO_INCREMENT ,
`roleId`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`menuId`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
/*!50003 AUTO_INCREMENT=17 */

;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
BEGIN;
INSERT INTO t_role_menu VALUES ('1', '1', '1'), ('2', '1', '2'), ('3', '1', '3'), ('4', '1', '4'), ('5', '1', '5'), ('6', '1', '6'), ('7', '1', '7'), ('8', '1', '8'), ('9', '1', '9'), ('10', '1', '10'), ('11', '1', '11'), ('12', '2', '1'), ('13', '2', '2'), ('14', '2', '6'), ('15', '2', '7');
COMMIT;

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
`id`  varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' ,
`name`  varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`loginNumber`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`password`  varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`roleId`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO t_user VALUES ('1', '张三', 'admin', 'admin', '1'), ('2', '李四', 'admin2', 'admin', '2'), ('3', '王五', 'admin3', 'admin', '3');
COMMIT;

-- ----------------------------
-- Table structure for `t_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
`id`  int(15) NOT NULL AUTO_INCREMENT ,
`userId`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`roleId`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
/*!50003 AUTO_INCREMENT=4 */

;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
BEGIN;
INSERT INTO t_user_role VALUES ('1', '1', '1'), ('2', '2', '2'), ('3', '3', '3');
COMMIT;

-- ----------------------------
-- Auto increment value for `t_role_menu`
-- ----------------------------
ALTER TABLE `t_role_menu` AUTO_INCREMENT=17;

-- ----------------------------
-- Auto increment value for `t_user_role`
-- ----------------------------
ALTER TABLE `t_user_role` AUTO_INCREMENT=4;
