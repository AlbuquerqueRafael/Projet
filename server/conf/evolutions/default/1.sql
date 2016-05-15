# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table carona (
  id                            bigint not null,
  horario_id                    bigint,
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

create table endereco (
  id                            bigint not null,
  rua                           varchar(255),
  bairro                        varchar(255),
  constraint pk_endereco primary key (id)
);
create sequence endereco_seq;

create table horario (
  id                            bigint not null,
  aula                          varchar(255),
  dia                           integer,
  constraint ck_horario_dia check (dia in (0,1,2,3,4)),
  constraint pk_horario primary key (id)
);
create sequence horario_seq;

create table notificacao (
  id                            bigint not null,
  message                       varchar(255),
  constraint pk_notificacao primary key (id)
);
create sequence notificacao_seq;

create table solicitacao (
  id                            bigint not null,
  passageiro_id                 bigint,
  carona_id                     bigint,
  constraint pk_solicitacao primary key (id)
);
create sequence solicitacao_seq;

create table usuario (
  id                            bigint not null,
  nome                          varchar(255),
  matricula                     varchar(255),
  telefone                      varchar(255),
  email                         varchar(255),
  senha                         varchar(255),
  endereco_id                   bigint,
  foto                          longvarbinary,
  constraint uq_usuario_endereco_id unique (endereco_id),
  constraint pk_usuario primary key (id)
);
create sequence usuario_seq;

create table usuario_notificacao (
  usuario_id                    bigint not null,
  notificacao_id                bigint not null,
  constraint pk_usuario_notificacao primary key (usuario_id,notificacao_id)
);

alter table carona add constraint fk_carona_horario_id foreign key (horario_id) references horario (id) on delete restrict on update restrict;
create index ix_carona_horario_id on carona (horario_id);

alter table carona add constraint fk_carona_motorista_id foreign key (motorista_id) references usuario (id) on delete restrict on update restrict;
create index ix_carona_motorista_id on carona (motorista_id);

alter table carona_usuario add constraint fk_carona_usuario_carona foreign key (carona_id) references carona (id) on delete restrict on update restrict;
create index ix_carona_usuario_carona on carona_usuario (carona_id);

alter table carona_usuario add constraint fk_carona_usuario_usuario foreign key (usuario_id) references usuario (id) on delete restrict on update restrict;
create index ix_carona_usuario_usuario on carona_usuario (usuario_id);

alter table solicitacao add constraint fk_solicitacao_passageiro_id foreign key (passageiro_id) references usuario (id) on delete restrict on update restrict;
create index ix_solicitacao_passageiro_id on solicitacao (passageiro_id);

alter table solicitacao add constraint fk_solicitacao_carona_id foreign key (carona_id) references carona (id) on delete restrict on update restrict;
create index ix_solicitacao_carona_id on solicitacao (carona_id);

alter table usuario add constraint fk_usuario_endereco_id foreign key (endereco_id) references endereco (id) on delete restrict on update restrict;

alter table usuario_notificacao add constraint fk_usuario_notificacao_usuario foreign key (usuario_id) references usuario (id) on delete restrict on update restrict;
create index ix_usuario_notificacao_usuario on usuario_notificacao (usuario_id);

alter table usuario_notificacao add constraint fk_usuario_notificacao_notificacao foreign key (notificacao_id) references notificacao (id) on delete restrict on update restrict;
create index ix_usuario_notificacao_notificacao on usuario_notificacao (notificacao_id);


# --- !Downs

alter table carona drop constraint if exists fk_carona_horario_id;
drop index if exists ix_carona_horario_id;

alter table carona drop constraint if exists fk_carona_motorista_id;
drop index if exists ix_carona_motorista_id;

alter table carona_usuario drop constraint if exists fk_carona_usuario_carona;
drop index if exists ix_carona_usuario_carona;

alter table carona_usuario drop constraint if exists fk_carona_usuario_usuario;
drop index if exists ix_carona_usuario_usuario;

alter table solicitacao drop constraint if exists fk_solicitacao_passageiro_id;
drop index if exists ix_solicitacao_passageiro_id;

alter table solicitacao drop constraint if exists fk_solicitacao_carona_id;
drop index if exists ix_solicitacao_carona_id;

alter table usuario drop constraint if exists fk_usuario_endereco_id;

alter table usuario_notificacao drop constraint if exists fk_usuario_notificacao_usuario;
drop index if exists ix_usuario_notificacao_usuario;

alter table usuario_notificacao drop constraint if exists fk_usuario_notificacao_notificacao;
drop index if exists ix_usuario_notificacao_notificacao;

drop table if exists carona;
drop sequence if exists carona_seq;

drop table if exists carona_usuario;

drop table if exists endereco;
drop sequence if exists endereco_seq;

drop table if exists horario;
drop sequence if exists horario_seq;

drop table if exists notificacao;
drop sequence if exists notificacao_seq;

drop table if exists solicitacao;
drop sequence if exists solicitacao_seq;

drop table if exists usuario;
drop sequence if exists usuario_seq;

drop table if exists usuario_notificacao;

