CREATE DATABASE aluguel_carros
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'C'
    LC_CTYPE = 'C'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

<!--tabela veiculos-->
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

<!--tabela usuarios-->
CREATE TABLE user_entity (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    endereco VARCHAR(255) NOT NULL,
    profissao VARCHAR(100) NOT NULL,
    role VARCHAR(50) NOT NULL -- Adicionando o campo role
);


INSERT INTO user_entity (nome, cpf, endereco, profissao, role)
VALUES
    ('João Silva', '123.456.789-00', 'Rua das Flores, 123', 'Engenheiro', 'cliente'),
    ('Maria Oliveira', '987.654.321-00', 'Avenida Central, 456', 'Médica', 'cliente'),
    ('Carlos Pereira', '321.654.987-00', 'Travessa das Palmeiras, 789', 'Advogado', 'agente'),
    ('Ana Souza', '456.789.123-00', 'Rua dos Pinheiros, 101', 'Professora', 'cliente'),
    ('Roberto Lima', '654.321.987-00', 'Praça das Árvores, 202', 'Arquiteto', 'cliente'),
    ('Paula Martins', '789.123.456-00', 'Rua do Sol, 303', 'Designer Gráfico', 'agente'),
    ('Fernanda Costa', '159.753.486-00', 'Avenida das Nações, 404', 'Dentista', 'cliente'),
    ('Eduardo Ferreira', '357.951.258-00', 'Rua do Porto, 505', 'Programador', 'agente'),
    ('Bruna Alves', '258.147.369-00', 'Avenida do Mar, 606', 'Psicóloga', 'cliente'),
    ('Ricardo Gomes', '147.258.369-00', 'Rua da Montanha, 707', 'Administrador', 'agente');

    UPDATE user_entity SET role = 'CLIENTE' WHERE id IN (1, 2, 4, 5, 7, 9);
    UPDATE user_entity SET role = 'AGENET' WHERE id IN (3, 6, 8, 10);


SELECT * FROM user_entity;

