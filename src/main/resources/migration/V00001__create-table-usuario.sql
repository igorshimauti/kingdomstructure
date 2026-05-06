CREATE TABLE usuario (
    id bigserial NOT NULL,
    cpf char(11) NOT NULL,
    email varchar(150) NOT NULL,
    nome_completo varchar(100) NOT NULL,
    data_nascimento date,
    telefone varchar(11),
    whatsapp boolean,
    tipo_usuario varchar(20) NOT NULL,
    senha varchar(300) NOT NULL,
    ativo boolean NOT NULL DEFAULT TRUE,
    data_cadastro timestamptz NOT NULL,
    ultima_atualizacao timestamptz,
    CONSTRAINT usuario_pk PRIMARY KEY (id),
    CONSTRAINT usuario_cpf_uk UNIQUE (cpf),
    CONSTRAINT usuario_email_social_uk UNIQUE (email)
);

INSERT INTO usuario(cpf, email, nome_completo, telefone, whatsapp, tipo_usuario, senha, data_cadastro)
VALUES( '36285117802', 'igorshimauti@gmail.com', 'Igor Gonçalves Shimauti', '11986255438', true, 'ADMINISTRADOR', '$2a$10$bQ8lA/F98JkNRhspZw3w9umEnIu5Oq8scT1qSc6N3avKiVA7Yc686', CURRENT_TIMESTAMP);