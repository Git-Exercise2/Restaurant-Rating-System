/*
 Navicat Premium Dump SQL

 Source Server         : localhost_mysql_8.0_3307
 Source Server Type    : MySQL
 Source Server Version : 80038 (8.0.38)
 Source Host           : localhost:3307
 Source Schema         : yelp

 Target Server Type    : MySQL
 Target Server Version : 80038 (8.0.38)
 File Encoding         : 65001

 Date: 02/03/2025 22:21:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for discuss
-- ----------------------------
DROP TABLE IF EXISTS `discuss`;
CREATE TABLE `discuss`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` bigint NOT NULL COMMENT 'USER ID',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'USER NAME',
  `parent_id` bigint NULL DEFAULT NULL COMMENT 'PARENT ID',
  `parent_user_id` bigint NULL DEFAULT NULL COMMENT 'PARENT USER ID',
  `parent_user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'PARENT USER NAME',
  `restaurant_id` bigint NOT NULL COMMENT 'RESTAURANT ID',
  `restaurant_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'RESTAURANT NAME',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT 'DISCUSS CONTENT',
  `create_date` datetime NULL DEFAULT NULL COMMENT 'CREATE DATE',
  `is_delete` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'STATUS',
  `attr1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `attr2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `attr3` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `attr4` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `attr5` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of discuss
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `BLOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `SCHED_NAME`(`SCHED_NAME` ASC, `TRIGGER_NAME` ASC, `TRIGGER_GROUP` ASC) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `CRON_EXPRESSION` varchar(120) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `TIME_ZONE_ID` varchar(80) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `ENTRY_ID` varchar(95) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `FIRED_TIME` bigint NOT NULL,
  `SCHED_TIME` bigint NOT NULL,
  `PRIORITY` int NOT NULL,
  `STATE` varchar(16) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `ENTRY_ID`) USING BTREE,
  INDEX `IDX_QRTZ_FT_TRIG_INST_NAME`(`SCHED_NAME` ASC, `INSTANCE_NAME` ASC) USING BTREE,
  INDEX `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY`(`SCHED_NAME` ASC, `INSTANCE_NAME` ASC, `REQUESTS_RECOVERY` ASC) USING BTREE,
  INDEX `IDX_QRTZ_FT_J_G`(`SCHED_NAME` ASC, `JOB_NAME` ASC, `JOB_GROUP` ASC) USING BTREE,
  INDEX `IDX_QRTZ_FT_JG`(`SCHED_NAME` ASC, `JOB_GROUP` ASC) USING BTREE,
  INDEX `IDX_QRTZ_FT_T_G`(`SCHED_NAME` ASC, `TRIGGER_NAME` ASC, `TRIGGER_GROUP` ASC) USING BTREE,
  INDEX `IDX_QRTZ_FT_TG`(`SCHED_NAME` ASC, `TRIGGER_GROUP` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `IS_DURABLE` varchar(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `IS_UPDATE_DATA` varchar(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_J_REQ_RECOVERY`(`SCHED_NAME` ASC, `REQUESTS_RECOVERY` ASC) USING BTREE,
  INDEX `IDX_QRTZ_J_GRP`(`SCHED_NAME` ASC, `JOB_GROUP` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `LOCK_NAME` varchar(40) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `LOCK_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `LAST_CHECKIN_TIME` bigint NOT NULL,
  `CHECKIN_INTERVAL` bigint NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `REPEAT_COUNT` bigint NOT NULL,
  `REPEAT_INTERVAL` bigint NOT NULL,
  `TIMES_TRIGGERED` bigint NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `STR_PROP_1` varchar(512) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `STR_PROP_2` varchar(512) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `STR_PROP_3` varchar(512) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `INT_PROP_1` int NULL DEFAULT NULL,
  `INT_PROP_2` int NULL DEFAULT NULL,
  `LONG_PROP_1` bigint NULL DEFAULT NULL,
  `LONG_PROP_2` bigint NULL DEFAULT NULL,
  `DEC_PROP_1` decimal(13, 4) NULL DEFAULT NULL,
  `DEC_PROP_2` decimal(13, 4) NULL DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint NULL DEFAULT NULL,
  `PREV_FIRE_TIME` bigint NULL DEFAULT NULL,
  `PRIORITY` int NULL DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `TRIGGER_TYPE` varchar(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `START_TIME` bigint NOT NULL,
  `END_TIME` bigint NULL DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `MISFIRE_INSTR` smallint NULL DEFAULT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_J`(`SCHED_NAME` ASC, `JOB_NAME` ASC, `JOB_GROUP` ASC) USING BTREE,
  INDEX `IDX_QRTZ_T_JG`(`SCHED_NAME` ASC, `JOB_GROUP` ASC) USING BTREE,
  INDEX `IDX_QRTZ_T_C`(`SCHED_NAME` ASC, `CALENDAR_NAME` ASC) USING BTREE,
  INDEX `IDX_QRTZ_T_G`(`SCHED_NAME` ASC, `TRIGGER_GROUP` ASC) USING BTREE,
  INDEX `IDX_QRTZ_T_STATE`(`SCHED_NAME` ASC, `TRIGGER_STATE` ASC) USING BTREE,
  INDEX `IDX_QRTZ_T_N_STATE`(`SCHED_NAME` ASC, `TRIGGER_NAME` ASC, `TRIGGER_GROUP` ASC, `TRIGGER_STATE` ASC) USING BTREE,
  INDEX `IDX_QRTZ_T_N_G_STATE`(`SCHED_NAME` ASC, `TRIGGER_GROUP` ASC, `TRIGGER_STATE` ASC) USING BTREE,
  INDEX `IDX_QRTZ_T_NEXT_FIRE_TIME`(`SCHED_NAME` ASC, `NEXT_FIRE_TIME` ASC) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST`(`SCHED_NAME` ASC, `TRIGGER_STATE` ASC, `NEXT_FIRE_TIME` ASC) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_MISFIRE`(`SCHED_NAME` ASC, `MISFIRE_INSTR` ASC, `NEXT_FIRE_TIME` ASC) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST_MISFIRE`(`SCHED_NAME` ASC, `MISFIRE_INSTR` ASC, `NEXT_FIRE_TIME` ASC, `TRIGGER_STATE` ASC) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP`(`SCHED_NAME` ASC, `MISFIRE_INSTR` ASC, `NEXT_FIRE_TIME` ASC, `TRIGGER_GROUP` ASC, `TRIGGER_STATE` ASC) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for report
-- ----------------------------
DROP TABLE IF EXISTS `report`;
CREATE TABLE `report`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `discuss_id` int NULL DEFAULT NULL COMMENT 'DISCUSS ID',
  `report_user_id` bigint NULL DEFAULT NULL COMMENT 'REPORT USER ID',
  `report_user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'REPORT USER',
  `report_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'REPORT TYPE',
  `report_content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT 'REPORT CONTENT',
  `report_handle_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'REPORT HANDLING TYPE',
  `report_handle` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'REPORT HANDLING',
  `audit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'AUDIT STATUS',
  `audit_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'AUDIT CONTENT',
  `create_date` datetime NULL DEFAULT NULL COMMENT 'CREATE DATE',
  `update_date` datetime NULL DEFAULT NULL COMMENT 'UPDATE DATE',
  `attr1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `attr2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `attr3` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `attr4` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of report
-- ----------------------------

-- ----------------------------
-- Table structure for restaurant
-- ----------------------------
DROP TABLE IF EXISTS `restaurant`;
CREATE TABLE `restaurant`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'RESTAURANT NAME',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'RESTAURANT ADDRESS',
  `latitude` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'LATITUDE',
  `longitude` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'LONGITUDE',
  `rating` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'RATING',
  `review_count` int NULL DEFAULT NULL COMMENT 'REVIEW COUNT',
  `price_level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'PRICE LEVEL',
  `categories` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'CATEGORIES',
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'IMAGE URL',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'RESTAURANT LINK',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'RESTAURANT PHONE',
  `create_date` datetime NULL DEFAULT NULL COMMENT 'CREATE DATE',
  `attr1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `attr2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `attr3` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `attr4` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1896203417788297218 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of restaurant
-- ----------------------------
INSERT INTO `restaurant` VALUES (1896203417788297217, 'Gramercy Tavern', '42 E 20th St', '40.73844', '-73.98825', '4.4', NULL, '$$$$', 'New American', 'https://s3-media2.fl.yelpcdn.com/bphoto/hPsBMgXXl1jUiSKKP9QRLw/o.jpg', 'https://www.yelp.com/biz/gramercy-tavern-new-york?adjust_creative=Y-izyM5h_6tHFgClshOvpw&amp;utm_campaign=yelp_api_v3&amp;utm_medium=api_v3_business_search&amp;utm_source=Y-izyM5h_6tHFgClshOvpw', '+12124770777', '2025-03-02 22:16:43', '[{\"alias\":\"newamerican\",\"title\":\"New American\"}]', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job`;
CREATE TABLE `schedule_job`  (
  `id` bigint NOT NULL COMMENT 'id',
  `bean_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'spring bean名称',
  `params` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint UNSIGNED NULL DEFAULT NULL COMMENT '任务状态  0：暂停  1：正常',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_create_date`(`create_date` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '定时任务' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of schedule_job
-- ----------------------------
INSERT INTO `schedule_job` VALUES (1067246875800000076, 'testTask', 'renren', '0 0/30 * * * ?', 0, '有参测试，多个参数使用json', 1067246875800000001, '2025-02-05 15:12:41', 1067246875800000001, '2025-02-05 15:12:41');

-- ----------------------------
-- Table structure for schedule_job_log
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job_log`;
CREATE TABLE `schedule_job_log`  (
  `id` bigint NOT NULL COMMENT 'id',
  `job_id` bigint NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'spring bean名称',
  `params` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '参数',
  `status` tinyint UNSIGNED NOT NULL COMMENT '任务状态    0：失败    1：成功',
  `error` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '失败信息',
  `times` int NOT NULL COMMENT '耗时(单位：毫秒)',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_job_id`(`job_id` ASC) USING BTREE,
  INDEX `idx_create_date`(`create_date` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '定时任务日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of schedule_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` bigint NOT NULL COMMENT 'id',
  `pid` bigint NULL DEFAULT NULL COMMENT '上级ID',
  `pids` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所有上级ID，用逗号分开',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '部门名称',
  `sort` int UNSIGNED NULL DEFAULT NULL COMMENT '排序',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_pid`(`pid` ASC) USING BTREE,
  INDEX `idx_sort`(`sort` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '部门管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1067246875800000066, 0, '0', 'NORMAL', 0, 1067246875800000001, '2025-02-05 15:12:41', 1067246875800000001, '2025-03-01 21:55:01');

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `id` bigint NOT NULL COMMENT 'id',
  `dict_type_id` bigint NOT NULL COMMENT '字典类型ID',
  `dict_label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典标签',
  `dict_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字典值',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `sort` int UNSIGNED NULL DEFAULT NULL COMMENT '排序',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_dict_type_value`(`dict_type_id` ASC, `dict_value` ASC) USING BTREE,
  INDEX `idx_sort`(`sort` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '字典数据' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1160061112075464705, 1160061077912858625, '男', '0', '', 0, 1067246875800000001, '2025-02-05 15:12:41', 1067246875800000001, '2025-02-05 15:12:41');
INSERT INTO `sys_dict_data` VALUES (1160061146967879681, 1160061077912858625, '女', '1', '', 1, 1067246875800000001, '2025-02-05 15:12:41', 1067246875800000001, '2025-02-05 15:12:41');
INSERT INTO `sys_dict_data` VALUES (1160061190127267841, 1160061077912858625, '保密', '2', '', 2, 1067246875800000001, '2025-02-05 15:12:41', 1067246875800000001, '2025-02-05 15:12:41');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `id` bigint NOT NULL COMMENT 'id',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典类型',
  `dict_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典名称',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `sort` int UNSIGNED NULL DEFAULT NULL COMMENT '排序',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `dict_type`(`dict_type` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '字典类型' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1160061077912858625, 'gender', '性别', '', 0, 1067246875800000001, '2025-02-05 15:12:41', 1067246875800000001, '2025-02-05 15:12:41');

-- ----------------------------
-- Table structure for sys_log_error
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_error`;
CREATE TABLE `sys_log_error`  (
  `id` bigint NOT NULL COMMENT 'id',
  `request_uri` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求URI',
  `request_method` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求方式',
  `request_params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '请求参数',
  `user_agent` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户代理',
  `ip` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作IP',
  `error_info` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '异常信息',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_create_date`(`create_date` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '异常日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log_error
-- ----------------------------
INSERT INTO `sys_log_error` VALUES (1896201149240258561, '/renren-admin/demo/restaurant/queryYelpApi', 'GET', '{\"_t\":\"1740924510181\",\"location\":\"NEWYORK\"}', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 'org.springframework.web.context.request.async.AsyncRequestNotUsableException: ServletOutputStream failed to write: java.io.IOException: 你的主机中的软件中止了一个已建立的连接。\r\n	at org.springframework.web.context.request.async.StandardServletAsyncWebRequest$LifecycleHttpServletResponse.handleIOException(StandardServletAsyncWebRequest.java:320)\r\n	at org.springframework.web.context.request.async.StandardServletAsyncWebRequest$LifecycleServletOutputStream.write(StandardServletAsyncWebRequest.java:378)\r\n	at org.springframework.util.StreamUtils$NonClosingOutputStream.write(StreamUtils.java:261)\r\n	at com.fasterxml.jackson.core.json.UTF8JsonGenerator._flushBuffer(UTF8JsonGenerator.java:2203)\r\n	at com.fasterxml.jackson.core.json.UTF8JsonGenerator._writeStringSegment2(UTF8JsonGenerator.java:1515)\r\n	at com.fasterxml.jackson.core.json.UTF8JsonGenerator._writeStringSegment(UTF8JsonGenerator.java:1462)\r\n	at com.fasterxml.jackson.core.json.UTF8JsonGenerator.writeString(UTF8JsonGenerator.java:524)\r\n	at com.fasterxml.jackson.databind.ser.std.StringSerializer.serialize(StringSerializer.java:41)\r\n	at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:732)\r\n	at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:772)\r\n	at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)\r\n	at com.fasterxml.jackson.databind.ser.impl.IndexedListSerializer.serializeContents(IndexedListSerializer.java:119)\r\n	at com.fasterxml.jackson.databind.ser.impl.IndexedListSerializer.serialize(IndexedListSerializer.java:79)\r\n	at com.fasterxml.jackson.databind.ser.impl.IndexedListSerializer.serialize(IndexedListSerializer.java:18)\r\n	at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:732)\r\n	at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:772)\r\n	at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)\r\n	at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:479)\r\n	at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:318)\r\n	at com.fasterxml.jackson.databind.ObjectWriter$Prefetch.serialize(ObjectWriter.java:1572)\r\n	at com.fasterxml.jackson.databind.ObjectWriter.writeValue(ObjectWriter.java:1061)\r\n	at org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter.writeInternal(AbstractJackson2HttpMessageConverter.java:483)\r\n	at org.springframework.http.converter.AbstractGenericHttpMessageConverter.write(AbstractGenericHttpMessageConverter.java:114)\r\n	at org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodProcessor.writeWithMessageConverters(AbstractMessageConverterMethodProcessor.java:297)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor.handleReturnValue(RequestResponseBodyMethodProcessor.java:192)\r\n	at org.springframework.web.method.support.HandlerMethodReturnValueHandlerComposite.handleReturnValue(HandlerMethodReturnValueHandlerComposite.java:78)\r\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:136)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:926)\r\n	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:831)\r\n	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\r\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1089)\r\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:979)\r\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1014)\r\n	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:903)\r\n	at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)\r\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:885)\r\n	at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:195)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)\r\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:51)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)\r\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:61)\r\n	at org.apache.shiro.web.servlet.AdviceFilter.executeChain(AdviceFilter.java:108)\r\n	at org.apache.shiro.web.servlet.AdviceFilter.doFilterInternal(AdviceFilter.java:137)\r\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:154)\r\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:66)\r\n	at org.apache.shiro.web.servlet.AdviceFilter.executeChain(AdviceFilter.java:108)\r\n	at org.apache.shiro.web.servlet.AdviceFilter.doFilterInternal(AdviceFilter.java:137)\r\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:154)\r\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:66)\r\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.executeChain(AbstractShiroFilter.java:458)\r\n	at org.apache.shiro.web.servlet.AbstractShiroFilter$1.call(AbstractShiroFilter.java:373)\r\n	at org.apache.shiro.subject.support.SubjectCallable.doCall(SubjectCallable.java:90)\r\n	at org.apache.shiro.subject.support.SubjectCallable.call(SubjectCallable.java:83)\r\n	at org.apache.shiro.subject.support.DelegatingSubject.execute(DelegatingSubject.java:387)\r\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.doFilterInternal(AbstractShiroFilter.java:370)\r\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:154)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)\r\n	at io.renren.common.xss.XssFilter.doFilter(XssFilter.java:25)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)\r\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:61)\r\n	at org.apache.shiro.web.servlet.AdviceFilter.executeChain(AdviceFilter.java:108)\r\n	at org.apache.shiro.web.servlet.AdviceFilter.doFilterInternal(AdviceFilter.java:137)\r\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:154)\r\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:66)\r\n	at org.apache.shiro.web.servlet.AdviceFilter.executeChain(AdviceFilter.java:108)\r\n	at org.apache.shiro.web.servlet.AdviceFilter.doFilterInternal(AdviceFilter.java:137)\r\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:154)\r\n	at org.apache.shiro.web.servlet.ProxiedFilterChain.doFilter(ProxiedFilterChain.java:66)\r\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.executeChain(AbstractShiroFilter.java:458)\r\n	at org.apache.shiro.web.servlet.AbstractShiroFilter$1.call(AbstractShiroFilter.java:373)\r\n	at org.apache.shiro.subject.support.SubjectCallable.doCall(SubjectCallable.java:90)\r\n	at org.apache.shiro.subject.support.SubjectCallable.call(SubjectCallable.java:83)\r\n	at org.apache.shiro.subject.support.DelegatingSubject.execute(DelegatingSubject.java:387)\r\n	at org.apache.shiro.web.servlet.AbstractShiroFilter.doFilterInternal(AbstractShiroFilter.java:370)\r\n	at org.apache.shiro.web.servlet.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:154)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:352)\r\n	at org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:268)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)\r\n	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)\r\n	at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)\r\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:167)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:90)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:482)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:115)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:93)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:344)\r\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:389)\r\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63)\r\n	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:896)\r\n	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1741)\r\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:52)\r\n	at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1190)\r\n	at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)\r\n	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:63)\r\n	at java.base/java.lang.Thread.run(Thread.java:840)\r\n	Suppressed: org.springframework.web.context.request.async.AsyncRequestNotUsableException: Response not usable after response errors.\r\n		at org.springframework.web.context.request.async.StandardServletAsyncWebRequest$LifecycleHttpServletResponse.obtainLockAndCheckState(StandardServletAsyncWebRequest.java:314)\r\n		at org.springframework.web.context.request.async.StandardServletAsyncWebRequest$LifecycleServletOutputStream.write(StandardServletAsyncWebRequest.java:373)\r\n		at org.springframework.util.StreamUtils$NonClosingOutputStream.write(StreamUtils.java:261)\r\n		at com.fasterxml.jackson.core.json.UTF8JsonGenerator._flushBuffer(UTF8JsonGenerator.java:2203)\r\n		at com.fasterxml.jackson.core.json.UTF8JsonGenerator.close(UTF8JsonGenerator.java:1227)\r\n		at org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter.writeInternal(AbstractJackson2HttpMessageConverter.java:452)\r\n		... 89 more\r\nCaused by: org.apache.catalina.connector.ClientAbortException: java.io.IOException: 你的主机中的软件中止了一个已建立的连接。\r\n	at org.apache.catalina.connector.OutputBuffer.realWriteBytes(OutputBuffer.java:341)\r\n	at org.apache.catalina.connector.OutputBuffer.flushByteBuffer(OutputBuffer.java:776)\r\n	at org.apache.catalina.connector.OutputBuffer.append(OutputBuffer.java:673)\r\n	at org.apache.catalina.connector.OutputBuffer.writeBytes(OutputBuffer.java:376)\r\n	at org.apache.catalina.connector.OutputBuffer.write(OutputBuffer.java:354)\r\n	at org.apache.catalina.connector.CoyoteOutputStream.write(CoyoteOutputStream.java:103)\r\n	at org.springframework.web.context.request.async.StandardServletAsyncWebRequest$LifecycleServletOutputStream.write(StandardServletAsyncWebRequest.java:375)\r\n	... 109 more\r\nCaused by: java.io.IOException: 你的主机中的软件中止了一个已建立的连接。\r\n	at java.base/sun.nio.ch.SocketDispatcher.write0(Native Method)\r\n	at java.base/sun.nio.ch.SocketDispatcher.write(SocketDispatcher.java:54)\r\n	at java.base/sun.nio.ch.IOUtil.writeFromNativeBuffer(IOUtil.java:132)\r\n	at java.base/sun.nio.ch.IOUtil.write(IOUtil.java:97)\r\n	at java.base/sun.nio.ch.IOUtil.write(IOUtil.java:53)\r\n	at java.base/sun.nio.ch.SocketChannelImpl.write(SocketChannelImpl.java:532)\r\n	at org.apache.tomcat.util.net.NioChannel.write(NioChannel.java:122)\r\n	at org.apache.tomcat.util.net.NioEndpoint$NioSocketWrapper.doWrite(NioEndpoint.java:1378)\r\n	at org.apache.tomcat.util.net.SocketWrapperBase.doWrite(SocketWrapperBase.java:764)\r\n	at org.apache.tomcat.util.net.SocketWrapperBase.writeBlocking(SocketWrapperBase.java:589)\r\n	at org.apache.tomcat.util.net.SocketWrapperBase.write(SocketWrapperBase.java:533)\r\n	at org.apache.coyote.http11.Http11OutputBuffer$SocketOutputBuffer.doWrite(Http11OutputBuffer.java:533)\r\n	at org.apache.coyote.http11.filters.ChunkedOutputFilter.doWrite(ChunkedOutputFilter.java:112)\r\n	at org.apache.coyote.http11.Http11OutputBuffer.doWrite(Http11OutputBuffer.java:193)\r\n	at org.apache.coyote.Response.doWrite(Response.java:623)\r\n	at org.apache.catalina.connector.OutputBuffer.realWriteBytes(OutputBuffer.java:329)\r\n	... 115 more\r\n', 1067246875800000001, '2025-03-02 22:09:18');

-- ----------------------------
-- Table structure for sys_log_login
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_login`;
CREATE TABLE `sys_log_login`  (
  `id` bigint NOT NULL COMMENT 'id',
  `operation` tinyint UNSIGNED NULL DEFAULT NULL COMMENT '用户操作   0：用户登录   1：用户退出',
  `status` tinyint UNSIGNED NOT NULL COMMENT '状态  0：失败    1：成功    2：账号已锁定',
  `user_agent` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户代理',
  `ip` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作IP',
  `creator_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_create_date`(`create_date` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '登录日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log_login
-- ----------------------------
INSERT INTO `sys_log_login` VALUES (1887168369731768321, 0, 1, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36 QuarkPC/2.0.5.220', '0:0:0:0:0:0:0:1', 'admin', 1067246875800000001, '2025-02-05 23:56:16');
INSERT INTO `sys_log_login` VALUES (1887168404833898497, 0, 1, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36 QuarkPC/2.0.5.220', '0:0:0:0:0:0:0:1', 'admin', 1067246875800000001, '2025-02-05 23:56:24');
INSERT INTO `sys_log_login` VALUES (1887168503903358977, 0, 1, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36 QuarkPC/2.0.5.220', '0:0:0:0:0:0:0:1', 'admin', 1067246875800000001, '2025-02-05 23:56:48');
INSERT INTO `sys_log_login` VALUES (1887169141722779650, 0, 0, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36 QuarkPC/2.0.5.220', '0:0:0:0:0:0:0:1', 'admin', 1067246875800000001, '2025-02-05 23:59:20');
INSERT INTO `sys_log_login` VALUES (1887169195908993025, 0, 1, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36 QuarkPC/2.0.5.220', '0:0:0:0:0:0:0:1', 'admin', 1067246875800000001, '2025-02-05 23:59:33');
INSERT INTO `sys_log_login` VALUES (1887390290364764162, 0, 1, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36 QuarkPC/2.0.5.220', '0:0:0:0:0:0:0:1', 'admin', 1067246875800000001, '2025-02-06 14:38:06');
INSERT INTO `sys_log_login` VALUES (1895799087722360833, 0, 1, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 'admin', 1067246875800000001, '2025-03-01 19:31:39');
INSERT INTO `sys_log_login` VALUES (1895994294169677825, 0, 1, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 'admin', 1067246875800000001, '2025-03-02 08:27:20');
INSERT INTO `sys_log_login` VALUES (1896151166172508162, 0, 1, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 'admin', 1067246875800000001, '2025-03-02 18:50:41');

-- ----------------------------
-- Table structure for sys_log_operation
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_operation`;
CREATE TABLE `sys_log_operation`  (
  `id` bigint NOT NULL COMMENT 'id',
  `operation` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户操作',
  `request_uri` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求URI',
  `request_method` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求方式',
  `request_params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '请求参数',
  `request_time` int UNSIGNED NOT NULL COMMENT '请求时长(毫秒)',
  `user_agent` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户代理',
  `ip` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作IP',
  `status` tinyint UNSIGNED NOT NULL COMMENT '状态  0：失败   1：成功',
  `creator_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_create_date`(`create_date` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '操作日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log_operation
-- ----------------------------
INSERT INTO `sys_log_operation` VALUES (1887169268113936385, '删除', '/renren-admin/sys/dept/1067246875800000064', 'DELETE', '1067246875800000064', 202, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36 QuarkPC/2.0.5.220', '0:0:0:0:0:0:0:1', 0, 'admin', 1067246875800000001, '2025-02-05 23:59:50');
INSERT INTO `sys_log_operation` VALUES (1887169285193142273, '删除', '/renren-admin/sys/dept/1067246875800000065', 'DELETE', '1067246875800000065', 256, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36 QuarkPC/2.0.5.220', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-02-05 23:59:54');
INSERT INTO `sys_log_operation` VALUES (1887169300020006913, '删除', '/renren-admin/sys/dept/1067246875800000067', 'DELETE', '1067246875800000067', 259, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36 QuarkPC/2.0.5.220', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-02-05 23:59:58');
INSERT INTO `sys_log_operation` VALUES (1887169319406080001, '删除', '/renren-admin/sys/dept/1067246875800000068', 'DELETE', '1067246875800000068', 259, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36 QuarkPC/2.0.5.220', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-02-06 00:00:02');
INSERT INTO `sys_log_operation` VALUES (1887169333326974977, '删除', '/renren-admin/sys/dept/1067246875800000062', 'DELETE', '1067246875800000062', 231, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36 QuarkPC/2.0.5.220', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-02-06 00:00:06');
INSERT INTO `sys_log_operation` VALUES (1887169346673250306, '删除', '/renren-admin/sys/dept/1067246875800000063', 'DELETE', '1067246875800000063', 301, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36 QuarkPC/2.0.5.220', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-02-06 00:00:09');
INSERT INTO `sys_log_operation` VALUES (1887169360283766785, '删除', '/renren-admin/sys/dept/1067246875800000064', 'DELETE', '1067246875800000064', 289, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36 QuarkPC/2.0.5.220', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-02-06 00:00:12');
INSERT INTO `sys_log_operation` VALUES (1887390413333368834, '删除', '/renren-admin/sys/dict/data', 'DELETE', '[1225814069634195457,1225814107559092225,1225814271879340034]', 75, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36 QuarkPC/2.0.5.220', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-02-06 14:38:35');
INSERT INTO `sys_log_operation` VALUES (1887390430207053825, '删除', '/renren-admin/sys/dict/type', 'DELETE', '[1225813644059140097]', 56, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36 QuarkPC/2.0.5.220', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-02-06 14:38:39');
INSERT INTO `sys_log_operation` VALUES (1895833265847357441, '修改', '/renren-admin/sys/menu', 'PUT', '{\"id\":1895796272565297154,\"pid\":1067246875800000035,\"children\":[],\"name\":\"Discuss\",\"url\":\"demo/discuss\",\"menuType\":0,\"icon\":\"icon-desktop\",\"permissions\":null,\"sort\":0,\"createDate\":null,\"parentName\":\"系统设置\"}', 51, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 21:47:28');
INSERT INTO `sys_log_operation` VALUES (1895833307509379073, '修改', '/renren-admin/sys/menu', 'PUT', '{\"id\":1895796272724680705,\"pid\":1067246875800000035,\"children\":[],\"name\":\"REPORT\",\"url\":\"demo/report\",\"menuType\":0,\"icon\":\"icon-desktop\",\"permissions\":null,\"sort\":0,\"createDate\":null,\"parentName\":\"系统设置\"}', 13, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 21:47:38');
INSERT INTO `sys_log_operation` VALUES (1895833373804548097, '修改', '/renren-admin/sys/menu', 'PUT', '{\"id\":1895796272724680716,\"pid\":1067246875800000035,\"children\":[],\"name\":\"RESTAURANT\",\"url\":\"demo/restaurant\",\"menuType\":0,\"icon\":\"icon-desktop\",\"permissions\":null,\"sort\":0,\"createDate\":null,\"parentName\":\"系统设置\"}', 10, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 21:47:54');
INSERT INTO `sys_log_operation` VALUES (1895833437419556866, '修改', '/renren-admin/sys/menu', 'PUT', '{\"id\":1895796272565297154,\"pid\":1067246875800000035,\"children\":[],\"name\":\"DISCUSS\",\"url\":\"demo/discuss\",\"menuType\":0,\"icon\":\"icon-desktop\",\"permissions\":null,\"sort\":0,\"createDate\":null,\"parentName\":\"系统设置\"}', 10, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 21:48:09');
INSERT INTO `sys_log_operation` VALUES (1895833458525294593, '删除', '/renren-admin/sys/menu/1067246875800000047', 'DELETE', '1067246875800000047', 8, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 21:48:14');
INSERT INTO `sys_log_operation` VALUES (1895833499847577602, '删除', '/renren-admin/sys/menu/1067246875800000042', 'DELETE', '1067246875800000042', 6, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 21:48:24');
INSERT INTO `sys_log_operation` VALUES (1895833508924051458, '删除', '/renren-admin/sys/menu/1067246875800000043', 'DELETE', '1067246875800000043', 4, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 21:48:26');
INSERT INTO `sys_log_operation` VALUES (1895833516750622722, '删除', '/renren-admin/sys/menu/1067246875800000044', 'DELETE', '1067246875800000044', 5, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 21:48:28');
INSERT INTO `sys_log_operation` VALUES (1895833524438781953, '删除', '/renren-admin/sys/menu/1067246875800000045', 'DELETE', '1067246875800000045', 4, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 21:48:30');
INSERT INTO `sys_log_operation` VALUES (1895833538481311745, '删除', '/renren-admin/sys/menu/1067246875800000041', 'DELETE', '1067246875800000041', 4, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 21:48:33');
INSERT INTO `sys_log_operation` VALUES (1895833557540229122, '删除', '/renren-admin/sys/menu/1067246875800000059', 'DELETE', '1067246875800000059', 6, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 21:48:38');
INSERT INTO `sys_log_operation` VALUES (1895833564758626305, '删除', '/renren-admin/sys/menu/1067246875800000057', 'DELETE', '1067246875800000057', 6, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 21:48:39');
INSERT INTO `sys_log_operation` VALUES (1895833572081881090, '删除', '/renren-admin/sys/menu/1067246875800000060', 'DELETE', '1067246875800000060', 5, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 21:48:41');
INSERT INTO `sys_log_operation` VALUES (1895833583351975938, '删除', '/renren-admin/sys/menu/1067246875800000061', 'DELETE', '1067246875800000061', 5, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 21:48:44');
INSERT INTO `sys_log_operation` VALUES (1895833596169768961, '删除', '/renren-admin/sys/menu/1067246875800000058', 'DELETE', '1067246875800000058', 3, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 21:48:47');
INSERT INTO `sys_log_operation` VALUES (1895833605523066882, '删除', '/renren-admin/sys/menu/1067246875800000040', 'DELETE', '1067246875800000040', 5, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 21:48:49');
INSERT INTO `sys_log_operation` VALUES (1895833645301846017, '修改', '/renren-admin/sys/menu', 'PUT', '{\"id\":1067246875800000025,\"pid\":1067246875800000035,\"children\":[],\"name\":\"MENU\",\"url\":\"sys/menu\",\"menuType\":0,\"icon\":\"icon-unorderedlist\",\"permissions\":null,\"sort\":0,\"createDate\":null,\"parentName\":\"系统设置\"}', 11, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 21:48:59');
INSERT INTO `sys_log_operation` VALUES (1895833690814238722, '修改', '/renren-admin/sys/menu', 'PUT', '{\"id\":1067246875800000035,\"pid\":0,\"children\":[],\"name\":\"SETTINGS\",\"url\":null,\"menuType\":0,\"icon\":\"icon-setting\",\"permissions\":null,\"sort\":1,\"createDate\":null,\"parentName\":\"一级菜单\"}', 8, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 21:49:09');
INSERT INTO `sys_log_operation` VALUES (1895833813384384513, '修改', '/renren-admin/sys/menu', 'PUT', '{\"id\":1895796272565297154,\"pid\":0,\"children\":[],\"name\":\"DISCUSS\",\"url\":\"demo/discuss\",\"menuType\":0,\"icon\":\"icon-desktop\",\"permissions\":null,\"sort\":1,\"createDate\":null,\"parentName\":\"一级菜单\"}', 6, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 21:49:39');
INSERT INTO `sys_log_operation` VALUES (1895833857684623361, '修改', '/renren-admin/sys/menu', 'PUT', '{\"id\":1895796272724680705,\"pid\":0,\"children\":[],\"name\":\"REPORT\",\"url\":\"demo/report\",\"menuType\":0,\"icon\":\"icon-desktop\",\"permissions\":null,\"sort\":2,\"createDate\":null,\"parentName\":\"一级菜单\"}', 8, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 21:49:49');
INSERT INTO `sys_log_operation` VALUES (1895833910297972737, '修改', '/renren-admin/sys/menu', 'PUT', '{\"id\":1895796272724680716,\"pid\":0,\"children\":[],\"name\":\"RESTAURANT\",\"url\":\"demo/restaurant\",\"menuType\":0,\"icon\":\"icon-desktop\",\"permissions\":null,\"sort\":3,\"createDate\":null,\"parentName\":\"一级菜单\"}', 6, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 21:50:02');
INSERT INTO `sys_log_operation` VALUES (1895833980862943234, '修改', '/renren-admin/sys/menu', 'PUT', '{\"id\":1067246875800000046,\"pid\":0,\"children\":[],\"name\":\"LOGS\",\"url\":null,\"menuType\":0,\"icon\":\"icon-container\",\"permissions\":null,\"sort\":4,\"createDate\":null,\"parentName\":\"一级菜单\"}', 7, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 21:50:19');
INSERT INTO `sys_log_operation` VALUES (1895834006536278018, '删除', '/renren-admin/sys/menu/1067246875800000051', 'DELETE', '1067246875800000051', 4, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 21:50:25');
INSERT INTO `sys_log_operation` VALUES (1895834025666498562, '删除', '/renren-admin/sys/menu/1156748733921165314', 'DELETE', '1156748733921165314', 4, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 21:50:29');
INSERT INTO `sys_log_operation` VALUES (1895834037641236482, '删除', '/renren-admin/sys/menu/1067246875800000053', 'DELETE', '1067246875800000053', 4, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 21:50:32');
INSERT INTO `sys_log_operation` VALUES (1895834070625243137, '修改', '/renren-admin/sys/menu', 'PUT', '{\"id\":1067246875800000048,\"pid\":1067246875800000046,\"children\":[],\"name\":\"LOG IN\",\"url\":\"sys/log-login\",\"menuType\":0,\"icon\":\"icon-filedone\",\"permissions\":\"sys:log:login\",\"sort\":0,\"createDate\":null,\"parentName\":\"LOGS\"}', 6, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 21:50:40');
INSERT INTO `sys_log_operation` VALUES (1895834100690014210, '修改', '/renren-admin/sys/menu', 'PUT', '{\"id\":1067246875800000049,\"pid\":1067246875800000046,\"children\":[],\"name\":\"OPERATION\",\"url\":\"sys/log-operation\",\"menuType\":0,\"icon\":\"icon-solution\",\"permissions\":\"sys:log:operation\",\"sort\":1,\"createDate\":null,\"parentName\":\"LOGS\"}', 7, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 21:50:47');
INSERT INTO `sys_log_operation` VALUES (1895834123779657729, '修改', '/renren-admin/sys/menu', 'PUT', '{\"id\":1067246875800000050,\"pid\":1067246875800000046,\"children\":[],\"name\":\"ERROR\",\"url\":\"sys/log-error\",\"menuType\":0,\"icon\":\"icon-file-exception\",\"permissions\":\"sys:log:error\",\"sort\":2,\"createDate\":null,\"parentName\":\"LOGS\"}', 7, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 21:50:53');
INSERT INTO `sys_log_operation` VALUES (1895834420077875202, '修改', '/renren-admin/sys/menu', 'PUT', '{\"id\":1067246875800000055,\"pid\":0,\"children\":[],\"name\":\"用户管理\",\"url\":\"sys/user\",\"menuType\":0,\"icon\":\"icon-user\",\"permissions\":null,\"sort\":0,\"createDate\":null,\"parentName\":\"一级菜单\"}', 6, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 21:52:03');
INSERT INTO `sys_log_operation` VALUES (1895834471147720706, '修改', '/renren-admin/sys/menu', 'PUT', '{\"id\":1067246875800000055,\"pid\":0,\"children\":[],\"name\":\"USERS\",\"url\":\"sys/user\",\"menuType\":0,\"icon\":\"icon-user\",\"permissions\":null,\"sort\":0,\"createDate\":null,\"parentName\":\"一级菜单\"}', 6, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 21:52:15');
INSERT INTO `sys_log_operation` VALUES (1895834520464347137, '修改', '/renren-admin/sys/menu', 'PUT', '{\"id\":1067246875800000007,\"pid\":0,\"children\":[],\"name\":\"ROLES\",\"url\":\"sys/role\",\"menuType\":0,\"icon\":\"icon-team\",\"permissions\":null,\"sort\":2,\"createDate\":null,\"parentName\":\"一级菜单\"}', 9, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 21:52:27');
INSERT INTO `sys_log_operation` VALUES (1895834926066126855, '保存', '/renren-admin/sys/role', 'POST', '{\"id\":null,\"name\":\"Restaurant Manager\",\"remark\":\"\",\"createDate\":null,\"menuIdList\":[1895796272565297154,1895796272565297155,1895796272565297156,1895796272565297157,1895796272565297158,1895796272565297159],\"deptIdList\":[]}', 18, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 21:54:04');
INSERT INTO `sys_log_operation` VALUES (1895835058992009218, '保存', '/renren-admin/sys/role', 'POST', '{\"id\":null,\"name\":\"Normal User\",\"remark\":\"\",\"createDate\":null,\"menuIdList\":[1895796272565297154,1895796272565297155,1895796272565297156,1895796272565297157,1895796272565297158,1895796272565297159,1895796272724680716,1895796272724680717,1895796272724680718,1895796272724680719,1895796272724680720,1895796272724680721],\"deptIdList\":[]}', 14, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 21:54:36');
INSERT INTO `sys_log_operation` VALUES (1895835165560885249, '修改', '/renren-admin/sys/dept', 'PUT', '{\"id\":1067246875800000066,\"pid\":0,\"children\":[],\"name\":\"NORMAL\",\"sort\":0,\"createDate\":null,\"parentName\":\"一级部门\"}', 10, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 21:55:01');
INSERT INTO `sys_log_operation` VALUES (1895835228555137026, '删除', '/renren-admin/sys/menu/1067246875800000012', 'DELETE', '1067246875800000012', 22, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 21:55:16');
INSERT INTO `sys_log_operation` VALUES (1895835244875173889, '删除', '/renren-admin/sys/menu/1067246875800000014', 'DELETE', '1067246875800000014', 6, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 21:55:20');
INSERT INTO `sys_log_operation` VALUES (1895835252634636289, '删除', '/renren-admin/sys/menu/1067246875800000015', 'DELETE', '1067246875800000015', 5, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 21:55:22');
INSERT INTO `sys_log_operation` VALUES (1895835259727204354, '删除', '/renren-admin/sys/menu/1067246875800000016', 'DELETE', '1067246875800000016', 4, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 21:55:23');
INSERT INTO `sys_log_operation` VALUES (1895835272729546754, '删除', '/renren-admin/sys/menu/1067246875800000017', 'DELETE', '1067246875800000017', 4, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 21:55:27');
INSERT INTO `sys_log_operation` VALUES (1895835282175119361, '删除', '/renren-admin/sys/menu/1067246875800000012', 'DELETE', '1067246875800000012', 3, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 21:55:29');
INSERT INTO `sys_log_operation` VALUES (1895835309660393473, '删除', '/renren-admin/sys/menu/1067246875800000002', 'DELETE', '1067246875800000002', 5, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 21:55:35');
INSERT INTO `sys_log_operation` VALUES (1895856931339456514, '修改', '/renren-admin/sys/menu', 'PUT', '{\"id\":1067246875800000025,\"pid\":0,\"children\":[],\"name\":\"MENU\",\"url\":\"sys/menu\",\"menuType\":0,\"icon\":\"icon-unorderedlist\",\"permissions\":null,\"sort\":0,\"createDate\":null,\"parentName\":\"一级菜单\"}', 48, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 23:21:30');
INSERT INTO `sys_log_operation` VALUES (1895856945038053377, '删除', '/renren-admin/sys/menu/1067246875800000035', 'DELETE', '1067246875800000035', 6, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-01 23:21:34');
INSERT INTO `sys_log_operation` VALUES (1895994869577854978, '修改', '/renren-admin/sys/menu', 'PUT', '{\"id\":1895796272565297154,\"pid\":0,\"children\":[],\"name\":\"REVIEW\",\"url\":\"demo/discuss\",\"menuType\":0,\"icon\":\"icon-desktop\",\"permissions\":null,\"sort\":1,\"createDate\":null,\"parentName\":\"一级菜单\"}', 15, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-02 08:29:37');
INSERT INTO `sys_log_operation` VALUES (1896151264302444546, '删除', '/renren-admin/sys/menu/1067246875800000008', 'DELETE', '1067246875800000008', 8, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-02 18:51:05');
INSERT INTO `sys_log_operation` VALUES (1896151274096144385, '删除', '/renren-admin/sys/menu/1067246875800000009', 'DELETE', '1067246875800000009', 5, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-02 18:51:07');
INSERT INTO `sys_log_operation` VALUES (1896151285164912641, '删除', '/renren-admin/sys/menu/1067246875800000010', 'DELETE', '1067246875800000010', 4, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-02 18:51:10');
INSERT INTO `sys_log_operation` VALUES (1896151294212026370, '删除', '/renren-admin/sys/menu/1067246875800000011', 'DELETE', '1067246875800000011', 5, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-02 18:51:12');
INSERT INTO `sys_log_operation` VALUES (1896151303565324289, '删除', '/renren-admin/sys/menu/1067246875800000007', 'DELETE', '1067246875800000007', 5, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-02 18:51:14');
INSERT INTO `sys_log_operation` VALUES (1896197681666494465, '保存', '/renren-admin/sys/menu', 'POST', '{\"id\":null,\"pid\":0,\"children\":[],\"name\":\"YELP\",\"url\":\"demo/restaurant-yelp\",\"menuType\":0,\"icon\":\"icon-apple-fill\",\"permissions\":\"\",\"sort\":3,\"createDate\":null,\"parentName\":\"一级菜单\"}', 181, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-02 21:55:32');
INSERT INTO `sys_log_operation` VALUES (1896197757822472193, '修改', '/renren-admin/sys/menu', 'PUT', '{\"id\":1067246875800000025,\"pid\":0,\"children\":[],\"name\":\"MENU\",\"url\":\"sys/menu\",\"menuType\":0,\"icon\":\"icon-unorderedlist\",\"permissions\":null,\"sort\":1,\"createDate\":null,\"parentName\":\"一级菜单\"}', 15, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-02 21:55:50');
INSERT INTO `sys_log_operation` VALUES (1896197777762193410, '修改', '/renren-admin/sys/menu', 'PUT', '{\"id\":1895796272565297154,\"pid\":0,\"children\":[],\"name\":\"REVIEW\",\"url\":\"demo/discuss\",\"menuType\":0,\"icon\":\"icon-desktop\",\"permissions\":null,\"sort\":2,\"createDate\":null,\"parentName\":\"一级菜单\"}', 10, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-02 21:55:55');
INSERT INTO `sys_log_operation` VALUES (1896197789946646530, '修改', '/renren-admin/sys/menu', 'PUT', '{\"id\":1895796272724680705,\"pid\":0,\"children\":[],\"name\":\"REPORT\",\"url\":\"demo/report\",\"menuType\":0,\"icon\":\"icon-desktop\",\"permissions\":null,\"sort\":3,\"createDate\":null,\"parentName\":\"一级菜单\"}', 10, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-02 21:55:57');
INSERT INTO `sys_log_operation` VALUES (1896197836708941826, '修改', '/renren-admin/sys/menu', 'PUT', '{\"id\":1896197681096069122,\"pid\":0,\"children\":[],\"name\":\"YELP\",\"url\":\"demo/restaurant-yelp\",\"menuType\":0,\"icon\":\"icon-apple-fill\",\"permissions\":\"\",\"sort\":2,\"createDate\":null,\"parentName\":\"一级菜单\"}', 9, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-02 21:56:09');
INSERT INTO `sys_log_operation` VALUES (1896198003499634689, '修改', '/renren-admin/sys/menu', 'PUT', '{\"id\":1895796272565297154,\"pid\":0,\"children\":[],\"name\":\"REVIEW\",\"url\":\"demo/discuss\",\"menuType\":0,\"icon\":\"icon-desktop\",\"permissions\":null,\"sort\":3,\"createDate\":null,\"parentName\":\"一级菜单\"}', 9, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-02 21:56:48');
INSERT INTO `sys_log_operation` VALUES (1896198063922778114, '修改', '/renren-admin/sys/menu', 'PUT', '{\"id\":1895796272724680716,\"pid\":0,\"children\":[],\"name\":\"RESTAURANT\",\"url\":\"demo/restaurant\",\"menuType\":0,\"icon\":\"icon-desktop\",\"permissions\":null,\"sort\":3,\"createDate\":null,\"parentName\":\"一级菜单\"}', 9, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-02 21:57:03');
INSERT INTO `sys_log_operation` VALUES (1896198080142151682, '修改', '/renren-admin/sys/menu', 'PUT', '{\"id\":1895796272565297154,\"pid\":0,\"children\":[],\"name\":\"REVIEW\",\"url\":\"demo/discuss\",\"menuType\":0,\"icon\":\"icon-desktop\",\"permissions\":null,\"sort\":4,\"createDate\":null,\"parentName\":\"一级菜单\"}', 11, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-02 21:57:07');
INSERT INTO `sys_log_operation` VALUES (1896198094524420098, '修改', '/renren-admin/sys/menu', 'PUT', '{\"id\":1895796272724680705,\"pid\":0,\"children\":[],\"name\":\"REPORT\",\"url\":\"demo/report\",\"menuType\":0,\"icon\":\"icon-desktop\",\"permissions\":null,\"sort\":5,\"createDate\":null,\"parentName\":\"一级菜单\"}', 8, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-02 21:57:10');
INSERT INTO `sys_log_operation` VALUES (1896198114053099521, '修改', '/renren-admin/sys/menu', 'PUT', '{\"id\":1067246875800000046,\"pid\":0,\"children\":[],\"name\":\"LOGS\",\"url\":null,\"menuType\":0,\"icon\":\"icon-container\",\"permissions\":null,\"sort\":6,\"createDate\":null,\"parentName\":\"一级菜单\"}', 10, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-02 21:57:15');
INSERT INTO `sys_log_operation` VALUES (1896198182906793986, '修改', '/renren-admin/sys/menu', 'PUT', '{\"id\":1895796272724680716,\"pid\":0,\"children\":[],\"name\":\"RESTAURANT\",\"url\":\"demo/restaurant\",\"menuType\":0,\"icon\":\"icon-bank-fill\",\"permissions\":null,\"sort\":3,\"createDate\":null,\"parentName\":\"一级菜单\"}', 8, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-02 21:57:31');
INSERT INTO `sys_log_operation` VALUES (1896198268692893697, '修改', '/renren-admin/sys/menu', 'PUT', '{\"id\":1895796272565297154,\"pid\":0,\"children\":[],\"name\":\"REVIEW\",\"url\":\"demo/discuss\",\"menuType\":0,\"icon\":\"icon-meh\",\"permissions\":null,\"sort\":4,\"createDate\":null,\"parentName\":\"一级菜单\"}', 7, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-02 21:57:52');
INSERT INTO `sys_log_operation` VALUES (1896198348460167170, '修改', '/renren-admin/sys/menu', 'PUT', '{\"id\":1895796272724680705,\"pid\":0,\"children\":[],\"name\":\"REPORT\",\"url\":\"demo/report\",\"menuType\":0,\"icon\":\"icon-trademark-circle-fil\",\"permissions\":null,\"sort\":5,\"createDate\":null,\"parentName\":\"一级菜单\"}', 10, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.6261.95 Safari/537.36', '0:0:0:0:0:0:0:1', 1, 'admin', 1067246875800000001, '2025-03-02 21:58:11');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint NOT NULL COMMENT 'id',
  `pid` bigint NULL DEFAULT NULL COMMENT '上级ID，一级菜单为0',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单URL',
  `permissions` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：sys:user:list,sys:user:save)',
  `menu_type` tinyint UNSIGNED NULL DEFAULT NULL COMMENT '类型   0：菜单   1：按钮',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `sort` int NULL DEFAULT NULL COMMENT '排序',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_pid`(`pid` ASC) USING BTREE,
  INDEX `idx_sort`(`sort` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1067246875800000003, 1067246875800000055, '新增', NULL, 'sys:user:save,sys:dept:list,sys:role:list', 1, NULL, 1, 1067246875800000001, '2025-02-05 15:12:38', 1067246875800000001, '2025-02-05 15:12:38');
INSERT INTO `sys_menu` VALUES (1067246875800000004, 1067246875800000055, '修改', NULL, 'sys:user:update,sys:dept:list,sys:role:list', 1, NULL, 2, 1067246875800000001, '2025-02-05 15:12:38', 1067246875800000001, '2025-02-05 15:12:38');
INSERT INTO `sys_menu` VALUES (1067246875800000005, 1067246875800000055, '删除', NULL, 'sys:user:delete', 1, NULL, 3, 1067246875800000001, '2025-02-05 15:12:38', 1067246875800000001, '2025-02-05 15:12:38');
INSERT INTO `sys_menu` VALUES (1067246875800000006, 1067246875800000055, '导出', NULL, 'sys:user:export', 1, NULL, 4, 1067246875800000001, '2025-02-05 15:12:39', 1067246875800000001, '2025-02-05 15:12:39');
INSERT INTO `sys_menu` VALUES (1067246875800000025, 0, 'MENU', 'sys/menu', NULL, 0, 'icon-unorderedlist', 1, 1067246875800000001, '2025-02-05 15:12:39', 1067246875800000001, '2025-03-02 21:55:50');
INSERT INTO `sys_menu` VALUES (1067246875800000026, 1067246875800000025, '查看', NULL, 'sys:menu:list,sys:menu:info', 1, NULL, 0, 1067246875800000001, '2025-02-05 15:12:39', 1067246875800000001, '2025-02-05 15:12:39');
INSERT INTO `sys_menu` VALUES (1067246875800000027, 1067246875800000025, '新增', NULL, 'sys:menu:save', 1, NULL, 1, 1067246875800000001, '2025-02-05 15:12:39', 1067246875800000001, '2025-02-05 15:12:39');
INSERT INTO `sys_menu` VALUES (1067246875800000028, 1067246875800000025, '修改', NULL, 'sys:menu:update', 1, NULL, 2, 1067246875800000001, '2025-02-05 15:12:39', 1067246875800000001, '2025-02-05 15:12:39');
INSERT INTO `sys_menu` VALUES (1067246875800000029, 1067246875800000025, '删除', NULL, 'sys:menu:delete', 1, NULL, 3, 1067246875800000001, '2025-02-05 15:12:39', 1067246875800000001, '2025-02-05 15:12:39');
INSERT INTO `sys_menu` VALUES (1067246875800000036, 1067246875800000030, '暂停', NULL, 'sys:schedule:pause', 1, NULL, 4, 1067246875800000001, '2025-02-05 15:12:39', 1067246875800000001, '2025-02-05 15:12:39');
INSERT INTO `sys_menu` VALUES (1067246875800000037, 1067246875800000030, '恢复', NULL, 'sys:schedule:resume', 1, NULL, 5, 1067246875800000001, '2025-02-05 15:12:39', 1067246875800000001, '2025-02-05 15:12:39');
INSERT INTO `sys_menu` VALUES (1067246875800000038, 1067246875800000030, '立即执行', NULL, 'sys:schedule:run', 1, NULL, 6, 1067246875800000001, '2025-02-05 15:12:39', 1067246875800000001, '2025-02-05 15:12:39');
INSERT INTO `sys_menu` VALUES (1067246875800000039, 1067246875800000030, '日志列表', NULL, 'sys:schedule:log', 1, NULL, 7, 1067246875800000001, '2025-02-05 15:12:40', 1067246875800000001, '2025-02-05 15:12:40');
INSERT INTO `sys_menu` VALUES (1067246875800000046, 0, 'LOGS', NULL, NULL, 0, 'icon-container', 6, 1067246875800000001, '2025-02-05 15:12:40', 1067246875800000001, '2025-03-02 21:57:15');
INSERT INTO `sys_menu` VALUES (1067246875800000048, 1067246875800000046, 'LOG IN', 'sys/log-login', 'sys:log:login', 0, 'icon-filedone', 0, 1067246875800000001, '2025-02-05 15:12:40', 1067246875800000001, '2025-03-01 21:50:40');
INSERT INTO `sys_menu` VALUES (1067246875800000049, 1067246875800000046, 'OPERATION', 'sys/log-operation', 'sys:log:operation', 0, 'icon-solution', 1, 1067246875800000001, '2025-02-05 15:12:40', 1067246875800000001, '2025-03-01 21:50:47');
INSERT INTO `sys_menu` VALUES (1067246875800000050, 1067246875800000046, 'ERROR', 'sys/log-error', 'sys:log:error', 0, 'icon-file-exception', 2, 1067246875800000001, '2025-02-05 15:12:40', 1067246875800000001, '2025-03-01 21:50:53');
INSERT INTO `sys_menu` VALUES (1067246875800000055, 0, 'USERS', 'sys/user', NULL, 0, 'icon-user', 0, 1067246875800000001, '2025-02-05 15:12:40', 1067246875800000001, '2025-03-01 21:52:15');
INSERT INTO `sys_menu` VALUES (1067246875800000056, 1067246875800000055, '查看', NULL, 'sys:user:page,sys:user:info', 1, NULL, 0, 1067246875800000001, '2025-02-05 15:12:40', 1067246875800000001, '2025-02-05 15:12:40');
INSERT INTO `sys_menu` VALUES (1895796272565297154, 0, 'REVIEW', 'demo/discuss', NULL, 0, 'icon-meh', 4, 1067246875800000001, '2025-03-01 19:38:52', 1067246875800000001, '2025-03-02 21:57:52');
INSERT INTO `sys_menu` VALUES (1895796272565297155, 1895796272565297154, '查看', NULL, 'demo:discuss:page,demo:discuss:info', 1, NULL, 0, 1067246875800000001, '2025-03-01 19:38:52', 1067246875800000001, '2025-03-01 19:38:52');
INSERT INTO `sys_menu` VALUES (1895796272565297156, 1895796272565297154, '新增', NULL, 'demo:discuss:save', 1, NULL, 1, 1067246875800000001, '2025-03-01 19:38:52', 1067246875800000001, '2025-03-01 19:38:52');
INSERT INTO `sys_menu` VALUES (1895796272565297157, 1895796272565297154, '修改', NULL, 'demo:discuss:update', 1, NULL, 2, 1067246875800000001, '2025-03-01 19:38:52', 1067246875800000001, '2025-03-01 19:38:52');
INSERT INTO `sys_menu` VALUES (1895796272565297158, 1895796272565297154, '删除', NULL, 'demo:discuss:delete', 1, NULL, 3, 1067246875800000001, '2025-03-01 19:38:52', 1067246875800000001, '2025-03-01 19:38:52');
INSERT INTO `sys_menu` VALUES (1895796272565297159, 1895796272565297154, '导出', NULL, 'demo:discuss:export', 1, NULL, 4, 1067246875800000001, '2025-03-01 19:38:52', 1067246875800000001, '2025-03-01 19:38:52');
INSERT INTO `sys_menu` VALUES (1895796272724680705, 0, 'REPORT', 'demo/report', NULL, 0, 'icon-trademark-circle-fil', 5, 1067246875800000001, '2025-03-01 19:39:01', 1067246875800000001, '2025-03-02 21:58:11');
INSERT INTO `sys_menu` VALUES (1895796272724680706, 1895796272724680705, '查看', NULL, 'demo:report:page,demo:report:info', 1, NULL, 0, 1067246875800000001, '2025-03-01 19:39:01', 1067246875800000001, '2025-03-01 19:39:01');
INSERT INTO `sys_menu` VALUES (1895796272724680707, 1895796272724680705, '新增', NULL, 'demo:report:save', 1, NULL, 1, 1067246875800000001, '2025-03-01 19:39:01', 1067246875800000001, '2025-03-01 19:39:01');
INSERT INTO `sys_menu` VALUES (1895796272724680708, 1895796272724680705, '修改', NULL, 'demo:report:update', 1, NULL, 2, 1067246875800000001, '2025-03-01 19:39:01', 1067246875800000001, '2025-03-01 19:39:01');
INSERT INTO `sys_menu` VALUES (1895796272724680709, 1895796272724680705, '删除', NULL, 'demo:report:delete', 1, NULL, 3, 1067246875800000001, '2025-03-01 19:39:01', 1067246875800000001, '2025-03-01 19:39:01');
INSERT INTO `sys_menu` VALUES (1895796272724680710, 1895796272724680705, '导出', NULL, 'demo:report:export', 1, NULL, 4, 1067246875800000001, '2025-03-01 19:39:01', 1067246875800000001, '2025-03-01 19:39:01');
INSERT INTO `sys_menu` VALUES (1895796272724680716, 0, 'RESTAURANT', 'demo/restaurant', NULL, 0, 'icon-bank-fill', 3, 1067246875800000001, '2025-03-01 19:39:08', 1067246875800000001, '2025-03-02 21:57:31');
INSERT INTO `sys_menu` VALUES (1895796272724680717, 1895796272724680716, '查看', NULL, 'demo:restaurant:page,demo:restaurant:info', 1, NULL, 0, 1067246875800000001, '2025-03-01 19:39:08', 1067246875800000001, '2025-03-01 19:39:08');
INSERT INTO `sys_menu` VALUES (1895796272724680718, 1895796272724680716, '新增', NULL, 'demo:restaurant:save', 1, NULL, 1, 1067246875800000001, '2025-03-01 19:39:08', 1067246875800000001, '2025-03-01 19:39:08');
INSERT INTO `sys_menu` VALUES (1895796272724680719, 1895796272724680716, '修改', NULL, 'demo:restaurant:update', 1, NULL, 2, 1067246875800000001, '2025-03-01 19:39:08', 1067246875800000001, '2025-03-01 19:39:08');
INSERT INTO `sys_menu` VALUES (1895796272724680720, 1895796272724680716, '删除', NULL, 'demo:restaurant:delete', 1, NULL, 3, 1067246875800000001, '2025-03-01 19:39:08', 1067246875800000001, '2025-03-01 19:39:08');
INSERT INTO `sys_menu` VALUES (1895796272724680721, 1895796272724680716, '导出', NULL, 'demo:restaurant:export', 1, NULL, 4, 1067246875800000001, '2025-03-01 19:39:08', 1067246875800000001, '2025-03-01 19:39:08');
INSERT INTO `sys_menu` VALUES (1896197681096069122, 0, 'YELP', 'demo/restaurant-yelp', '', 0, 'icon-apple-fill', 2, 1067246875800000001, '2025-03-02 21:55:31', 1067246875800000001, '2025-03-02 21:56:09');

-- ----------------------------
-- Table structure for sys_oss
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss`  (
  `id` bigint NOT NULL COMMENT 'id',
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'URL地址',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_create_date`(`create_date` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文件上传' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_oss
-- ----------------------------

-- ----------------------------
-- Table structure for sys_params
-- ----------------------------
DROP TABLE IF EXISTS `sys_params`;
CREATE TABLE `sys_params`  (
  `id` bigint NOT NULL COMMENT 'id',
  `param_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '参数编码',
  `param_value` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '参数值',
  `param_type` tinyint UNSIGNED NULL DEFAULT 1 COMMENT '类型   0：系统参数   1：非系统参数',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_param_code`(`param_code` ASC) USING BTREE,
  INDEX `idx_create_date`(`create_date` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '参数管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_params
-- ----------------------------
INSERT INTO `sys_params` VALUES (1067246875800000073, 'CLOUD_STORAGE_CONFIG_KEY', '{\"type\":1,\"qiniuDomain\":\"http://test.oss.renren.io\",\"qiniuPrefix\":\"upload\",\"qiniuAccessKey\":\"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ\",\"qiniuSecretKey\":\"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV\",\"qiniuBucketName\":\"renren-oss\",\"aliyunDomain\":\"\",\"aliyunPrefix\":\"\",\"aliyunEndPoint\":\"\",\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qcloudBucketName\":\"\"}', 0, '云存储配置信息', 1067246875800000001, '2025-02-05 15:12:41', 1067246875800000001, '2025-02-05 15:12:41');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint NOT NULL COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `dept_id` bigint NULL DEFAULT NULL COMMENT '部门ID',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_dept_id`(`dept_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1895834925999017985, 'Restaurant Manager', '', NULL, 1067246875800000001, '2025-03-01 21:54:04', 1067246875800000001, '2025-03-01 21:54:04');
INSERT INTO `sys_role` VALUES (1895835058941677569, 'Normal User', '', NULL, 1067246875800000001, '2025-03-01 21:54:36', 1067246875800000001, '2025-03-01 21:54:36');

-- ----------------------------
-- Table structure for sys_role_data_scope
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_data_scope`;
CREATE TABLE `sys_role_data_scope`  (
  `id` bigint NOT NULL COMMENT 'id',
  `role_id` bigint NULL DEFAULT NULL COMMENT '角色ID',
  `dept_id` bigint NULL DEFAULT NULL COMMENT '部门ID',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_role_id`(`role_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色数据权限' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_data_scope
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint NOT NULL COMMENT 'id',
  `role_id` bigint NULL DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint NULL DEFAULT NULL COMMENT '菜单ID',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_role_id`(`role_id` ASC) USING BTREE,
  INDEX `idx_menu_id`(`menu_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色菜单关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1895834926066126849, 1895834925999017985, 1895796272565297154, 1067246875800000001, '2025-03-01 21:54:04');
INSERT INTO `sys_role_menu` VALUES (1895834926066126850, 1895834925999017985, 1895796272565297155, 1067246875800000001, '2025-03-01 21:54:04');
INSERT INTO `sys_role_menu` VALUES (1895834926066126851, 1895834925999017985, 1895796272565297156, 1067246875800000001, '2025-03-01 21:54:04');
INSERT INTO `sys_role_menu` VALUES (1895834926066126852, 1895834925999017985, 1895796272565297157, 1067246875800000001, '2025-03-01 21:54:04');
INSERT INTO `sys_role_menu` VALUES (1895834926066126853, 1895834925999017985, 1895796272565297158, 1067246875800000001, '2025-03-01 21:54:04');
INSERT INTO `sys_role_menu` VALUES (1895834926066126854, 1895834925999017985, 1895796272565297159, 1067246875800000001, '2025-03-01 21:54:04');
INSERT INTO `sys_role_menu` VALUES (1895835058950066177, 1895835058941677569, 1895796272565297154, 1067246875800000001, '2025-03-01 21:54:36');
INSERT INTO `sys_role_menu` VALUES (1895835058950066178, 1895835058941677569, 1895796272565297155, 1067246875800000001, '2025-03-01 21:54:36');
INSERT INTO `sys_role_menu` VALUES (1895835058950066179, 1895835058941677569, 1895796272565297156, 1067246875800000001, '2025-03-01 21:54:36');
INSERT INTO `sys_role_menu` VALUES (1895835058958454786, 1895835058941677569, 1895796272565297157, 1067246875800000001, '2025-03-01 21:54:36');
INSERT INTO `sys_role_menu` VALUES (1895835058958454787, 1895835058941677569, 1895796272565297158, 1067246875800000001, '2025-03-01 21:54:36');
INSERT INTO `sys_role_menu` VALUES (1895835058958454788, 1895835058941677569, 1895796272565297159, 1067246875800000001, '2025-03-01 21:54:36');
INSERT INTO `sys_role_menu` VALUES (1895835058958454789, 1895835058941677569, 1895796272724680716, 1067246875800000001, '2025-03-01 21:54:36');
INSERT INTO `sys_role_menu` VALUES (1895835058966843394, 1895835058941677569, 1895796272724680717, 1067246875800000001, '2025-03-01 21:54:36');
INSERT INTO `sys_role_menu` VALUES (1895835058966843395, 1895835058941677569, 1895796272724680718, 1067246875800000001, '2025-03-01 21:54:36');
INSERT INTO `sys_role_menu` VALUES (1895835058966843396, 1895835058941677569, 1895796272724680719, 1067246875800000001, '2025-03-01 21:54:36');
INSERT INTO `sys_role_menu` VALUES (1895835058971037698, 1895835058941677569, 1895796272724680720, 1067246875800000001, '2025-03-01 21:54:36');
INSERT INTO `sys_role_menu` VALUES (1895835058971037699, 1895835058941677569, 1895796272724680721, 1067246875800000001, '2025-03-01 21:54:36');

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user`  (
  `id` bigint NOT NULL COMMENT 'id',
  `role_id` bigint NULL DEFAULT NULL COMMENT '角色ID',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户ID',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_role_id`(`role_id` ASC) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色用户关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint NOT NULL COMMENT 'id',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `real_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `head_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `gender` tinyint UNSIGNED NULL DEFAULT NULL COMMENT '性别   0：男   1：女    2：保密',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `dept_id` bigint NULL DEFAULT NULL COMMENT '部门ID',
  `super_admin` tinyint UNSIGNED NULL DEFAULT NULL COMMENT '超级管理员   0：否   1：是',
  `status` tinyint NULL DEFAULT NULL COMMENT '状态  0：停用   1：正常',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_username`(`username` ASC) USING BTREE,
  INDEX `idx_create_date`(`create_date` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1067246875800000001, 'admin', '$2a$10$012Kx2ba5jzqr9gLlG4MX.bnQJTD9UWqF57XDo2N3.fPtLne02u/m', '管理员', NULL, 0, 'root@renren.io', '13612345678', NULL, 1, 1, 1067246875800000001, '2025-02-05 15:12:38', 1067246875800000001, '2025-02-05 15:12:38');

-- ----------------------------
-- Table structure for sys_user_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token`  (
  `id` bigint NOT NULL COMMENT 'id',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `token` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户token',
  `expire_date` datetime NULL DEFAULT NULL COMMENT '过期时间',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_id`(`user_id` ASC) USING BTREE,
  UNIQUE INDEX `token`(`token` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统用户Token' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_token
-- ----------------------------
INSERT INTO `sys_user_token` VALUES (1887168370688069634, 1067246875800000001, '292896996c068c4157ebc50fd43adf0a', '2025-03-03 06:50:41', '2025-03-02 18:50:41', '2025-02-05 23:56:16');

-- ----------------------------
-- Table structure for tb_token
-- ----------------------------
DROP TABLE IF EXISTS `tb_token`;
CREATE TABLE `tb_token`  (
  `id` bigint NOT NULL COMMENT 'id',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `token` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT 'token',
  `expire_date` datetime NULL DEFAULT NULL COMMENT '过期时间',
  `update_date` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_id`(`user_id` ASC) USING BTREE,
  UNIQUE INDEX `token`(`token` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '用户Token' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_token
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` bigint NOT NULL COMMENT 'id',
  `username` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户名',
  `mobile` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '手机号',
  `password` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '密码',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1067246875900000001, 'admin', 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '2025-02-05 15:12:42');

SET FOREIGN_KEY_CHECKS = 1;
