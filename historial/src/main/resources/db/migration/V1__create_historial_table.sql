CREATE TABLE IF NOT EXISTS historial (
    id_historial    BIGINT NOT NULL AUTO_INCREMENT,
    id_usuario      BIGINT NOT NULL,
    fecha_ingreso   DATE,
    hora_ingreso    TIME,
    fecha_salida    DATE,
    hora_salida     TIME,
    punto_acceso    VARCHAR(255) NOT NULL,
    estado_ingreso  VARCHAR(255) NOT NULL,
    vigente         BOOLEAN NOT NULL,
    PRIMARY KEY (id_historial)
);
