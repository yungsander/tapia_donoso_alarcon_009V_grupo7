CREATE TABLE IF NOT EXISTS documentos (
    id_documento          BIGINT NOT NULL AUTO_INCREMENT,
    numero_manifiesto     VARCHAR(255),
    nombre_original       VARCHAR(255),
    tipo_archivo          VARCHAR(255),
    ruta_almacenamiento   VARCHAR(255),
    fecha_subida          DATETIME,
    PRIMARY KEY (id_documento)
);
