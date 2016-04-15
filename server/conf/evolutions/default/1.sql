# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table user (
  id                            bigint not null,
  nome                          varchar(255),
  matricula                     varchar(255),
  telefone                      varchar(255),
  bairro                        varchar(255),
  email                         varchar(255),
  rua                           varchar(255),
  senha                         varchar(255),
  vagas                         varchar(255),
  constraint pk_user primary key (id)
);
create sequence user_seq;


# --- !Downs

drop table if exists user;
drop sequence if exists user_seq;

