-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 10, 2022 at 06:02 PM
-- Server version: 10.4.20-MariaDB
-- PHP Version: 8.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `student_service`
--

-- --------------------------------------------------------

--
-- Table structure for table `active_exams`
--

CREATE TABLE `active_exams` (
  `student_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `active_exams`
--

INSERT INTO `active_exams` (`student_id`, `id`) VALUES
(3, 3),
(4, 3),
(5, 2);

-- --------------------------------------------------------

--
-- Table structure for table `city`
--

CREATE TABLE `city` (
  `zipcode` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `city`
--

INSERT INTO `city` (`zipcode`, `name`) VALUES
('11000', 'Beograd'),
('21000', 'Novi Sad');

-- --------------------------------------------------------

--
-- Table structure for table `exam`
--

CREATE TABLE `exam` (
  `id` bigint(20) NOT NULL,
  `date` datetime DEFAULT NULL,
  `exam_term` bigint(20) DEFAULT NULL,
  `subject` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `exam`
--

INSERT INTO `exam` (`id`, `date`, `exam_term`, `subject`) VALUES
(1, '2022-07-10 22:00:00', 1, 2),
(2, '2022-07-11 22:00:00', 1, 4),
(3, '2022-07-12 20:00:00', 1, 5),
(4, '2022-07-12 00:00:00', 1, 6),
(5, '2022-07-13 22:00:00', 1, 7),
(6, '2022-08-11 00:00:00', 2, 2),
(8, '2022-08-17 00:00:00', 2, 7),
(10, '2022-09-09 00:00:00', 3, 5);

-- --------------------------------------------------------

--
-- Table structure for table `exam_term`
--

CREATE TABLE `exam_term` (
  `exam_term_id` bigint(20) NOT NULL,
  `end_date` datetime DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `exam_term`
--

INSERT INTO `exam_term` (`exam_term_id`, `end_date`, `is_active`, `name`, `start_date`) VALUES
(1, '2022-07-14 20:00:00', b'1', 'Julski', '2022-06-30 20:00:00'),
(2, '2022-08-20 00:00:00', b'0', 'Avgustovski', '2022-08-10 00:00:00'),
(3, '2022-09-12 00:00:00', b'0', 'Septembarski', '2022-09-02 00:00:00'),
(4, '2022-10-10 00:00:00', b'0', 'Oktobarski', '2022-09-26 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `passed_exam`
--

CREATE TABLE `passed_exam` (
  `passed_exam_id` bigint(20) NOT NULL,
  `grade` int(11) DEFAULT NULL,
  `exam` bigint(20) DEFAULT NULL,
  `student` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `passed_exam`
--

INSERT INTO `passed_exam` (`passed_exam_id`, `grade`, `exam`, `student`) VALUES
(1, 8, 5, 5),
(3, 8, 2, 5);

-- --------------------------------------------------------

--
-- Table structure for table `professor`
--

CREATE TABLE `professor` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `reelection_date` datetime DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `title` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `professor`
--

INSERT INTO `professor` (`id`, `address`, `email`, `first_name`, `last_name`, `phone_number`, `reelection_date`, `city`, `title`) VALUES
(1, 'Goranska', 'goran@goranski.com', 'Goran', 'Goranski', '123456789', '2022-07-29 20:00:00', '11000', 1),
(4, 'Markova', 'marko@markovski.com', 'Marko', 'Markovski', '123456789', '2022-07-28 22:00:00', '21000', 2),
(5, 'Ivanina', 'ivana@ivanovski.com', 'Ivana', 'Ivanovski', '123456789', '2022-07-29 22:00:00', '11000', 1),
(6, 'Pavlova', 'pavle@pavlovski.com', 'Pavle', 'Pavlovski', '123456789', '2022-07-23 00:00:00', '11000', 1),
(7, 'Lukina', 'luka@lukovski.com', 'Luka', 'Lukovski', '123456789', '2022-07-28 18:00:00', '21000', 1);

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `student_id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `index_number` varchar(255) DEFAULT NULL,
  `index_year` int(11) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `year_of_study` int(11) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`student_id`, `address`, `email`, `first_name`, `index_number`, `index_year`, `last_name`, `year_of_study`, `city`) VALUES
(1, 'Zoranina', 'zorana@zoric.com', 'Zorana', '0012', 2021, 'Zoric', 1, '21000'),
(2, 'Petrina', 'petra@petrovic.com', 'Petra', '0013', 2018, 'Petrovic', 2, '11000'),
(3, 'Milicina', 'milica@milicic.com', 'Milica', '0017', 2018, 'Milicic', 3, '21000'),
(4, 'Jovanova', 'jovan@jovanovic.com', 'Jovan', '0001', 2017, 'Jovanovic', 4, '21000'),
(5, 'Brankova', 'branko@brankovic.com', 'Branko', '0002', 2015, 'Brankovic', 5, '11000');

-- --------------------------------------------------------

--
-- Table structure for table `subject`
--

CREATE TABLE `subject` (
  `subject_id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `no_ofesp` int(11) DEFAULT NULL,
  `semester` varchar(255) DEFAULT NULL,
  `year_of_study` int(11) DEFAULT NULL,
  `professor` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `subject`
--

INSERT INTO `subject` (`subject_id`, `description`, `name`, `no_ofesp`, `semester`, `year_of_study`, `professor`) VALUES
(2, 'Aritmetika', 'Aritmetika', 5, 'Winter', 1, 1),
(4, 'Matematika', 'Matematika', 5, 'Summer', 2, 5),
(5, 'Algebra', 'Algebra', 4, 'Winter', 3, 7),
(6, 'Geometrija', 'Geometrija', 4, 'Winter', 4, 6),
(7, 'Primenjena Matematika', 'Primenjena Matematika', 6, 'Summer', 5, 4);

-- --------------------------------------------------------

--
-- Table structure for table `title`
--

CREATE TABLE `title` (
  `title_id` bigint(20) NOT NULL,
  `title` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `title`
--

INSERT INTO `title` (`title_id`, `title`) VALUES
(2, 'Assistant professor'),
(1, 'Professor');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `active_exams`
--
ALTER TABLE `active_exams`
  ADD PRIMARY KEY (`student_id`,`id`),
  ADD KEY `FKc174t19ly9fj4umvtanqtmfdh` (`id`);

--
-- Indexes for table `city`
--
ALTER TABLE `city`
  ADD PRIMARY KEY (`zipcode`);

--
-- Indexes for table `exam`
--
ALTER TABLE `exam`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKi4mjlqexx19vspry5a7t0bf99` (`exam_term`,`subject`),
  ADD KEY `FK3csjssdryi00tqvoi4qc86jv0` (`subject`);

--
-- Indexes for table `exam_term`
--
ALTER TABLE `exam_term`
  ADD PRIMARY KEY (`exam_term_id`);

--
-- Indexes for table `passed_exam`
--
ALTER TABLE `passed_exam`
  ADD PRIMARY KEY (`passed_exam_id`),
  ADD UNIQUE KEY `UKnx1oyfq1ke2h5fgsbdmvl5jma` (`exam`,`student`),
  ADD KEY `FK5nv6rb5oei772j6tmyu892m79` (`student`);

--
-- Indexes for table `professor`
--
ALTER TABLE `professor`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_qjm28ojevoom770jyieljec3e` (`email`),
  ADD KEY `FKc30nh0nrkt1meks4sa9hx4fgb` (`city`),
  ADD KEY `FK5l5wnf4kh6egu4wcq4lvl6djy` (`title`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`student_id`),
  ADD UNIQUE KEY `UK_fe0i52si7ybu0wjedj6motiim` (`email`),
  ADD KEY `FKioreet3fqymgjtq06ussgsso5` (`city`);

--
-- Indexes for table `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`subject_id`),
  ADD KEY `FKrey4v6ogfq80dmhc55l00qa5r` (`professor`);

--
-- Indexes for table `title`
--
ALTER TABLE `title`
  ADD PRIMARY KEY (`title_id`),
  ADD UNIQUE KEY `UK_a0o656wlcyr2exdg1ib3iemc4` (`title`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `exam`
--
ALTER TABLE `exam`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `exam_term`
--
ALTER TABLE `exam_term`
  MODIFY `exam_term_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `passed_exam`
--
ALTER TABLE `passed_exam`
  MODIFY `passed_exam_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `professor`
--
ALTER TABLE `professor`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `student_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `subject`
--
ALTER TABLE `subject`
  MODIFY `subject_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `title`
--
ALTER TABLE `title`
  MODIFY `title_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `active_exams`
--
ALTER TABLE `active_exams`
  ADD CONSTRAINT `FK3k9hxx43jgikwsdr2yfhgwjo4` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`),
  ADD CONSTRAINT `FKc174t19ly9fj4umvtanqtmfdh` FOREIGN KEY (`id`) REFERENCES `exam` (`id`);

--
-- Constraints for table `exam`
--
ALTER TABLE `exam`
  ADD CONSTRAINT `FK3csjssdryi00tqvoi4qc86jv0` FOREIGN KEY (`subject`) REFERENCES `subject` (`subject_id`),
  ADD CONSTRAINT `FKcbtyaggv5crfrdmwpkxagl7to` FOREIGN KEY (`exam_term`) REFERENCES `exam_term` (`exam_term_id`);

--
-- Constraints for table `passed_exam`
--
ALTER TABLE `passed_exam`
  ADD CONSTRAINT `FK5nv6rb5oei772j6tmyu892m79` FOREIGN KEY (`student`) REFERENCES `student` (`student_id`),
  ADD CONSTRAINT `FKhj4vohiaudggmgtl3sf5pb8js` FOREIGN KEY (`exam`) REFERENCES `exam` (`id`);

--
-- Constraints for table `professor`
--
ALTER TABLE `professor`
  ADD CONSTRAINT `FK5l5wnf4kh6egu4wcq4lvl6djy` FOREIGN KEY (`title`) REFERENCES `title` (`title_id`),
  ADD CONSTRAINT `FKc30nh0nrkt1meks4sa9hx4fgb` FOREIGN KEY (`city`) REFERENCES `city` (`zipcode`);

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `FKioreet3fqymgjtq06ussgsso5` FOREIGN KEY (`city`) REFERENCES `city` (`zipcode`);

--
-- Constraints for table `subject`
--
ALTER TABLE `subject`
  ADD CONSTRAINT `FKrey4v6ogfq80dmhc55l00qa5r` FOREIGN KEY (`professor`) REFERENCES `professor` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
