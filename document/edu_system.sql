/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : edu_system

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2017-06-19 16:40:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_message_code
-- ----------------------------
DROP TABLE IF EXISTS `tb_message_code`;
CREATE TABLE `tb_message_code` (
  `id` int(19) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `type` int(1) DEFAULT NULL COMMENT '类型（1:注册;2:忘记密码）',
  `code` varchar(10) DEFAULT NULL COMMENT '验证码',
  `begin_time` timestamp NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '过期时间',
  `status` int(1) DEFAULT NULL COMMENT '状态(0:失效;1:生效)',
  PRIMARY KEY (`id`),
  KEY `ind_tb_validate_mobile` (`mobile`) USING BTREE,
  KEY `ind_tb_validate_type` (`type`) USING BTREE,
  KEY `ind_tb_validate_status` (`status`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='验证码存储表';

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(19) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) DEFAULT NULL COMMENT '用户名称',
  `user_mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `user_pwd` varchar(100) DEFAULT NULL COMMENT '密码（加密）',
  `reg_time` timestamp NULL DEFAULT NULL COMMENT '注册时间',
  `error_num` int(1) DEFAULT NULL COMMENT '剩余可错误次数',
  `frozen_time` timestamp NULL DEFAULT NULL COMMENT '冻结到期时间',
  `last_login_time` timestamp NULL DEFAULT NULL COMMENT '最近登录时间',
  `last_error_time` timestamp NULL DEFAULT NULL COMMENT '上次错误时间',
  `status` int(1) DEFAULT NULL COMMENT '状态（0:失效;1:生效）',
  `open_id` varchar(100) DEFAULT NULL COMMENT '微信关联id',
  `creator_id` int(19) DEFAULT NULL,
  `created_time` timestamp NULL DEFAULT NULL,
  `last_operator_id` int(19) DEFAULT NULL,
  `last_operated_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_tb_user_mobile` (`user_mobile`) USING BTREE,
  UNIQUE KEY `uni_tb_user_open_id` (`open_id`) USING BTREE,
  KEY `ind_tb_user_status` (`status`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户基础表';
