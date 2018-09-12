-- 导出 base 的数据库结构
CREATE DATABASE IF NOT EXISTS `base` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `base`;


-- 导出  表 base.cloud_disk_t 结构
CREATE TABLE IF NOT EXISTS `cloud_disk_t` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '资源名称',
  `link` varchar(100) NOT NULL COMMENT '下载链接',
  `code` varchar(10) DEFAULT NULL COMMENT '提取码',
  `res_info` text COMMENT '资源详情',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='云盘链接';

-- 正在导出表  base.cloud_disk_t 的数据：~3 rows (大约)
INSERT INTO `cloud_disk_t` (`id`, `name`, `link`, `code`) VALUES
    (3, '余罪第一季', 'http://pan.baidu.com/s/1c2gs86k', 'h84p'),
    (4, '余罪第二季', 'http://pan.baidu.com/s/1dETztnj', 'kri7');