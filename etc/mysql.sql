CREATE DATABASE `sba_load_monitor` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE `load_1m` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(50) NOT NULL,
  `port` int(11) DEFAULT NULL,
  `host_name` varchar(50) NOT NULL,
  `count` int(11) NOT NULL,
  `minute` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='load in 1 minute';

CREATE TABLE `load_1m_aggr` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(50) NOT NULL,
  `count` int(11) NOT NULL,
  `minute` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='load in 1 minute aggregated';
