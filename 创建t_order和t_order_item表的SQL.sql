USE book;

CREATE TABLE t_order(
	order_id VARCHAR(50) PRIMARY KEY,
	create_time DATETIME,
	price DECIMAL(11,2),
	`status` VARCHAR(50),
	user_id INT,
	FOREIGN KEY(user_id)REFERENCES t_user(id)
);

CREATE TABLE t_order_item(
	id INT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(100),
	`count` INT,
	`price` DECIMAL(11,2),
	total_price DECIMAL(11,2),
	order_id VARCHAR(50),
	FOREIGN KEY(order_id)REFERENCES t_order(order_id)
);


INSERT INTO t_order(order_id,create_time,price,`status`,user_id)VALUES('12154','2021-10-06 16:21:02','未发货','100','1');

SELECT order_id orderId,create_time createTime,price,`status`,user_id userId  FROM t_order;

SELECT * FROM t_order;

SELECT * FROM t_order WHERE user_id =1;