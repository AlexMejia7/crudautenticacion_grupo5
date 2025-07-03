-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 06-07-2025 a las 06:27:30
-- Versión del servidor: 10.4.11-MariaDB
-- Versión de PHP: 7.2.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `crudautenticacion_grupo5`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `precio` decimal(10,2) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id`, `nombre`, `descripcion`, `precio`, `created_at`) VALUES
(2, 'Llanta OffRoad #2', 'Llanta para terrenos difíciles', '345.00', '2025-07-03 19:34:06'),
(4, 'llanta 295/75r22.5', 'Llantas para camión 295/50r22.5', '300.00', '2025-07-03 19:36:32'),
(5, 'llanta 35/65R-33', 'Llantas para camión Fuera de carretera minero amarillo 35/65R-33', '300.00', '2025-07-03 20:01:58'),
(6, 'llanta 35/65R-33 grande', 'Llantas para camión Fuera de carretera minero Amarillo 35/65R-33', '300.00', '2025-07-03 20:22:52'),
(7, 'llanta 35/65R-33 grandes', 'Llantas para camión Fuera de carretera minero Amarillo 35/65R-33', '300.00', '2025-07-03 20:33:29'),
(10, 'Llanta OffRoad', 'Llanta para terrenos difíciles pantanos', '3.00', '2025-07-04 02:53:36'),
(11, 'Tomate cherry verde ', 'Tomate pequeño dulce acido', '100.00', '2025-07-04 04:14:37'),
(17, 'Tomate cherry', 'Tomate pequeño dulce', '2.50', '2025-07-05 05:27:06'),
(19, 'Tomate cherry manzano verde', 'Tomate pequeño dulce', '2.50', '2025-07-05 05:58:50'),
(21, 'Tomate cherry manzano verde', 'Tomate pequeño dulce', '400.00', '2025-07-05 06:05:48'),
(22, 'llanta 295/75/75', 'Llantas para camión 295/50r22.5', '300.00', '2025-07-05 06:17:50'),
(23, 'Llanta OffRoad', 'Llanta para terrenos difíciles pantanos', '4.00', '2025-07-05 06:39:52'),
(25, 'Tomate cherry manzano verde', 'Tomate pequeño dulce agrio', '400.00', '2025-07-05 07:16:27'),
(26, 'Llanta Aro 17', 'Llanta deportiva para autos', '150.00', '2025-07-05 21:00:32'),
(27, 'naranja', 'fruta', '100.00', '2025-07-05 21:44:12'),
(28, 'Tomate manzano verde', 'Tomate manzano dulce agrio', '400.00', '2025-07-06 04:05:07'),
(29, 'Frijoles', 'Rojos', '30.00', '2025-07-06 04:12:50'),
(30, 'Tomate manzano verde', 'Tomate manzano rojo', '400.00', '2025-07-06 04:14:03');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
