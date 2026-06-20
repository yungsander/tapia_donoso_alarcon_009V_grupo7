CREATE TABLE IF NOT EXISTS importaciones (
    id                  BIGINT NOT NULL AUTO_INCREMENT,
    fecha_importacion   DATE NOT NULL,
    pais_origen         VARCHAR(255) NOT NULL,
    estado              VARCHAR(255) NOT NULL,
    id_mercancia        BIGINT NOT NULL,
    peso                DOUBLE NOT NULL,
    tipo_mercancia      VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);
