CREATE DATABASE linketinder
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;

    CREATE TABLE IF NOT EXISTS pessoafisica (
    id serial PRIMARY KEY,
    nome text,
	sobrenome text,
    email text,
	senha text,
    pais text,
    estado text,
    cep text,
    descricao text,
    cpf text,
	formacao text,
    idade int,
    UNIQUE (email, senha)
    );

    CREATE TABLE IF NOT EXISTS pessoajuridica (
    id serial PRIMARY KEY,
    nome text,
    email text,
	senha text,
    pais text,
    estado text,
    cep text,
    descricao text,
    cnpj text,
    UNIQUE (email, senha)
    );

    CREATE TABLE IF NOT EXISTS competencia (
	id serial PRIMARY KEY,
    descricao text,
    nivel text,
    UNIQUE (descricao, nivel)
    );

	CREATE TABLE IF NOT EXISTS relacaocompetenciapessoa (
	id serial PRIMARY KEY,
	competenciaid bigint REFERENCES competencia (id),
	pessoafisicaid bigint REFERENCES pessoafisica (id),
    UNIQUE (competenciaid, pessoafisicaid)
	);

	CREATE TABLE IF NOT EXISTS vaga (
	id serial PRIMARY KEY,
	nome text,
	descrição text,
	estado text,
	cidade text,
	pessoajuridicaid bigint REFERENCES pessoajuridica (id),
	UNIQUE (nome, descrição, estado, cidade)
	);

	CREATE TABLE IF NOT EXISTS relacaocompetenciavaga (
	id serial PRIMARY KEY,
	competenciaid bigint REFERENCES competencia (id),
	vagaid bigint REFERENCES vaga (id),
    UNIQUE (competenciaid, vagaid)
	);

	CREATE TABLE IF NOT EXISTS curtidas (
	id serial PRIMARY KEY,
	pessoafisicacurtiu boolean,
	pessoajuridicacurtiu boolean,
	pessoafisicaid bigint REFERENCES pessoafisica (id),
    pessoajuridicaid bigint REFERENCES pessoajuridica (id)
	);