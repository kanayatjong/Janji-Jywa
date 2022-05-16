-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 10, 2022 at 11:41 AM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `janji_jywa`
--

-- --------------------------------------------------------

--
-- Table structure for table `beverages`
--

CREATE TABLE `beverages` (
  `BeverageID` char(5) DEFAULT NULL,
  `BeverageName` varchar(30) DEFAULT NULL,
  `BeverageType` varchar(30) DEFAULT NULL,
  `BeveragePrice` int(11) DEFAULT NULL,
  `BeverageStock` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `beverages`
--

INSERT INTO `beverages` (`BeverageID`, `BeverageName`, `BeverageType`, `BeveragePrice`, `BeverageStock`) VALUES
('BE001', 'Boba Ashiap', 'Coffee', 10000, 10),
('BE002', 'Es Jeruk', 'Smoothies', 15000, 88),
('BE003', 'Mango smoothie', 'Smoothies', 20000, 90),
('BE004', 'Boba sedih', 'Boba', 30000, 110),
('BE006', 'jus Melon', 'Smoothies', 13000, 8),
('BE008', 'Kopi Luwak', 'Coffee', 20000, 3),
('BE010', 'Es Green Tea', 'Tea', 20000, 6);

-- --------------------------------------------------------

--
-- Table structure for table `carts`
--

CREATE TABLE `carts` (
  `UserID` char(5) NOT NULL,
  `BeverageID` char(5) NOT NULL,
  `Quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `detailtransactions`
--

CREATE TABLE `detailtransactions` (
  `TransactionID` char(5) NOT NULL,
  `BeverageID` char(5) NOT NULL,
  `Quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `detailtransactions`
--

INSERT INTO `detailtransactions` (`TransactionID`, `BeverageID`, `Quantity`) VALUES
('TR001', 'BE001', 22),
('TR002', 'BE002', 3),
('TR002', 'BE004', 2),
('TR003', 'BE003', 5),
('TR003', 'BE004', 8),
('TR004', 'BE002', 2),
('TR005', 'BE002', 5),
('TR005', 'BE003', 3),
('TR006', 'BE002', 2),
('TR006', 'BE003', 5);

-- --------------------------------------------------------

--
-- Table structure for table `headertransactions`
--

CREATE TABLE `headertransactions` (
  `TransactionID` char(5) NOT NULL,
  `UserID` char(5) DEFAULT NULL,
  `TransactionDate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `headertransactions`
--

INSERT INTO `headertransactions` (`TransactionID`, `UserID`, `TransactionDate`) VALUES
('TR001', 'US002', '2021-05-28'),
('TR002', 'US002', '2021-05-28'),
('TR003', 'US002', '2022-01-08'),
('TR004', 'US003', '2022-01-08'),
('TR005', 'US003', '2022-01-08'),
('TR006', 'US002', '2022-01-10');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `UserID` char(5) DEFAULT NULL,
  `UserName` varchar(30) DEFAULT NULL,
  `UserEmail` varchar(50) DEFAULT NULL,
  `UserPassword` varchar(30) DEFAULT NULL,
  `UserDOB` date DEFAULT NULL,
  `UserGender` varchar(10) DEFAULT NULL,
  `UserAddress` varchar(255) DEFAULT NULL,
  `UserPhone` varchar(30) DEFAULT NULL,
  `UserRole` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`UserID`, `UserName`, `UserEmail`, `UserPassword`, `UserDOB`, `UserGender`, `UserAddress`, `UserPhone`, `UserRole`) VALUES
('US001', 'Revaldi Mijaya', 'admin@gmail.com', 'admin123', NULL, 'male', 'Mangga Street', '0920398193812399', 'Admin'),
('US002', 'daniela', 'customer@gmail.com', 'customer123', NULL, 'female', 'binusmaya Street', '012345678989', 'Customer'),
('US003', 'candraa', 'candaa@gmail.com', 'candra1234', NULL, 'female', 'Jeruk Bali Street', '102938471688', 'Customer'),
('US004', 'kanaya', 'kana@gmail.com', 'kanaya123', NULL, 'female', 'Semangka Street', '098273615263', 'Customer'),
('US005', 'Pipin May', 'pipinMay@gmail.com', 'pipinmay123', NULL, 'female', 'pirsak Street', '092837163526', 'Admin'),
('US006', 'Anggora', 'anggora@gmail.com', 'anggora123', NULL, 'male', 'kucing Street', '092837481920', 'Admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `carts`
--
ALTER TABLE `carts`
  ADD PRIMARY KEY (`UserID`,`BeverageID`);

--
-- Indexes for table `detailtransactions`
--
ALTER TABLE `detailtransactions`
  ADD PRIMARY KEY (`TransactionID`,`BeverageID`);

--
-- Indexes for table `headertransactions`
--
ALTER TABLE `headertransactions`
  ADD PRIMARY KEY (`TransactionID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
