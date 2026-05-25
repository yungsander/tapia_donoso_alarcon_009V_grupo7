CREATE TABLE IF NOT EXISTS exportaciones (
    id                  BIGINT NOT NULL AUTO_INCREMENT,
    fecha_exportacion   DATE NOT NULL,
    pais_destino        VARCHAR(100) NOT NULL,
    estado              VARCHAR(50) NOT NULL,
    id_mercancia        BIGINT NOT NULL,
    peso                DOUBLE NOT NULL,
    tipo_mercancia      VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);
