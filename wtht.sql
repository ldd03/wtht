/*
 Navicat Premium Dump SQL

 Source Server         : university
 Source Server Type    : MySQL
 Source Server Version : 80037 (8.0.37)
 Source Host           : localhost:3306
 Source Schema         : mybatis

 Target Server Type    : MySQL
 Target Server Version : 80037 (8.0.37)
 File Encoding         : 65001

 Date: 23/06/2025 10:04:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_avaliabletime
-- ----------------------------
DROP TABLE IF EXISTS `tb_avaliabletime`;
CREATE TABLE `tb_avaliabletime`  (
  `time_id` int NOT NULL AUTO_INCREMENT,
  `time_range` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`time_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_avaliabletime
-- ----------------------------
INSERT INTO `tb_avaliabletime` VALUES (1, '08:00-09:00', '可用');
INSERT INTO `tb_avaliabletime` VALUES (2, '09:00-10:00', '可用');
INSERT INTO `tb_avaliabletime` VALUES (3, '10:00-11:00', '可用');
INSERT INTO `tb_avaliabletime` VALUES (4, '11:00-12:00', '可用');
INSERT INTO `tb_avaliabletime` VALUES (5, '12:00-13:00', '不可用');
INSERT INTO `tb_avaliabletime` VALUES (6, '13:00-14:00', '不可用');
INSERT INTO `tb_avaliabletime` VALUES (7, '14:00-15:00', '可用');
INSERT INTO `tb_avaliabletime` VALUES (8, '15:00-16:00', '可用');
INSERT INTO `tb_avaliabletime` VALUES (9, '16:00-17:00', '可用');
INSERT INTO `tb_avaliabletime` VALUES (10, '17:00-18:00', '可用');
INSERT INTO `tb_avaliabletime` VALUES (11, '18:00-19:00', '不可用');
INSERT INTO `tb_avaliabletime` VALUES (12, '19:00-20:00', '不可用');

-- ----------------------------
-- Table structure for tb_history
-- ----------------------------
DROP TABLE IF EXISTS `tb_history`;
CREATE TABLE `tb_history`  (
  `history_id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `place_id` int NULL DEFAULT NULL,
  `time_id` int NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`history_id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  INDEX `place_id`(`place_id` ASC) USING BTREE,
  INDEX `time_id`(`time_id` ASC) USING BTREE,
  CONSTRAINT `tb_history_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `tb_history_ibfk_2` FOREIGN KEY (`place_id`) REFERENCES `tb_place` (`place_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `tb_history_ibfk_3` FOREIGN KEY (`time_id`) REFERENCES `tb_avaliabletime` (`time_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_history
-- ----------------------------
INSERT INTO `tb_history` VALUES (1, '202216132', 1, 1, '2025-06-16 11:27:47', '已预约', '需要提前10分钟到场热身');
INSERT INTO `tb_history` VALUES (2, '202218401', 2, 2, '2025-06-16 11:27:47', '已预约', '');
INSERT INTO `tb_history` VALUES (3, '202218402', 8, 2, '2025-06-16 11:27:47', '已预约', '');
INSERT INTO `tb_history` VALUES (4, '202218403', 10, 2, '2025-06-16 11:27:47', '已预约', '');
INSERT INTO `tb_history` VALUES (5, '202211919', 11, 2, '2025-06-16 11:27:47', '已预约', '');

-- ----------------------------
-- Table structure for tb_notice
-- ----------------------------
DROP TABLE IF EXISTS `tb_notice`;
CREATE TABLE `tb_notice`  (
  `notice_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `publish_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `out_time` datetime NULL DEFAULT NULL,
  `notice_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_notice
-- ----------------------------
INSERT INTO `tb_notice` VALUES (1, '关于系统维护的通知', '系统将于2025年1月11日进行维护，期间将暂停服务。', '2025-06-16 11:27:07', '2025-01-11 00:00:00', '已结束');
INSERT INTO `tb_notice` VALUES (2, '图书馆开放时间调整', '因春节假期，图书馆开放时间调整为每天10:00-16:00。', '2025-06-16 11:27:07', '2025-02-10 00:00:00', '正常');
INSERT INTO `tb_notice` VALUES (3, '新生入学指南', '欢迎新生入学，详情请查看新生入学指南。', '2025-06-16 11:27:07', '2025-03-01 00:00:00', '正常');
INSERT INTO `tb_notice` VALUES (4, '关于期末考试安排', '期末考试将于2025年6月15日开始，请各位同学做好准备。', '2025-06-16 11:27:07', '2025-06-15 00:00:00', '已结束');
INSERT INTO `tb_notice` VALUES (5, '暑期实践项目报名', '暑期实践项目开始报名，详情请咨询教务处。', '2025-06-16 11:27:07', '2025-07-01 00:00:00', '正常');
INSERT INTO `tb_notice` VALUES (6, '关于校园招聘会', '校园招聘会将于2025年9月20日举行，欢迎各位同学参加。', '2025-06-16 11:27:07', '2025-09-20 00:00:00', '正常');
INSERT INTO `tb_notice` VALUES (7, '国庆节放假通知', '国庆节放假时间为2025年10月1日至2025年10月7日。', '2025-06-16 11:27:07', '2025-10-01 00:00:00', '正常');
INSERT INTO `tb_notice` VALUES (8, '关于冬季运动会', '冬季运动会将于2025年11月15日举行，请各位同学积极参加。', '2025-06-16 11:27:07', '2025-11-15 00:00:00', '正常');
INSERT INTO `tb_notice` VALUES (9, '关于寒假安排', '寒假时间为2025年1月20日至2025年2月19日，祝大家假期愉快。', '2025-06-16 11:27:07', '2025-01-20 00:00:00', '正常');

-- ----------------------------
-- Table structure for tb_place
-- ----------------------------
DROP TABLE IF EXISTS `tb_place`;
CREATE TABLE `tb_place`  (
  `place_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `site` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `facility` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `place_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`place_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_place
-- ----------------------------
INSERT INTO `tb_place` VALUES (1, '室内乒乓球场', '文体会堂二楼01号桌', '乒乓球台', '适合比赛和训练', '可用');
INSERT INTO `tb_place` VALUES (2, '室内乒乓球场', '文体会堂二楼02号桌', '乒乓球台', '适合比赛和训练', '可用');
INSERT INTO `tb_place` VALUES (3, '室内乒乓球场', '文体会堂二楼03号桌', '乒乓球台', '适合比赛和训练', '可用');
INSERT INTO `tb_place` VALUES (4, '室内乒乓球场', '文体会堂二楼04号桌', '乒乓球台', '适合比赛和训练', '可用');
INSERT INTO `tb_place` VALUES (5, '室内乒乓球场', '文体会堂二楼05号桌', '乒乓球台', '适合比赛和训练', '可用');
INSERT INTO `tb_place` VALUES (6, '室内乒乓球场', '文体会堂二楼06号桌', '乒乓球台', '适合比赛和训练', '可用');
INSERT INTO `tb_place` VALUES (7, '室内乒乓球场', '文体会堂二楼07号桌', '乒乓球台', '适合比赛和训练', '可用');
INSERT INTO `tb_place` VALUES (8, '室内羽毛球场', '文体会堂二楼01号场', '羽毛球场地', '适合比赛和训练', '可用');
INSERT INTO `tb_place` VALUES (9, '室内羽毛球场', '文体会堂二楼02号场', '羽毛球场地', '适合比赛和训练', '可用');
INSERT INTO `tb_place` VALUES (10, '室内羽毛球场', '文体会堂二楼03号场', '羽毛球场地', '适合比赛和训练', '可用');
INSERT INTO `tb_place` VALUES (11, '室内羽毛球场', '文体会堂二楼04号场', '羽毛球场地', '适合比赛和训练', '可用');
INSERT INTO `tb_place` VALUES (12, '室内羽毛球场', '文体会堂二楼05号场', '羽毛球场地', '适合比赛和训练', '可用');
INSERT INTO `tb_place` VALUES (13, '室内羽毛球场', '文体会堂二楼06号场', '羽毛球场地', '适合比赛和训练', '可用');
INSERT INTO `tb_place` VALUES (14, '室内羽毛球场', '文体会堂二楼07号场', '羽毛球场地', '适合比赛和训练', '可用');
INSERT INTO `tb_place` VALUES (15, '室内羽毛球场', '文体会堂二楼08号场', '羽毛球场地', '适合比赛和训练', '可用');
INSERT INTO `tb_place` VALUES (16, '室外篮球场', '二区篮球场01号场', '篮球场地', '适合比赛和训练', '可用');
INSERT INTO `tb_place` VALUES (17, '室外篮球场', '二区篮球场02号场', '篮球场地', '适合比赛和训练', '可用');
INSERT INTO `tb_place` VALUES (18, '室外篮球场', '二区篮球场03号场', '篮球场地', '适合比赛和训练', '可用');
INSERT INTO `tb_place` VALUES (19, '室外篮球场', '二区篮球场04号场', '篮球场地', '适合比赛和训练', '可用');
INSERT INTO `tb_place` VALUES (20, '室外篮球场', '二区篮球场05号场', '篮球场地', '适合比赛和训练', '可用');
INSERT INTO `tb_place` VALUES (21, '室外篮球场', '二区篮球场06号场', '篮球场地', '适合比赛和训练', '可用');
INSERT INTO `tb_place` VALUES (22, '室外篮球场', '二区篮球场07号场', '篮球场地', '适合比赛和训练', '可用');

-- ----------------------------
-- Table structure for tb_studentinfo
-- ----------------------------
DROP TABLE IF EXISTS `tb_studentinfo`;
CREATE TABLE `tb_studentinfo`  (
  `student_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `department` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `grade` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `class` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`student_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_studentinfo
-- ----------------------------
INSERT INTO `tb_studentinfo` VALUES ('202211919', '侯自然', '软件工程', '2022', '184班', '2535@example.com', '12345678902');
INSERT INTO `tb_studentinfo` VALUES ('202213216', '李豆豆', '软件工程', '2022', '184班', '3191344334@qq.com', '16696231706');
INSERT INTO `tb_studentinfo` VALUES ('202217912', 'Alice', '计算机科学与技术', '2022', '179班', 'alice@example.com', '12345678901');
INSERT INTO `tb_studentinfo` VALUES ('202218422', 'Bob', '软件工程', '2022', '184班', 'bob@example.com', '12345678902');

-- ----------------------------
-- Table structure for tb_teacherinfo
-- ----------------------------
DROP TABLE IF EXISTS `tb_teacherinfo`;
CREATE TABLE `tb_teacherinfo`  (
  `teacher_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `department` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `position` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`teacher_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_teacherinfo
-- ----------------------------
INSERT INTO `tb_teacherinfo` VALUES ('1001', 'Dr. Smith', '计算机科学与技术', '教授', 'smith@example.com', '12345678903');
INSERT INTO `tb_teacherinfo` VALUES ('1002', 'Dr. Johnson', '软件工程', '副教授', 'johnson@example.com', '12345678904');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('admin', 'admin', 'admin', '管理员', '正常');
INSERT INTO `tb_user` VALUES ('1001', 'Dr. Smith', '123456', '教师', '正常');
INSERT INTO `tb_user` VALUES ('202211919', '侯自然', '123456', '学生', '正常');
INSERT INTO `tb_user` VALUES ('202216132', '梁毅恒', '654321', '教师', '正常');
INSERT INTO `tb_user` VALUES ('202218401', '王鑫鹏', '123456', '教师', '正常');
INSERT INTO `tb_user` VALUES ('202218402', '王伟杰', '123456', '教师', '正常');
INSERT INTO `tb_user` VALUES ('202218403', '李瑞明', '123456', '教师', '正常');

SET FOREIGN_KEY_CHECKS = 1;
