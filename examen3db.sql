-- Tabla para la cabecera de la factura
CREATE TABLE FacturaCabecera (
    factura_id SERIAL PRIMARY KEY,
    cliente_nombre VARCHAR(100) NOT NULL,
	cliente_num_documento VARCHAR(15) NOT NULL,
    fecha_emision DATE NOT NULL,
    total DECIMAL(10, 2) NOT NULL
);

-- Tabla para los productos
CREATE TABLE Productos (
    producto_id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL
);
-- Tabla para el detalle de la factura
CREATE TABLE FacturaDetalle (
    detalle_id SERIAL PRIMARY KEY,
    factura_id INT REFERENCES FacturaCabecera(factura_id) ON DELETE CASCADE,
    producto_id INT REFERENCES Productos(producto_id),
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10, 2) NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL
);