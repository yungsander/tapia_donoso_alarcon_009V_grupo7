CREATE TABLE IF NOT EXISTS paises (
    id          BIGINT NOT NULL AUTO_INCREMENT,
    codigo_iso  VARCHAR(255),
    nombre      VARCHAR(255),
    PRIMARY KEY (id)
);
