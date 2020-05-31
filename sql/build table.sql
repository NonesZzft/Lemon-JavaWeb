SELECT * FROM stock.stock;

show create table stock;

show create table bank;

show create table user;

show create table buy;

drop table buy;
drop table bank;
drop table stock;
drop table user;

CREATE TABLE `stock` (
  `name` varchar(45) NOT NULL,
  `price` float NOT NULL,
  `time` datetime NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`name`,`time`)
) ;

CREATE TABLE `user` (
  `name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `balance` float NOT NULL,
  PRIMARY KEY (`name`)
) ;

CREATE TABLE `bank` (
  `accounting` varchar(45) NOT NULL,
  `routing` varchar(45) NOT NULL,
  `balance` float NOT NULL,
  `u_name` varchar(45) NOT NULL,
  PRIMARY KEY (`accounting`),
  KEY `name_idx` (`u_name`),
  CONSTRAINT `u_name` FOREIGN KEY (`u_name`) REFERENCES `user` (`name`)
) ;

CREATE TABLE `buy` (
  `u_name` varchar(45) NOT NULL,
  `s_name` varchar(45) NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`u_name`,`s_name`),
  KEY `stock_idx` (`s_name`),
  CONSTRAINT `stock` FOREIGN KEY (`s_name`) REFERENCES `stock` (`name`),
  CONSTRAINT `user` FOREIGN KEY (`u_name`) REFERENCES `user` (`name`)
) ;
