-- Insertar datos en la tabla usuario
INSERT INTO usuario (nombre, email, password) VALUES ('Juan Perez', 'juan.perez@example.com', 'password123');
INSERT INTO usuario (nombre, email, password) VALUES ('Maria Lopez', 'maria.lopez@example.com', 'password456');

-- Insertar datos en la tabla album
INSERT INTO album (nombre, descripcion, usuario_id) VALUES ('La Liga', 'Álbum de cromos de la liga española de fútbol 2024-25', 1);
INSERT INTO album (nombre, descripcion, usuario_id) VALUES ('YU-GI-OH', 'Álbum de cartas de la conocida serie de animacion YU-GI-OH', 2);

-- Insertar datos en la tabla cromos
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Federico Valverde', 'Futbolista uruguayo nacionalizado español en 2020 que juega como centrocampista actualmente en el Real Madrid C. F.', 2023, 10.5, 'Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Dragon blanco de ojos azules', 'Poderoso dragón de ojos azules', 2023, 15.0, 'Muy Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Kylian Mbappé', 'Futbolista francés que juega como delantero en el Real Madrid C. F. de la Primera División de España. Es internacional con la selección de Francia', 2023, 5.0, 'Muy Rara', 1);