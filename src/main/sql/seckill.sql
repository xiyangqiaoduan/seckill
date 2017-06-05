/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50537
Source Host           : localhost:3306
Source Database       : seckill

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2017-06-05 14:22:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for seckill
-- ----------------------------
DROP TABLE IF EXISTS `seckill`;
CREATE TABLE `seckill` (
  `seckill_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品库存ID',
  `name` varchar(20) NOT NULL COMMENT '商品名称',
  `number` int(11) NOT NULL COMMENT '商品数量',
  `start_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '开始时间',
  `end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '结束时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`seckill_id`),
  KEY `idx_start_time` (`start_time`),
  KEY `idx_end_time` (`end_time`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1005 DEFAULT CHARSET=utf8 COMMENT='秒杀表';

-- ----------------------------
-- Records of seckill
-- ----------------------------
INSERT INTO `seckill` VALUES ('1000', '1000元秒杀iphone6', '982', '2016-05-28 00:00:00', '2016-05-31 00:00:00', '2016-05-27 13:08:00');
INSERT INTO `seckill` VALUES ('1001', '500元秒杀Ipad2', '988', '2016-05-25 00:00:00', '2016-06-01 00:00:00', '2016-05-30 10:11:30');
INSERT INTO `seckill` VALUES ('1002', '300元秒杀小米4', '1000', '2016-05-30 00:00:00', '2016-06-01 00:00:00', '2016-05-27 13:09:13');
INSERT INTO `seckill` VALUES ('1003', '700元秒杀华为P9', '1000', '2016-05-31 00:00:00', '2016-06-02 00:00:00', '2016-05-27 13:09:19');
INSERT INTO `seckill` VALUES ('1004', '50元秒杀100元话费', '1000', '2016-05-31 00:00:00', '2016-06-02 00:00:00', '2016-05-27 13:09:26');

-- ----------------------------
-- Table structure for success_killed
-- ----------------------------
DROP TABLE IF EXISTS `success_killed`;
CREATE TABLE `success_killed` (
  `seckill_id` bigint(20) NOT NULL COMMENT '秒杀商品ID',
  `user_phone` bigint(20) NOT NULL COMMENT '用户手机号',
  `state` tinyint(4) NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`seckill_id`,`user_phone`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of success_killed
-- ----------------------------
INSERT INTO `success_killed` VALUES ('1001', '18611111111', '0', '2016-05-27 20:18:28');

-- ----------------------------
-- Procedure structure for execute_seckill
-- ----------------------------
DROP PROCEDURE IF EXISTS `execute_seckill`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `execute_seckill`(
	in v_seckill_id bigint,in v_phone bigint,in v_kill_time timestamp ,out r_result int 
)
begin 
	declare insert_count INT  default 0;
	start  transaction ;
	-- 插入秒杀信息
	INSERT IGNORE INTO success_killed ( seckill_id,user_phone,create_time) VALUES (v_seckill_id,v_phone,v_kill_time) ;
	-- 获取影响行数
	SELECT ROW_COUNT() INTO insert_count;
	IF (insert_count=0) THEN 
	ROLLBACK;
	SET r_result=-1;
	ELSEIF (insert_count<0) THEN
	ROLLBACK;
	SET r_result=-2;
	ELSE 
		update seckill SET number=number-1 WHERE  seckill_id=v_seckill_id  AND  number>0 AND start_time< v_kill_time  AND end_time > v_kill_time ;
		SELECT ROW_COUNT() INTO insert_count;
		IF  (insert_count=0) THEN 
			ROLLBACK;
			SET r_result=-1;
				
		ELSEIF (insert_count<0)   THEN
			ROLLBACK;
			SET r_result=-2;
		ELSE
			COMMIT;
			SET r_result=1;
		end IF;
	end IF;
end
;;
DELIMITER ;
