-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 11, 2016 at 05:04 PM
-- Server version: 5.6.26
-- PHP Version: 5.6.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `feitdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `datoteki`
--

CREATE TABLE IF NOT EXISTS `datoteki` (
  `ID` int(11) NOT NULL,
  `ime` varchar(40) NOT NULL,
  `kreiranje` date NOT NULL,
  `korisnik` char(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `datoteki`
--

INSERT INTO `datoteki` (`ID`, `ime`, `kreiranje`, `korisnik`) VALUES
(15, 'bl123', '2016-04-11', 'uploads/abcd.zip');

-- --------------------------------------------------------

--
-- Table structure for table `korisnici`
--

CREATE TABLE IF NOT EXISTS `korisnici` (
  `ime` varchar(20) NOT NULL,
  `prezime` varchar(20) NOT NULL,
  `kime` varchar(20) NOT NULL,
  `lozinka` varchar(20) NOT NULL,
  `datumR` date NOT NULL,
  `adresa` varchar(50) NOT NULL,
  `profesija` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `korisnici`
--

INSERT INTO `korisnici` (`ime`, `prezime`, `kime`, `lozinka`, `datumR`, `adresa`, `profesija`, `email`) VALUES
('Blagoja', 'Trajkovski', 'bl123', 'bl*89', '1989-04-24', 'Marko Oreskovic', 'Student', 'blagoja.trajkovski1@gmail.com'),
('Ljubica', 'Trajkovska', 'lj56', 'lj*56', '0000-00-00', '', '', ''),
('Petko', 'Petkovski', 'pet123', 'pt56', '1956-04-07', 'Sava Kovacevic', 'Pensioner', 'petko.petkovski@gmail.com'),
('Tihomir', 'Trajkovski', 'tih123', '', '0000-00-00', '', '', ''),
('Trajko', 'Trajkovski', 'tr123', '', '0000-00-00', '', '', ''),
('Xevo', 'Ibraimi', 'xo123', '', '0000-00-00', '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `prijatelstva`
--

CREATE TABLE IF NOT EXISTS `prijatelstva` (
  `ID` int(11) NOT NULL,
  `prijatelstvoOD` varchar(20) NOT NULL,
  `prijatelstvoKON` varchar(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `prijatelstva`
--

INSERT INTO `prijatelstva` (`ID`, `prijatelstvoOD`, `prijatelstvoKON`) VALUES
(3, 'Tihomir', 'Ljubica'),
(23, 'Trajko', 'Blagoja'),
(27, 'Petko', 'Ljubica'),
(28, 'Blagoja', 'Ljubica'),
(29, 'Blagoja', 'Tihomir');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `datoteki`
--
ALTER TABLE `datoteki`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `korisnici`
--
ALTER TABLE `korisnici`
  ADD PRIMARY KEY (`kime`);

--
-- Indexes for table `prijatelstva`
--
ALTER TABLE `prijatelstva`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `datoteki`
--
ALTER TABLE `datoteki`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `prijatelstva`
--
ALTER TABLE `prijatelstva`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=30;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
