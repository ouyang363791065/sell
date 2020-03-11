# sell
wechat sell

sql:
CREATE TABLE `product_info` (
	`product_id` VARCHAR ( 32 ) NOT NULL,
	`product_name` VARCHAR ( 64 ) NOT NULL COMMENT '商品名称',
	-- 	八位，2位小数
	`product_price` DECIMAL ( 8, 2 ) NOT NULL COMMENT '商品价格',
	`product_stock` INT NOT NULL COMMENT '库存',
	`product_description` VARCHAR ( 64 ) COMMENT '描述',
	`product_icon` VARCHAR ( 512 ) COMMENT '小图',
	`category_type` INT NOT NULL COMMENT '类目编号',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY ( `product_id` ) 
) COMMENT '商品表';



CREATE TABLE `product_category` (

	`category_id` INT NOT NULL auto_increment,
	`category_name` VARCHAR ( 64 ) NOT NULL COMMENT '类目名称',
-- 	因为id是自增的，类目编号我们需要自定义，分别代表不同的意思
	`category_type` INT NOT NULL COMMENT '类目编号',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY ( `category_id` ),
	UNIQUE KEY `uqe_category_type` ( `category_type` )

) COMMENT '类目表'; 



CREATE TABLE `order_master` ( 

	`order_id` VARCHAR ( 32 ) NOT NULL,
	`buyer_name` VARCHAR ( 64 ) NOT NULL COMMENT '买家名字',
-- 	因为id是自增的，类目编号我们需要自定义，分别代表不同的意思
	`buyer_phone` VARCHAR ( 32 ) NOT NULL COMMENT '买家电话',
	`buyer_address` VARCHAR ( 128 ) NOT NULL COMMENT '买家地址',
	`buyer_openid` VARCHAR ( 64 ) NOT NULL COMMENT '买家微信id',
	`order_amount` DECIMAL ( 8, 2 ) NOT NULL COMMENT '订单总金额',
	`order_status` TINYINT ( 3 ) NOT NULL DEFAULT '0' COMMENT '订单状态，默认0为新下单',
	`pay_status` TINYINT ( 3 ) NOT NULL DEFAULT '0' COMMENT '支付状态，默认0未支付',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY ( `order_id` ),
-- 	因为没有用户注册，而且要根据用户微信openid来查，所以设置索引
	KEY `idx_buyer_openid` ( 'buyer_openid' )

) COMMENT '订单表';





CREATE TABLE `order_detail` ( 

	`detail_id` VARCHAR ( 32 ) NOT NULL,
	`order_id` VARCHAR ( 32 ) NOT NULL,
	`product_id` VARCHAR ( 32 ) NOT NULL,
	`product_name` VARCHAR ( 64 ) NOT NULL COMMENT '商品名称',
	`product_price` DECIMAL ( 8, 2 ) NOT NULL COMMENT '商品价格',
	`product_quantity` INT NOT NULL COMMENT '商品数量',
	`product_icon` VARCHAR ( 512 ) NOT NULL COMMENT '商品小图',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY ( `detail_id` ),
-- 	会拿订单id来查，所以创建索引
	KEY `idx_order_id` ( `order_id` )

) COMMENT '订单详情表';


