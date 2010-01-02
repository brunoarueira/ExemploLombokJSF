CREATE DATABASE "ExemploLombokJSF"
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       CONNECTION LIMIT = -1;

CREATE TABLE noticias
(
  titulo character varying(40) NOT NULL,
  codigo serial NOT NULL,
  conteudo character varying(150) NOT NULL,
  CONSTRAINT noticias_pk PRIMARY KEY (codigo)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE noticias OWNER TO postgres;