-- Crear tablas
CREATE TABLE Ingredientes (
    ID_Ingrediente INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Cantidad_Inicial INT NOT NULL,
    Cantidad_Disponible INT NOT NULL
);

CREATE TABLE Ordenes_Compra (
    ID_Orden INT AUTO_INCREMENT PRIMARY KEY,
    Fecha DATE NOT NULL,
    Estado VARCHAR(50) NOT NULL
);

CREATE TABLE Proveedores (
    ID_Proveedor INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Contacto VARCHAR(100) NOT NULL
);

CREATE TABLE Inventario (
    ID_Inventario INT AUTO_INCREMENT PRIMARY KEY,
    ID_Ingrediente INT,
    Cantidad INT NOT NULL,
    Fecha_Registro DATE NOT NULL,
    FOREIGN KEY (ID_Ingrediente) REFERENCES Ingredientes(ID_Ingrediente)
);

-- Insertar datos de prueba
INSERT INTO Ingredientes (Nombre, Cantidad_Inicial, Cantidad_Disponible) VALUES ('Harina', 100, 100);
INSERT INTO Ingredientes (Nombre, Cantidad_Inicial, Cantidad_Disponible) VALUES ('Azúcar', 50, 50);

INSERT INTO Proveedores (Nombre, Contacto) VALUES ('Proveedor A', 'contacto@proveedora.com');
INSERT INTO Proveedores (Nombre, Contacto) VALUES ('Proveedor B', 'contacto@proveedorb.com');

INSERT INTO Ordenes_Compra (Fecha, Estado) VALUES ('2024-06-28', 'Pendiente');
INSERT INTO Ordenes_Compra (Fecha, Estado) VALUES ('2024-06-29', 'Completada');

INSERT INTO Inventario (ID_Ingrediente, Cantidad, Fecha_Registro) VALUES (1, 100, '2024-06-28');
INSERT INTO Inventario (ID_Ingrediente, Cantidad, Fecha_Registro) VALUES (2, 50, '2024-06-28');

-- Consultar datos de prueba
SELECT * FROM Ingredientes;
SELECT * FROM Proveedores;
SELECT * FROM Ordenes_Compra;
SELECT * FROM Inventario;

--
