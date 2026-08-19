# Criar o database chamado db_alunos
create database if not exists db_alunos;

# Entrar no database db_alunos
use db_alunos;

# Remover a tabela para recriá-la
drop table if exists tb_alunos;

# Criar a tabela de tb_alunos
create table tb_alunos (id      integer not null, 
                        nome    varchar(100), 
                        idade   integer,
                        curso   varchar(50),
                        fase    integer,
                        constraint pk_tb_alunos primary key(id));

# Listar a tabela criada
show tables;
