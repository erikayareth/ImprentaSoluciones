use imprenta;

-- Insertar Venta
create procedure insertarVenta(in importep double, in totalp double,in descuentop double, in cambiop double, in foliop varchar(255), in subtotalp double  )
insert into Venta(importe,total,descuento,cambio,folio,subtotal) values(importep,totalp,descuentop,cambiop,foliop,subtotalp);
drop procedure insertarVenta;
call insertarVenta(5000,200,100,20,"LC20093",10);
select*from Venta;

-- insertar producto
create procedure insertarProducto(in nombrep Varchar(250), in descripcionp Text,in tipoDeVentap Enum('por paquete','por unidad'), in preciop double, 
in precioMayoreop double, in cantMayoreop int, in estadop boolean, in Proveedor_idProveedorp int,in stockp int, in minimop int)
insert into Productos(nombre, descripcion, tipoDeVenta, precio, precioMayoreo, cantMayoreo, estado, Proveedor_idProveedor,stock,minimo) 
values(nombrep, descripcionp, tipoDeVentap, preciop, precioMayoreop, cantMayoreop, estadop, Proveedor_idProveedorp,stockp,minimop);
call insertarProducto("papel","papel couche tamaño 12","por paquete",true, 20,10,100,1,30,10);
select*from Productos;
DROP PROCEDURE insertarProducto;

select*from productos;

-- insertar proveedor 
create procedure insertarProveedor(in nombrep Varchar(100), in telefonop varchar(10), in estadop boolean)
insert into Proveedor(nombre,telefono,estado) values(nombrep,telefonop,estadop);
call insertarProveedor("papel","2292530108", true);
select*from Proveedor;
DROP PROCEDURE insertarProveedor;

-- insertar Cotizacion
create procedure insertarCotizacion(in nombreClientep varchar(255), in telefonop varchar(10),in descuentop double, in totalp double , in subtotalp double )
insert into Cotizacion(nombreCliente,telefono,descuento,total,subtotal) values(nombreClientep,telefonop,descuentop,totalp, subtotalp);
drop procedure insertarCotizacion;
call insertarCotizacion("Avril","2292530407",100,2000,200);
select*from Cotizacion;

-- Insertar Entradas y salidas
create procedure insertarES(in cantidadp double ,  in comentariop varchar(255),in entradap boolean )
insert into EntradaSalida(cantidad,comentario,entrada) values(cantidadp,comentariop,entradap);
drop procedure insertarES;
call insertarES(200,"para el de la basura",true);
select*from EntradaSalida;

-- Mostrar proveedor
Delimiter // 
create procedure select_all_proveedor()
begin 
select * from proveedor ;
end // 
delimiter ; 

-- Seleccionar un producto
DELIMITER //
CREATE PROCEDURE seleccionar_producto(IN id INT) 
BEGIN
	SELECT idProductos, nombre, descripcion, precio FROM productos WHERE idProductos = id;
END //
delimiter ;
DROP PROCEDURE seleccionar_producto;

-- Mostrar Productos
DELIMITER //
CREATE PROCEDURE select_all_productos()
BEGIN 
	SELECT * FROM productos;
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
drop procedure select_all_ventas;

call select_all_ventas();
-- Mostrar salidas
DELIMITER //
CREATE PROCEDURE select_all_salidas()
BEGIN 
	SELECT * FROM EntradaSalida where entrada = 0;
END//
DELIMITER ;
drop procedure select_all_salidas;
call select_all_salidas;
-- Mostrar entradas
DELIMITER //
CREATE PROCEDURE select_all_entradas()
BEGIN 
	SELECT * FROM EntradaSalida where entrada = 1;
END//
DELIMITER ;
drop procedure select_all_entradas;
call select_all_entradas;