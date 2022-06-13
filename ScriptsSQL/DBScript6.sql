DROP DATABASE greenersdb;

CREATE DATABASE greenersdb;

USE greenersdb;

CREATE TABLE personas(
	id INT AUTO_INCREMENT,
	nombre VARCHAR(250) NOT NULL,
	ap_paterno VARCHAR(250) NOT NULL,
	ap_materno VARCHAR(250) NOT NULL,
    correo VARCHAR(250) NOT NULL,
    telf VARCHAR(30) NOT NULL,
    
    PRIMARY KEY(id)
);

INSERT INTO personas(nombre, ap_paterno, ap_materno, correo, telf)
	VALUES
    ("Jessica Andrea", "Aquino", "Torrez", "j-aquino@outlook.com", "77306454"),
    ("Sarah Elizabeth", "Chalup", "Roca", "sarah.chalup.r@gmail.com", "77347295"),
    ("Mariana", "Arnez", "Andia", "mariana.anez@gmail.com", "75049821"),
    ("Marco Antonio", "Lopez", "Guevara", "mlopezdev@gmail.com", "75326161"),
    ("Jaime", "Gareca", "Guarachi", "jaimegarecagg@gmail.com", "74176300")
;

#FALTA UBICACION CLIENTE

#ENCARGADO COMERCIO, ADMINISTRAODR Y CLIENTE

CREATE TABLE permisos(
	id INT AUTO_INCREMENT,
    nombre VARCHAR(250) UNIQUE NOT NULL,
    descripcion VARCHAR(500),
    PRIMARY KEY(id)
);

INSERT INTO permisos(nombre, descripcion) 
	VALUES
    ("Crear paquete detallado", "Habilita la opcion para la creacion de un paquete detallado"),
    ("Crear paquete sorpresa", "Habilita la opcion para la creacion de un paquete sorpresa"),
    ("Crear producto", "Habilita la opcion para la creacion de un producto"),
    ("Crear pedido", "Habilita la opcion para la creacion de un pedido")
;

CREATE TABLE roles(
	id INT AUTO_INCREMENT,
    nombre VARCHAR(250) UNIQUE NOT NULL,
    descripcion VARCHAR(500),
    PRIMARY KEY(id)
);

INSERT INTO roles(nombre, descripcion)
	VALUES
    ("Administrador emprendimiento", "Su funcion es registrar la informacion de los comercios y realizar actividades administrativas"),
    ("Administrador comercio", "Cuenta con todos los permisos para realizar cambios sobre el comercio que tiene designado"),
    ("Cliente", "Puede realizar la compra de paquetes en los comercios")
;
    
    
CREATE TABLE roles_permisos(
    id_rol INT,
    id_permiso INT,
    
    PRIMARY KEY(id_rol, id_permiso),
    FOREIGN KEY(id_rol) REFERENCES roles(id),
    FOREIGN KEY(id_permiso) REFERENCES permisos(id)
);

INSERT INTO roles_permisos(id_rol, id_permiso)
	VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (2, 1),
    (2, 2),
    (3, 4)
;
    
CREATE TABLE usuarios(
	id INT AUTO_INCREMENT,
    nombre_usuario VARCHAR(250) UNIQUE NOT NULL,
    contra_usuario VARCHAR(250) NOT NULL,
    
    id_persona INT,
    id_rol INT,
    
    PRIMARY KEY(id),
    FOREIGN KEY(id_persona) REFERENCES personas(id),
    FOREIGN KEY(id_rol) REFERENCES roles(id)
);


INSERT INTO usuarios(nombre_usuario, contra_usuario, id_persona, id_rol)
	VALUES
    ("JessAT18", "Passw0rd", 1, 1),
    ("Sarech", "Passw0rd", 2, 2),
    ("Marianits", "Passw0rd", 3, 3),
    ("MLopez", "Passw0rd", 4, 2),
    ("JGareca", "Passw0rd", 5, 2);


CREATE TABLE comercios(
	id INT AUTO_INCREMENT,
    nombre VARCHAR(250) NOT NULL,
    telf VARCHAR(30) NOT NULL,
    direccion VARCHAR(250) NOT NULL,
    link_logo VARCHAR(400) DEFAULT ("https://i.imgur.com/22zktlI.png") NOT NULL,
    
    id_adm_comercio INT, #UN COMERCIO SOLO PUEDE TENER UN USUARIO ADMINISTRADOR
    
    PRIMARY KEY(id),
    FOREIGN KEY(id_adm_comercio) REFERENCES usuarios(id)
);

INSERT INTO comercios(nombre, telf, direccion, link_logo, id_adm_comercio)
	VALUES
    ("Factory - Av. Velarde", "77306454", "Av. Velarde", "https://i.imgur.com/tjhXUJt.png" , 2),
    ("Acai Bar Superfood - Velarde", "12345678", "Av. Velarde", "https://i.imgur.com/x8Mkl6y.png", 4),
    ("Hipermaxi - Pirai", "12345678", "2do Anillo Av. Pirai", "https://i.imgur.com/iXhu184.jpg", 5);

INSERT INTO comercios(nombre, telf, direccion, id_adm_comercio)
	VALUES
    ("La casa del camba - Cine Center", "3550822", "Cine Center", 2),
    ("Burguer king - Cristo", "3550822", "2do Anillo, Av. Monseñor", 5),
    ("KFC - La Salle", "3544822", "2do Anillo, Av. La Salle", 4);
    


CREATE TABLE clientes_comercios_suscripcion(
    id_usuario INT,
    id_comercio INT,
    
    PRIMARY KEY(id_usuario, id_comercio),
    FOREIGN KEY(id_usuario) REFERENCES usuarios(id),
    FOREIGN KEY(id_comercio) REFERENCES comercios(id)
);

INSERT INTO clientes_comercios_suscripcion(id_usuario, id_comercio)
	VALUES
    (3, 1),
    (3, 2),
    (3, 3);

CREATE TABLE productos(
    codigo VARCHAR(100),
    nombre VARCHAR(250) NOT NULL,
    descripcion VARCHAR(500) NOT NULL,
    
    id_comercio INT,
    
    PRIMARY KEY(id_comercio, codigo),
    FOREIGN KEY(id_comercio) REFERENCES comercios(id)
);

INSERT INTO productos(codigo, nombre, descripcion, id_comercio)
	VALUES
    ("1", "Papas fritas", "100 gramos de papas fritas", 1),
    ("2", "South of the Border", "Hamburguesa de 100g de carne con tocino, ...", 1),
    ("1", "Classic bowl", "Frutilla, platano, leche en polvo, ...", 2),
    ("1", "Paneton chocolate", "Paneton exclusivo de la panaderia hipermaxi sabor chocolate", 3),
    ("2", "Paneton frutas", "Paneton exclusivo de la panaderia hipermaxi con frutas", 3);
    
#PRODUCTOS FORMAN PARTE DE PAQUETE DETALLADO

CREATE TABLE paquetes_detallados(
    id_paquete INT,
    id_producto_comercio INT,
    id_producto_codigo VARCHAR(100),
    cantidad INT NOT NULL DEFAULT 1,
        
    PRIMARY KEY(id_paquete, id_producto_comercio, id_producto_codigo)
);

INSERT INTO paquetes_detallados(id_paquete, id_producto_comercio, id_producto_codigo, cantidad)
	VALUES
    (1, 3, "1", 1),
    (1, 3, "2", 2);
    
CREATE TABLE tipo_paquetes(
	id INT AUTO_INCREMENT,
    nombre VARCHAR(100) UNIQUE NOT NULL,
    descripcion VARCHAR(250) NOT NULL,
    
    PRIMARY KEY(id)
);

INSERT INTO tipo_paquetes (nombre, descripcion)
	VALUES 
    ("Paquete sorpresa", "Solo contiene descripcion de los paquetes."),
	("Paquete detallado", "Informacion a detalle de los items del paquete.");

CREATE TABLE paquetes(
	id INT AUTO_INCREMENT,
    nombre VARCHAR(250) NOT NULL,
    descripcion VARCHAR(500) NOT NULL, #hasta aqui bien para los paquetes sorpresa
    link_paquete VARCHAR(400) DEFAULT ("https://i.imgur.com/i2Pyx6A.png") NOT NULL,
    habilitado BOOLEAN DEFAULT FALSE,
    
    id_comercio INT NOT NULL,
    id_tipo_paquete INT NOT NULL, #1 Sorpresa 2 Detallado
    
    PRIMARY KEY(id),
    FOREIGN KEY(id_tipo_paquete) REFERENCES tipo_paquetes(id),
    FOREIGN KEY(id_comercio) REFERENCES comercios(id)

);
#INSERTAR IMAGENES CUANDO TENGA TIEMPO xD
INSERT INTO paquetes(nombre, descripcion, id_tipo_paquete, id_comercio)
	VALUES
    ("Papitas fritas Mistery", "Papitas fritas :D", 1, 1),
    ("Trio de bowls sorpresa", "Tres bowls M a eleccion", 1, 2),
    ("Panetones random", "Dos panetones de sabor variado", 2, 3),
    ("Papitas fritas Americanas", "Papitas fritas mmm", 1, 1);


CREATE TABLE inventarios(
	id INT AUTO_INCREMENT,
    fecha DATETIME DEFAULT NOW(),
    codigo VARCHAR(100) NOT NULL,
    precio FLOAT NOT NULL,
    stock INT NOT NULL,
    
    id_comercio INT,
    id_paquete INT,
    
    PRIMARY KEY(id),
    FOREIGN KEY (id_comercio) REFERENCES comercios(id),
    FOREIGN KEY (id_paquete) REFERENCES paquetes(id) 
);


INSERT INTO inventarios(codigo, fecha, precio, stock, id_comercio, id_paquete)
	VALUES
    ("1", "2022-06-10 12:00:00", 75, 20, 1, 1),
    ("1", "2022-06-10 12:00:00", 60, 30, 2, 2),
    ("1", "2022-06-10 12:00:00", 30, 10, 3, 3);

CREATE TABLE metodos_de_pago(
	id INT AUTO_INCREMENT,
    descripcion VARCHAR(100) UNIQUE NOT NULL,
    
    PRIMARY KEY(id)
);

INSERT INTO metodos_de_pago(descripcion)
	VALUES
    ("Efectivo"),
    ("Tarjeta"),
    ("QR");
    
    
CREATE TABLE estados(
	id INT AUTO_INCREMENT,
    descripcion VARCHAR(100) UNIQUE NOT NULL,
    
    PRIMARY KEY(id)
);

INSERT INTO estados(descripcion)
	VALUES
    ("Pendiente"),
    ("Pagado"),
    ("Cancelado");

CREATE TABLE pagos(
	id INT AUTO_INCREMENT,
    monto FLOAT NOT NULL,
    
    id_estado INT, #1 Pendiente 2 Pagado 3 Cancelado
    id_metodo_pago INT,
    
    PRIMARY KEY(id),
    FOREIGN KEY(id_estado) REFERENCES estados(id),
    FOREIGN KEY(id_metodo_pago) REFERENCES metodos_de_pago(id)
);

CREATE TABLE pedidos(	
	id INT AUTO_INCREMENT, #N_PEDIDO
    fecha DATETIME NOT NULL,
    
    id_cliente INT,
    id_pago INT,
    
    PRIMARY KEY(id),
    FOREIGN KEY(id_cliente) REFERENCES usuarios(id),
    FOREIGN KEY(id_pago) REFERENCES pagos(id)
);

CREATE TABLE lineas_de_pedido(	
	id INT AUTO_INCREMENT,
    cod_paquete VARCHAR(100) NOT NULL,
    cant INT NOT NULL,
    
    id_pedido INT,
    id_inventario INT,
    
    PRIMARY KEY(id),
    FOREIGN KEY(id_pedido) REFERENCES pedidos(id),
    FOREIGN KEY(id_inventario) REFERENCES inventarios(id)
);

#SELECT inventarios.codigo, inventarios.precio, paquetes.nombre, paquetes.descripcion, paquetes.link_paquete FROM inventarios
#	LEFT JOIN paquetes ON inventarios.id_paquete = paquetes.id
#    WHERE inventarios.id_comercio=1 AND fecha >= CURDATE()
#    ORDER BY fecha DESC;

SELECT * FROM paquetes WHERE id_comercio = 1;

SELECT paquetes.id, inventarios.codigo, inventarios.precio, paquetes.nombre, paquetes.descripcion, paquetes.link_paquete FROM inventarios
	LEFT JOIN paquetes ON inventarios.id_paquete = paquetes.id
    WHERE inventarios.id_comercio=1
    ORDER BY fecha DESC;
    
SELECT * FROM inventarios
	LEFT JOIN paquetes ON inventarios.id_paquete = paquetes.id
    WHERE inventarios.id_comercio=1
    ORDER BY fecha DESC;