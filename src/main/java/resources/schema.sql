CREATE TABLE vendings (
                          id       BIGINT AUTO_INCREMENT PRIMARY KEY,
                          address  VARCHAR(255) NOT NULL,
                          status   VARCHAR(50)  NOT NULL,
                          capacity INT
);