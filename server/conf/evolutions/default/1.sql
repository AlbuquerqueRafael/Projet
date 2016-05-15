# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table carona (
  id                            bigint not null,
  motorista_id                  bigint,
  vagas                         integer,
  tipo                          integer,
  constraint ck_carona_tipo check (tipo in (0,1)),
  constraint pk_carona primary key (id)
);
create sequence carona_seq;

create table carona_usuario (
  carona_id                     bigint not null,
  usuario_id                    bigint not null,
  constraint pk_carona_usuario primary key (carona_id,usuario_id)
);

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

alter table carona add constraint fk_carona_motorista_id foreign key (motorista_id) references usuario (id) on delete restrict on update restrict;
create index ix_carona_motorista_id on carona (motorista_id);

alter table carona_usuario add constraint fk_carona_usuario_carona foreign key (carona_id) references carona (id) on delete restrict on update restrict;
create index ix_carona_usuario_carona on carona_usuario (carona_id);

alter table carona_usuario add constraint fk_carona_usuario_usuario foreign key (usuario_id) references usuario (id) on delete restrict on update restrict;
create index ix_carona_usuario_usuario on carona_usuario (usuario_id);


# --- !Downs

alter table carona drop constraint if exists fk_carona_motorista_id;
drop index if exists ix_carona_motorista_id;

alter table carona_usuario drop constraint if exists fk_carona_usuario_carona;
drop index if exists ix_carona_usuario_carona;

alter table carona_usuario drop constraint if exists fk_carona_usuario_usuario;
drop index if exists ix_carona_usuario_usuario;

drop table if exists carona;
drop sequence if exists carona_seq;

drop table if exists carona_usuario;

drop table if exists usuario;
drop sequence if exists usuario_seq;

