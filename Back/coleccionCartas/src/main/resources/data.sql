-- Insertar datos en la tabla usuario
INSERT INTO usuario (nombre, email, password) VALUES ('Juan Perez', 'juan.perez@example.com', 'password123');
INSERT INTO usuario (nombre, email, password) VALUES ('Maria Lopez', 'maria.lopez@example.com', 'password456');

-- Insertar datos en la tabla album
INSERT INTO album (nombre, descripcion, usuario_id) VALUES ('Album de Juan', 'Descripción del álbum de Juan', 1);
INSERT INTO album (nombre, descripcion, usuario_id) VALUES ('Album de Maria', 'Descripción del álbum de Maria', 2);

-- Insertar datos en la tabla cromos
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Cromo 1', 'Descripción del Cromo 1', 2023, 10.5, 'Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Cromo 2', 'Descripción del Cromo 2', 2023, 15.0, 'Muy Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Cromo 3', 'Descripción del Cromo 3', 2023, 5.0, 'Común', 2);