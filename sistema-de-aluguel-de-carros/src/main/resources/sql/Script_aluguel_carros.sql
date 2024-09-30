
CREATE TABLE auto_entity (
    id SERIAL PRIMARY KEY,
    matricula VARCHAR(10) NOT NULL,
    placa VARCHAR(10) NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    marca VARCHAR(50) NOT NULL,
    ano VARCHAR(10) NOT NULL
);

INSERT INTO auto_entity (matricula, placa, marca, modelo, ano)
VALUES
    ('ABC1234', 'XYZ1234', 'fiat', 'palio','2004'),
    ('DEF5678', 'XYZ5678', 'chevrolet', 'onix', '2013'),
    ('GHI9012', 'XYZ9012', 'nissan', 'frontier', '2021'),
    ('JKL3456', 'XYZ3456', 'honda', 'civic', '1996'),
    ('MNO7890', 'XYZ7890', 'audi', 'a3', '2022'),
    ('MNO7530', 'XYZ3135', 'renault', 'sandero', '2024');
;

SELECT * FROM auto_entity;

