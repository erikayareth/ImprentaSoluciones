use imprenta;

-- Insertar login
DELIMITER //
create procedure login(in usuariop varchar(45), in  contrasenap varchar(45))
begin
select*from usuario where Usuario=usuariop and contrasena=contrasenap;
end //
delimiter ;
insert into usuario (usuario, contrasena) values('admin','12345');
insert into usuario (usuario, contrasena) values('empleado','43');
SELECT*FROM usuario;

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
call insertarProducto("Papel","papel couche tamaño 12","por paquete", 200, 100, 50, true, 1, 10, 10);
select*from Productos;
DROP PROCEDURE insertarProducto;

select*from productos;

-- insertar proveedor 
create procedure insertarProveedor(in nombrep Varchar(100), in telefonop varchar(10), in estadop boolean)
insert into Proveedor(nombre,telefono,estado) values(nombrep,telefonop,estadop);
select*from Proveedor;
CALL insertarProveedor('Yareth', '1234567898', true);

-- insertar Cotizacion
create procedure insertarCotizacion(in nombreClientep varchar(255), in telefonop varchar(10),in descuentop double, in totalp double , in subtotalp double )
insert into Cotizacion(nombreCliente,telefono,descuento,total,subtotal) values(nombreClientep,telefonop,descuentop,totalp, subtotalp);
call insertarCotizacion("Avril","2292530407",100,2000,200);
select*from Cotizacion;

-- Insertar Entradas y salidas
create procedure insertarES(in cantidadp double ,  in comentariop varchar(255),in entradap boolean )
insert into EntradaSalida(cantidad,comentario,entrada) values(cantidadp,comentariop,entradap);
drop procedure insertarES;
call insertarES(200,"para el de la basura",true);
select*from EntradaSalida;

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
DROP PROCEDURE seleccionar_producto;

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
call modificarProducto(3, "Impresora", "papel couche tamaño 12", "por paquete", 20, 10, 5, true, 1, 30, 10);

-- Modificar Proveedor
DELIMITER //
CREATE PROCEDURE modificarProveedor(IN idProveedorp INT, IN nombrep VARCHAR(100), IN telefonop VARCHAR(10), IN estadop BOOLEAN)
BEGIN
UPDATE Proveedor SET nombre=nombrep, telefono=telefonop, estado=estadop
WHERE idProveedor=idProveedorp;
END//
DELIMITER ;
CALL modificarProveedor(2, "Preedor", "1234567898", true);

-- Seleccionar un Proveedor 
DELIMITER //
CREATE PROCEDURE seleccionar_proveedor(IN id INT)
BEGIN
	SELECT*FROM Proveedor WHERE idProveedor = id;
END //
DELIMITER ;

