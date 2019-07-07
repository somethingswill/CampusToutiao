/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50544
 Source Host           : localhost:3306
 Source Schema         : db-campus-toutiao

 Target Server Type    : MySQL
 Target Server Version : 50544
 File Encoding         : 65001

 Date: 21/06/2019 13:43:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_admin
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin`;
CREATE TABLE `tb_admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员用户名',
  `passwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员密码',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '盐',
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员手机号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_admin
-- ----------------------------
INSERT INTO `tb_admin` VALUES (1, 'admin', 'CA9D46AD943B1026ACF574EC3B470D68', '6e6d85', '17777777777');
INSERT INTO `tb_admin` VALUES (2, 'super', 'B48D8233B4776D55A8FED92A5FB855CA', 'fc52d5', '18888888888');

-- ----------------------------
-- Table structure for tb_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE `tb_comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `comment_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '评论内容',
  `article_id` int(11) NULL DEFAULT NULL COMMENT '评论的资讯',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '发表评论的人',
  `like_count` int(11) NULL DEFAULT NULL COMMENT '获赞数',
  `dislike_count` int(11) NULL DEFAULT NULL COMMENT '获踩数',
  `create_date` datetime NULL DEFAULT NULL COMMENT '发表评论的时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 161 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_comment
-- ----------------------------
INSERT INTO `tb_comment` VALUES (23, '棒棒哒', 4, 11, 0, 0, '2019-06-14 11:59:19');
INSERT INTO `tb_comment` VALUES (24, '刷评论了', 4, 11, 0, 0, '2019-06-13 11:59:30');
INSERT INTO `tb_comment` VALUES (25, '占个楼', 4, 11, 0, 0, '2019-06-14 11:59:36');
INSERT INTO `tb_comment` VALUES (26, '沙发', 5, 11, 1, 0, '2019-06-13 11:59:51');
INSERT INTO `tb_comment` VALUES (27, '沙发', 7, 11, 0, 0, '2019-06-14 12:00:09');
INSERT INTO `tb_comment` VALUES (28, '被人抢了一楼', 7, 11, 0, 0, '2019-06-14 12:00:18');
INSERT INTO `tb_comment` VALUES (29, '雄起', 9, 11, 0, 0, '2019-06-13 12:00:31');
INSERT INTO `tb_comment` VALUES (30, '理工大nb', 8, 11, 1, 0, '2019-06-15 12:00:51');
INSERT INTO `tb_comment` VALUES (31, 'lalalalalalalalala', 5, 13, 0, 1, '2019-06-15 12:01:31');
INSERT INTO `tb_comment` VALUES (32, '可以', 4, 13, 0, 0, '2019-06-11 12:02:00');
INSERT INTO `tb_comment` VALUES (33, '毕业了', 6, 13, 1, 0, '2019-06-14 12:02:10');
INSERT INTO `tb_comment` VALUES (34, '要毕业了', 6, 13, 1, 0, '2019-06-14 12:02:21');
INSERT INTO `tb_comment` VALUES (35, '别别别', 6, 13, 0, 0, '2019-06-11 12:02:36');
INSERT INTO `tb_comment` VALUES (36, '靓仔靓女', 10, 13, 1, 0, '2019-06-11 12:03:07');
INSERT INTO `tb_comment` VALUES (37, '主持人', 10, 13, 1, 0, '2019-06-11 12:03:18');
INSERT INTO `tb_comment` VALUES (38, '十大歌手', 7, 14, 1, 0, '2019-06-14 12:03:56');
INSERT INTO `tb_comment` VALUES (39, '评论一下', 10, 14, 1, 0, '2019-06-11 23:15:46');
INSERT INTO `tb_comment` VALUES (40, '皮一下就跟开心', 10, 14, 1, 0, '2019-06-11 23:15:59');
INSERT INTO `tb_comment` VALUES (41, '棒棒哒', 7, 14, 0, 0, '2019-06-11 23:16:17');
INSERT INTO `tb_comment` VALUES (42, '黑客嘛  这是', 5, 14, 0, 0, '2019-06-14 23:16:37');
INSERT INTO `tb_comment` VALUES (43, '不懂不懂', 5, 14, 0, 0, '2019-06-11 23:16:46');
INSERT INTO `tb_comment` VALUES (44, '心理健康辅导尤为重要', 4, 14, 0, 0, '2019-06-11 23:17:20');
INSERT INTO `tb_comment` VALUES (45, '加油  小哥哥们', 9, 14, 0, 0, '2019-06-12 23:17:41');
INSERT INTO `tb_comment` VALUES (46, '老师们也挺厉害的', 8, 14, 0, 0, '2019-06-10 23:17:58');
INSERT INTO `tb_comment` VALUES (47, '又是一年毕业季', 6, 14, 1, 0, '2019-06-10 23:18:19');
INSERT INTO `tb_comment` VALUES (48, '要毕业了', 14, 11, 0, 1, '2019-06-03 18:34:49');
INSERT INTO `tb_comment` VALUES (49, '要离校了', 14, 11, 0, 0, '2019-06-03 22:42:29');
INSERT INTO `tb_comment` VALUES (50, '拜拜了', 14, 11, 1, 0, '2019-06-03 22:42:50');
INSERT INTO `tb_comment` VALUES (51, '走了', 14, 11, 0, 0, '2019-06-03 22:43:08');
INSERT INTO `tb_comment` VALUES (52, '棒棒哒', 15, 17, 0, 0, '2019-06-11 00:51:32');
INSERT INTO `tb_comment` VALUES (53, '11', 15, 17, 0, 0, '2019-06-11 00:51:35');
INSERT INTO `tb_comment` VALUES (54, '222', 15, 17, 0, 0, '2019-06-11 00:51:37');
INSERT INTO `tb_comment` VALUES (55, '333', 15, 17, 0, 0, '2019-06-12 00:51:39');
INSERT INTO `tb_comment` VALUES (56, '777', 14, 17, 0, 0, '2019-06-11 00:51:53');
INSERT INTO `tb_comment` VALUES (57, '11111', 15, 17, 0, 0, '2019-06-12 00:52:21');
INSERT INTO `tb_comment` VALUES (58, '1111', 10, 17, 0, 0, '2019-06-11 00:52:26');
INSERT INTO `tb_comment` VALUES (59, '4444', 10, 17, 0, 0, '2019-06-11 00:52:33');
INSERT INTO `tb_comment` VALUES (60, '1111', 11, 17, 0, 0, '2019-06-11 00:52:38');
INSERT INTO `tb_comment` VALUES (61, '3333', 11, 17, 0, 0, '2019-06-11 00:52:40');
INSERT INTO `tb_comment` VALUES (62, '555', 11, 17, 0, 0, '2019-06-11 00:52:46');
INSERT INTO `tb_comment` VALUES (63, '6666', 7, 17, 0, 0, '2019-06-11 00:53:16');
INSERT INTO `tb_comment` VALUES (64, '1111', 7, 12, 0, 0, '2019-06-11 00:53:52');
INSERT INTO `tb_comment` VALUES (65, '2222', 7, 12, 0, 0, '2019-06-11 00:53:54');
INSERT INTO `tb_comment` VALUES (66, '555', 7, 12, 0, 0, '2019-06-11 00:53:56');
INSERT INTO `tb_comment` VALUES (67, '5555', 9, 12, 0, 0, '2019-06-11 00:54:03');
INSERT INTO `tb_comment` VALUES (68, '6666', 7, 12, 0, 0, '2019-06-11 00:54:10');
INSERT INTO `tb_comment` VALUES (69, '7777', 5, 12, 0, 0, '2019-06-16 00:54:17');
INSERT INTO `tb_comment` VALUES (70, '6666', 8, 12, 0, 0, '2019-06-16 00:54:29');
INSERT INTO `tb_comment` VALUES (71, '棒棒哒', 4, 12, 1, 0, '2019-06-16 00:55:09');
INSERT INTO `tb_comment` VALUES (72, '优秀优秀', 10, 12, 0, 0, '2019-06-16 00:55:41');
INSERT INTO `tb_comment` VALUES (73, '666', 10, 12, 0, 0, '2019-06-13 00:55:47');
INSERT INTO `tb_comment` VALUES (74, '777', 8, 12, 0, 0, '2019-06-13 00:56:04');
INSERT INTO `tb_comment` VALUES (75, '8888', 8, 12, 0, 0, '2019-06-13 00:56:13');
INSERT INTO `tb_comment` VALUES (76, '7777', 15, 12, 0, 0, '2019-06-11 00:56:31');
INSERT INTO `tb_comment` VALUES (77, '6666', 11, 12, 0, 0, '2019-06-13 00:56:40');
INSERT INTO `tb_comment` VALUES (78, '大撒大撒的撒撒啊', 10, 12, 0, 0, '2019-06-13 00:56:48');
INSERT INTO `tb_comment` VALUES (79, '666', 13, 12, 0, 0, '2019-06-13 00:56:58');
INSERT INTO `tb_comment` VALUES (80, '优秀优秀优秀优秀', 13, 12, 0, 0, '2019-06-13 00:57:11');
INSERT INTO `tb_comment` VALUES (81, '大撒大撒阿三', 10, 12, 0, 0, '2019-06-13 00:57:19');
INSERT INTO `tb_comment` VALUES (82, '优秀校友', 12, 12, 0, 0, '2019-06-13 00:58:16');
INSERT INTO `tb_comment` VALUES (83, '的撒德萨克和拉萨机了', 9, 12, 0, 0, '2019-06-16 00:59:07');
INSERT INTO `tb_comment` VALUES (84, '的萨拉和就和斯大林和，撒大', 5, 15, 0, 0, '2019-06-13 01:00:18');
INSERT INTO `tb_comment` VALUES (85, '信息安全', 5, 15, 0, 0, '2019-06-13 01:00:32');
INSERT INTO `tb_comment` VALUES (86, '666', 5, 15, 0, 0, '2019-06-13 01:00:45');
INSERT INTO `tb_comment` VALUES (87, '三大亨金卡凯撒喝了几口', 14, 15, 0, 0, '2019-06-11 01:00:54');
INSERT INTO `tb_comment` VALUES (88, '的撒谎就和课件撒', 8, 15, 0, 0, '2019-06-11 01:01:04');
INSERT INTO `tb_comment` VALUES (89, '的还是卡激活看啥看机会看', 8, 15, 0, 0, '2019-06-11 01:01:08');
INSERT INTO `tb_comment` VALUES (90, '的撒谎看活动是卡激活开始卡就', 10, 15, 0, 0, '2019-06-13 01:01:19');
INSERT INTO `tb_comment` VALUES (91, '的撒谎啥的灵魂刻录机撒谎刻录机', 8, 15, 0, 0, '2019-06-16 01:01:29');
INSERT INTO `tb_comment` VALUES (92, '的撒谎就来看傻了理解', 7, 15, 0, 0, '2019-06-13 01:01:37');
INSERT INTO `tb_comment` VALUES (93, '的撒谎可就是打很厉害立刻撒旦', 6, 15, 0, 0, '2019-06-13 01:01:58');
INSERT INTO `tb_comment` VALUES (94, '的撒谎就离开的撒娇了尽量', 14, 15, 0, 0, '2019-06-16 01:02:30');
INSERT INTO `tb_comment` VALUES (95, '都不撒谎的卡号是和课件撒', 7, 15, 0, 0, '2019-06-13 01:02:37');
INSERT INTO `tb_comment` VALUES (96, '的洒家离开就撒了大家来看', 7, 15, 0, 0, '2019-06-13 01:02:45');
INSERT INTO `tb_comment` VALUES (97, '的撒娇了就临时搭建了看', 15, 15, 0, 0, '2019-06-13 01:02:58');
INSERT INTO `tb_comment` VALUES (98, '的吉萨垃圾  地方撒的就撒开了就了', 14, 15, 0, 0, '2019-06-12 01:03:07');
INSERT INTO `tb_comment` VALUES (99, '的洒家离开机撒赖科技雷克萨大家来看', 13, 15, 0, 0, '2019-06-12 01:03:15');
INSERT INTO `tb_comment` VALUES (100, '很多手机卡还款沙迪克会立刻洒家好的', 10, 15, 0, 0, '2019-06-12 01:03:23');
INSERT INTO `tb_comment` VALUES (101, '的撒谎客户的萨拉和，加萨迪克很快就', 10, 15, 0, 0, '2019-06-12 01:06:08');
INSERT INTO `tb_comment` VALUES (102, '加油加油', 9, 15, 0, 0, '2019-06-12 01:06:24');
INSERT INTO `tb_comment` VALUES (103, '大厦借款还款就等哈看手机', 9, 15, 0, 0, '2019-06-12 01:06:30');
INSERT INTO `tb_comment` VALUES (104, '的撒谎就的和德萨克就和空间的撒谎空间还是大框架', 11, 15, 0, 0, '2019-06-13 01:07:20');
INSERT INTO `tb_comment` VALUES (105, '的撒赖科技留啥了就立刻撒旦', 9, 15, 0, 0, '2019-06-16 01:07:28');
INSERT INTO `tb_comment` VALUES (106, '是的撒谎老客户老师打的洒家离开', 9, 16, 0, 0, '2019-06-16 01:10:38');
INSERT INTO `tb_comment` VALUES (107, '大厦考虑就朗科大厦离开', 9, 16, 0, 0, '2019-06-11 01:10:46');
INSERT INTO `tb_comment` VALUES (108, '的撒娇了看觉得撒赖科技了', 9, 16, 0, 0, '2019-06-12 01:11:06');
INSERT INTO `tb_comment` VALUES (109, '的就撒开就离开的撒娇了', 15, 16, 0, 0, '2019-06-13 01:11:13');
INSERT INTO `tb_comment` VALUES (110, '的撒赖科技离开大家撒赖科技', 8, 16, 0, 0, '2019-06-12 01:11:24');
INSERT INTO `tb_comment` VALUES (111, '爱上不该v大厦感觉很干净的撒', 8, 16, 0, 0, '2019-06-12 01:11:30');
INSERT INTO `tb_comment` VALUES (112, '大厦空间和课件撒很快就很多看啥', 7, 16, 0, 0, '2019-06-12 01:11:56');
INSERT INTO `tb_comment` VALUES (113, '恶恶哦i额u我uoi', 7, 16, 0, 0, '2019-06-10 01:12:05');
INSERT INTO `tb_comment` VALUES (114, '德萨克看了', 7, 16, 0, 0, '2019-06-10 01:12:15');
INSERT INTO `tb_comment` VALUES (115, '毕业毕业毕业', 6, 16, 0, 0, '2019-06-10 01:12:28');
INSERT INTO `tb_comment` VALUES (116, '的啥计划啊撒旦记录看尽量', 5, 16, 0, 0, '2019-06-16 01:12:45');
INSERT INTO `tb_comment` VALUES (117, '入围i偶肉热我金额为', 5, 16, 0, 0, '2019-06-16 01:12:53');
INSERT INTO `tb_comment` VALUES (118, '的撒娇回来沙龙会了的撒离开', 6, 16, 0, 0, '2019-06-16 01:13:11');
INSERT INTO `tb_comment` VALUES (119, '的撒娇看了吉林省打卡记录看大家撒赖科技了的撒', 11, 16, 0, 0, '2019-06-16 01:13:30');
INSERT INTO `tb_comment` VALUES (120, '嗯我去我去哦哦我去', 11, 16, 0, 0, '2019-06-16 01:13:40');
INSERT INTO `tb_comment` VALUES (121, '主持人主持人', 10, 16, 0, 0, '2019-06-16 01:14:33');
INSERT INTO `tb_comment` VALUES (122, '的洒家离开就拉萨机了了', 10, 16, 0, 0, '2019-06-16 01:14:43');
INSERT INTO `tb_comment` VALUES (123, 'dsajk ujdlskajl dsa', 10, 18, 0, 0, '2019-06-16 01:34:02');
INSERT INTO `tb_comment` VALUES (124, '的洒家离开就的拉萨机了看的撒', 10, 18, 0, 0, '2019-06-16 01:34:06');
INSERT INTO `tb_comment` VALUES (125, '的撒谎就看和大厦空间', 10, 18, 0, 0, '2019-06-16 01:34:09');
INSERT INTO `tb_comment` VALUES (126, '的洒家离开大家沙拉看将来打算', 15, 18, 0, 0, '2019-06-16 01:34:23');
INSERT INTO `tb_comment` VALUES (127, '的撒谎空间和看几点睡啊', 15, 18, 0, 0, '2019-06-16 01:34:26');
INSERT INTO `tb_comment` VALUES (128, '的啥好看撒谎空间', 7, 18, 0, 0, '2019-06-16 01:34:34');
INSERT INTO `tb_comment` VALUES (129, '9999', 7, 18, 0, 0, '2019-06-16 01:34:38');
INSERT INTO `tb_comment` VALUES (130, '的啥活动会计师啊很快就会的', 5, 18, 0, 0, '2019-06-16 01:34:46');
INSERT INTO `tb_comment` VALUES (131, '大厦空间和看就的撒谎空间', 5, 18, 0, 0, '2019-06-16 01:34:55');
INSERT INTO `tb_comment` VALUES (132, '单杀就开了大厦考虑就回来看几点睡啊', 4, 18, 0, 0, '2019-06-16 01:35:17');
INSERT INTO `tb_comment` VALUES (133, '6666', 4, 18, 0, 0, '2019-06-16 01:35:23');
INSERT INTO `tb_comment` VALUES (134, '666', 6, 18, 0, 0, '2019-06-16 01:35:33');
INSERT INTO `tb_comment` VALUES (135, '打个撒基金会的撒', 6, 18, 0, 0, '2019-06-16 01:35:39');
INSERT INTO `tb_comment` VALUES (136, '的撒谎空间和看撒谎可就是打', 14, 18, 0, 0, '2019-06-16 01:35:47');
INSERT INTO `tb_comment` VALUES (137, '其他委屈有图有武器', 14, 18, 0, 0, '2019-06-16 01:35:53');
INSERT INTO `tb_comment` VALUES (138, 'qqqq', 14, 18, 1, 0, '2019-06-16 01:35:57');
INSERT INTO `tb_comment` VALUES (139, '大厦及活动空间安徽省空间', 11, 18, 0, 0, '2019-06-16 01:36:06');
INSERT INTO `tb_comment` VALUES (140, '的撒根据海关大厦', 11, 18, 0, 0, '2019-06-16 01:36:09');
INSERT INTO `tb_comment` VALUES (141, 'dsajlkdjsalkj lkdas', 15, 19, 0, 0, '2019-06-16 01:36:46');
INSERT INTO `tb_comment` VALUES (142, '的洒家离开就了洒进来就了的凯撒', 15, 19, 0, 0, '2019-06-15 01:36:51');
INSERT INTO `tb_comment` VALUES (143, '而无球饿哦我i去', 15, 19, 0, 0, '2019-06-16 01:36:55');
INSERT INTO `tb_comment` VALUES (144, '的撒谎的撒谎看解决克里斯蒂安', 9, 19, 0, 0, '2019-06-16 01:37:03');
INSERT INTO `tb_comment` VALUES (145, '大厦空间和的凯撒就会立刻', 9, 19, 0, 0, '2019-06-16 01:37:07');
INSERT INTO `tb_comment` VALUES (146, '的撒谎就看和凯撒就', 8, 19, 0, 0, '2019-06-16 01:37:46');
INSERT INTO `tb_comment` VALUES (147, '德萨克就很快就安徽省夸奖哈看就', 8, 19, 0, 0, '2019-06-16 01:37:50');
INSERT INTO `tb_comment` VALUES (148, '的撒谎看见撒旦好看就和空间的和凯撒就', 5, 19, 0, 0, '2019-06-16 01:38:03');
INSERT INTO `tb_comment` VALUES (149, '大厦空间和看就大厦空间和课件撒', 5, 19, 0, 0, '2019-06-15 01:38:07');
INSERT INTO `tb_comment` VALUES (150, '大厦空间和看吉萨很快就的撒', 6, 19, 0, 0, '2019-06-15 01:38:38');
INSERT INTO `tb_comment` VALUES (151, '的韩国撒印度空军撒', 6, 19, 0, 0, '2019-06-16 01:38:41');
INSERT INTO `tb_comment` VALUES (152, 'dsahkj ashdkh ksdja d', 6, 20, 0, 0, '2019-06-16 01:39:02');
INSERT INTO `tb_comment` VALUES (153, 'sah slkahl dsa大家撒赖科技了', 6, 20, 0, 0, '2019-06-15 01:39:07');
INSERT INTO `tb_comment` VALUES (154, '的撒谎空间德萨克和撒尽快', 8, 20, 0, 0, '2019-06-16 01:39:16');
INSERT INTO `tb_comment` VALUES (155, '而无球额我穷', 8, 20, 0, 0, '2019-06-16 01:39:21');
INSERT INTO `tb_comment` VALUES (156, '的撒谎就看和开啥会看见获得凯萨就', 5, 20, 0, 0, '2019-06-16 01:39:57');
INSERT INTO `tb_comment` VALUES (157, '单杀了回来看见的撒哈拉', 5, 20, 0, 0, '2019-06-16 01:40:00');
INSERT INTO `tb_comment` VALUES (158, '的撒谎计划萨克计划的撒娇看', 7, 20, 0, 0, '2019-06-16 01:40:22');
INSERT INTO `tb_comment` VALUES (159, '大厦考虑就了撒娇离开', 7, 20, 0, 0, '2019-06-16 01:40:27');
INSERT INTO `tb_comment` VALUES (160, '时间快到了接口', 16, 20, 0, 0, '2019-06-16 02:01:22');

-- ----------------------------
-- Table structure for tb_news
-- ----------------------------
DROP TABLE IF EXISTS `tb_news`;
CREATE TABLE `tb_news`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `news_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资讯标题',
  `news_content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '资讯内容',
  `news_imgs` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资讯中的图片',
  `like_count` int(11) NULL DEFAULT NULL COMMENT '资讯获赞数',
  `dislike_count` int(11) NULL DEFAULT NULL COMMENT '资讯获踩数',
  `comment_count` int(11) NULL DEFAULT NULL COMMENT '资讯评论数',
  `create_date` datetime NULL DEFAULT NULL COMMENT '资讯发布时间',
  `category_id` int(11) NULL DEFAULT NULL COMMENT '资讯类型',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '发布资讯的用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_news
-- ----------------------------
INSERT INTO `tb_news` VALUES (4, '我校举办第二届大学生心理健康微课大赛决赛', '<p><img src=\"api/image?name=NEWS256e75b232ab42ff8a586163b6079fb5.jpg\" alt=\"心理健康违抗大赛决赛\" width=\"720\" height=\"480\" /></p>\n<p>&nbsp; &nbsp; &nbsp; &nbsp;为深入贯彻全国教育大会精神，落实立德树人根本任务，推进《高等学校学生心理健康教育指导纲要》有关精神落地生根，5月28日下午，由学生工作部（处） 、心理健康教育与咨询中心主办，应急管理学院承办的第二届大学生心理健康微课大赛在文综楼510教室举行。</p>\n<p>&nbsp; &nbsp; &nbsp; &nbsp;大赛以&ldquo;心声音，心健康&rdquo;为主题，采取&ldquo;命题+自选&rdquo;的方式，要求在5分钟内讲授一堂与心理健康相关的主题微课。经过前期选拔，共有来自不同学院的32支队伍入围决赛。</p>\n<p>&nbsp; &nbsp; &nbsp; &nbsp;比赛过程中，参赛者结合精彩的PPT演示，分析了大学生心理健康可能存在的问题，并提出了相关解决建议，为在场观众带来了一场滋养心灵的视听盛宴。其中，数信学院杨晨曦以生命教育为主题，就生命的意义展开阐述，告诫学生接受生命的不完美，阳光才能照进心灵；应急管理学院李可欣将爱情的解读融入辩证元素，对健康恋爱观进行梳理整合，向大家展示了积极向上的恋爱观本来的模样；工商学院王焕彩围绕大学生人际交往展开分析，深入剖析大学生在现实交往中存在心理障碍，鼓励同学们敞开心扉、真诚待人；计算机学院陈蓓乐以情绪为轴线，联系现实感悟，就情绪的管理、调节等给予方法与经验借鉴，呼吁同学们找准应对不良情绪的方法，把握好情绪的方向盘。</p>\n<p>&nbsp; &nbsp; &nbsp; &nbsp;评委们本着公平公正原则，结合教学设计、教学效果、形式创意等多方面进行综合评分。最终，应急管理学院李可欣等3组获一等奖，电气学院楚玉建等6组获二等奖，土木学院秦贺文等9组获三等奖。</p>\n<p>&nbsp; &nbsp; &nbsp; &nbsp;见微知著，小课堂大教学。比赛通过新媒体平台，借助微课形式传播心理健康知识，引领学生树立正确的审美观念、陶冶高尚的道德情操、塑造美好心灵，对切实加强我校心理健康教育工作、进一步提升心理育人质量具有积极推进作用，同时也为我校培养德智体美劳全面发展的社会主义建设者和接班人提供有力支持。</p>', 'api/image?name=NEWS256e75b232ab42ff8a586163b6079fb5.jpg', 2, 0, 8, '2019-05-30 02:36:16', 1, 11);
INSERT INTO `tb_news` VALUES (5, '我校举办第三届信息安全对抗赛', '<p><img src=\"api/image?name=NEWS66e373a19a884e14b4e766ce2a1a944e.jpg\" alt=\"\" width=\"720\" height=\"480\" /></p>\n<p>&nbsp; &nbsp; &nbsp; &nbsp;5月26日，由校团委、教务处、计算机学院联合主办，大学生计算机协会承办的第三届信息安全对抗赛在计算机学院403室和408室举行，共有42支队伍126名同学参加比赛。</p>\n<p>&nbsp; &nbsp; &nbsp; &nbsp;竞赛平台由协会成员自主开发及维护，包括答题闯关和攻防夺旗赛两个环节，评分系统自动判定答案并积分，答题闯关成功队伍可晋级到攻防夺旗赛。答题闯关比赛中，参赛队员争分夺秒，不断探寻最佳的解题方法，尝试闯过层层关卡，最终有20支队伍成功晋级。攻防夺旗赛环节，主要采取阵地夺旗、占领高地等模式进行攻防比拼。各队伍运用灵活的战术，默契的配合，通过对指定的服务器进行入侵攻击达到获取旗子，并在攻上高地后防御其他队伍的攻击，充分展示了各位选手的个人水平和小组的协同合作能力。最终经过激烈的角逐，屠向阳、郭永航、段世龙、穆耀东所在队伍获得一等奖；蒋志斌、马俊博、席文楷、周允等人所在队伍获得二等奖；李康、张嘉伟、刘爽爽、李小旭等人所在队伍获得三等奖。</p>\n<p>&nbsp; &nbsp; &nbsp; &nbsp;竞赛旨在培养一批具备信息安全素养的优秀学生，提升同学们的信息安全意识、普及信息安全知识，同时增强同学们信息安全技术的实践能力，开创了青年一代在网络信息安全工作方面的新局面。</p>', 'api/image?name=NEWS66e373a19a884e14b4e766ce2a1a944e.jpg', 4, 0, 16, '2019-05-30 13:35:33', 2, 12);
INSERT INTO `tb_news` VALUES (6, '学校召开2019届毕业生离校工作协调会', '<p><img src=\"api/image?name=NEWS9a4d5efd458c45529201de4b4f82abf4.jpg\" alt=\"\" width=\"935\" height=\"595\" /></p>\n<p>&nbsp; &nbsp; &nbsp; &nbsp;为确保2019届毕业生安全、文明、有序离校，5月28日下午，学校2019届毕业生离校工作协调会在力行楼一楼会议室召开。校党委副书记安士伟、副校长赵俊伟出席会议。校长办公室、学生工作部（处）、研究生院、教务处、党委组织部、宣传部、财务处、招就处、国际教育学院、后勤管理处（后勤集团）、保卫处（保卫部、武装部）、校医院等相关单位负责人和各学院党委副书记参加了会议。会议由安士伟主持。</p>\n<p>&nbsp; &nbsp; &nbsp; &nbsp;会上，学生工作部（处）黄保金首先对《关于做好2019届毕业生工作的通知》进行了梳理和解读，围绕毕业生离校工作的主要任务及职责分工、毕业工作安排进度、手续办理以及总体要求进行了详细部署；随后，与会人员结合本部门工作实际，就毕业生离校工作实际操作中的相关问题提出了具体的意见和建议。</p>\n<p><img src=\"api/image?name=NEWS32a376a97d354027af9e70cc7c092e0b.jpg\" alt=\"\" width=\"720\" height=\"480\" /></p>\n<p>&nbsp; &nbsp; &nbsp; &nbsp;安士伟作总结讲话，他高度评价了2019届毕业生离校工作的前期准备和工作安排，就做好毕业生离校工作提出四点要求：一要高度重视。他指出，毕业生离校工作是我们全程教育育人的重要环节，要牢固树立全员、全过程、全方位育人的教育理念，高度重视毕业生离校工作，给学生留下美好印象，让学生对母校深厚的感情继续升华。二要精心策划。各部门及相关单位要各司其职、各负其责，精心设计各项具体环节，做有温度的毕业生工作，以细致、贴心、热情、周到的工作搭起毕业生情感的桥梁，让学生感受到满满的爱。三要倾入感情。各单位可充分利用微信、微博等积极营造一个温暖、和谐、有爱的离校氛围，要让每一位毕业生怀揣温暖、感恩离开学校。四要确保安全。安全问题重于泰山，尤其是在毕业生离校期间，要多关注和了解毕业生在毕业时期的心理情绪变化，及时进行疏导，排除一切不稳定因素及安全隐患，确保毕业生安全平稳离校。</p>', 'api/image?name=NEWS9a4d5efd458c45529201de4b4f82abf4.jpg', 3, 0, 13, '2019-05-30 13:39:38', 5, 12);
INSERT INTO `tb_news` VALUES (7, '我校第十五届校园十大歌手决赛圆满落幕', '<p><img src=\"api/image?name=NEWS79f7041409df462cae08493a39357f20.jpg\" alt=\"\" width=\"720\" height=\"480\" /></p>\n<p>&nbsp; &nbsp; &nbsp; &nbsp;用音乐诠释热爱，用歌声点亮人生。11月18日晚，由校团委主办，学生社团联合会和校艺术团承办的&ldquo;音为有你，唱响理工&rdquo;第十五届校园十大歌手大赛决赛在小礼堂举行。全校800余名师生共同欣赏了这次精彩绝伦的视听盛宴。</p>\n<p>&nbsp; &nbsp; &nbsp; &nbsp;经过初赛复赛近一个月时间的激烈角逐，二十名选手脱颖而出，晋级决赛。决赛分为对抗赛、复活赛和巅峰对决争夺战3轮比拼。对抗赛中选手们通过两两合唱一首歌曲进行PK，评审之后十名选手晋级进入下一阶段，十名进入复活赛。复活赛阶段：经大众评审投票选出刘庆红、杨帅恒、李思凡三位选手与对抗赛晋级选手竞争，最终十名选手进入最后的巅峰对决争夺战。巅峰对决争夺战中，王麒涵的《你还要我怎样》以其高超的歌唱技巧呈现出了深爱却不得的苦楚；李柯欣的一首《另觅沧海》以其标准的英文发音，动人心弦的演唱获得观众们的阵阵掌声；张鑫鹏的《我好想你》细腻悦耳，描述喜欢一个人而落地却变成遗憾的无奈；娄玉洁一首《红玫瑰》唱的婉转柔情，使观众如同身临其境。</p>\n<p>&nbsp; &nbsp; &nbsp; &nbsp;经过激烈的角逐，评委严格公正的评分，王麒涵凭借一曲《你还要我怎样》摘得桂冠，张鑫鹏等3位同学获得二等奖，余进海等六名选手荣获三等奖。此外，经网上投票统计，行念臻获得最佳人气奖。</p>\n<p>&nbsp; &nbsp; &nbsp; 校园歌手大赛的举办，为学生们创造了一个展现自我的舞台，鼓励同学们勇于追逐梦想，把握自己的人生，也推动了学校校园文化建设，丰富了学生课余文化生活。</p>', 'api/image?name=NEWS79f7041409df462cae08493a39357f20.jpg', 3, 0, 19, '2019-05-30 13:54:12', 4, 14);
INSERT INTO `tb_news` VALUES (8, '我校举办2018年师生冬季长跑比赛', '<p><img src=\"api/image?name=NEWSb68869d3be0640b98836ddaebf5d80cf.jpg\" alt=\"\" width=\"720\" height=\"480\" /></p>\n<p style=\"text-align: center;\">冬季长跑比赛现场</p>\n<p style=\"text-align: left;\"><img style=\"display: block; margin-left: auto; margin-right: auto;\" src=\"api/image?name=NEWSbb7faa8997044711b5c6614bdc89c89f.jpg\" alt=\"\" width=\"720\" height=\"478\" /></p>\n<p style=\"text-align: center;\">教职工冬季长跑比赛掠影</p>\n<p style=\"text-align: left;\"><img src=\"api/image?name=NEWSce5782288a3b4b299021be8dd544912d.jpg\" alt=\"\" width=\"720\" height=\"478\" /></p>\n<p style=\"text-align: center;\">学生冬季长跑比赛掠影</p>\n<p style=\"text-align: left;\">&nbsp; &nbsp; &nbsp; &nbsp;为进一步增强全校师生的体质健康水平和团结凝聚力，继承和发扬坚持不懈、顽强拼搏、勇往直前、永不言弃的理工精神，11月20日下午，2018年师生冬季长跑比赛在南校区举办。校领导邹友峰、安士伟、张锟、陈自录、赵俊伟、翟传森、解伟、赵同谦、翟耀南分别出席学生男子组、学生女子组和教职工代表队的起跑仪式，并为比赛发令。</p>\n<p style=\"text-align: left;\">&nbsp; &nbsp; &nbsp; &nbsp;全校24个学生男子组、23个学生女子组和28个教工代表队，共约3000名师生参加比赛。学生男子组全程约8500米，学生女子组、教工组全程约5500米。</p>\n<p style=\"text-align: left;\">&nbsp; &nbsp; &nbsp; &nbsp;赛前由体育老师讲解注意事项，随后参赛选手在音乐声中进行热身，经过充分准备后，校领导发令枪响，参赛者冲刺而出。</p>\n<p style=\"text-align: left;\">&nbsp; &nbsp; &nbsp; &nbsp;经过激烈角逐，后备军官学院王佳浩、体育学院王研博、资环学院王更分获学生男子组冠、亚、季军；安全学院闫姗姗、能源学院冯宇、机械学院周慧坚分获女子组冠、亚、季军；测绘学院傅建春、土木学院王金星、后勤集团全文君分获教工男子组冠、亚、季军；医学院李金丽、图书馆杜鹃、图书馆徐娜分获教工女子组冠、亚、季军。张锟、赵俊伟、翟传森为获奖学生组颁发荣誉证书和奖品，陈自录为获奖教职工代表队颁发荣誉证书和奖品。</p>\n<p style=\"text-align: left;\">&nbsp; &nbsp; &nbsp; &nbsp;冬季长跑由校体育运动委员会、校工会主办，由体育运动委员办公室、体育学院（太极拳学院）和体育场馆中心承办，宣传部、学生处（部）、校团委、保卫处（部）和校医院协办。活动全面推动了阳光体育运动在我校的深入开展，对于增强我校师生的身体素质水平以及促进全体师生身心健康发展起到了积极推动作用。</p>', 'api/image?name=NEWSb68869d3be0640b98836ddaebf5d80cf.jpg', 1, 0, 14, '2019-05-30 13:58:29', 3, 14);
INSERT INTO `tb_news` VALUES (9, '我校第十五届“理工杯”球类比赛落幕', '<p><img src=\"api/image?name=NEWS1516563da9de4c978cb862fe11b723d0.jpg\" alt=\"\" width=\"720\" height=\"480\" /></p>\n<p>&nbsp;</p>\n<p>&nbsp; &nbsp; &nbsp; &nbsp;12月2日,由校团委、体委办及校学生会主办，资环学院和财经学院承办，大学生篮球协会协办的第十五届&ldquo;理工杯&rdquo;篮球赛决赛在南校区新体育场打响。校体委办、党委研工部、校团委、土木学院、财经学院相关负责老师和全校2000余名师生共同观看了比赛。男子篮球决赛的圆满结束也标志着历时一个月的第十五届&ldquo;理工杯&rdquo;球类比赛顺利落幕。</p>\n<p>&nbsp; &nbsp; &nbsp; &nbsp; &ldquo;理工杯&rdquo;男子篮球赛共有来自全校各学院以及篮球协会的23支队伍参赛。经过一个月激烈的竞争，机械学院和财经学院一路冲关斩将挺进决赛，角逐冠军。研究生院以35：25战胜土木学院获得季军，土木学院取得了第四名的成绩。激动人心的冠军争夺赛在财经学院和机械学院展开。比赛中，传球、突破、跳投、上篮，两队运动员干净利落的动作和精彩绝伦的配合展现出实力的强劲，双方的激烈对抗也使场馆内不断响起观众热烈欢呼与阵阵掌声。最终财经学院后程发力以71：54摘得桂冠，机械学院获得亚军。赛后，阎俊豪、辛文昉等教师为获奖队伍颁奖并合影留念。</p>\n<p>&nbsp; &nbsp; &nbsp; &nbsp; &ldquo;理工杯&rdquo;球类比赛除男子篮球赛之外，还有男子足球、男子排球、女子排球、乒乓球等比赛项目。财经学院篮球队、国际教育学院足球队、机械学院男子排球队、工商学院女子排球队、体育学院乒乓球队摘得各项比赛的桂冠。</p>\n<p>&nbsp; &nbsp; &nbsp; &nbsp;&ldquo;理工杯&rdquo;球类比赛自2004年举办至今走过了15个年头，已经成为我校校园文化生活精品活动，是我校学校指导学生自行举办的最大规模的体育运动品牌。</p>', 'api/image?name=NEWS1516563da9de4c978cb862fe11b723d0.jpg', 1, 0, 12, '2019-05-30 14:02:39', 3, 15);
INSERT INTO `tb_news` VALUES (10, '我校举办第十三届主持人大赛', '<p><img src=\"api/image?name=NEWSf80c4dafa15b4c4689d4555cb09d2344.png\" alt=\"\" width=\"720\" height=\"480\" /></p>\n<p>&nbsp;</p>\n<p>&nbsp; &nbsp; &nbsp; &nbsp;麦响理工，音你而生。为丰富校园课余文化生活，活跃校园文化氛围，11月25日，由校团委主办，学生社团联合会、校艺术团、校广播台联合承办的河南理工大学第十三届主持人大赛决赛在我校小礼堂落下帷幕。数百名师生共同观看了比赛。</p>\n<p>&nbsp; &nbsp; &nbsp; &nbsp;经过前期预赛、复赛的激烈比拼、层层选拔，共有12名优秀主持人一路披荆斩棘，从近百位参赛选手中脱颖而出，晋级到了决赛。此次决赛设有风采展示、妙语连珠、模拟主持和评委提问4个环节。在开场舞之后的风采展示中，播放了选手们的自我介绍和才艺展示的视频。妙语连珠考察了选手的语言文化功底，即兴的演说展现了选手的随机应变能力。模拟主持环节选手两两一组、互相配合，从新闻、生活、文化等节目题材出发，将一个个小节目生动地展示在观众面前，将现场氛围推向高潮。选手们创意层次不穷，展示出主持风采。活动从各个方面考察了选手的艺术修养、表达能力和临场应变能力。在评委点评过程中，各个选手灵活地应对评委老师的提问，虚心接受老师给予的意见，彰显了个人魅力与素养。</p>\n<p>&nbsp; &nbsp; &nbsp; &nbsp;经过两个小时的精彩角逐，文法学院的刘畅同学凭借自己出色的发挥夺得桂冠，斩获一等奖，翁奕然和于浩翔同学获得二等奖，赵梓名、叶岚、项温娴同学获得三等奖，叶岚获得最佳人气奖。</p>\n<p>&nbsp; &nbsp; &nbsp; &nbsp;大赛为广大播音主持爱好者提供了一个展示自我、放飞自我的舞台。活跃了校园文化氛围，提高了我校学生艺术修养与实践能力，展现了我校学生朝气昂扬的精神风貌，丰富了我校广大师生的课余文化生活，展示了当代大学生的青春风采。</p>', 'api/image?name=NEWSf80c4dafa15b4c4689d4555cb09d2344.png', 1, 0, 18, '2019-05-30 14:05:41', 4, 15);
INSERT INTO `tb_news` VALUES (11, '我校一项发明专利获得第二十届中国专利奖', '<p>&nbsp; &nbsp; &nbsp; &nbsp;12月25日，由中国国家知识产权局和世界知识产权组织共同主办的第二十届中国专利奖颁奖大会在北京举行，国家知识产权局和世界知识产权组织决定授予我校安全学院教师李辉、魏建平的专利&ldquo;瓦斯预抽钻孔煤屑回填封孔方法&rdquo;（ZL201410051006.1）第二十届中国专利奖优秀奖，本届专利奖我校是河南省高校中唯一获奖的单位，与中国矿业大学、中国矿业大学（北京）一起名列煤炭系统高校三个获奖单位之一。据悉，本届专利奖全国共有51所高校获奖。<br />&nbsp; &nbsp; &nbsp; &nbsp;中国专利奖由国家知识产权局于1989年设立，是我国唯一的专门对授予专利权的发明创造给予奖励的政府部门奖，旨在大力推进创新驱动发展战略和知识产权战略，努力加快知识产权强国建设。<br />&nbsp; &nbsp; &nbsp; &nbsp;中国专利奖作为我国专利领域的最高奖项，不仅是我国知识产权事业发展水平的重要标志之一，更为激发全社会创业创新热情、催生更多更好的专利成果提供了有力支撑。该奖项是衡量企业和高校的自主创新能力的重要标志，也是企业和高校专利质量的重要衡量标准。<br />&nbsp; &nbsp; &nbsp; &nbsp;我校重视自主创新能力的提高，不断加大对专利的资助力度，专利质量明显提升，专利转化明显增多，继2017年获得首届河南省专利奖二等奖后，又获得2018年第二十届中国专利奖，在省内高校中，处于领先位置。</p>', 'https://www.gravatar.com/avatar/a1887a615b9a4ddabf386c7dfebb36dc?s=230&d=identicon', 1, 0, 9, '2019-06-02 21:21:57', 2, 16);
INSERT INTO `tb_news` VALUES (12, '校友蒋世银捐赠110万元贺母校110周年校庆', '<p><img src=\"api/image?name=NEWSd000c8ca5eae496c8e2757f556e43ca8.jpg\" alt=\"\" width=\"720\" height=\"480\" /></p>\n<p style=\"text-align: center;\">捐赠仪式现场</p>\n<p style=\"text-align: center;\">&nbsp;</p>\n<p style=\"text-align: left;\"><img src=\"api/image?name=NEWS06a38f00cd2444eeadc89c20aaaa96a9.jpg\" alt=\"\" width=\"720\" height=\"480\" /></p>\n<p style=\"text-align: center;\"><span style=\"text-align: center;\">校长杨小林致欢迎词</span></p>\n<p style=\"text-align: center;\">&nbsp;</p>\n<p style=\"text-align: left;\"><span style=\"text-align: center;\"><img src=\"api/image?name=NEWSaa52308323b942bb95f525e5e1c0258e.jpg\" alt=\"\" width=\"720\" height=\"480\" /></span></p>\n<p style=\"text-align: center;\">校友蒋世银讲话</p>\n<p style=\"text-align: center;\">&nbsp;</p>\n<p style=\"text-align: left;\"><img style=\"display: block; margin-left: auto; margin-right: auto;\" src=\"api/image?name=NEWS649d56564ea7473d928dd830c24d4c71.jpg\" alt=\"\" width=\"720\" height=\"480\" /></p>\n<p style=\"text-align: center;\">校长杨小林向校友蒋世银颁发捐赠证书</p>\n<p style=\"text-align: center;\">&nbsp;</p>\n<p style=\"text-align: left;\">&nbsp; &nbsp; &nbsp; &nbsp;5月8日上午，学校在明德楼会议室举行&ldquo;蒋世银校友贺母校建校110周年捐赠仪式&rdquo;，校长杨小林，79级校友蒋世银，副校长赵俊伟出席仪式，校教育发展基金会理事长杨韶昆，校办、校友办、校庆办负责人参加仪式。仪式由校友与社会合作办公室主任邵强主持。<br />&nbsp; &nbsp; &nbsp; &nbsp;杨小林在致辞中首先代表校党委、行政对蒋世银荣归母校表示热烈的欢迎。杨小林说，此次捐赠是迎接校庆活动期间学校获得的第一笔大额现金捐赠，充分彰显了蒋世银饮水思源、回馈母校的爱校情怀，全校师生对此深表谢意和敬意。自1909年建校以来，学校历经110年风雨，始终传承着&ldquo;自强不息、奋发向上&rdquo;的办学精神，为国家培养了24万缤纷桃李。如今广大校友遍布祖国各地，并通过不懈奋斗做出了令母校引以为荣的不凡业绩，为母校赢得了巨大光荣。蒋世银几十载如一日，践行&ldquo;好学力行&rdquo;校风，不求名利，默默奉献，为我国煤炭事业发展做出了积极贡献，是我校广大学子奋发图强、报效祖国的杰出楷模。最后，杨小林真诚祝福蒋世银事业蒸蒸日上，企业蓬勃发展，家人幸福安康，并希望他能多回母校看看，为母校的发展建言献策。<br />&nbsp; &nbsp; &nbsp; &nbsp;蒋世银在讲话中动情地说，随着岁月的推移，对母校的思念之情越来越浓厚，欣闻母校正在筹备110 周年校庆，就想要回来为母校的发展献上一份心意。他在讲话中深情回顾了自己的工作历程，感谢母校教给他专业知识，使他能够几十年如一日做好专业技术研发工作，为煤炭事业发展贡献自己的力量。<br />&nbsp; &nbsp; &nbsp; &nbsp;赵俊伟和杨韶昆分别代表学校、校教育发展基金会与蒋世银签署捐赠协议，杨小林代表学校接受110 万元捐赠，并向蒋世银颁发捐赠证书。<br />&nbsp; &nbsp; &nbsp; &nbsp;捐赠仪式之前，校党委书记邹友峰亲切会见了蒋世银。<br />&nbsp; &nbsp; &nbsp; &nbsp;校友蒋世银，1979-1983年在我校前身&mdash;&mdash;焦作矿业学院机电系煤矿机械制造专业学习，毕业后分配到原煤炭工业研究总院太原分院工作，工作业绩突出。上世纪90年代，他创立太原圣邦液压器件有限公司，倾心于研究、制造非标类油缸和煤矿专用设备，生产的产品达到国际先进水平，主要服务于我国神华集团等特大型煤炭能源企业。</p>', 'api/image?name=NEWSd000c8ca5eae496c8e2757f556e43ca8.jpg', 1, 0, 1, '2019-06-02 21:34:41', 5, 16);
INSERT INTO `tb_news` VALUES (13, '英国皇家化学会期刊以封面报道材料学院教师科研新进展', '<p><img src=\"api/image?name=NEWScda7cb95de0e4bc18946be5ef29e6a68.jpg\" alt=\"\" width=\"720\" height=\"480\" /></p>\n<p>&nbsp;</p>\n<p>&nbsp; &nbsp; &nbsp; &nbsp;近日，继《材料化学A》，英国皇家化学会期刊再次以封面亮点报道我校&ldquo;河南省微尺度理论模拟与设计创新团队&rdquo;的研究进展。此次工作聚焦于碳化硅的磁性调控，发表在《化学新志》，并被以前外封面亮点报道。《化学新志》主要报道化学、材料领域的新方向、新发现，是化学、材料领域颇有影响的期刊之一，最新影响因子3.269。<br />&nbsp; &nbsp; &nbsp; &nbsp;碳化硅为第三代半导体材料，具有许多优良的性质，如带隙宽、抗电压击穿能力强、热导率高、饱和电子迁移率大等，适合制备高频率、高功率、耐高温以及抗辐照的电子器件。本工作通过(铝,锰)双掺杂调控了碳化硅的磁性，得到了磁性远大于目前报道的室温稀磁半导体，指出了过渡金属单掺、非磁性元素双掺以及锰-碳-锰共轭链对磁性引入的关键作用，从原子尺度揭示了磁性产生的本质原因。<br />&nbsp; &nbsp; &nbsp; &nbsp;该工作主要由我校材料学院在站博士后林龙（第一作者）和副教授赵瑞奇（通讯作者）及研究生祝令豪（第二作者）完成。该工作得到了国家自然科学基金、河南省微尺度理论模拟与设计创新型科技团队、河南省自然科学基金、我校低维材料理论创新团队和高性能计算平台的支持。</p>', 'api/image?name=NEWScda7cb95de0e4bc18946be5ef29e6a68.jpg', 1, 1, 3, '2019-06-02 21:45:22', 2, 16);
INSERT INTO `tb_news` VALUES (14, '材料学院举办材料之星颁奖暨欢送2019届毕业生晚会', '<p><img src=\"api/image?name=NEWS08e0f65bdedd424ca355ac62e57c0cce.jpg\" alt=\"\" width=\"720\" height=\"480\" /></p>\n<p>&nbsp;</p>\n<p>&nbsp; &nbsp; &nbsp; &nbsp;时光荏苒，又是一年毕业季；骊歌响起，挥手再踏征途。6月1日晚，材料学院在学校小礼堂举办材料之星颁奖暨&ldquo;材华横溢，料定未来&rdquo;欢送2019届毕业生晚会。副校长翟传森出席，电气学院党委书记欧阳文峰、校工会办公室主任谢玉芬、材料学院党委书记杜泽兵、院长管学茂、党委副书记张建新与800余名师生共同观看了晚会。<br />&nbsp; &nbsp; &nbsp; &nbsp;红日隐去，夜幕降临，晚会在一支激情洋溢的开场舞《赞赞新时代》中拉开了帷幕。气势宏大的歌伴武《以歌之名，武动中国》，讲述了中华儿女以其血肉之躯保家卫国的故事。随后，杜泽兵书记上台致辞，表达了对材料之星获得者的肯定和鼓励，希望获得材料之星的师生继续立足岗位，继续践行学校&ldquo;好学力行&rdquo;的校风，杜书记还对毕业生提出了三个希望和建议，希望所有材料学院2019级毕业生要充满信心、坚持学习、持续奋斗。晚会期间进行了&ldquo;材料之星&rdquo;颁奖活动，评选出的学生&ldquo;材料之星&rdquo;、教师&ldquo;科研之星&rdquo;&ldquo;教学之星&rdquo;&ldquo;特别之星&rdquo;以及&ldquo;服务之星&rdquo;等获奖代表一一上台领奖。晚会节目的手语舞描绘最美的《国家》，点燃了大家心中的爱国之情；一篇《材料赋》表达了莘莘学子对学院的热爱，铿锵有力的朗诵中饱含的感情，引起了师生们的共鸣；2015级辅导员和毕业生带来的相声《新闻趣播》和小品《十年》让观众开怀大笑，饱含了浓浓师生情谊。最后，晚会在合唱《强国一代有我在》的歌声中拉下帷幕，现场掌声雷动，学生们纷纷上台合影留念。<br />&nbsp; &nbsp; &nbsp; &nbsp;相逢又告别，归帆又离岸。晚会充分展现材料人风华正茂，昂扬向上的精神风貌。毕业生们会带着材料师生共同的祝福，开启新的征程，逐梦远航。</p>', 'api/image?name=NEWS08e0f65bdedd424ca355ac62e57c0cce.jpg', 1, 0, 11, '2019-06-03 18:30:17', 5, 11);
INSERT INTO `tb_news` VALUES (15, '测绘学院开展“追梦新时代，开启新征程”经典诵读活动', '<p><img src=\"api/image?name=NEWS8d8c12c00ff746c2a16f57f7d36a056b.jpg\" alt=\"\" width=\"720\" height=\"480\" /></p>\n<p>&nbsp; &nbsp; &nbsp; &nbsp;为积极培育和践行社会主义核心价值观，弘扬中华民族优秀传统文化，抒发爱国情怀，向建党98周年、建国70周年和建校110周年献礼，6月11日下午，测绘学院组织开展了&ldquo;追梦新时代，开启新征程&rdquo;经典诵读活动，学院师生代表70余人参加诵读。<br />&nbsp; &nbsp; &nbsp; &nbsp;诵读活动融合端午节、建校110周年、建党98周年和建国70周年等主题，以《弘扬传统文化&middot;纪念爱国先贤》《振兴百年理工&middot;助力民族复兴》《党旗永远飘扬&middot;始终不忘初心》《共诵中华经典&middot;同抒爱国情怀》四个篇章展开，师生们共同诵读经典诗句，传承理工精神，表达对党和祖国的无限热爱。<br />&nbsp; &nbsp; &nbsp; &nbsp;活动充分展示了测绘学院师生良好的精神风貌和文化建设成果，激励广大师生传承红色基因，为我校改革和发展作出新贡献，展现了全体师生在新时代背景下坚定不移跟党走、开启发展新征程的信心和决心。</p>', 'api/image?name=NEWS8d8c12c00ff746c2a16f57f7d36a056b.jpg', 3, 0, 13, '2019-06-16 00:49:32', 5, 11);
INSERT INTO `tb_news` VALUES (16, '我校举办第十一届“任责杯”团日活动设计大赛', '<p><img src=\"api/image?name=NEWS3e934f55c704488c864371a8eacf0613.jpg\" alt=\"\" width=\"720\" height=\"480\" /></p>\n<p>&nbsp; &nbsp; &nbsp; &nbsp;6月9日晚，由校团委主办，电气学院团委承办的第十一届&ldquo;任责杯&rdquo;团日活动设计大赛校级决赛于力行楼学术报告厅举行。校团委书记支光辉、党委组织部副部长张红霞、党委宣传部副部长刘春德、党委学工部副部长孙彦、党委研工部副部长王伟超等教师受邀担任决赛的评委。我校300余名师生共同观看了比赛。<br />&nbsp; &nbsp; &nbsp; &nbsp;团日活动设计大赛以&ldquo;弘扬五四精神，做新时代优秀大学生&rdquo;为主题，全校共有497个班级团支部、近万名团员青年积极参与。各团支部紧密围绕主题开展了形式多样、内容丰富的团日活动。经学院初赛、校级复赛，共有10个班级团支部脱颖而出，进入校级决赛。决赛由PPT展示、现场答辩、人气得分等环节组成。<br />&nbsp; &nbsp; &nbsp; &nbsp;大赛经过各支部细心准备、精彩展示、激烈角逐，建筑学1702团支部、法学1701团支部、电子1813团支部和市场1720团支部荣获一等奖；电气合1804团支部、计科合1804团支部等6个团支部荣获二等奖；电子1818团支部、测绘1702团支部等10个团支部荣获三等奖。<br />&nbsp; &nbsp; &nbsp; &nbsp;团日活动设计大赛的举办，促进了我校团员青年对习近平总书记在纪念五四运动100周年大会上讲话精神的学习领会，提高了他们对五四精神内涵的深刻把握，引领我校团员青年&ldquo;树立远大理想、热爱伟大祖国、担当时代责任、勇于砥砺奋斗、练就过硬本领、锤炼品德修为&rdquo;。</p>', 'api/image?name=NEWS3e934f55c704488c864371a8eacf0613.jpg', 1, 0, 1, '2019-06-16 02:01:14', 4, 20);

-- ----------------------------
-- Table structure for tb_news_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_news_category`;
CREATE TABLE `tb_news_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `category_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资讯分类名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_news_category
-- ----------------------------
INSERT INTO `tb_news_category` VALUES (1, '学习');
INSERT INTO `tb_news_category` VALUES (2, '科研');
INSERT INTO `tb_news_category` VALUES (3, '体育');
INSERT INTO `tb_news_category` VALUES (4, '娱乐');
INSERT INTO `tb_news_category` VALUES (5, '生活');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `passwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '盐',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `gender` int(11) NULL DEFAULT NULL COMMENT '用户性别：0代表男，1代表女',
  `head_image_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户手机号',
  `profile` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '用户个人简介',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (7, 'ttttt', 'E21575B269D23182075A490F47A2B909', '2b9789', 'tttt', 0, 'https://www.gravatar.com/avatar/edbdd8908261478990d119711daa3761?s=230&d=identicon', '18888888888', '的撒谎都考啥很快就撒的谎lkjhsladsajhlsakjdl 的撒娇的撒娇离开');
INSERT INTO `tb_user` VALUES (8, 'mmmmmm', 'CA9D46AD943B1026ACF574EC3B470D68', '6e6d85', '龚某某', 1, '/api/image?name=USER7cc03897048847ab8d44ac1b97b681b5.png', '17777777777', '');
INSERT INTO `tb_user` VALUES (9, 'pppppp', 'B48D8233B4776D55A8FED92A5FB855CA', 'fc52d5', '啦啦啦', 1, '/api/image?name=USERe1e9f788c6d2489cb415263a6fbdffd3.png', '18888888888', '打撒打撒大厦恐惧和撒大哈啥开了撒六角恐龙看到的拉萨机了看盛大的++++dsadsadsadsa');
INSERT INTO `tb_user` VALUES (10, 'mmm', '2F472E650D179D444C18927D675749A2', '5489d0', '，，，', 0, 'https://www.gravatar.com/avatar/4d5b9243bb5f46d68395d3362d36127f?s=230&d=identicon', '15555555555', 'd的洒家离开就是大家了解到了sadsa');
INSERT INTO `tb_user` VALUES (11, 'toutiao01', '119930824595CC6E0DE228D65A25D77D', 'aeebee', '头条一号', 0, 'https://www.gravatar.com/avatar/76b4855f21f143cc9679b8e15bd5da70?s=230&d=identicon', '17788889999', '我是校园头条第一个测试用户');
INSERT INTO `tb_user` VALUES (12, 'toutiao02', 'D4431286608CAB025DABA8A886440798', 'f0b2fa', '头条二号', 1, 'https://www.gravatar.com/avatar/c98a3317def54f61983c3f5f53c3d503?s=230&d=identicon', '18877779999', '');
INSERT INTO `tb_user` VALUES (13, 'toutiao03', 'EC480BEEEC2BC65101955B8231B934FB', '0bbe60', '头条三号', 0, 'https://www.gravatar.com/avatar/3e0ca56aa36241fa9a2fdeefa4138595?s=230&d=identicon', '17777778888', '我是头条三号，头条三号');
INSERT INTO `tb_user` VALUES (14, 'toutiao04', '59ADF4289B239B47B1EB02DB17F16564', '4cdbd8', '头条四号', 0, 'https://www.gravatar.com/avatar/add80e14d5f2405987fbf33780759bf7?s=230&d=identicon', '17766668888', '的撒及恺撒奖了进来撒看撒赖科技卢卡斯的ask就立刻吉萨垃裸吊就拉数据搭建卢卡斯');
INSERT INTO `tb_user` VALUES (15, 'toutiao05', '642865B793057096152F89C977A94025', '827062', '头条五号', 1, 'https://www.gravatar.com/avatar/d4506ace06384afb8d4eebe174020b20?s=230&d=identicon', '18812345678', '我是头条五号、头条五号、头条五号');
INSERT INTO `tb_user` VALUES (16, 'toutiao06', 'DAF92261850E687DC7B09343F5088AED', 'b718c2', '头条六号', 1, 'https://www.gravatar.com/avatar/43cdf46dc1da43b297ea750132c89a7e?s=230&d=identicon', '18899991111', '我是头条6号，头条六号，头条六号');
INSERT INTO `tb_user` VALUES (17, 'toutiao07', '595E88558014B4CE2C77FE1AE08B162A', 'e20fcb', '头条七号', 0, '/api/image?name=USER531df677e7f54a86a231c97f29e97eb1.jpg', '17788888888', '大厦空间和的撒谎就啥的接口和的撒谎空间和阿三的洒家离开');
INSERT INTO `tb_user` VALUES (18, 'toutiao08', 'A54FEDECCF2AFC91051D728C6A794076', 'b48169', '头条8号', 1, 'https://www.gravatar.com/avatar/dfdc05dd96a147aa97f357e269c0dd7f?s=230&d=identicon', '18899999999', '我是头条8号');
INSERT INTO `tb_user` VALUES (19, 'toutiao09', '86828339F37F531A833F0D7B4FEF4C94', 'f5adfe', '头条9号', 1, 'https://www.gravatar.com/avatar/b748c1a02a3e4e77b3d829d8f3dc933c?s=230&d=identicon', '17711111111', '我是头条9号');
INSERT INTO `tb_user` VALUES (20, 'toutiao10', '928F00189D5713E2E9366612DCA8E870', 'a1e882', '头条10号', 0, 'https://www.gravatar.com/avatar/d71fc793333d43c8bed21d6036a390b8?s=230&d=identicon', '15588888888', '我是头条10号');
INSERT INTO `tb_user` VALUES (21, 'toutiao11', '0BCD0953590C03D04F64456CDB0C0FCA', 'b08e1b', '头条11号', 0, 'https://www.gravatar.com/avatar/698b08cc00694feba18fa758fa4026f9?s=230&d=identicon', '15511112222', '我是头条11号');

SET FOREIGN_KEY_CHECKS = 1;
