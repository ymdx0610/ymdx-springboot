CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `name` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `birthday` date DEFAULT NULL COMMENT '生日',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

insert into t_user(name,password,email,birthday) values('张三','111111','zhangsan@163.com','1985-06-01');
insert into t_user(name,password,email,birthday) values('李四','111111','lisi@163.com','1987-07-02');
insert into t_user(name,password,email,birthday) values('王五','111111','wangwu@qq.com','1989-08-11');
insert into t_user(name,password,email,birthday) values('赵六','111111','zhaoliu@qq.com','1990-09-22');