-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 06-05-2014 a las 12:15:59
-- Versión del servidor: 5.5.37
-- Versión de PHP: 5.3.10-1ubuntu3.11

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `prg1314finalgrupo1`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Cliente`
--

CREATE TABLE IF NOT EXISTS `Cliente` (
  `id` char(4) NOT NULL,
  `nombre` varchar(60) NOT NULL DEFAULT '',
  `email` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `Cliente`
--

INSERT INTO `Cliente` (`id`, `nombre`, `email`) VALUES
('1', 'Paco Aldarias', 'abbb'),
('2', 'a', 'a'),
('3', 'ab', 'ab'),
('4', 'abcd', 'abcd'),
('5', 'dddd', 'dddd');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Configura`
--

CREATE TABLE IF NOT EXISTS `Configura` (
  `id` char(4) NOT NULL,
  `nombreaplicacion` varchar(25) NOT NULL,
  `autor` varchar(50) NOT NULL,
  `basedatos` varchar(25) NOT NULL,
  `fuentedatos` varchar(25) NOT NULL,
  `fecha` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `Configura`
--

INSERT INTO `Configura` (`id`, `nombreaplicacion`, `autor`, `basedatos`, `fuentedatos`, `fecha`) VALUES
('1', 'Tienda', 'Paco Aldarias Raya', 'prg1314finalgrupo1', 'hibernate', '2014-04-28');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Linea`
--

CREATE TABLE IF NOT EXISTS `Linea` (
  `idlinea` char(4) NOT NULL,
  `importe` varchar(10) DEFAULT NULL,
  `cantidad` char(4) DEFAULT NULL,
  `fecha` char(10) DEFAULT NULL,
  `idpedido` char(4) DEFAULT NULL,
  `idproducto` char(4) DEFAULT NULL,
  PRIMARY KEY (`idlinea`),
  KEY `idpedido` (`idpedido`),
  KEY `idproducto` (`idproducto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `Linea`
--

INSERT INTO `Linea` (`idlinea`, `importe`, `cantidad`, `fecha`, `idpedido`, `idproducto`) VALUES
('1', '55', '1', '2014-4-25', '1', '2'),
('2', '4', '3', '2014-4-25', '1', '1'),
('3', '4.5', '2', '2014-4-27', '2', '3'),
('4', '67.8', '1', '2014-4-27', '3', '4'),
('5', '55', '1', '2014-4-27', '3', '2');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Pedido`
--

CREATE TABLE IF NOT EXISTS `Pedido` (
  `id` char(4) NOT NULL,
  `fecha` varchar(10) DEFAULT NULL,
  `idcliente` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idcliente` (`idcliente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `Pedido`
--

INSERT INTO `Pedido` (`id`, `fecha`, `idcliente`) VALUES
('1', '2014-4-9', '1'),
('2', '2014-4-25', '1'),
('3', '2014-4-27', '4');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Producto`
--

CREATE TABLE IF NOT EXISTS `Producto` (
  `idproducto` char(4) NOT NULL,
  `nombre` varchar(30) NOT NULL DEFAULT '',
  `precio` varchar(6) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idproducto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `Producto`
--

INSERT INTO `Producto` (`idproducto`, `nombre`, `precio`) VALUES
('1', 'a', '4'),
('2', 'b', '55'),
('3', 'cc', '4.5'),
('4', 'dddd', '67.8');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `Linea`
--
ALTER TABLE `Linea`
  ADD CONSTRAINT `Linea_ibfk_2` FOREIGN KEY (`idpedido`) REFERENCES `Pedido` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `Linea_ibfk_3` FOREIGN KEY (`idproducto`) REFERENCES `Producto` (`idproducto`);

--
-- Filtros para la tabla `Pedido`
--
ALTER TABLE `Pedido`
  ADD CONSTRAINT `Pedido_ibfk_1` FOREIGN KEY (`idcliente`) REFERENCES `Cliente` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
