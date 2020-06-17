use imprenta;

-- Insertar login
DELIMITER //
create procedure login(in usuariop varchar(45), in  contrasenap varchar(45))
begin
select*from usuario where Usuario=usuariop and contrasena=contrasenap;
end //
delimiter ;
insert into usuario (usuario, contrasena) values('admin','12345');
insert into usuario (usuario, contrasena) values('mostrador','43');

-- Insertar Venta
create procedure insertarVenta(in importep double, in totalp double,in descuentop double, in cambiop double, in foliop varchar(255), in subtotalp double, in serviciosp TEXT,IN CLAp enum('C','L','A'),in tarjetap boolean,in mermap int, in estadop boolean,in cantidadp int)
insert into Venta(importe,total,descuento,cambio,folio,subtotal,servicios,CLA,tarjeta,merma,estado,cantidad) values(importep,totalp,descuentop,cambiop,foliop,subtotalp,serviciosp,CLAp,tarjetap,mermap,estadop,cantidadp);

-- insertar producto
create procedure insertarProducto(in nombrep Varchar(250), in descripcionp Text,in tipoDeVentap Enum('por paquete','por unidad'), in preciop double, 
in precioMayoreop double, in cantMayoreop int, in estadop boolean, in Proveedor_idProveedorp int,in stockp int, in minimop int)
insert into Productos(nombre, descripcion, tipoDeVenta, precio, precioMayoreo, cantMayoreo, estado, Proveedor_idProveedor,stock,minimo) 
values(nombrep, descripcionp, tipoDeVentap, preciop, precioMayoreop, cantMayoreop, estadop, Proveedor_idProveedorp,stockp,minimop);
-- insertar proveedor 
create procedure insertarProveedor(in nombrep Varchar(100), in telefonop varchar(10), in estadop boolean)
insert into Proveedor(nombre,telefono,estado) values(nombrep,telefonop,estadop);
-- insertar Cotizacion
create procedure insertarCotizacion(in nombreClientep varchar(255), in telefonop varchar(10),in descuentop double, in totalp double , in subtotalp double, in serviciosp TEXT,in foliop varchar(255),in ivap double)
insert into Cotizacion(nombreCliente,telefono,descuento,total,subtotal,servicios,folio,iva) values(nombreClientep,telefonop,descuentop,totalp, subtotalp, serviciosp,foliop,ivap);

-- Insertar Entradas y salidas
create procedure insertarES(in cantidadp double ,  in comentariop varchar(255),in entradap boolean )
insert into EntradaSalida(cantidad,comentario,entrada) values(cantidadp,comentariop,entradap);
-- Mostrar proveedores
Delimiter // 
create procedure select_all_proveedor()
begin 
select * from proveedor where estado = 1;
end // 
delimiter ; 
-- Mostrar proveedores2
Delimiter // 
create procedure select_all_proveedor2()
begin 
select * from proveedor where estado = 0;
end // 
delimiter ; 
-- Seleccionar un producto
DELIMITER //
CREATE PROCEDURE seleccionar_producto(IN id INT) 
BEGIN
	SELECT*FROM productos WHERE idProductos = id;
END //
delimiter ;
-- Seleccionar entrada salida
DELIMITER //
CREATE PROCEDURE seleccionar_es(IN id INT) 
BEGIN
	SELECT*FROM EntradaSalida WHERE idSalida = id;
END //
delimiter ;
-- Seleccionar una venta
delimiter //
create procedure seleccionar_venta(IN id int)
begin 
SELECT * from venta where idVenta = id;
end //
delimiter ;
-- Seleccionar un usuario
delimiter //
create procedure seleccionar_usuario()
begin 
SELECT * from usuario where idUsuario = 2;
end //
delimiter ;
-- seleccionar usuario 2 
delimiter //
create procedure seleccionar_usuario2()
begin 
SELECT * from usuario where idUsuario = 1;
end //
delimiter ;
-- Mostrar Productos
DELIMITER //
CREATE PROCEDURE select_all_productos()
BEGIN 
	SELECT * FROM productos WHERE estado = 1;
END//
DELIMITER ;
-- Mostrar Productos Inactivos
DELIMITER //
CREATE PROCEDURE select_all_productos2()
BEGIN 
	SELECT * FROM productos WHERE estado = 0;
END//
DELIMITER ;
-- Mostrar Productos Bajos en Inventarios
DELIMITER //
CREATE PROCEDURE select_all_productos3()
BEGIN 
	SELECT*from Productos WHERE stock<=minimo and estado=1;
END//
DELIMITER ;
-- Mostrar cotizaciones
DELIMITER //
CREATE PROCEDURE select_all_cotizacion()
BEGIN 
	SELECT * FROM cotizacion;
END//
DELIMITER ;
-- Mostrar ventas
DELIMITER //
CREATE PROCEDURE select_all_ventas()
BEGIN 
	SELECT * FROM Venta;
END//
DELIMITER ;
-- Mostrar salidas
DELIMITER //
CREATE PROCEDURE select_all_salidas()
BEGIN 
	SELECT * FROM EntradaSalida where entrada = 0;
END//
DELIMITER ;
-- Mostrar entradas
DELIMITER //
CREATE PROCEDURE select_all_entradas()
BEGIN 
	SELECT * FROM EntradaSalida where entrada = 1;
END//
DELIMITER ;
-- Modificar Producto
DELIMITER //
CREATE PROCEDURE modificarProducto(IN idProductosp INT, IN nombrep Varchar(250), IN descripcionp Text, IN tipoDeVentap Enum('por paquete','por unidad'), IN preciop Double, 
IN precioMayoreop Double, IN cantMayoreop Int, IN estadop Boolean, IN Proveedor_idProveedorp Int, IN stockp int, IN minimop Int)
BEGIN
UPDATE Productos SET nombre=nombrep, descripcion=descripcionp, tipoDeVenta=tipoDeVentap, precio=preciop, precioMayoreo=precioMayoreop, 
cantMayoreo=cantMayoreop, estado=estadop, Proveedor_idProveedor=Proveedor_idProveedorp, stock=stockp, minimo=minimop
WHERE idProductos=idProductosp;
END //
DELIMITER ;
-- Modificar Proveedor
DELIMITER //
CREATE PROCEDURE modificarProveedor(IN idProveedorp INT, IN nombrep VARCHAR(100), IN telefonop VARCHAR(10), IN estadop BOOLEAN)
BEGIN
UPDATE Proveedor SET nombre=nombrep, telefono=telefonop, estado=estadop
WHERE idProveedor=idProveedorp;
END//
DELIMITER ;
-- Seleccionar un Proveedor 
DELIMITER //
CREATE PROCEDURE seleccionar_proveedor(IN id INT)
BEGIN
	SELECT*FROM Proveedor WHERE idProveedor = id;
END //
DELIMITER ;
-- Actualizar stock 
DELIMITER //
CREATE PROCEDURE actualizar_stock(IN id INT, IN cant INT)
BEGIN
	update Productos set stock=cant where idProductos=id;
END //
DELIMITER ;
-- Ventas del dia
delimiter //
create procedure seleccionar_venta2(IN fechap TimesTamp)
begin 
SELECT * from venta where fecha = fechap;
end //
delimiter ;
-- Total de las ventas de un dia 
delimiter //
create procedure total_ventas()
begin
select sum(total) from venta WHERE fecha BETWEEN CURDATE() and CURDATE() + INTERVAL 1 DAY;
end //
delimiter ;
-- Cargar Ventas de un dia con tarjeta
delimiter //
create procedure cargar_ventas()
begin
SELECT * FROM venta WHERE fecha BETWEEN CURDATE() and CURDATE() + INTERVAL 1 DAY and tarjeta like 1 and estado like 1;
end //
delimiter ;
-- Cargar Ventas de un dia sin tarjeta
delimiter //
create procedure cargar_ventasS()
begin
SELECT * FROM venta WHERE fecha BETWEEN CURDATE() and CURDATE() + INTERVAL 1 DAY and tarjeta like 0 and estado like 1;
end //
delimiter ;
-- Cargar Entradas del dia  
delimiter //
create procedure cargar_entradas()
begin
SELECT * FROM EntradaSalida WHERE fecha BETWEEN CURDATE() and CURDATE() + INTERVAL 1 DAY and entrada = true;
end //
delimiter ;
-- Cargar Salidas del dia  
delimiter //
create procedure cargar_salidas()
begin
SELECT * FROM EntradaSalida WHERE fecha BETWEEN CURDATE() and CURDATE() + INTERVAL 1 DAY and entrada = false;
end //
delimiter ;
-- Seleccionar una venta
delimiter //
create procedure seleccionar_coti(IN id int)
begin 
SELECT * from Cotizacion where idCotizacion = id;
end //
delimiter ;
-- cancelar venta
DELIMITER // 
 CREATE PROCEDURE bajaVenta(in id int)
 BEGIN 
 UPDATE venta set estado= false where idVenta=id;
 END // 
 DELIMITER ;
 -- cargar ventas bien 
DELIMITER //
CREATE PROCEDURE select_all_ventas2()
BEGIN 
Select * from venta where estado like 1 ;
END//
DELIMITER ;
select sum(total) from venta WHERE fecha BETWEEN CURDATE() and CURDATE() + INTERVAL 1 DAY;
select sum(cantidad) from EntradaSalida WHERE fecha BETWEEN CURDATE() and CURDATE() + INTERVAL 1 DAY and entrada = true;