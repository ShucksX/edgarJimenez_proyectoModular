-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 12-12-2023 a las 02:41:01
-- Versión del servidor: 10.5.20-MariaDB
-- Versión de PHP: 7.3.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `id20342822_bd22313_alzh_edg`
--
CREATE DATABASE IF NOT EXISTS `id20342822_bd22313_alzh_edg` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `id20342822_bd22313_alzh_edg`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `centros`
--

CREATE TABLE `centros` (
  `id` int(11) NOT NULL,
  `nombre` text NOT NULL,
  `pais` text NOT NULL,
  `municipio` text NOT NULL,
  `localidad` text NOT NULL,
  `direccion` text NOT NULL,
  `numerotelefono` text DEFAULT NULL,
  `correo` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `centros`
--

INSERT INTO `centros` (`id`, `nombre`, `pais`, `municipio`, `localidad`, `direccion`, `numerotelefono`, `correo`) VALUES
(1, 'Centro de atencion Alzh', 'Mexico', 'Jalisco', 'Real del Valle', 'Santa Monica 1321', '123123123', 'centroatentiones@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `colores`
--

CREATE TABLE `colores` (
  `id` int(11) NOT NULL,
  `idusuario` int(11) NOT NULL,
  `puntos` int(11) NOT NULL,
  `fecharesultado` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `colores`
--

INSERT INTO `colores` (`id`, `idusuario`, `puntos`, `fecharesultado`) VALUES
(1, 13, 4, '2023-07-18'),
(2, 14, 4, '2023-07-20'),
(3, 13, 4, '2023-08-05'),
(4, 13, 3, '2023-08-05'),
(5, 13, 5, '2023-08-05'),
(6, 13, 4, '2023-08-07'),
(7, 13, 4, '2023-08-07'),
(8, 14, 5, '2023-10-21');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `laberintos`
--

CREATE TABLE `laberintos` (
  `id` int(11) NOT NULL,
  `idusuario` int(11) NOT NULL,
  `tiempo` int(11) NOT NULL,
  `fecharesultado` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `laberintos`
--

INSERT INTO `laberintos` (`id`, `idusuario`, `tiempo`, `fecharesultado`) VALUES
(1, 13, 100, '2023-07-18'),
(2, 14, 87, '2023-07-20'),
(3, 13, 94, '2023-08-07');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `palabras`
--

CREATE TABLE `palabras` (
  `id` int(11) NOT NULL,
  `idusuario` int(20) NOT NULL,
  `palabra1` varchar(100) NOT NULL,
  `palabra2` varchar(100) NOT NULL,
  `palabra3` varchar(100) NOT NULL,
  `status` int(1) NOT NULL,
  `fechaaccesible` date NOT NULL,
  `fecharespuesta` date DEFAULT NULL,
  `puntos` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `palabras`
--

INSERT INTO `palabras` (`id`, `idusuario`, `palabra1`, `palabra2`, `palabra3`, `status`, `fechaaccesible`, `fecharespuesta`, `puntos`) VALUES
(9, 13, 'NARIZ', 'AGUA', 'LAPIZ', 1, '2023-07-08', '2023-07-09', 3),
(10, 13, 'DESECHO', 'FRESA', 'OJO', 1, '2023-07-19', '2023-07-20', 3),
(11, 13, 'TECHO', 'PLUMA', 'AZUL', 1, '2023-07-23', '2023-08-05', 2),
(12, 13, 'PLUMA', 'COCINA', 'OIDO', 1, '2023-08-06', '2023-08-07', 2),
(13, 13, 'HUESO', 'AGUA', 'LAPIZ', 1, '2023-08-06', '2023-08-07', 2),
(14, 14, 'LAPIZ', 'PLUMA', 'COLUMPIO', 1, '2023-10-04', '2023-10-21', 3),
(15, 14, 'AZUL', 'OJO', 'HUESO', 0, '2023-10-22', NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sopas`
--

CREATE TABLE `sopas` (
  `id` int(11) NOT NULL,
  `idusuario` int(11) NOT NULL,
  `tiempo` int(11) NOT NULL,
  `fecharesultado` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `sopas`
--

INSERT INTO `sopas` (`id`, `idusuario`, `tiempo`, `fecharesultado`) VALUES
(1, 13, 69, '2023-07-18'),
(2, 14, 154, '2023-07-20'),
(3, 13, 93, '2023-08-07');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(20) NOT NULL,
  `nombre` varchar(250) NOT NULL,
  `contrasena` varchar(50) NOT NULL,
  `correo` varchar(250) NOT NULL,
  `fechanac` date NOT NULL,
  `pais` varchar(250) NOT NULL,
  `municipio` varchar(250) NOT NULL,
  `localidad` varchar(250) NOT NULL,
  `status` int(1) NOT NULL DEFAULT 0,
  `activationcode` varchar(255) NOT NULL,
  `postingdate` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Proyecto modular';

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `nombre`, `contrasena`, `correo`, `fechanac`, `pais`, `municipio`, `localidad`, `status`, `activationcode`, `postingdate`) VALUES
(1, 'Edgar', 'b5452b4a5a850623001d552d9fc3caf9', 'edgarjigh@gmail.com', '1999-09-08', 'Mexico', 'Jalisco', 'Real', 1, 'bd2b989f5ccc55e97f1233942923baf7', '2023-06-05 18:53:09'),
(12, 'Edgardo Jija', '9c2f924fb2f7004e7979ab2027ca1d65', 'edgae', '2023-06-14', 'waewa', 'awewa', 'awewa', 0, '4a52b247273335ab0a8b9697370b19b7', '2023-06-05 22:33:32'),
(13, 'Edgar Jimenez', '9c2f924fb2f7004e7979ab2027ca1d65', 'edgarjagh@gmail.com', '1999-09-08', 'Mexico', 'Jalisco', 'Real del Valle', 2, 'f2cb16c0c263792f2b1148040711fb29', '2023-06-05 22:34:25'),
(14, 'Edgarde', '9c2f924fb2f7004e7979ab2027ca1d65', 'edgamin', '2023-06-14', 'Mexico', 'Jalisco', 'Real del Valle', 2, 'b5562d550b7162f15568e2424d2ed872', '2023-06-05 22:34:55'),
(15, 'awdawdad wadwd ', 'd2f2297d6e829cd3493aa7de4416a18f', 'ewadawd', '2023-10-11', 'awd', 'awd', 'awd', 0, 'c80287e0d1056c1efb76cb67e8cc5d1f', '2023-06-05 22:45:40'),
(16, 'aweawe waeadwa', 'e088464dc9f2c66d341b4054266f2e66', 'aweaw', '2023-06-14', 'aweawd', 'awdaw', 'awda', 0, 'b2493a8c0c9fc7ea30275bc6a821f3f0', '2023-06-05 22:48:01'),
(17, 'edga', 'd2f2297d6e829cd3493aa7de4416a18f', 'fefe', '2023-10-04', 'fefe', 'fafa', 'feafe', 0, '1c6e6833fd8ca65af183354e3b67844a', '2023-10-22 01:35:13');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `centros`
--
ALTER TABLE `centros`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `colores`
--
ALTER TABLE `colores`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `laberintos`
--
ALTER TABLE `laberintos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `palabras`
--
ALTER TABLE `palabras`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `sopas`
--
ALTER TABLE `sopas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `centros`
--
ALTER TABLE `centros`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `colores`
--
ALTER TABLE `colores`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `laberintos`
--
ALTER TABLE `laberintos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `palabras`
--
ALTER TABLE `palabras`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `sopas`
--
ALTER TABLE `sopas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
