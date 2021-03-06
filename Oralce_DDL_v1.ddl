-- Generated by Oracle SQL Developer Data Modeler 17.4.0.355.2131
--   at:        2018-02-12 10:56:18 CST
--   site:      Oracle Database 11g
--   type:      Oracle Database 11g


CREATE TABLE aw_admin.addenda (
  id             INTEGER
    CONSTRAINT nnc_addenda_id_addenda NOT NULL,
  nombre         CHAR(50)
    CONSTRAINT nnc_addenda_nombre NOT NULL,
  descripcion    CHAR(100),
  xsd            CHAR(50),
  fecha_creacion DATE
    CONSTRAINT nncafecha_creacion NOT NULL,
  fecha_edicion  DATE
    CONSTRAINT nncafecha_edicion NOT NULL,
  id_estatus     INTEGER DEFAULT 1
    CONSTRAINT nnca_id_estatus NOT NULL,
  id_empresa     INTEGER
    CONSTRAINT nnc_addenda_id_empresa NOT NULL
);

COMMENT ON COLUMN aw_admin.addenda.id IS
'Datos generales de las adendas';

ALTER TABLE aw_admin.addenda
  ADD CONSTRAINT addenda_pk PRIMARY KEY (id);

CREATE TABLE aw_admin.addenda_campo (
  id              INTEGER
    CONSTRAINT nncacampo_id_addenda NOT NULL,
  id_etiqueta     INTEGER
    CONSTRAINT nncacampo_idetiqueta NOT NULL,
  etiqueta        CHAR(150)
    CONSTRAINT nncacampo_etiqueta NOT NULL,
  valor_defecto   CHAR(50),
  id_validacion   INTEGER
    CONSTRAINT nncacampo_idvalidacion NOT NULL,
  is_requerido    INTEGER DEFAULT 0
    CONSTRAINT nnc_addenda_campo_is_requerido NOT NULL,
  limite_inferior INTEGER,
  limite_superior INTEGER,
  id_estatus      INTEGER DEFAULT 1
    CONSTRAINT nnc_addenda_campo_id_estatus NOT NULL
);

ALTER TABLE aw_admin.addenda_campo
  ADD CONSTRAINT addenda_campo_pk PRIMARY KEY (id);

CREATE TABLE aw_admin.addenda_etiqueta (
  id                           INTEGER
    CONSTRAINT nncaetiqueta_idetiqueta NOT NULL,
  id_addenda_etiqueta_relacion INTEGER,
  id_addenda                   INTEGER
    CONSTRAINT nncaetiqueta_idaddenda NOT NULL,
  name_space                   CHAR(100),
  etiqueta                     CHAR(100)
    CONSTRAINT nnc_addenda_etiqueta_etiqueta NOT NULL,
  descripcion                  CHAR(250),
  min_occurs                   INTEGER DEFAULT -1,
  max_occurs                   INTEGER DEFAULT -1,
  is_requerido                 CHAR(1) DEFAULT '0'
    CONSTRAINT nncaetiqueta_isrequerido NOT NULL,
  id_estatus                   INTEGER DEFAULT 1
    CONSTRAINT nncaetiqueta_idestatus NOT NULL
);

COMMENT ON COLUMN aw_admin.addenda_etiqueta.id IS
'Guarda las etiquetas que corresponden a la addenda, puede contener relaciones a otras etiquetas';

ALTER TABLE aw_admin.addenda_etiqueta
  ADD CONSTRAINT addenda_etiqueta_pk PRIMARY KEY (id);

CREATE TABLE aw_admin.addenda_validacion (
  id         INTEGER
    CONSTRAINT nncvala_validacion NOT NULL,
  regex      CHAR(500)
    CONSTRAINT nncavalidacion_regex NOT NULL,
  id_estatus INTEGER DEFAULT 1
    CONSTRAINT nncavalid_estatus NOT NULL
);

ALTER TABLE aw_admin.addenda_validacion
  ADD CONSTRAINT avalidacion_pk PRIMARY KEY (id);

CREATE TABLE aw_admin.addenda_xmlns (
  id         INTEGER
    CONSTRAINT nncaxmlns_id_xmlns NOT NULL,
  id_addenda INTEGER
    CONSTRAINT nnc_addenda_xmlns_id_addenda NOT NULL,
  suffix     CHAR(20),
  xmlns      CHAR(250)
);

COMMENT ON COLUMN aw_admin.addenda_xmlns.suffix IS
'ACOTACION DEL XMLNS';

ALTER TABLE aw_admin.addenda_xmlns
  ADD CONSTRAINT axmlns_pk PRIMARY KEY (id);

CREATE TABLE aw_admin.bitacora_addenda (
  id                INTEGER
                              CONSTRAINT nncbitacora_id NOT NULL,
  fecha             DATE      NOT NULL,
  id_user           INTEGER
                              CONSTRAINT nnc_bitacora_addenda_id_user NOT NULL,
  id_addenda        INTEGER
                              CONSTRAINT nncbitacora_id_addenda NOT NULL,
  tipo_modificacion CHAR(20)  NOT NULL,
  descripcion       CHAR(200) NOT NULL,
  comentarios       CHAR(200)
);

COMMENT ON COLUMN aw_admin.bitacora_addenda.id IS
'Bitacora de los cambios en las addendas';

ALTER TABLE aw_admin.bitacora_addenda
  ADD CONSTRAINT bitacora_addenda_pk PRIMARY KEY (id);

CREATE TABLE aw_admin.correo_plantilla (
  id     INTEGER
    CONSTRAINT nnc_correo_plantilla_id NOT NULL,
  nombre VARCHAR2(20),
  asunto VARCHAR2(30),
  body   VARCHAR2(500),
  html   CHAR(1)
);

COMMENT ON COLUMN aw_admin.correo_plantilla.id IS
'Plantillas de los correos a enviar';

ALTER TABLE aw_admin.correo_plantilla
  ADD CONSTRAINT correo_plantilla_pk PRIMARY KEY (id);

CREATE TABLE aw_admin.empresa (
  id              INTEGER
    CONSTRAINT nnc_empresa_id NOT NULL,
  nombre          VARCHAR2(100),
  rfc             VARCHAR2(14),
  logo            VARCHAR2(100),
  direccion       VARCHAR2(200),
  telefono        VARCHAR2(20),
  responsable     VARCHAR2(100),
  correo_contacto VARCHAR2(100)
);

COMMENT ON COLUMN aw_admin.empresa.id IS
'Se guardan los datos de las empresas';

ALTER TABLE aw_admin.empresa
  ADD CONSTRAINT empresa_pk PRIMARY KEY (id);

CREATE TABLE aw_admin.facturas_procesadas (
  id              INTEGER
    CONSTRAINT nnc_facturas_procesadas_id NOT NULL,
  fecha           DATE,
  rfc_receptor    VARCHAR2(14),
  uuid            VARCHAR2(100),
  sello           VARCHAR2(200),
  num_certificado VARCHAR2(20),
  sello_sat       VARCHAR2(200),
  id_adenda       INTEGER
    CONSTRAINT nncfprocesadas_idadenda NOT NULL,
  id_empresa      INTEGER
    CONSTRAINT nncfprocesadas_idempresa NOT NULL
);

COMMENT ON COLUMN aw_admin.facturas_procesadas.id IS
'Se registran todas las facturas procesadas por empresa';

ALTER TABLE aw_admin.facturas_procesadas
  ADD CONSTRAINT facturas_procesadas_pk PRIMARY KEY (id);

CREATE TABLE aw_admin.parametros (
  id        INTEGER
    CONSTRAINT nnc_parametros_id NOT NULL,
  parametro VARCHAR2(20),
  valor     VARCHAR2(50)
);

COMMENT ON COLUMN aw_admin.parametros.id IS
'Parametros de configuracion:

Ex: 
Datos de conexion al servicio de envio de correo';

ALTER TABLE aw_admin.parametros
  ADD CONSTRAINT parametros_pk PRIMARY KEY (id);

CREATE TABLE aw_admin.roles (
  id     INTEGER
    CONSTRAINT nnc_roles_id NOT NULL,
  nombre VARCHAR2(50)
);

COMMENT ON COLUMN aw_admin.roles.id IS
'Roles permitidos en el sistema';

ALTER TABLE aw_admin.roles
  ADD CONSTRAINT roles_pk PRIMARY KEY (id);

CREATE TABLE aw_admin.user_roles (
  id      INTEGER
    CONSTRAINT nnc_user_roles_id NOT NULL,
  id_user INTEGER
    CONSTRAINT nnc_user_roles_id_user NOT NULL,
  id_role INTEGER
    CONSTRAINT nnc_user_roles_id_role NOT NULL
);

COMMENT ON COLUMN aw_admin.user_roles.id IS
'Guarda la relacion de los usuarios con los roles';

ALTER TABLE aw_admin.user_roles
  ADD CONSTRAINT user_roles_pk PRIMARY KEY (id);

CREATE TABLE aw_admin.usuarios (
  id                INTEGER
    CONSTRAINT nnc_user_id NOT NULL,
  username          VARCHAR2(45)
    CONSTRAINT nnc_user_username NOT NULL,
  nombre            VARCHAR2(20),
  apaterno          VARCHAR2(20),
  amaterno          VARCHAR2(20),
  correo            VARCHAR2(150),
  password          VARCHAR2(200),
  enabled           CHAR(1),
  pregunta_secreta  VARCHAR2(100),
  respuesta_secreta VARCHAR2(100),
  cambiar_pwd       CHAR(1),
  last_pwd_chg      DATE,
  id_empresa        INTEGER
    CONSTRAINT nnc_user_id_empresa NOT NULL
);

COMMENT ON COLUMN aw_admin.usuarios.id IS
'Contiene los datos de los usuarios';

ALTER TABLE aw_admin.usuarios
  ADD CONSTRAINT user_pk PRIMARY KEY (id);

ALTER TABLE aw_admin.bitacora_addenda
  ADD CONSTRAINT abitacora_fk1 FOREIGN KEY (id_addenda)
REFERENCES aw_admin.addenda (id);

ALTER TABLE aw_admin.bitacora_addenda
  ADD CONSTRAINT abitacora_fk2 FOREIGN KEY (id_user)
REFERENCES aw_admin.usuarios (id);

ALTER TABLE aw_admin.addenda_campo
  ADD CONSTRAINT acampo_fk1 FOREIGN KEY (id_etiqueta)
REFERENCES aw_admin.addenda_etiqueta (id)
ON DELETE CASCADE;

ALTER TABLE aw_admin.addenda_campo
  ADD CONSTRAINT acampo_fk2 FOREIGN KEY (id_validacion)
REFERENCES aw_admin.addenda_validacion (id);

ALTER TABLE aw_admin.addenda
  ADD CONSTRAINT addenda_fk1 FOREIGN KEY (id_empresa)
REFERENCES aw_admin.empresa (id)
ON DELETE CASCADE;

ALTER TABLE aw_admin.addenda_etiqueta
  ADD CONSTRAINT aetiqueta_fk1 FOREIGN KEY (id_addenda)
REFERENCES aw_admin.addenda (id)
ON DELETE CASCADE;

ALTER TABLE aw_admin.addenda_xmlns
  ADD CONSTRAINT axmlns_fk1 FOREIGN KEY (id_addenda)
REFERENCES aw_admin.addenda (id)
ON DELETE CASCADE;

ALTER TABLE aw_admin.facturas_procesadas
  ADD CONSTRAINT fprocesadas_fk1 FOREIGN KEY (id_adenda)
REFERENCES aw_admin.addenda (id);

ALTER TABLE aw_admin.facturas_procesadas
  ADD CONSTRAINT fprocesadas_fk2 FOREIGN KEY (id_empresa)
REFERENCES aw_admin.empresa (id)
ON DELETE CASCADE;

ALTER TABLE aw_admin.usuarios
  ADD CONSTRAINT user_empresa_fk FOREIGN KEY (id_empresa)
REFERENCES aw_admin.empresa (id);

ALTER TABLE aw_admin.user_roles
  ADD CONSTRAINT user_roles_roles_fk FOREIGN KEY (id_role)
REFERENCES aw_admin.roles (id)
ON DELETE CASCADE;

ALTER TABLE aw_admin.user_roles
  ADD CONSTRAINT user_roles_user_fk FOREIGN KEY (id_user)
REFERENCES aw_admin.usuarios (id)
ON DELETE CASCADE;

CREATE SEQUENCE aw_admin.empresa_seq
  START WITH 1
  NOCACHE
  ORDER;

CREATE OR REPLACE TRIGGER aw_admin.empresa_seq_trg
  BEFORE
  INSERT
  ON aw_admin.empresa
  FOR EACH ROW
  WHEN ( new.id IS NULL )
  BEGIN
    :new.id := aw_admin.empresa_seq.nextval;
  END;

CREATE SEQUENCE aw_admin.usuarios_seq
  START WITH 1
  NOCACHE
  ORDER;

CREATE OR REPLACE TRIGGER aw_admin.usuarios_seq_trg
  BEFORE
  INSERT
  ON aw_admin.usuarios
  FOR EACH ROW
  WHEN ( new.id IS NULL )
  BEGIN
    :new.id := aw_admin.usuarios_seq.nextval;
  END;
/

CREATE SEQUENCE aw_admin.userroles_seq
  START WITH 1
  NOCACHE
  ORDER;

CREATE OR REPLACE TRIGGER aw_admin.userroles_seq_trg
  BEFORE
  INSERT
  ON aw_admin.user_roles
  FOR EACH ROW
  WHEN ( new.id IS NULL )
  BEGIN
    :new.id := aw_admin.userroles_seq.nextval;
  END;
/

CREATE SEQUENCE aw_admin.addenda_id_seq
  START WITH 1
  NOCACHE
  ORDER;

CREATE OR REPLACE TRIGGER aw_admin.addenda_id_trg
  BEFORE
  INSERT
  ON aw_admin.addenda
  FOR EACH ROW
  WHEN ( new.id IS NULL )
  BEGIN
    :new.id := aw_admin.addenda_id_seq.nextval;
  END;
/

CREATE SEQUENCE aw_admin.addenda_campo_id_seq
  START WITH 1
  NOCACHE
  ORDER;

CREATE OR REPLACE TRIGGER aw_admin.addenda_campo_id_trg
  BEFORE
  INSERT
  ON aw_admin.addenda_campo
  FOR EACH ROW
  WHEN ( new.id IS NULL )
  BEGIN
    :new.id := aw_admin.addenda_campo_id_seq.nextval;
  END;
/

CREATE SEQUENCE aw_admin.addenda_etiqueta_id_seq
  START WITH 1
  NOCACHE
  ORDER;

CREATE OR REPLACE TRIGGER aw_admin.addenda_etiqueta_id_trg
  BEFORE
  INSERT
  ON aw_admin.addenda_etiqueta
  FOR EACH ROW
  WHEN ( new.id IS NULL )
  BEGIN
    :new.id := aw_admin.addenda_etiqueta_id_seq.nextval;
  END;
/

CREATE SEQUENCE aw_admin.addenda_validacion_id_seq
  START WITH 1
  NOCACHE
  ORDER;

CREATE OR REPLACE TRIGGER aw_admin.addenda_validacion_id_trg
  BEFORE
  INSERT
  ON aw_admin.addenda_validacion
  FOR EACH ROW
  WHEN ( new.id IS NULL )
  BEGIN
    :new.id := aw_admin.addenda_validacion_id_seq.nextval;
  END;
/

CREATE SEQUENCE aw_admin.addenda_xmlns_id_seq
  START WITH 1
  NOCACHE
  ORDER;

CREATE OR REPLACE TRIGGER aw_admin.addenda_xmlns_id_trg
  BEFORE
  INSERT
  ON aw_admin.addenda_xmlns
  FOR EACH ROW
  WHEN ( new.id IS NULL )
  BEGIN
    :new.id := aw_admin.addenda_xmlns_id_seq.nextval;
  END;
/



-- Oracle SQL Developer Data Modeler Summary Report: 
-- 
-- CREATE TABLE                            13
-- CREATE INDEX                             0
-- ALTER TABLE                             25
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           5
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          5
-- CREATE MATERIALIZED VIEW                 0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
