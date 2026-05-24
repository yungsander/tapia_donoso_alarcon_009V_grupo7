CREATE TABLE IF NOT EXISTS impuesto (
    id                  BIGINT NOT NULL AUTO_INCREMENT,
    tipo_impuesto       VARCHAR(100) NOT NULL,
    tasa_impuesto       DOUBLE NOT NULL,
    monto_calculado     DOUBLE NOT NULL,
    fecha_aplicacion    DATE NOT NULL,
    estado              VARCHAR(50) NOT NULL,
    id_mercancia        BIGINT NOT NULL,
    PRIMARY KEY (id)
);
