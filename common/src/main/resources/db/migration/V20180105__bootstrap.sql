
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '品牌名称',
  `logo` bigint(20) NOT NULL DEFAULT '0' COMMENT 'logo图片,指向图片表',
  `category_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '类目',
  `create_user` varchar(30) NOT NULL DEFAULT '' COMMENT '创建者',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_user` varchar(30) NOT NULL DEFAULT '' COMMENT '最后修改者',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌表';


DROP TABLE IF EXISTS `catalog`;
CREATE TABLE `catalog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '目录名称',
  `logo` bigint(20) NOT NULL DEFAULT '0' COMMENT 'logo图片,指向图片表',
  `levl` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父级ID',
  `is_enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用',
  `create_user` varchar(30) NOT NULL DEFAULT '' COMMENT '创建者',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_user` varchar(30) NOT NULL DEFAULT '' COMMENT '最后修改者',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='目录表';


DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '类型名称',
  `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父级ID',
  `is_enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用',
  `create_user` varchar(30) NOT NULL DEFAULT '' COMMENT '创建者',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_user` varchar(30) NOT NULL DEFAULT '' COMMENT '最后修改者',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='类型表';


DROP TABLE IF EXISTS `evaluate`;
CREATE TABLE `evaluate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `item_code` varchar(50) NOT NULL DEFAULT '' COMMENT '商品编码',
  `price_star` int(10) NOT NULL DEFAULT '1' COMMENT '价格星级',
  `quality_star` int(10) NOT NULL DEFAULT '1' COMMENT '质量星级',
  `match_star` int(10) NOT NULL DEFAULT '1' COMMENT '商品相符星级',
  `logistics_star` int(10) NOT NULL DEFAULT '1' COMMENT '物流星级',
  `content` varchar(256) NOT NULL DEFAULT '' COMMENT '评价内容',
  `is_anonymous` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否匿名',
  `is_sslide` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否晒图',
  `member_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户',
  `create_user` varchar(30) NOT NULL DEFAULT '' COMMENT '创建者',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_user` varchar(30) NOT NULL DEFAULT '' COMMENT '最后修改者',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品评价表';

DROP TABLE IF EXISTS `evaluate_image`;
CREATE TABLE `evaluate_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `evaluate_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '评价ID',
  `image` bigint(20) NOT NULL DEFAULT '1' COMMENT '图片',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品评价图片表';




DROP TABLE IF EXISTS `express`;
CREATE TABLE `express` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `keywords` varchar(50) NOT NULL DEFAULT '' COMMENT '关键字,Json数组',
  `express_name` varchar(50) NOT NULL DEFAULT '0' COMMENT '快递名称',
  `create_user` varchar(30) NOT NULL DEFAULT '' COMMENT '创建者',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_user` varchar(30) NOT NULL DEFAULT '' COMMENT '最后修改者',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='快递类型表';


DROP TABLE IF EXISTS `image`;
CREATE TABLE `image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL DEFAULT '1' COMMENT '图片类型',
  `size` varchar(10) NOT NULL DEFAULT '' COMMENT '图片大小',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '图片名称',
  `url` varchar(256) NOT NULL DEFAULT '' COMMENT '图片URL',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图片表';


DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `item_code` varchar(30) NOT NULL DEFAULT '' COMMENT '商品编码',
  `name` varchar(150) NOT NULL DEFAULT '' COMMENT '商品名称',
  `short_title` varchar(300) DEFAULT NULL COMMENT '短标题',
  `supplier_id` bigint(20) DEFAULT NULL COMMENT '供应商',
  `category_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '基础分类ID',
  `catalog_id` bigint(20) NOT NULL COMMENT '目录ID',
  `brand_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '品牌Id',
  `image_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '橱窗图,指向图片表',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '基础定价',
  `unit` varchar(30) NOT NULL DEFAULT '' COMMENT '单位',
  `keywords` varchar(255) NOT NULL DEFAULT '' COMMENT '关键词',
  `is_virtual` tinyint(1) DEFAULT '0' COMMENT '是否虚拟商品',
  `seller_note` varchar(150) NOT NULL DEFAULT '' COMMENT '商品的商家备注，仅商家可见',
  `create_user` varchar(30) NOT NULL DEFAULT '' COMMENT '创建者',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_user` varchar(30) NOT NULL DEFAULT '' COMMENT '最后修改者',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `is_disable` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';


DROP TABLE IF EXISTS `item_detail`;
CREATE TABLE `item_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `item_code` varchar(50) NOT NULL DEFAULT '' COMMENT '商品编码',
  `content` text NOT NULL COMMENT '商品详情',
  `create_user` varchar(30) NOT NULL DEFAULT '' COMMENT '创建者',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_user` varchar(30) NOT NULL DEFAULT '' COMMENT '最后修改者',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品详情信息表';


DROP TABLE IF EXISTS `item_image`;
CREATE TABLE `item_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `item_code` varchar(10) NOT NULL DEFAULT '' COMMENT '商品编码',
  `image_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '图片Id',
  `create_user` varchar(30) NOT NULL DEFAULT '' COMMENT '创建者',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品图片表';


DROP TABLE IF EXISTS `loves`;
CREATE TABLE `loves` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户Id',
  `item_code` int(11) NOT NULL DEFAULT '0' COMMENT '商品编码',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收藏信息表';


DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(50) NOT NULL DEFAULT '' COMMENT '密码',
  `sex` int(10) NOT NULL DEFAULT '0' COMMENT '性别(0=未选择,1=男,2=女)',
  `birth` date NOT NULL COMMENT '出生日期',
  `phone` varchar(13) NOT NULL DEFAULT '' COMMENT '手机',
  `email` varchar(50) NOT NULL DEFAULT '' COMMENT '邮箱',
  `header` bigint(20) NOT NULL DEFAULT '0' COMMENT '头像',
  `integral` int(10) NOT NULL DEFAULT '0' COMMENT '积分',
  `member_type` bigint(20) NOT NULL DEFAULT '0' COMMENT '会员级别',
  `status` int(11) NOT NULL DEFAULT '10' COMMENT '状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `is_disable` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';


DROP TABLE IF EXISTS `member_type`;
CREATE TABLE `member_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '级别名称',
  `icon` bigint(20) NOT NULL DEFAULT '0' COMMENT '基本logo',
  `min` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '消费范围开始(包括等于)',
  `max` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '消费范围解释(包括等于)',
  `is_discount` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否享受折扣',
  `discount` decimal(10,2) NOT NULL DEFAULT '1.00' COMMENT '折扣,百分数，0.85=85折',
  `desc` varchar(50) NOT NULL DEFAULT '' COMMENT '描述',
  `create_user` varchar(30) NOT NULL DEFAULT '' COMMENT '创建者',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_user` varchar(30) NOT NULL DEFAULT '' COMMENT '最后修改者',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员级别表';


DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `type` int(11) NOT NULL DEFAULT '10' COMMENT '消息类型',
  `content` varchar(200) NOT NULL DEFAULT '' COMMENT '消息内容',
  `status` int(11) NOT NULL DEFAULT '10' COMMENT '消息状态',
  `create_user` varchar(30) NOT NULL DEFAULT '' COMMENT '创建者',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_user` varchar(30) NOT NULL DEFAULT '' COMMENT '最后修改者',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `is_disable` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息表';


DROP TABLE IF EXISTS `order_payment`;
CREATE TABLE `order_payment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sales_order_no` varchar(30) NOT NULL DEFAULT '' COMMENT '订单号',
  `pay_method_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '支付方式',
  `amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '支付金额',
  `serial_no` varchar(50) NOT NULL DEFAULT '' COMMENT '流水号',
  `pay_no` varchar(50) NOT NULL DEFAULT '' COMMENT '支付账单单号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `is_disable` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单支付信息';

-- ----------------------------

DROP TABLE IF EXISTS `pay_method`;
CREATE TABLE `pay_method` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '0' COMMENT '名称',
  `is_enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用',
  `app_id` varchar(50) NOT NULL DEFAULT '' COMMENT 'appid',
  `mch_id` varchar(50) NOT NULL DEFAULT '' COMMENT 'mchId',
  `key` varchar(256) NOT NULL DEFAULT '' COMMENT 'key',
  `create_user` varchar(30) NOT NULL DEFAULT '' COMMENT '创建者',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_user` varchar(30) NOT NULL DEFAULT '' COMMENT '最后修改者',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付方式表';


DROP TABLE IF EXISTS `sales_order`;
CREATE TABLE `sales_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sales_order_no` varchar(30) NOT NULL DEFAULT '' COMMENT '订单号',
  `order_type` int(11) NOT NULL DEFAULT '10' COMMENT '订单类型',
  `invoce_type` int(11) NOT NULL DEFAULT '10' COMMENT '发票类型',
  `invoce_status` int(11) NOT NULL DEFAULT '10' COMMENT '发票状态',
  `order_status` int(11) NOT NULL DEFAULT '10' COMMENT '订单状态',
  `quantity` int(11) NOT NULL DEFAULT '0' COMMENT '商品数量',
  `express_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '快递Id',
  `express_no` varchar(30) NOT NULL DEFAULT '' COMMENT '快递单号',
  `receivable_amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '应收总金额',
  `real_amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '实际收款金额',
  `discount_amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣总金额',
  `coupon_amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '优惠劵折扣金额',
  `integral_amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '积分抵用金额',
  `province` varchar(20) NOT NULL DEFAULT '' COMMENT '省',
  `city` varchar(20) NOT NULL DEFAULT '' COMMENT '市',
  `county` varchar(20) NOT NULL DEFAULT '' COMMENT '县/区',
  `address` varchar(150) NOT NULL DEFAULT '' COMMENT '详细地址',
  `receive_name` varchar(13) NOT NULL DEFAULT '' COMMENT '收货人',
  `phone` varchar(13) NOT NULL DEFAULT '' COMMENT '收货人电话',
  `zipCode` varchar(8) NOT NULL DEFAULT '' COMMENT '邮编',
  `member_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户',
  `buyer_message` varchar(150) NOT NULL DEFAULT '' COMMENT '买家备注',
  `shop_message` varchar(150) NOT NULL DEFAULT '' COMMENT '商家备注',
  `delivery_time` datetime DEFAULT NULL COMMENT '发货时间',
  `finish_time` datetime DEFAULT NULL COMMENT '完成时间',
  `create_user` varchar(30) NOT NULL DEFAULT '' COMMENT '创建者',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_user` varchar(30) NOT NULL DEFAULT '' COMMENT '最后修改者',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `is_disable` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sales_order_no` (`sales_order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='销售订单表';


DROP TABLE IF EXISTS `sales_order_line`;
CREATE TABLE `sales_order_line` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sales_order_no` varchar(30) NOT NULL DEFAULT '' COMMENT '订单号',
  `item_code` varchar(50) NOT NULL DEFAULT '' COMMENT '商品编码',
  `item_name` varchar(150) NOT NULL DEFAULT '' COMMENT '商品名称',
  `sku_no` varchar(50) NOT NULL DEFAULT '' COMMENT '货号,商品唯一标识',
  `quantity` int(11) NOT NULL DEFAULT '1' COMMENT '数量',
  `shop_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '售卖价格(未折扣之前)',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '实际单价(折扣之后单价)',
  `discount` decimal(10,2) NOT NULL DEFAULT '1.00' COMMENT '折扣百分率(8折=0.8)',
  `discount_total` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '折扣总金额',
  `line_receivable_total` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '商品应收金额 = (售价 * 数量)或者(实收金额 + 折扣总金额)',
  `line_real_total` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '商品实收金额 = (实际单价 * 数量) 或者 (应收金额 - 折扣总金额)',
  `is_gift` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否赠品',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_user` varchar(30) NOT NULL DEFAULT '' COMMENT '最后修改者',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `is_disable` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单商品明细表';

DROP TABLE IF EXISTS `sku`;
CREATE TABLE `sku` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sku_code` varchar(30) NOT NULL DEFAULT '' COMMENT '规格编码',
  `item_code` varchar(50) NOT NULL DEFAULT '' COMMENT '商品编码',
  `category_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '基础分类ID',
  `catalog_id` bigint(20) NOT NULL COMMENT '目录ID',
  `barcode` varchar(50) NOT NULL DEFAULT '' COMMENT '条码',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '售价',
	`market_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '市场价',
	`image` bigint(20) DEFAULT 0 COMMENT 'SKU图片',
	`status` INT NOT NULL DEFAULT 10  COMMENT '状态',
  `create_user` varchar(30) NOT NULL DEFAULT '' COMMENT '创建者',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_user` varchar(30) NOT NULL DEFAULT '' COMMENT '最后修改者',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_disable` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='SKU';


DROP TABLE IF EXISTS `sku_spec`;
CREATE TABLE `sku_spec` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sku_code` varchar(30) NOT NULL DEFAULT '' COMMENT '规格编码',
  `spec_value_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '值表Id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品规格表';


DROP TABLE IF EXISTS `sku_stock`;
CREATE TABLE `sku_stock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `item_code` varchar(50) NOT NULL DEFAULT '' COMMENT '商品编码',
  `sku_code` varchar(50) NOT NULL DEFAULT '' COMMENT '商品编码',
  `stock` int(11) NOT NULL DEFAULT '0' COMMENT '总库存',
  `order_occupancy` int(11) NOT NULL DEFAULT '0' COMMENT '预占用',
  `activity_occupancy` int(11) NOT NULL DEFAULT '0' COMMENT '活动占用',
  `create_user` varchar(30) NOT NULL DEFAULT '' COMMENT '创建者',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_user` varchar(30) NOT NULL DEFAULT '' COMMENT '最后修改者',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_disable` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品库存表';


DROP TABLE IF EXISTS `sku_stock_log`;
CREATE TABLE `sku_stock_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL DEFAULT '0' COMMENT '版本号',
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '操作类型',
  `item_code` varchar(50) NOT NULL DEFAULT '' COMMENT '商品编码',
  `sku_code` varchar(50) NOT NULL DEFAULT '' COMMENT '商品编码',
  `stock` int(11) NOT NULL DEFAULT '0' COMMENT '记录总库存',
  `order_occupancy` int(11) NOT NULL DEFAULT '0' COMMENT '记录预占用',
  `activity_occupancy` int(11) NOT NULL DEFAULT '0' COMMENT '记录活动占用',
  `create_user` varchar(30) NOT NULL DEFAULT '' COMMENT '创建者',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='库存日志记录表';


DROP TABLE IF EXISTS `spec`;
CREATE TABLE `spec` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '分类',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '规格名称(前端显示)',
  `alias` varchar(50) NOT NULL DEFAULT '' COMMENT '别名(后台区分字段)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品规格表';


DROP TABLE IF EXISTS `spec_value`;
CREATE TABLE `spec_value` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `spec_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '属性ID',
  `value` varchar(50) NOT NULL DEFAULT '' COMMENT '值',
  `alias` varchar(50) NOT NULL DEFAULT '' COMMENT '别名(后台区分字段)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品规格值表';


