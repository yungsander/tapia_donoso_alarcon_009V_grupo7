CREATE TABLE IF NOT EXISTS usuario (
    id          BIGINT NOT NULL AUTO_INCREMENT,
    nombre      VARCHAR(100) NOT NULL,
    apellido    VARCHAR(100) NOT NULL,
    email       VARCHAR(150) NOT NULL UNIQUE,
    telefono    VARCHAR(20),
    fecha_nacimiento DATE NOT NULL,
    estado      VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);
