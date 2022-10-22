-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.33 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for uts
DROP DATABASE IF EXISTS `uts`;
CREATE DATABASE IF NOT EXISTS `uts` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `uts`;

-- Dumping structure for table uts.customer
DROP TABLE IF EXISTS `customer`;
CREATE TABLE IF NOT EXISTS `customer` (
  `idpelangganan` varchar(12) NOT NULL,
  `pesanan` varchar(15) DEFAULT NULL,
  `nomeja` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`idpelangganan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table uts.customer: ~4 rows (approximately)
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` (`idpelangganan`, `pesanan`, `nomeja`) VALUES
	('CZ1', '01', 'cafee Latte'),
	('CZ2', '02', 'CAFFE ESPRESSO'),
	('CZ3', '03', 'Green Tea'),
	('CZ4', '04', 'Taro Latte'),
	('CZ5', '05', 'Sapi Asap');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;

-- Dumping structure for table uts.menu
DROP TABLE IF EXISTS `menu`;
CREATE TABLE IF NOT EXISTS `menu` (
  `code` varchar(12) NOT NULL,
  `namamakanan` varchar(12) DEFAULT NULL,
  `harga` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table uts.menu: ~9 rows (approximately)
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` (`code`, `namamakanan`, `harga`) VALUES
	('01', 'Rp 10000', 'Coffe Latte'),
	('02', 'Rp 12500', 'Espresso'),
	('03', 'Rp 15000', 'Green Tea'),
	('04', 'Rp 15000', 'Taro Latte'),
	('05', 'Rp15000', 'Banana Latte'),
	('06 ', 'Rp 25000', 'Sapi Asap'),
	('07', 'Rp 22500', 'Kentang'),
	('08', 'Rp 40000', 'chicken wing'),
	('09', 'Rp 30000', 'katsu'),
	('10', 'RP 45000', 'beef beacon');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;

-- Dumping structure for table uts.stock
DROP TABLE IF EXISTS `stock`;
CREATE TABLE IF NOT EXISTS `stock` (
  `code` varchar(25) NOT NULL DEFAULT '',
  `namabarang` varchar(25) DEFAULT NULL,
  `jumlah` varchar(25) DEFAULT NULL,
  `tanggalmasuk` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table uts.stock: ~6 rows (approximately)
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` (`code`, `namabarang`, `jumlah`, `tanggalmasuk`) VALUES
	('01', '40', 'Sapi', '2022-10-21'),
	('02', '40', 'Ayam', '2022-10-21'),
	('03 ', '1kg', 'garam', '2022-10-21'),
	('04', '1kg', 'merica', '2022-10-21'),
	('05', '20kg', 'beras', '2022-10-21'),
	('06', '4kg', 'sayur mayur', '2022-10-21');
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
