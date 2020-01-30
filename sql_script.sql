CREATE DATABASE  IF NOT EXISTS `tickethub`;
USE `tickethub`;

DROP TABLE IF EXISTS `customerorders`;
CREATE TABLE `customerorders` (
  `OrderId` int(11) NOT NULL,
  `userName` varchar(40) NOT NULL,
  `orderName` varchar(200) NOT NULL,
  `orderPrice` double DEFAULT NULL,
  `userAddress` text,
  `creditCardNo` varchar(40) DEFAULT NULL,
  `rowNumber` varchar(40) DEFAULT NULL,
  `seatNumber` varchar(20) DEFAULT NULL,
  `zoneInfo` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`OrderId`,`userName`,`orderName`)
) ;

DROP TABLE IF EXISTS `listings`;
CREATE TABLE `listings` (
  `matchIdRef` int(11) DEFAULT NULL,
  `currentPrice` double DEFAULT NULL,
  `deliveryMethodList` text,
  `listingId` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `rowInfo` text,
  `seatNumbers` varchar(30) DEFAULT NULL,
  `sectionName` text,
  `zoneName` text,
  `sellerSectionName` text,
  KEY `matchIdRef` (`matchIdRef`),
  CONSTRAINT `listings_ibfk_1` FOREIGN KEY (`matchIdRef`) REFERENCES `matchlist` (`matchId`) ON DELETE SET NULL ON UPDATE CASCADE
) ;

DROP TABLE IF EXISTS `matchlist`;

CREATE TABLE `matchlist` (
  `matchId` int(11) NOT NULL,
  `matchCategory` varchar(30) DEFAULT NULL,
  `matchName` text,
  `matchStadium` text,
  `matchCity` varchar(30) DEFAULT NULL,
  `matchState` varchar(30) DEFAULT NULL,
  `teamOne` text,
  `teamTwo` text,
  `matchDate` varchar(40) DEFAULT NULL,
  `minPrice` double DEFAULT NULL,
  `maxPrice` double DEFAULT NULL,
  PRIMARY KEY (`matchId`)
) ;


DROP TABLE IF EXISTS `registration`;

CREATE TABLE `registration` (
  `userName` varchar(40) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  `rePassword` varchar(40) DEFAULT NULL,
  `userType` varchar(40) DEFAULT NULL,
  `emailId` varchar(40) DEFAULT NULL
) ;
