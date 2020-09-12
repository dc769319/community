/*
 Navicat Premium Data Transfer

 Source Server         : localMysql
 Source Server Type    : MySQL
 Source Server Version : 50647
 Source Host           : localhost:3306
 Source Schema         : charles_community

 Target Server Type    : MySQL
 Target Server Version : 50647
 File Encoding         : 65001

 Date: 05/04/2020 21:29:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `CONTENT` varchar(1024) NOT NULL,
  `PARENT_ID` int(11) NOT NULL,
  `TYPE` tinyint(4) NOT NULL,
  `GMT_CREATE` bigint(20) NOT NULL,
  `GMT_MODIFIED` bigint(20) NOT NULL,
  `OBSERVER` int(11) NOT NULL,
  `COMMENT_COUNT` int(11) DEFAULT '0',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of comment
-- ----------------------------
BEGIN;
INSERT INTO `comment` VALUES (1, 'test', 1, 1, 1583216493, 1583216493, 3, 0);
INSERT INTO `comment` VALUES (2, 'test', 1, 1, 1583216493, 1583216493, 3, 0);
INSERT INTO `comment` VALUES (33, '新评论1', 41, 1, 1583216731390, 1583216731390, 1, NULL);
INSERT INTO `comment` VALUES (65, '二级评论1', 33, 2, 1583216891576, 1583216891576, 1, NULL);
INSERT INTO `comment` VALUES (66, '二级评论1', 33, 2, 1583216899298, 1583216899298, 1, NULL);
INSERT INTO `comment` VALUES (97, '二级评论2', 33, 2, 1583218462268, 1583218462268, 1, NULL);
INSERT INTO `comment` VALUES (98, '二级评论3', 33, 2, 1583218474317, 1583218474317, 1, NULL);
INSERT INTO `comment` VALUES (99, '测试8第一个回复', 40, 1, 1584774353465, 1584774353465, 1, NULL);
INSERT INTO `comment` VALUES (100, '测试8第2个回复', 40, 1, 1584774362547, 1584774362547, 1, 2);
INSERT INTO `comment` VALUES (101, '第二条评论的二级评论', 100, 2, 1584774368645, 1584774368645, 1, NULL);
INSERT INTO `comment` VALUES (102, '第二条评论的二级评论2', 100, 2, 1584775959039, 1584775959039, 1, NULL);
COMMIT;

-- ----------------------------
-- Table structure for notification
-- ----------------------------
DROP TABLE IF EXISTS `notification`;
CREATE TABLE `notification` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `notifier` int(11) unsigned NOT NULL,
  `receiver` int(11) unsigned NOT NULL,
  `outer_id` int(11) unsigned NOT NULL,
  `type` tinyint(2) NOT NULL,
  `gmt_create` bigint(20) unsigned NOT NULL,
  `status` tinyint(1) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of notification
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(50) NOT NULL,
  `DESCRIPTION` text,
  `TAG` varchar(100) DEFAULT NULL,
  `GMT_CREATE` bigint(20) DEFAULT NULL,
  `GMT_MODIFIED` bigint(20) DEFAULT NULL,
  `VIEW_COUNT` int(11) DEFAULT '0',
  `LIKE_COUNT` int(11) DEFAULT '0',
  `COMMENT_COUNT` int(11) DEFAULT '0',
  `CREATOR` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of question
-- ----------------------------
BEGIN;
INSERT INTO `question` VALUES (1, '测试一下', '测试一下', '标签,标签2', 1570881520955, 1570881520955, 0, 0, 0, 1);
INSERT INTO `question` VALUES (2, '测一把', '测一把', '标签3,标签2', 1570881544273, 1570881544273, 0, 0, 0, 1);
INSERT INTO `question` VALUES (33, '测试1', '测试1', '标签3,标签2', 1573822380399, 1573822380399, 0, 0, 0, 1);
INSERT INTO `question` VALUES (34, '测试2', '测试2', '标签3,标签2', 1573822386763, 1573822386763, 0, 0, 0, 1);
INSERT INTO `question` VALUES (35, '测试3', '测试3', '标签3,标签2', 1573822390706, 1573822390706, 0, 0, 0, 1);
INSERT INTO `question` VALUES (36, '测试4', '测试4', '标签3,标签2', 1573822394579, 1573822394579, 0, 0, 0, 1);
INSERT INTO `question` VALUES (37, '测试5', '测试5', '标签3,标签2', 1573822398700, 1573822398700, 0, 0, 0, 1);
INSERT INTO `question` VALUES (38, '测试6', '测试6', '标签3,标签2', 1573822403035, 1573822403035, 0, 0, 0, 1);
INSERT INTO `question` VALUES (39, '测试7', '测试7', '标签3,标签2', 1573822406651, 1573822406651, 0, 0, 0, 1);
INSERT INTO `question` VALUES (40, '测试8', '测试8', '标签3,标签2', 1573822410195, 1573822410195, 18, 0, 2, 1);
INSERT INTO `question` VALUES (41, '测试9', '测试9', '标签3,标签2', 1573822413938, 1573822413938, 28, 0, 3, 2);
INSERT INTO `question` VALUES (42, '利用StringUtils工具类进行String为空的判断', '利用StringUtils工具类进行String为空的判断', 'springboot,java,springmvc', 1584781299696, 1584781299696, NULL, NULL, NULL, 1);
INSERT INTO `question` VALUES (43, '模糊查询LIKE的三种方式', '模糊查询LIKE的三种方式', 'springboot', 1584781317699, 1584781317699, NULL, NULL, NULL, 1);
INSERT INTO `question` VALUES (44, '构建微服务：Spring boot 入门篇  ', '构建微服务：Spring boot 入门篇\r\n\r\n ', 'springboot', 1584781391342, 1584781391342, NULL, NULL, NULL, 1);
INSERT INTO `question` VALUES (45, 'Spring Boot精要', 'Spring Boot精要', 'springboot,springmvc', 1584781431402, 1584781431402, NULL, NULL, NULL, 1);
INSERT INTO `question` VALUES (46, '审核导航数据接口', '审核导航数据接口', 'javascript,npm', 1586007659350, 1586007659350, NULL, NULL, NULL, 1);
INSERT INTO `question` VALUES (47, '测试211', '211', 'javascript,bootstrap', 1586007681596, 1586007681596, NULL, NULL, NULL, 1);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `ACCOUNT_ID` varchar(100) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `TOKEN` char(100) DEFAULT '',
  `GMT_CREATE` bigint(20) DEFAULT NULL,
  `GMT_MODIFIED` bigint(20) DEFAULT NULL,
  `BIO` varchar(100) DEFAULT NULL,
  `AVATAR_URL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, '11712400', 'Charles', '6ef9b471-4364-4710-a48d-103c67e3e3f3', 1570881498953, 1586092926187, NULL, 'https://avatars3.githubusercontent.com/u/11712400?v=4');
INSERT INTO `user` VALUES (2, '11712401', 'Charles', '5rf9b471-4364-4710-a48d-103c67e3e3f3', 1570881498953, 1573822354703, NULL, 'https://avatars3.githubusercontent.com/u/11712400?v=4');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
