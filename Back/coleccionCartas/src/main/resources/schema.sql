CREATE SEQUENCE IF NOT EXISTS cromos_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS album (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    usuario_id BIGINT,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

CREATE TABLE IF NOT EXISTS cromos (
    id BIGINT DEFAULT nextval('cromos_seq') PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    anio INT,
    valor DECIMAL(10, 2),
    rareza VARCHAR(50),
    album_id BIGINT,
    FOREIGN KEY (album_id) REFERENCES album(id) ON DELETE CASCADE
);