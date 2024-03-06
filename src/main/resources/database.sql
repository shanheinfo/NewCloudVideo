CREATE DATABASE IF NOT EXISTS NewCodeVideo CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci';

USE NewCodeVideo;

SET NAMES utf8mb4;

DROP TABLE IF EXISTS `video_user`;
DROP TABLE IF EXISTS `video_admin`;
DROP TABLE IF EXISTS `video_file`;
DROP TABLE IF EXISTS `video_data`;
DROP TABLE IF EXISTS `video_info`;
DROP TABLE IF EXISTS `video_category`;
DROP TABLE IF EXISTS `video_tag`;
DROP TABLE IF EXISTS `video_order_bill_recharge`;
DROP TABLE IF EXISTS `video_order_bill_credits`;
DROP TABLE IF EXISTS `video_order_bill_buy`;
DROP TABLE IF EXISTS `video_auction`;
DROP TABLE IF EXISTS `video_tag_relation`;

#----------------------
# 用户表
#----------------------
CREATE TABLE IF NOT EXISTS `video_user`(
     `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键Id',
     `user_id` varchar(90) NOT NULL COMMENT '用户id',
     `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账号',
     `user_nick_name` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '昵称',
     `user_mail` varchar(320) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '邮箱',
     `user_phone` varchar(320) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '手机号',
     `user_pwd` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
     `alipay_user_id` varchar(255) NULL COMMENT '支付宝用户ID',
     `money_data` decimal(10,2) default 0 NULL COMMENT '余额',
     `user_icon` bigint(20) NULL DEFAULT 1 COMMENT '头像地址id,默认为默认头像地址',
     `user_create_ip` varchar(30) NOT NULL DEFAULT 0 COMMENT '创建时IP',
     `user_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `user_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
     `user_status` tinyint(1) DEFAULT 1 COMMENT '用户是否封禁 1 封禁',
     PRIMARY KEY (`id`) USING BTREE,
     UNIQUE (`user_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact COMMENT '用户表';


#----------------------
# 管理员表
#----------------------
CREATE TABLE IF NOT EXISTS `video_admin`(
     `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键Id',
     `user_id` varchar(90) NOT NULL COMMENT '用户id',
     `user_status` tinyint(1) DEFAULT 1 COMMENT '是否封禁 1 封禁',
     `type` int(6) NOT NULL COMMENT '权限级别',
     `admin_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     PRIMARY KEY (`id`) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact COMMENT '管理员表';

#----------------------
# 图片地址表
#----------------------
CREATE TABLE IF NOT EXISTS `video_file`(
        `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键Id',
        `img_id` bigint(20) UNSIGNED NOT NULL COMMENT '图片id',
        `img_addr` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '图片地址',
        PRIMARY KEY (`id`) USING BTREE,
        UNIQUE (`img_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact COMMENT '图片表';

#----------------------
# 图片表
#----------------------
CREATE TABLE IF NOT EXISTS `video_data`(
        `img_id` bigint(20) UNSIGNED NOT NULL COMMENT '图片id',
        `user_id` varchar(90) NOT NULL COMMENT '发布图片的用户id',
        `img_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间',
        UNIQUE KEY `user_img_id` (`img_id`,`user_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact COMMENT '图片表';

#----------------------
# 余额流水表
#----------------------
CREATE TABLE IF NOT EXISTS `video_order_bill_credits`(
         `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键Id',
         `user_id` varchar(90) NOT NULL COMMENT '用户id',
         `bill_credits_data` text NOT NULL COMMENT '余额流水信息',
         `bill_credits_money_data` decimal(10,2) NOT NULL COMMENT '流水金额数据',
         `payment_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
         PRIMARY KEY (`id`) USING BTREE,
         INDEX (`user_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact COMMENT '积分流水表';


CREATE TABLE `video_order_list` (
     `order_id` INT AUTO_INCREMENT PRIMARY KEY COMMENT '订单ID',
     `user_id` varchar(90)  COMMENT '用户ID',
     `order_number` VARCHAR(50) NOT NULL COMMENT '订单号',
     `total_amount` DECIMAL(10,2) NOT NULL COMMENT '订单金额',
     `order_status` VARCHAR(50) NOT NULL COMMENT '订单状态',
     `alipay_trade_no` VARCHAR(255) COMMENT '支付宝交易号',
     `payment_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '支付时间'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact COMMENT='订单表';


CREATE TABLE `vide_payment_record` (
      `payment_id` INT AUTO_INCREMENT PRIMARY KEY COMMENT '支付记录ID',
      `order_id` INT COMMENT '订单ID',
      `payment_type` VARCHAR(50) COMMENT '支付类型',
      `payment_amount` DECIMAL(10,2) COMMENT '支付金额',
      `alipay_trade_no` VARCHAR(255) COMMENT '支付宝交易号',
      `payment_status` VARCHAR(50) COMMENT '支付状态',
      `payment_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '支付时间'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact COMMENT='支付记录表';


CREATE TABLE `payment_configuration` (
     `config_id` INT AUTO_INCREMENT PRIMARY KEY COMMENT '配置ID',
     `app_id` VARCHAR(255) NOT NULL COMMENT '支付宝应用ID',
     `app_private_key` TEXT NOT NULL COMMENT '支付宝应用私钥',
     `alipay_public_key` TEXT NOT NULL COMMENT '支付宝公钥'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支付配置表';



