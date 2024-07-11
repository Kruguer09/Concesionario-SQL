-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-10-2021 a las 10:14:20
-- Versión del servidor: 10.4.21-MariaDB
-- Versión de PHP: 8.0.10

CREATE DATABASE IF NOT EXISTS `neumaticos` CHARACTER SET utf8 COLLATE utf8_spanish2_ci;;
USE `neumaticos`;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";



/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `neumaticos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `nif` varchar(9) COLLATE utf8_spanish2_ci NOT NULL,
  `nombre` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  `direccion` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `num` int(5) NOT NULL,
  `cp` int(7) NOT NULL,
  `poblacion` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `provincia` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `telefono` varchar(12) COLLATE utf8_spanish2_ci NOT NULL,
  `email` varchar(100) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`nif`, `nombre`, `direccion`, `num`, `cp`, `poblacion`, `provincia`, `telefono`, `email`) VALUES
('12345678A', 'Alicia', 'Calle de la Alegría', 20, 14001, 'Madrid', 'Madrid', '999999999', 'alicia@gmail.com'),
('12345678B', 'Bartolomé', 'Avenida del Pesar', 36, 25003, 'Salamanca', 'Salamanca', '999999999', 'bartolo@hotmail.com'),
('12345678C', 'Carlos', 'Plaza del manzano', 10, 18005, 'Alpujarras', 'Granada', '999999999', 'applelane@gmail.com'),
('12345678D', 'Davinia', 'Plaza de la constitución', 4, 10002, 'Tomelloso', 'Ciudad Real', '999999999', 'abulta@gmail.com'),
('12345678E', 'Eloisa', 'Calle del pesar', 56, 56004, 'Boadilla del monte', 'Madrid', '999999999', 'vivaelmonte@yahoo.es'),
('12345678F', 'Fernando', 'Avenida del Juicio', 4, 14003, 'Córdoba', 'Córdoba', '999999999', 'feliz@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

CREATE TABLE `factura` (
  `numfactura` int(9) NOT NULL,
  `nifcliente` varchar(9) COLLATE utf8_spanish2_ci NOT NULL,
  `nifemisor` varchar(9) COLLATE utf8_spanish2_ci NOT NULL,
  `fechasistema` varchar(10) COLLATE utf8_spanish2_ci NOT NULL,
  `base` double(10,2) NOT NULL,
  `iva` double(10,2) NOT NULL,
  `total` double(10,2) NOT NULL,
  `pagada` tinyint(1) NOT NULL,
  `numcuenta` varchar(100) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `factura`
--

INSERT INTO `factura` (`numfactura`, `nifcliente`, `nifemisor`, `fechasistema`, `base`, `iva`, `total`, `pagada`, `numcuenta`) VALUES
(1, '12345678B', '99999999Z', '31/08/2021', 3700.00, 777.00, 4477.00, 0, 'ES99-9999-9999-99-999999999'),
(2, '12345678E', '99999999Z', '15/09/2021', 3500.00, 735.00, 4235.00, 0, 'ES99-9999-9999-99-999999999');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `linea_factura`
--

CREATE TABLE `linea_factura` (
  `numlinea` int(9) NOT NULL,
  `numfactura` int(9) NOT NULL,
  `codneumatico` int(11) NOT NULL,
  `concepto` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `numneumaticos` int(11) NOT NULL,
  `precio` double(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `linea_factura`
--

INSERT INTO `linea_factura` (`numlinea`, `numfactura`, `codneumatico`, `concepto`, `numneumaticos`, `precio`) VALUES
(1, 1, 1, 'MICHELIN', 2, 250.00),
(2, 1, 2, 'GOODYEAR', 2, 700.00),
(3, 1, 4, 'MAXXIS', 1, 1800.00),
(4, 2, 2, 'GOODYEAR', 5, 700.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `neumatico`
--

CREATE TABLE `neumatico` (
  `cod` int(11) NOT NULL,
  `marca` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  `modelo` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  `ancho` int(9) NOT NULL,
  `perfil` varchar(20) COLLATE utf8_spanish2_ci NOT NULL CHECK (`perfil` in ('BAJO','MEDIO','ALTO')),
  `diametro` double(7,2) NOT NULL,
  `IC` int(5) NOT NULL CHECK (`IC` >= 70 and `IC` <= 120),
  `IV` varchar(50) COLLATE utf8_spanish2_ci NOT NULL CHECK (`IV` in ('H','N','Q','R','S','T','V','W','Y')),
  `precio` double(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `neumatico`
--

INSERT INTO `neumatico` (`cod`, `marca`, `modelo`, `ancho`, `perfil`, `diametro`, `IC`, `IV`, `precio`) VALUES
(1, 'MICHELIN', 'ZR17', 100, 'BAJO', 250.00, 80, 'R', 250.00),
(2, 'GOODYEAR', 'SUV', 100, 'MEDIO', 455.00, 100, 'H', 700.00),
(3, 'HANKOOK', '205 55 r16', 150, 'BAJO', 150.00, 120, 'T', 300.00),
(4, 'MAXXIS', 'AP2 ALL SEASON', 100, 'ALTO', 200.00, 90, 'W', 1800.00);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`nif`);

--
-- Indices de la tabla `factura`
--
ALTER TABLE `factura`
  ADD PRIMARY KEY (`numfactura`),
  ADD KEY `factura_fk_1` (`nifcliente`);

--
-- Indices de la tabla `linea_factura`
--
ALTER TABLE `linea_factura`
  ADD PRIMARY KEY (`numlinea`,`numfactura`),
  ADD KEY `linea_factura_fk_1` (`numfactura`),
  ADD KEY `linea_factura_fk_2` (`codneumatico`);

--
-- Indices de la tabla `neumatico`
--
ALTER TABLE `neumatico`
  ADD PRIMARY KEY (`cod`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `factura`
--
ALTER TABLE `factura`
  MODIFY `numfactura` int(9) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `linea_factura`
--
ALTER TABLE `linea_factura`
  MODIFY `numlinea` int(9) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `factura`
--
ALTER TABLE `factura`
  ADD CONSTRAINT `factura_fk_1` FOREIGN KEY (`nifcliente`) REFERENCES `cliente` (`nif`);

--
-- Filtros para la tabla `linea_factura`
--
ALTER TABLE `linea_factura`
  ADD CONSTRAINT `linea_factura_fk_1` FOREIGN KEY (`numfactura`) REFERENCES `factura` (`numfactura`),
  ADD CONSTRAINT `linea_factura_fk_2` FOREIGN KEY (`codneumatico`) REFERENCES `neumatico` (`cod`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
