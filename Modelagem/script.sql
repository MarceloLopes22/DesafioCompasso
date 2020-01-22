-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler  version: 0.7.0
-- PostgreSQL version: 9.3
-- Project Site: pgmodeler.com.br
-- Model Author: ---

SET check_function_bodies = false;
-- ddl-end --


-- Database creation must be done outside an multicommand file.
-- These commands were put in this file only for convenience.
-- -- object: novo_banco_de_dados | type: DATABASE --
-- -- DROP DATABASE novo_banco_de_dados;
-- CREATE DATABASE novo_banco_de_dados
-- ;
-- -- ddl-end --
-- 

-- object: public.cliente | type: TABLE --
-- DROP TABLE public.cliente;
CREATE TABLE public.cliente(
	id_cliente integer NOT NULL,
	nome_completo varchar(100),
	sexo int4,
	data_nascimento date,
	idade int4,
	CONSTRAINT pk_cliente PRIMARY KEY (id_cliente),
	CONSTRAINT un_cliente UNIQUE (id_cliente)

);
-- ddl-end --
-- object: public.cidade | type: TABLE --
-- DROP TABLE public.cidade;
CREATE TABLE public.cidade(
	id_cidade int4 NOT NULL,
	nome varchar(100),
	estado varchar(2),
	CONSTRAINT pk_cidade PRIMARY KEY (id_cidade),
	CONSTRAINT un_cidade UNIQUE (id_cidade)

);
-- ddl-end --
-- object: public.cliente_cidade | type: TABLE --
-- DROP TABLE public.cliente_cidade;
CREATE TABLE public.cliente_cidade(
	id_cliente integer,
	id_cidade int4,
	CONSTRAINT cliente_cidade_pk PRIMARY KEY (id_cliente,id_cidade)

);
-- ddl-end --
-- object: cliente_fk | type: CONSTRAINT --
-- ALTER TABLE public.cliente_cidade DROP CONSTRAINT cliente_fk;
ALTER TABLE public.cliente_cidade ADD CONSTRAINT cliente_fk FOREIGN KEY (id_cliente)
REFERENCES public.cliente (id_cliente) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --


-- object: cidade_fk | type: CONSTRAINT --
-- ALTER TABLE public.cliente_cidade DROP CONSTRAINT cidade_fk;
ALTER TABLE public.cliente_cidade ADD CONSTRAINT cidade_fk FOREIGN KEY (id_cidade)
REFERENCES public.cidade (id_cidade) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --



