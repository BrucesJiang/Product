/*
SQLyog Enterprise Trial - MySQL GUI v7.11 
MySQL - 5.0.87-community-nt : Database - product
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`product` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `product`;

/*Table structure for table `orderitem` */

DROP TABLE IF EXISTS `orderitem`;

CREATE TABLE `orderitem` (
  `order_id` varchar(100) NOT NULL default '',
  `product_id` varchar(100) NOT NULL default '',
  `buynum` int(11) default NULL,
  PRIMARY KEY  (`order_id`,`product_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `orderitem_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `orderitem_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orderitem` */

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `id` varchar(100) NOT NULL default '',
  `receiverName` varchar(20) default NULL,
  `receiverPhone` varchar(20) default NULL,
  `paystate` int(11) default NULL,
  `ordertime` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `user_id` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orders` */

/*Table structure for table `products` */

DROP TABLE IF EXISTS `products`;

CREATE TABLE `products` (
  `id` varchar(100) NOT NULL default '',
  `name` varchar(40) default NULL,
  `category` varchar(40) default NULL,
  `pnum` int(11) default NULL,
  `imgurl` varchar(100) default NULL,
  `description` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `products` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(20) default NULL,
  `password` varchar(32) default NULL,
  `gender` varchar(10) default NULL,
  `email` varchar(50) default NULL,
  `telephone` varchar(20) default NULL,
  `introduce` varchar(100) default NULL,
  `activeCode` varchar(50) default NULL,
  `state` int(11) default '0',
  `role` varchar(10) NOT NULL default '普通用户',
  `registTime` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`gender`,`email`,`telephone`,`introduce`,`activeCode`,`state`,`role`,`registTime`) values (1,'BruceJiang','123456','男','hntkjyn@163.com','13043266625','发顺丰的',NULL,1,'普通用户','2018-01-27 22:08:18'),(2,'tom','123456','男','bruces.jiang@gmail.com','13043266625','hah',NULL,1,'admin','2018-01-27 22:15:16'),(3,'Bruce','123456','男','hntkjyn@163.com ','13043266625','来自北京','a4bad4da-c028-4c01-8292-97ba615dadac',0,'普通用户','2018-01-31 20:05:02'),(4,'Bruce','123456','男','hntkjyn@163.com ','13043266625','来自北京','dab610b0-6fa6-4fe1-b419-feeed7841ac3',0,'普通用户','2018-02-01 14:48:16'),(5,'Bruce','123456','男','hntkjyn@163.com','13043266625','fdsf ','e64f7e6d-a756-4dc7-9765-c1949fea7f87',1,'普通用户','2018-02-01 23:10:31');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
