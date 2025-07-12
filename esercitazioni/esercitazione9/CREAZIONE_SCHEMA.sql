-- Group
create table "GROUP" (
   "ID"  integer  not null,
   "GROUPNAME"  varchar(50),
  primary key ("ID")
);


-- Module
create table "MODULE" (
   "ID"  integer  not null,
   "MODULEID"  varchar(50),
   "MODULENAME"  varchar(50),
  primary key ("ID")
);


-- User
create table "USER" (
   "ID"  integer  not null,
   "USERNAME"  varchar(50),
   "PASSWORD"  varchar(50),
   "EMAIL"  varchar(50),
  primary key ("ID")
);


-- Artista
create table "ARTISTA" (
   "ID"  integer  not null,
   "BIOGRAFIA"  varchar(255),
   "NOME"  varchar(50),
  primary key ("ID")
);


-- Album
create table "ALBUM" (
   "ID"  integer  not null,
   "ANNO" INT CHECK ((("ANNO">0) AND ("ANNO"<9999) OR ("ANNO" IS NULL))),
   "TITOLO"  varchar(50),
  primary key ("ID")
);

-- Brano
CREATE TABLE "BRANO"(
	"ID" integer NOT NULL,
	"TITOLO" VARCHAR(50),
	"DURATA" DECIMAL (4,2),
	PRIMARY KEY(id)
);


-- Supporto
create table "SUPPORTO" (
   "ID"  integer  not null,
   "PREZZO"  double,
  primary key ("ID")
);


-- TipoSupporto
create table "TIPO_SUPPORTO" (
   "ID"  integer  not null,
   "NOME"  varchar(50),
  primary key ("ID")
);


-- LineaOrdine
create table "LINEA_ORDINE" (
   "ID"  integer  not null,
   "QUANTITA"  integer,
  primary key ("ID")
);


-- Ordine
create table "ORDINE" (
   "ID"  integer  not null,
   "DATA"  date,
  primary key ("ID")
);


-- Group_DefaultModule
alter table "GROUP"  add column  "MODULE_ID"  integer;
alter table "GROUP"   add constraint FK_GROUP_MODULE foreign key ("MODULE_ID") references "MODULE" ("ID");


-- Group_Module
create table "GROUP_MODULE" (
   "GROUP_ID"  integer not null,
   "MODULE_ID"  integer not null,
  primary key ("GROUP_ID", "MODULE_ID")
);
-- group_module to group
alter table "GROUP_MODULE"   add constraint FK_GROUP_MODULE_GROUP foreign key ("GROUP_ID") references "GROUP" ("ID");
-- group_module to module
alter table "GROUP_MODULE"   add constraint FK_GROUP_MODULE_MODULE foreign key ("MODULE_ID") references "MODULE" ("ID");


-- User_DefaultGroup
alter table "USER"  add column  "GROUP_ID"  integer;
alter table "USER"   add constraint FK_USER_GROUP foreign key ("GROUP_ID") references "GROUP" ("ID");


-- User_Group
create table "USER_GROUP" (
   "ID_USER"  integer not null,
   "ID_GROUP"  integer not null,
  primary key ("ID_USER", "ID_GROUP")
);

-- user_group to user
alter table "USER_GROUP"   add constraint FK_USER_GROUP_USER foreign key ("ID_USER") references "USER" ("ID");
-- user_group to group
alter table "USER_GROUP"   add constraint FK_USER_GROUP_GROUP foreign key ("ID_GROUP") references "GROUP" ("ID");

-- Brano_Album
alter table "BRANO"  add column  "ALBUM_ID"  integer;
alter table "BRANO"   add constraint FK_BRANO_ALBUM foreign key ("ALBUM_ID") references "ALBUM" ("ID");

-- Album_Artista
create table "ALBUM_ARTISTA" (
   "ALBUM_ID"  integer not null,
   "ARTISTA_ID"  integer not null,
  primary key ("ALBUM_ID", "ARTISTA_ID")
);
-- album_artista to album
alter table "ALBUM_ARTISTA"   add constraint FK_ALBUM_ARTISTA_ALBUM foreign key ("ALBUM_ID") references "ALBUM" ("ID");
-- album_artista to artista
alter table "ALBUM_ARTISTA"   add constraint FK_ALBUM_ARTISTA_ARTISTA foreign key ("ARTISTA_ID") references "ARTISTA" ("ID");


-- Supporto_Album
alter table "SUPPORTO"  add column  "ALBUM_ID"  integer;
alter table "SUPPORTO"   add constraint FK_SUPPORTO_ALBUM foreign key ("ALBUM_ID") references "ALBUM" ("ID");


-- Supporto_TipoSupporto
alter table "SUPPORTO"  add column  "TIPO_SUPPORTO_ID"  integer;
alter table "SUPPORTO"   add constraint FK_SUPPORTO_TIPO_SUPPORTO foreign key ("TIPO_SUPPORTO_ID") references "TIPO_SUPPORTO" ("ID");


-- LineaOrdine_Supporto
alter table "LINEA_ORDINE"  add column  "SUPPORTO_ID"  integer;
alter table "LINEA_ORDINE"   add constraint FK_LINEA_ORDINE_SUPPORTO foreign key ("SUPPORTO_ID") references "SUPPORTO" ("ID");


-- LineaOrdine_Ordine
alter table "LINEA_ORDINE"  add column  "ID_ORDINE"  integer;
alter table "LINEA_ORDINE"   add constraint FK_LINEA_ORDINE_ORDINE foreign key ("ID_ORDINE") references "ORDINE" ("ID");


-- Ordine_User
alter table "ORDINE"  add column  "ID_USER"  integer;
alter table "ORDINE"   add constraint FK_ORDINE_USER foreign key ("ID_USER") references "USER" ("ID");

