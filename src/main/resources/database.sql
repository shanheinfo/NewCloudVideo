CREATE DATABASE IF NOT EXISTS NewCodeVideo CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci';

USE NewCodeVideo;

SET NAMES utf8mb4;

DROP TABLE IF EXISTS `video_user`;
DROP TABLE IF EXISTS `user_follow_relation`;
DROP TABLE IF EXISTS `video_admin`;
DROP TABLE IF EXISTS `video_admin_notice`;
DROP TABLE IF EXISTS `video_img_carousel`;
DROP TABLE IF EXISTS `video_info`;
DROP TABLE IF EXISTS `article_info`;
DROP TABLE IF EXISTS `video_favorite`;
DROP TABLE IF EXISTS `video_combined`;
DROP TABLE IF EXISTS `video_address`;
DROP TABLE IF EXISTS `video_category`;
DROP TABLE IF EXISTS `video_keyword`;
DROP TABLE IF EXISTS `video_comment`;
DROP TABLE IF EXISTS `video_thirty_comment`;
DROP TABLE IF EXISTS `video_view_history`;
DROP TABLE IF EXISTS `video_order_bill_credits`;
DROP TABLE IF EXISTS `video_order_list`;
DROP TABLE IF EXISTS `vide_payment_record`;
DROP TABLE IF EXISTS `payment_configuration`;



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
     `user_fans` int(11) NOT NULL DEFAULT 0 COMMENT '粉丝数',
     `user_follow` int(11) NOT NULL DEFAULT 0 COMMENT '关注数',
     `money_data` decimal(10,2) default 0 NULL COMMENT '余额',
     `user_icon` bigint(20) NULL DEFAULT 1 COMMENT '头像地址id,默认为默认头像地址',
     `user_create_ip` varchar(30) NOT NULL DEFAULT 0 COMMENT '创建时IP',
     `user_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `user_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
     `user_status` TINYINT(1) NOT NULL DEFAULT FALSE COMMENT '用户是否封禁',
     PRIMARY KEY (`id`) USING BTREE,
     UNIQUE (`user_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact COMMENT '用户表';

#----------------------
# 粉丝关注表
#----------------------
CREATE TABLE IF NOT EXISTS `user_follow_relation`(
      `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
      `user_id` varchar(90) NOT NULL COMMENT '用户ID',
      `followed_user_id` varchar(90) NOT NULL COMMENT '被关注者用户ID',
      PRIMARY KEY (`id`),
      INDEX `idx_user_id` (`user_id`),
      INDEX `idx_follower_id` (`followed_user_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact COMMENT '粉丝关注表';


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
# 管理员公告表
#----------------------
CREATE TABLE IF NOT EXISTS `video_admin_notice` (
      `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
      `admin_id` varchar(90) NOT NULL COMMENT '管理员ID',
      `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '公告标题',
      `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '公告内容',
      `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
      `is_top` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否顶置 0: 否，1: 是',
      `is_expired` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否过期 0: 否，1: 是',
      `is_homepage` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否首页 0: 否，1: 是',
      PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact COMMENT '管理员公告表';


#----------------------
# 图片地址表
#----------------------
CREATE TABLE IF NOT EXISTS `video_img_file`(
        `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键Id',
        `img_id` bigint(20) UNSIGNED NOT NULL COMMENT '图片id',
        `img_addr` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '图片地址',
        PRIMARY KEY (`id`) USING BTREE,
        UNIQUE (`img_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact COMMENT '图片表';

#----------------------
# 图片表
#----------------------
CREATE TABLE IF NOT EXISTS `video_img_data`(
        `img_id` bigint(20) UNSIGNED NOT NULL COMMENT '图片id',
        `user_id` varchar(90) NOT NULL COMMENT '发布图片的用户id',
        `img_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间',
        UNIQUE KEY `user_img_id` (`img_id`,`user_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact COMMENT '图片表';

#----------------------
# 轮播图表
#----------------------
CREATE TABLE IF NOT EXISTS `video_img_carousel` (
          `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
          `img_id` bigint(20) UNSIGNED NOT NULL COMMENT '图片ID',
          PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact COMMENT '轮播图表';

#----------------------
# 视频表
#----------------------
CREATE TABLE IF NOT EXISTS `video_info` (
        `video_id` varchar(450) NOT NULL COMMENT '视频ID',
        `user_id` varchar(90) NOT NULL COMMENT '上传者用户ID',
        `video_name` varchar(2048) NOT NULL COMMENT '视频名称',
        `video_description` text NOT NULL COMMENT '视频描述',
        `video_address_id` bigint(20) UNSIGNED NULL COMMENT '视频地址id',
        `cover_img_id` bigint(20) UNSIGNED NOT NULL COMMENT '封面图片ID,没有就以视频第一帧为封面',
        `video_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间',
        `is_combined` TINYINT(1) NOT NULL DEFAULT FALSE COMMENT '是否是组合表',
        `combined_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '组合id，相同组合就是同一个组合Id，非组合就为 0 ',
        `keywords_id` varchar(450) NOT NULL COMMENT '标签id用,分隔开',
        `category_id` varchar(450) NOT NULL COMMENT '分类id',
        UNIQUE (`video_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='视频信息表';

#----------------------
# 图文表
#----------------------
CREATE TABLE IF NOT EXISTS `article_info` (
      `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
      `article_id` varchar(450) NOT NULL COMMENT '图文ID',
      `user_id` varchar(90) NOT NULL COMMENT '发布者用户ID',
      `article_title` varchar(2048) NOT NULL COMMENT '图文标题',
      `article_content` text NOT NULL COMMENT '图文内容',
      `cover_img_id` bigint(20) UNSIGNED NOT NULL COMMENT '封面图片ID',
      `article_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
      `keywords_id` varchar(450) NOT NULL COMMENT '标签ID用，分隔开',
      `category_id` varchar(450) NOT NULL COMMENT '分类ID',
      PRIMARY KEY (`id`),
      UNIQUE (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='图文信息表';

#----------------------
# 收藏表
#----------------------
CREATE TABLE IF NOT EXISTS `video_favorite` (
        `favorite_id` varchar(450) NOT NULL COMMENT '视频ID',
        `favorite_type` tinyint(1) NOT NULL COMMENT '收藏类型(1:视频,2:图文)',
        `user_id` varchar(90) NOT NULL COMMENT '用户ID',
        UNIQUE KEY `user_video_favorite_id` (`favorite_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户收藏视频表';

#----------------------
# 组合表
#----------------------
CREATE TABLE IF NOT EXISTS `video_combined` (
    `combined_id` bigint(20) UNSIGNED NOT NULL COMMENT '组合ID',
    `video_id` varchar(450) NOT NULL COMMENT '视频ID',
    `combined_name` varchar(255) NOT NULL COMMENT '组合名称',
    `combined_description` text NOT NULL COMMENT '组合描述',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    UNIQUE (`video_id`),
    index (`combined_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='视频组合表';

#----------------------
# 视频地址表
#----------------------
CREATE TABLE IF NOT EXISTS `video_address` (
       `video_address_id` bigint(20) UNSIGNED NOT NULL COMMENT '视频地址ID',
       `video_id` varchar(450) NOT NULL COMMENT '视频ID',
       `video_url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '视频地址',
       UNIQUE (`video_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='视频地址表';


#----------------------
# 视频分类表
#----------------------
CREATE TABLE IF NOT EXISTS `video_category` (
        `category_id` varchar(450) NOT NULL COMMENT '分类ID',
        `category_name` varchar(450) NOT NULL COMMENT '分类名称',
        UNIQUE (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='视频分类表';


#----------------------
# 视频关键词表
#----------------------
CREATE TABLE IF NOT EXISTS `video_keyword` (
       `keyword_id` varchar(450) NOT NULL COMMENT '关键词ID',
       `keyword_name` varchar(450) NOT NULL COMMENT '关键词名称',
       UNIQUE (`keyword_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='视频关键词表';

#----------------------
# 一级评论表
#----------------------
CREATE TABLE IF NOT EXISTS `video_comment` (
       `comment_id` bigint(20) UNSIGNED NOT NULL COMMENT '评论ID',
       `video_id` varchar(450) NOT NULL COMMENT '视频ID',
       `user_id` varchar(90) NOT NULL COMMENT '评论用户ID',
       `parent_num` bigint(20) UNSIGNED DEFAULT 0 NULL COMMENT '二级评论数',
       `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评论内容',
       `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
       PRIMARY KEY (`comment_id`),
       KEY `idx_video_id` (`video_id`),
       KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频评论表';

#----------------------
# 二级评论表
#----------------------
CREATE TABLE IF NOT EXISTS `video_thirty_comment` (
       `comment_id` bigint(20) UNSIGNED NOT NULL COMMENT '评论ID',
       `user_id` varchar(90) NOT NULL COMMENT '评论用户ID',
       `parent_id` bigint(20) UNSIGNED DEFAULT NULL COMMENT '父级评论ID，用于支持二级评论',
       `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评论内容',
       `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
       PRIMARY KEY (`comment_id`),
       KEY `idx_user_id` (`user_id`),
       KEY `idx_parent_id` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频评论表';

#----------------------
# 视频记录
#----------------------
CREATE TABLE IF NOT EXISTS `video_view_history` (
        `video_id` varchar(450) NOT NULL COMMENT '视频ID',
        `like_number` bigint(20) UNSIGNED NOT NULL COMMENT '点赞次数',
        `comment_number` bigint(20) UNSIGNED NOT NULL COMMENT '评论数量',
        `history_number` bigint(20) UNSIGNED NOT NULL COMMENT '浏览次数',
        UNIQUE `idx_video_id` (`video_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='视频浏览记录表';


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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact COMMENT '余额流水表';


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



