-- Insertar datos en la tabla usuario
INSERT INTO usuario (nombre, email, password) VALUES ('Juan Perez', 'juan.perez@example.com', 'password123');
INSERT INTO usuario (nombre, email, password) VALUES ('Maria Lopez', 'maria.lopez@example.com', 'password456');

-- Insertar datos en la tabla album
INSERT INTO album (nombre, descripcion, usuario_id, imagen) VALUES ('La Liga', 'Álbum de cromos de la liga española de fútbol 2024-25', 1, '/home/curso003/Desktop/colleccion-de-Cromos-Cartas/Back/coleccionCartas/images/liga.png');
INSERT INTO album (nombre, descripcion, usuario_id, imagen) VALUES ('YU-GI-OH', 'Álbum de cartas de la conocida serie de animacion YU-GI-OH', 2, '/home/curso003/Desktop/colleccion-de-Cromos-Cartas/Back/coleccionCartas/images/yugioh.jpg');


-- Insertar datos en la tabla cromos
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Federico Valverde', 'Futbolista uruguayo nacionalizado español en 2020 que juega como centrocampista actualmente en el Real Madrid C. F.', 2023, 10.5, 'Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Kylian Mbappé', 'Futbolista francés que juega como delantero en el Real Madrid C. F. de la Primera División de España. Es internacional con la selección de Francia', 2023, 5.0, 'Muy Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Karim Benzema', 'Delantero del Real Madrid C. F.', 2023, 12.0, 'Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Lionel Messi', 'Delantero del FC Barcelona', 2023, 20.0, 'Muy Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Gerard Piqué', 'Defensa del FC Barcelona', 2023, 8.0, 'Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Jan Oblak', 'Portero del Atlético de Madrid', 2023, 10.0, 'Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Sergio Ramos', 'Defensa del Real Madrid C. F.', 2023, 9.5, 'Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Antoine Griezmann', 'Delantero del Atlético de Madrid', 2023, 11.0, 'Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Luka Modrić', 'Centrocampista del Real Madrid C. F.', 2023, 10.0, 'Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('João Félix', 'Delantero del Atlético de Madrid', 2023, 13.0, 'Muy Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Marc-André ter Stegen', 'Portero del FC Barcelona', 2023, 9.0, 'Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Eden Hazard', 'Delantero del Real Madrid C. F.', 2023, 14.0, 'Muy Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Pedri', 'Centrocampista del FC Barcelona', 2023, 12.5, 'Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Vinícius Júnior', 'Delantero del Real Madrid C. F.', 2023, 15.0, 'Muy Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Frenkie de Jong', 'Centrocampista del FC Barcelona', 2023, 11.0, 'Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Thibaut Courtois', 'Portero del Real Madrid C. F.', 2023, 10.0, 'Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Ansu Fati', 'Delantero del FC Barcelona', 2023, 13.0, 'Muy Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Gerard Moreno', 'Delantero del Villarreal CF', 2023, 9.0, 'Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Iago Aspas', 'Delantero del Celta de Vigo', 2023, 8.5, 'Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Jules Koundé', 'Defensa del Sevilla FC', 2023, 9.5, 'Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Yannick Carrasco', 'Centrocampista del Atlético de Madrid', 2023, 10.0, 'Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('David Alaba', 'Defensa del Real Madrid C. F.', 2023, 11.0, 'Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Rodrygo', 'Delantero del Real Madrid C. F.', 2023, 12.0, 'Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Sergio Busquets', 'Centrocampista del FC Barcelona', 2023, 9.0, 'Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Luis Suárez', 'Delantero del Atlético de Madrid', 2023, 14.0, 'Muy Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Dani Carvajal', 'Defensa del Real Madrid C. F.', 2023, 8.0, 'Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Jordi Alba', 'Defensa del FC Barcelona', 2023, 8.5, 'Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Koke', 'Centrocampista del Atlético de Madrid', 2023, 9.5, 'Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Gerard Moreno', 'Delantero del Villarreal CF', 2023, 9.0, 'Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Mikel Oyarzabal', 'Delantero de la Real Sociedad', 2023, 10.0, 'Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Pau Torres', 'Defensa del Villarreal CF', 2023, 8.5, 'Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Unai Simón', 'Portero del Athletic Club', 2023, 7.5, 'Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Marcos Llorente', 'Centrocampista del Atlético de Madrid', 2023, 9.0, 'Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Dani Parejo', 'Centrocampista del Villarreal CF', 2023, 8.0, 'Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Sergi Roberto', 'Centrocampista del FC Barcelona', 2023, 7.5, 'Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Ángel Correa', 'Delantero del Atlético de Madrid', 2023, 8.5, 'Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Nabil Fekir', 'Centrocampista del Real Betis', 2023, 9.0, 'Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Sergio Canales', 'Centrocampista del Real Betis', 2023, 8.5, 'Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Youssef En-Nesyri', 'Delantero del Sevilla FC', 2023, 9.0, 'Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Lucas Ocampos', 'Centrocampista del Sevilla FC', 2023, 8.5, 'Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Raúl García', 'Centrocampista del Athletic Club', 2023, 7.5, 'Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Iñaki Williams', 'Delantero del Athletic Club', 2023, 8.0, 'Rara', 1);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('José Luis Morales', 'Delantero del Levante UD', 2023, 7.5, 'Rara', 1);
-- Insertar datos album 2 (yugioh)
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Dragón Blanco de Ojos Azules', 'Poderoso dragón de ojos azules', 2023, 15.0, 'Muy Rara', 2);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Mago Oscuro', 'Poderoso mago oscuro', 2023, 15.0, 'Muy Rara', 2);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Dragón Negro de Ojos Rojos', 'Dragón con ojos rojos', 2023, 12.0, 'Rara', 2);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Exodia el Prohibido', 'Monstruo legendario que otorga la victoria automática si se reúnen todas sus partes', 2023, 20.0, 'Ultra Rara', 2);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Dragón Blanco Alternativo de Ojos Azules', 'Versión alternativa del Dragón Blanco de Ojos Azules', 2023, 18.0, 'Muy Rara', 2);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Mago Oscuro del Caos', 'Mago oscuro con habilidades de caos', 2023, 14.0, 'Rara', 2);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Dragón Supremo de Ojos Azules', 'Dragón supremo con tres cabezas', 2023, 22.0, 'Ultra Rara', 2);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Kuriboh', 'Pequeño monstruo peludo', 2023, 5.0, 'Común', 2);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Dragón de Polvo de Estrellas', 'Dragón con habilidades de polvo de estrellas', 2023, 16.0, 'Muy Rara', 2);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Dragón Arcoíris', 'Dragón con poderes de arcoíris', 2023, 17.0, 'Muy Rara', 2);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Mago del Tiempo', 'Mago con habilidades de manipulación del tiempo', 2023, 10.0, 'Rara', 2);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Dragón de Cinco Cabezas', 'Dragón con cinco cabezas', 2023, 25.0, 'Ultra Rara', 2);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Dragón de la Rosa Negra', 'Dragón con habilidades de rosa negra', 2023, 15.0, 'Muy Rara', 2);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Dragón de la Tormenta de Relámpagos', 'Dragón con habilidades de tormenta de relámpagos', 2023, 18.0, 'Muy Rara', 2);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Dragón de la Llama Azul', 'Dragón con habilidades de llama azul', 2023, 14.0, 'Rara', 2);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Dragón de la Llama Roja', 'Dragón con habilidades de llama roja', 2023, 14.0, 'Rara', 2);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Dragón de la Llama Verde', 'Dragón con habilidades de llama verde', 2023, 14.0, 'Rara', 2);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Dragón de la Llama Negra', 'Dragón con habilidades de llama negra', 2023, 14.0, 'Rara', 2);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Dragón de la Llama Blanca', 'Dragón con habilidades de llama blanca', 2023, 14.0, 'Rara', 2);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Dragón de la Llama Dorada', 'Dragón con habilidades de llama dorada', 2023, 14.0, 'Rara', 2);
INSERT INTO cromos (nombre, descripcion, anio, valor, rareza, album_id) VALUES ('Dragón de la Llama Plateada', 'Dragón con habilidades de llama plateada', 2023, 14.0, 'Rara', 2);