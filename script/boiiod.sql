/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50710
 Source Host           : localhost
 Source Database       : boiiod

 Target Server Type    : MySQL
 Target Server Version : 50710
 File Encoding         : utf-8

 Date: 03/03/2017 19:14:02 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_catalog`
-- ----------------------------
DROP TABLE IF EXISTS `t_catalog`;
CREATE TABLE `t_catalog` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOTE_ID` int(11) DEFAULT NULL,
  `PARENT_ID` int(11) DEFAULT NULL,
  `PATH` varchar(200) DEFAULT NULL,
  `NAME` varchar(200) DEFAULT NULL,
  `CREATE_DATETIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='wiki目录信息';

-- ----------------------------
--  Table structure for `t_category`
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PARENT_ID` int(11) DEFAULT '-1' COMMENT '父ID, 根-1',
  `PATH` varchar(200) DEFAULT '-1' COMMENT '第一个-1,   父PATH_父ID',
  `NAME` varchar(32) DEFAULT NULL COMMENT '分类名称',
  `TYPE` int(2) DEFAULT NULL COMMENT '业务标识. 1',
  `ICON_ID` int(11) DEFAULT NULL COMMENT '图标ID, 引用picture表',
  `SEQUENCE` int(4) DEFAULT NULL COMMENT '同一个parentId下排序, 越大越靠前',
  `DESCRIPTION` varchar(200) DEFAULT NULL COMMENT '分类描述',
  `CREATE_DATETIME` datetime DEFAULT NULL,
  `IS_DEL` int(2) DEFAULT '0' COMMENT '是否删除. 0否, 1是, 默认0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分类';

-- ----------------------------
--  Table structure for `t_env_var`
-- ----------------------------
DROP TABLE IF EXISTS `t_env_var`;
CREATE TABLE `t_env_var` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `KEY` varchar(32) DEFAULT NULL COMMENT '唯一',
  `VALUE` varchar(64) DEFAULT NULL,
  `DESCRIPTION` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `t_folder`
-- ----------------------------
DROP TABLE IF EXISTS `t_folder`;
CREATE TABLE `t_folder` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PARENT_ID` int(11) DEFAULT '-1' COMMENT '父ID, 根-1',
  `PATH` varchar(200) DEFAULT '-1' COMMENT '第一个-1,   父PATH_父ID',
  `USER_ID` int(11) DEFAULT NULL COMMENT '用户ID',
  `NAME` varchar(64) DEFAULT NULL COMMENT '名称',
  `SEQUENCE` int(4) DEFAULT NULL COMMENT '排序, 越大越靠前',
  `ICON_ID` int(11) DEFAULT NULL COMMENT '图标, pictureId',
  `CREATE_DATETIME` datetime DEFAULT NULL,
  `IS_DEL` int(2) DEFAULT '0' COMMENT '是否删除. 0否, 1是. 默认0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文件夹';

-- ----------------------------
--  Table structure for `t_note`
-- ----------------------------
DROP TABLE IF EXISTS `t_note`;
CREATE TABLE `t_note` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) DEFAULT NULL,
  `FOLDER_ID` int(11) DEFAULT NULL,
  `CATEGORY_ID` int(11) DEFAULT NULL COMMENT '分类ID',
  `TYPE` int(2) DEFAULT NULL COMMENT '类型. 1普通笔记, 2Markdown, 3wiki',
  `TITLE` varchar(200) DEFAULT NULL COMMENT '标题',
  `SECRET` int(2) DEFAULT '0' COMMENT '私密. 0公开, 1密码访问',
  `PASSWORD` varchar(64) DEFAULT NULL COMMENT '密码, 笔记为私密时有值',
  `DIGEST` varchar(200) DEFAULT NULL COMMENT '摘要',
  `AUTHOR` varchar(255) DEFAULT NULL COMMENT '作者',
  `ORIGIN_URL` varchar(500) DEFAULT NULL COMMENT '文章来源',
  `TOP` int(2) DEFAULT '0' COMMENT '置顶. 0否, 1是',
  `TOP_DATETIME` datetime DEFAULT NULL COMMENT '置顶操作时间',
  `VIEW_NUM` bigint(20) DEFAULT '0' COMMENT '阅读数',
  `STATUS` int(2) DEFAULT '1' COMMENT '生成静态资源状态. 1原生, 2已静态化',
  `UPDATE_DATETIME` datetime DEFAULT NULL,
  `CREATE_DATETIME` datetime DEFAULT NULL,
  `IS_DEL` int(2) DEFAULT '0' COMMENT '是否删除. 0否, 1是, 默认0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='笔记';

-- ----------------------------
--  Table structure for `t_note_detail`
-- ----------------------------
DROP TABLE IF EXISTS `t_note_detail`;
CREATE TABLE `t_note_detail` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) DEFAULT NULL,
  `NOTE_ID` int(11) DEFAULT NULL,
  `CONTENT` varchar(255) DEFAULT NULL,
  `TYPE` int(2) DEFAULT '1' COMMENT '类型. 1普通笔记, 2Markdown, 3wiki',
  `CATALOG_ID` int(11) DEFAULT NULL COMMENT '目录ID, 只当type=3时有值',
  `CREATE_DATETIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='笔记详情';

-- ----------------------------
--  Table structure for `t_picture`
-- ----------------------------
DROP TABLE IF EXISTS `t_picture`;
CREATE TABLE `t_picture` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) DEFAULT NULL COMMENT '用户ID',
  `NAME` varchar(100) DEFAULT NULL,
  `PATH` varchar(100) DEFAULT NULL,
  `WIDTH` int(6) DEFAULT NULL,
  `HEIGHT` int(6) DEFAULT NULL,
  `SIZE` bigint(20) DEFAULT NULL,
  `TYPE` varchar(10) DEFAULT NULL COMMENT '图片类型, 如: jpg, gif',
  `CRATE_DATETIME` datetime DEFAULT NULL,
  `IS_DEL` int(2) DEFAULT '0' COMMENT '是否删除. 0否, 1是. 默认0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='图片库';

-- ----------------------------
--  Table structure for `t_unite`
-- ----------------------------
DROP TABLE IF EXISTS `t_unite`;
CREATE TABLE `t_unite` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) DEFAULT NULL COMMENT '用户ID',
  `TYPE` int(11) DEFAULT NULL COMMENT '2微信, 3QQ, 4微博',
  `OPEN_ID` varchar(100) DEFAULT NULL COMMENT '第三方唯一标识',
  `UNION_ID` varchar(100) DEFAULT NULL COMMENT '微信联合标识, 只当type=2时有值',
  `AVATAR_PATH` varchar(200) DEFAULT NULL COMMENT '第三方头像',
  `NAME` varchar(100) DEFAULT NULL COMMENT '第三方用户名',
  `CREATE_DATETIME` datetime DEFAULT NULL COMMENT '第一次登录时间',
  `IS_DEL` int(2) DEFAULT '0' COMMENT '是否删除. 0否, 1是. 默认0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='联合登录';

-- ----------------------------
--  Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ALIAS` varchar(20) DEFAULT NULL COMMENT '别名, 可空',
  `NAME` varchar(20) DEFAULT NULL COMMENT '用户名, 唯一',
  `PHONE` varchar(20) DEFAULT NULL COMMENT '手机号, 唯一',
  `PASSWORD` varchar(64) DEFAULT NULL COMMENT '密码',
  `AVATAR_ID` int(11) DEFAULT NULL,
  `EMAIL` varchar(64) DEFAULT NULL COMMENT '邮箱, 可空',
  `GENDER` int(2) DEFAULT '0' COMMENT '性别. 0保密, 1男, 2女. 默认0',
  `STATUS` int(2) DEFAULT '1' COMMENT '状态. 0禁用, 1启用, 默认1',
  `LAST_LOGIN_DATETIME` datetime DEFAULT NULL COMMENT '最后登录时间',
  `CREATE_DATETIME` datetime DEFAULT NULL COMMENT '注册时间',
  `REGISTER_ORIGIN` int(2) DEFAULT NULL COMMENT '注册来源. 1手机号, 2微信, 3QQ, 4微博',
  `REGISTER_IP` varchar(32) DEFAULT NULL COMMENT '注册IP',
  `IS_DEL` int(2) DEFAULT '0' COMMENT '是否删除. 0否, 1是. 默认0',
  PRIMARY KEY (`ID`),
  KEY `PHONE` (`PHONE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息';

SET FOREIGN_KEY_CHECKS = 1;
