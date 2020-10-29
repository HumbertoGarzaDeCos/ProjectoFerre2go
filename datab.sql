GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP ON *.* TO 'root'@'localhost';

CREATE DATABASE IF NOT EXISTS `qa` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `qa`;

CREATE TABLE IF NOT EXISTS `abastecimiento` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `cantidad` int NOT NULL,
  `estado` int NOT NULL,
  `idProveedor` int NOT NULL,
  `precioUnitario` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS`abastecimientoproducto` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idProd` int NOT NULL,
  `cantidad` int NOT NULL,
  `precio` double NOT NULL,
  `idAbastecimiento` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idProd` (`idProd`),
  CONSTRAINT `abastecimientoproducto_ibfk_1` FOREIGN KEY (`idProd`) REFERENCES `productos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `admin` (
  `id` int NOT NULL,
  `u` blob NOT NULL,
  `p` blob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `articulos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idPedido` int NOT NULL,
  `idProducto` int NOT NULL,
  `cantidad` int NOT NULL,
  `precio` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idProducto` (`idProducto`),
  KEY `idPedido` (`idPedido`),
  CONSTRAINT `articulos_ibfk_1` FOREIGN KEY (`idProducto`) REFERENCES `productos` (`id`),
  CONSTRAINT `articulos_ibfk_2` FOREIGN KEY (`idPedido`) REFERENCES `pedidos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `categorias` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` blob NOT NULL,
  `activo` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `clientes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` blob NOT NULL,
  `direccion` blob NOT NULL,
  `telefono` decimal(10,0) NOT NULL,
  `usuario` blob NOT NULL,
  `contraseña` blob NOT NULL,
  `email` blob NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `pedidos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `numArticulos` int NOT NULL,
  `nombreCliente` blob NOT NULL,
  `direccion` blob NOT NULL,
  `telefono` decimal(10,0) NOT NULL,
  `estado` int NOT NULL,
  `idCliente` int NOT NULL,
  `articulosId` int DEFAULT NULL,
  `creacion` date NOT NULL,
  `actualizado` date NOT NULL,
  `total` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `producto_categoria` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idCat` int NOT NULL,
  `idProd` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idProd` (`idProd`),
  KEY `idCat` (`idCat`),
  CONSTRAINT `producto_categoria_ibfk_1` FOREIGN KEY (`idProd`) REFERENCES `productos` (`id`),
  CONSTRAINT `producto_categoria_ibfk_2` FOREIGN KEY (`idCat`) REFERENCES `categorias` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `productos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` blob NOT NULL,
  `sku` blob NOT NULL,
  `precio` blob NOT NULL,
  `detalles` blob NOT NULL,
  `dimesniones` blob NOT NULL,
  `activo` int NOT NULL,
  `cantidad` int NOT NULL,
  `alerta` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `proveedores` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` blob NOT NULL,
  `telefono` decimal(10,0) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `proveedorproducto` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idProv` int NOT NULL,
  `idProd` int NOT NULL,
  `precio1` double NOT NULL,
  `precio2` double DEFAULT NULL,
  `precio3` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idProv` (`idProv`),
  KEY `idProd` (`idProd`),
  CONSTRAINT `proveedorproducto_ibfk_1` FOREIGN KEY (`idProv`) REFERENCES `proveedores` (`id`),
  CONSTRAINT `proveedorproducto_ibfk_2` FOREIGN KEY (`idProd`) REFERENCES `productos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
