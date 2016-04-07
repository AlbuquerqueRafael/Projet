# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table horario (
  id                        bigint not null,
  data                      varchar(255),
  hora                      varchar(255),
  tipo                      varchar(255),
  usuario_id                bigint,
  constraint pk_horario primary key (id))
;

create table user (
  id                        bigint not null,
  nome                      varchar(255),
  matricula                 varchar(255),
  telefone                  varchar(255),
  bairro                    varchar(255),
  email                     varchar(255),
  rua                       varchar(255),
  senha                     varchar(255),
  vagas                     varchar(255),
  constraint pk_user primary key (id))
;

create table usuario (
  tipo                      varchar(1) not null,
  id                        bigint not null,
  nome                      varchar(255),
  matricula                 varchar(255),
  telefone                  varchar(255),
  bairro                    varchar(255),
  email                     varchar(255),
  rua                       varchar(255),
  senha                     varchar(255),
  vagas                     varchar(255),
  constraint pk_usuario primary key (id))
;

create sequence horario_seq;

create sequence user_seq;

create sequence usuario_seq;

alter table horario add constraint fk_horario_usuario_1 foreign key (usuario_id) references usuario (id) on delete restrict on update restrict;
create index ix_horario_usuario_1 on horario (usuario_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists horario;

drop table if exists user;

drop table if exists usuario;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists horario_seq;

drop sequence if exists user_seq;

drop sequence if exists usuario_seq;

