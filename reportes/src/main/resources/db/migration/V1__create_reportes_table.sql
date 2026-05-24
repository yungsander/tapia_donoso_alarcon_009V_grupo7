CREATE TABLE IF NOT EXISTS reportes (
    id_reporte              BIGINT NOT NULL AUTO_INCREMENT,
    numero_manifiesto       BIGINT NOT NULL,
    fecha_y_hora_ingreso    DATETIME,
    año_tramite             INT,
    mes_tramite             INT,
    nombre_agencia          INT,
    categoria_mercancia     INT,
    total_impuestos_pagados INT,
    estado_final            INT,
    PRIMARY KEY (id_reporte)
);
