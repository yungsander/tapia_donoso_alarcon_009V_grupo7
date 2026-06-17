DROP TABLE IF EXISTS reportes;

CREATE TABLE IF NOT EXISTS reportes (
    id_reporte              BIGINT NOT NULL AUTO_INCREMENT,
    numero_manifiesto       VARCHAR(255) NOT NULL,
    fecha_y_hora_ingreso    DATETIME,
    año_tramite             INT,
    mes_tramite             INT,
    nombre_agencia          VARCHAR(255),
    categoria_mercancia     INT,
    total_impuestos_pagados DOUBLE,
    estado_final            INT,
    PRIMARY KEY (id_reporte)
);