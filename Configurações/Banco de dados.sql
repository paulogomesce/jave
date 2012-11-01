﻿drop sequence if exists seq_id_email;

CREATE SEQUENCE seq_id_email
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

drop sequence if exists seq_id_endereco;

CREATE SEQUENCE seq_id_endereco
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

drop sequence if exists seq_id_pessoa;

CREATE SEQUENCE seq_id_pessoa
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

drop sequence if exists seq_id_telefone;

CREATE SEQUENCE seq_id_telefone
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

drop sequence if exists seq_id_uf;

CREATE SEQUENCE seq_id_uf
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

drop table if exists tb_telefone;

CREATE TABLE tb_telefone (
    id bigint NOT NULL,
    codigodearea varchar(3),
    numero varchar(10),
    pessoa_id bigint
);

drop table if exists tb_email;

CREATE TABLE tb_email (
    id bigint NOT NULL,
    email varchar(100),
    pessoa_id bigint
);

drop table if exists tb_endereco;

CREATE TABLE tb_endereco (
    id bigint NOT NULL,
    bairro varchar(100),
    cep varchar(10),
    cidade varchar(100),
    complemento varchar(255),
    numero integer,
    pontodereferencia varchar(255),
    rua varchar(100),
    pessoa_id bigint,
    uf_id bigint
);

drop table if exists tb_pessoa;

CREATE TABLE tb_pessoa (
    id bigint NOT NULL,
    cnpj varchar(14),
    cpf varchar(11),
    datacadastro timestamp without time zone,
    datanascimento timestamp without time zone,
    nome varchar(100),
    sexo varchar(10),
    foto bytea
);

drop table if exists tb_uf;

CREATE TABLE tb_uf (
    id bigint NOT NULL,
    nome varchar(50),
    sigla varchar(2)
);

ALTER TABLE ONLY tb_telefone
    ADD CONSTRAINT pk_tb_telefone PRIMARY KEY (id);

ALTER TABLE ONLY tb_email
    ADD CONSTRAINT pk_tb_email PRIMARY KEY (id);

ALTER TABLE ONLY tb_endereco
    ADD CONSTRAINT pk_tb_endereco PRIMARY KEY (id);

ALTER TABLE ONLY tb_pessoa
    ADD CONSTRAINT pk_tb_pessoa PRIMARY KEY (id);

ALTER TABLE ONLY tb_uf
    ADD CONSTRAINT pk_tb_uf PRIMARY KEY (id);

ALTER TABLE ONLY tb_telefone
    ADD CONSTRAINT fk_telefone_pessoa_id FOREIGN KEY (pessoa_id) REFERENCES tb_pessoa(id);

ALTER TABLE ONLY tb_endereco
    ADD CONSTRAINT fk_endereco_pessoa_id FOREIGN KEY (pessoa_id) REFERENCES tb_pessoa(id);

ALTER TABLE ONLY tb_endereco
    ADD CONSTRAINT fk_endereco_uf_id FOREIGN KEY (uf_id) REFERENCES tb_uf(id);

ALTER TABLE ONLY tb_email
    ADD CONSTRAINT fk_email_id_pessoa FOREIGN KEY (pessoa_id) REFERENCES tb_pessoa(id);

INSERT INTO tb_uf values(nextval('seq_id_uf'), 'Ceará', 'CE');
INSERT INTO tb_uf values(nextval('seq_id_uf'), 'Bahia', 'BA');
INSERT INTO tb_uf values(nextval('seq_id_uf'), 'Pernambuco', 'PE');
