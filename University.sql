-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 01-03-2021 a las 03:49:30
-- Versión del servidor: 10.4.17-MariaDB
-- Versión de PHP: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `University`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `courses`
--

CREATE TABLE `courses` (
  `id` bigint(20) NOT NULL,
  `class_schedule` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `total_students` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `courses`
--

INSERT INTO `courses` (`id`, `class_schedule`, `name`, `total_students`) VALUES
(1, '10', 'Matematicas', 20),
(2, '16', 'Lengua', 30);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `course_professor`
--

CREATE TABLE `course_professor` (
  `course_id` bigint(20) NOT NULL,
  `professor_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `course_student`
--

CREATE TABLE `course_student` (
  `student_id` bigint(20) NOT NULL,
  `course_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `professors`
--

CREATE TABLE `professors` (
  `id` bigint(20) NOT NULL,
  `active` bit(1) NOT NULL,
  `dni` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `professors`
--

INSERT INTO `professors` (`id`, `active`, `dni`, `last_name`, `name`) VALUES
(1, b'0', '12345678', 'Simpson', 'Homero'),
(2, b'0', '87654321', 'Simpson', 'Bart');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `students`
--

CREATE TABLE `students` (
  `id` bigint(20) NOT NULL,
  `dni` varchar(255) DEFAULT NULL,
  `file` bigint(20) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `students`
--

INSERT INTO `students` (`id`, `dni`, `file`, `last_name`, `name`) VALUES
(1, '14253678', 1234, 'Flanders', 'Ned'),
(2, '86754321', 1111, 'Flanders', 'Mod');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `dni` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id`, `dni`, `last_name`, `name`) VALUES
(1, '111111', 'Admin', 'Admin');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `courses`
--
ALTER TABLE `courses`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `course_professor`
--
ALTER TABLE `course_professor`
  ADD PRIMARY KEY (`course_id`,`professor_id`),
  ADD KEY `FKaw6bexllwt46dvom2asjxp3bo` (`professor_id`);

--
-- Indices de la tabla `course_student`
--
ALTER TABLE `course_student`
  ADD PRIMARY KEY (`student_id`,`course_id`),
  ADD KEY `FKlmsbddqkv96q4nijkrxuof3ug` (`course_id`);

--
-- Indices de la tabla `professors`
--
ALTER TABLE `professors`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_dwrvmewecmwkncpegt3ltp9g0` (`dni`);

--
-- Indices de la tabla `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_cmyptcbd3gj911vn3hhrq7yh` (`dni`),
  ADD UNIQUE KEY `UK_jhr3kle2mex93ohvrj5xc12rx` (`file`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_6aphui3g30h49muho4c91n0yl` (`dni`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `courses`
--
ALTER TABLE `courses`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `professors`
--
ALTER TABLE `professors`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `students`
--
ALTER TABLE `students`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `course_professor`
--
ALTER TABLE `course_professor`
  ADD CONSTRAINT `FKaw6bexllwt46dvom2asjxp3bo` FOREIGN KEY (`professor_id`) REFERENCES `professors` (`id`),
  ADD CONSTRAINT `FKf2fj26dctq6dp1tuesbhe6yow` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`);

--
-- Filtros para la tabla `course_student`
--
ALTER TABLE `course_student`
  ADD CONSTRAINT `FKacc7gn1l63go6x8dsx0wdnr38` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`),
  ADD CONSTRAINT `FKlmsbddqkv96q4nijkrxuof3ug` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
