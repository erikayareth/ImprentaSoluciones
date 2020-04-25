use imprenta;

-- Insertar Venta
create procedure insertarVenta(in importep double, in totalp double,in descuentop double, in cambiop double  )
insert into Venta(importe,total,descuento,cambio) values(importep,totalp,descuentop,cambiop);
drop procedure insertarVenta;
call insertarVenta(5000,200,100,20);
select*from Venta;
-- insertar producto
create procedure insertarProducto(in nombrep Varchar(250), in descripcionp Text,in tipoDeVentap Enum('por paquete','por unidad'), in preciop double, 
in precioMayoreop double, in cantMayoreop int, in Proveedor_idProveedorp int  )
insert into Productos(nombre,descripcion,tipoDeVenta,precio,precioMayoreo,cantMayoreo,Proveedor_idProveedor) 
values(nombrep,descripcionp,tipoDeVentap,preciop,precioMayoreop,cantMayoreop,Proveedor_idProveedorp);
drop procedure insertarProductos;
call insertarProducto("papel","papel couche tamaño 12","por paquete",20,10,100,1);
select*from Productos;
-- insertar proveedor 
create procedure insertarProveedor(in nombrep Varchar(100), in telefonop varchar(10)  )
insert into Proveedor(nombre,telefono) values(nombrep,telefonop);
drop procedure insertarProveedor;
call insertarProveedor("papel","2292530108");
select*from Proveedor;
-- insertar Cotizacion
create procedure insertarCotizacion(in nombreClientep varchar(255), in telefonop varchar(10),in descuentop double, in totalp double  )
insert into Cotizacion(nombreCliente,telefono,descuento,total) values(nombreClientep,telefonop,descuentop,totalp);
drop procedure insertarCotizacion;
call insertarCotizacion("Avril","2292530407",100,2000);
select*from Cotizacion;

-- Mostrar proveedor
Delimiter // 
create procedure select_all_proveedor()
begin 
select * from proveedor ;
end // 
delimiter ; 
drop procedure select_all_proveedor;
call select_all_proveedor();
-- Mostrar producto
DELIMITER //
CREATE PROCEDURE seleccionar_producto(IN id INT) 
BEGIN
	SELECT * FROM productos WHERE idProductos = id;
END //
delimiter ;

DELIMITER //
CREATE PROCEDURE select_all_producto()
BEGIN 
Select * from productos;
END//
DELIMITER ;
drop procedure select_all_producto;