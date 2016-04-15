# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table carona (
  id                            bigint not null,
  usuario_id                    bigint,
  constraint pk_carona primary key (id)
);
create sequence carona_seq;

create table endereco (
  id                            bigint not null,
  rua                           varchar(255),
  bairro                        varchar(255),
  constraint pk_endereco primary key (id)
);
create sequence endereco_seq;

create table horario (
  id                            bigint not null,
  data                          varchar(255),
  hora                          varchar(255),
  tipo                          varchar(255),
  constraint pk_horario primary key (id)
);
create sequence horario_seq;

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

create table usuario (
  id                            bigint not null,
  nome                          varchar(255),
  matricula                     varchar(255),
  telefone                      varchar(255),
  email                         varchar(255),
  senha                         varchar(255),
  constraint pk_usuario primary key (id)
);
create sequence usuario_seq;

alter table carona add constraint fk_carona_usuario_id foreign key (usuario_id) references usuario (id) on delete restrict on update restrict;
create index ix_carona_usuario_id on carona (usuario_id);


# --- !Downs

alter table carona drop constraint if exists fk_carona_usuario_id;
drop index if exists ix_carona_usuario_id;

drop table if exists carona;
drop sequence if exists carona_seq;

drop table if exists endereco;
drop sequence if exists endereco_seq;

drop table if exists horario;
drop sequence if exists horario_seq;

drop table if exists user;
drop sequence if exists user_seq;

drop table if exists usuario;
drop sequence if exists usuario_seq;

