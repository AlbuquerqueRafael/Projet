# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table usuario (
  id                            bigint not null,
  nome                          varchar(255),
  matricula                     varchar(255),
  telefone                      varchar(255),
  email                         varchar(255),
  senha                         varchar(255),
  foto                          longvarbinary,
  constraint pk_usuario primary key (id)
);
create sequence usuario_seq;


# --- !Downs

drop table if exists usuario;
drop sequence if exists usuario_seq;

