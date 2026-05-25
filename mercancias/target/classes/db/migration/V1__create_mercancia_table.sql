CREATE TABLE IF NOT EXISTS Mercancia (
    id          BIGINT NOT NULL AUTO_INCREMENT,
    nombre      VARCHAR(255) NOT NULL,
    tipo        VARCHAR(255) NOT NULL,
    peso        DOUBLE NOT NULL,
    valor       DOUBLE NOT NULL,
    paisOrigen  VARCHAR(255) NOT NULL,
    estado      VARCHAR(255),
    PRIMARY KEY (id)
);
