-- Crear tablas
CREATE DATABASE IF NOT EXISTS escuela_chefs;
USE escuela_chefs;

CREATE TABLE Ingredientes (
    ID_Ingrediente INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Cantidad INT NOT NULL,
    Indispensable BOOLEAN NOT NULL DEFAULT FALSE
);

-- Insertar datos de prueba
INSERT INTO Ingredientes (Nombre, Cantidad, Indispensable) VALUES ('Harina', 100, FALSE);
INSERT INTO Ingredientes (Nombre, Cantidad, Indispensable) VALUES ('Azúcar', 50, FALSE);

-- Consultar datos de prueba
SELECT * FROM Ingredientes;
