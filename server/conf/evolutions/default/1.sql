# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table carona (
  id                            bigserial not null,
  horario_id                    bigint,
  endereco_id                   bigint,
  vagas                         integer,
  tipo                          integer,
  constraint ck_carona_tipo check (tipo in ('1','0')),
  constraint uq_carona_horario_id unique (horario_id),
  constraint uq_carona_endereco_id unique (endereco_id),
  constraint pk_carona primary key (id)
);

create table endereco (
  id                            bigserial not null,
  rua                           varchar(255),
  bairro                        varchar(255),
  constraint pk_endereco primary key (id)
);

create table horario (
  id                            bigserial not null,
  aula                          varchar(255),
  dia                           integer,
  constraint ck_horario_dia check (dia in ('4','2','0','3','1')),
  constraint pk_horario primary key (id)
);

create table notificacao (
  id                            bigserial not null,
  message                       varchar(255),
  usuario_id                    bigint,
  constraint pk_notificacao primary key (id)
);

create table rota (
  id                            bigserial not null,
  carona_id                     bigint not null,
  destino                       varchar(255),
  constraint pk_rota primary key (id)
);

create table solicitacao (
  id                            bigserial not null,
  passageiro_id                 bigint,
  carona_id                     bigint,
  constraint uq_solicitacao_passageiro_id unique (passageiro_id),
  constraint uq_solicitacao_carona_id unique (carona_id),
  constraint pk_solicitacao primary key (id)
);

create table usuario (
  id                            bigserial not null,
  nome                          varchar(255),
  matricula                     varchar(255),
  telefone                      varchar(255),
  email                         varchar(255),
  senha                         varchar(255),
  carona_id                     bigint,
  constraint pk_usuario primary key (id)
);

alter table carona add constraint fk_carona_horario_id foreign key (horario_id) references horario (id) on delete restrict on update restrict;

alter table carona add constraint fk_carona_endereco_id foreign key (endereco_id) references endereco (id) on delete restrict on update restrict;

alter table notificacao add constraint fk_notificacao_usuario_id foreign key (usuario_id) references usuario (id) on delete restrict on update restrict;
create index ix_notificacao_usuario_id on notificacao (usuario_id);

alter table rota add constraint fk_rota_carona_id foreign key (carona_id) references carona (id) on delete restrict on update restrict;
create index ix_rota_carona_id on rota (carona_id);

alter table solicitacao add constraint fk_solicitacao_passageiro_id foreign key (passageiro_id) references usuario (id) on delete restrict on update restrict;

alter table solicitacao add constraint fk_solicitacao_carona_id foreign key (carona_id) references carona (id) on delete restrict on update restrict;

alter table usuario add constraint fk_usuario_carona_id foreign key (carona_id) references carona (id) on delete restrict on update restrict;
create index ix_usuario_carona_id on usuario (carona_id);


# --- !Downs

alter table if exists carona drop constraint if exists fk_carona_horario_id;

alter table if exists carona drop constraint if exists fk_carona_endereco_id;

alter table if exists notificacao drop constraint if exists fk_notificacao_usuario_id;
drop index if exists ix_notificacao_usuario_id;

alter table if exists rota drop constraint if exists fk_rota_carona_id;
drop index if exists ix_rota_carona_id;

alter table if exists solicitacao drop constraint if exists fk_solicitacao_passageiro_id;

alter table if exists solicitacao drop constraint if exists fk_solicitacao_carona_id;

alter table if exists usuario drop constraint if exists fk_usuario_carona_id;
drop index if exists ix_usuario_carona_id;

drop table if exists carona cascade;

drop table if exists endereco cascade;

drop table if exists horario cascade;

drop table if exists notificacao cascade;

drop table if exists rota cascade;

drop table if exists solicitacao cascade;

drop table if exists usuario cascade;

