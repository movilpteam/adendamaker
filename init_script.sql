-- Roles definidos ************************************************ --
INSERT INTO AW_ADMIN.ROLES (ID, NOMBRE) VALUES (1, 'Super-Administrador');
INSERT INTO AW_ADMIN.ROLES (ID, NOMBRE) VALUES (2, 'Administrador');
INSERT INTO AW_ADMIN.ROLES (ID, NOMBRE) VALUES (3, 'Operador');
INSERT INTO AW_ADMIN.ROLES (ID, NOMBRE) VALUES (4, 'Auditor');

-- Empresa Inicial
INSERT INTO AW_ADMIN.EMPRESA (NOMBRE, DIRECCION, TELEFONO, RESPONSABLE, CORREO_CONTACTO)
    VALUES ('Empresa Inicial', '', '', '', '');

-- Usuario Administrador Inicial Password = Admin123 ********************************* --
INSERT INTO AW_ADMIN.USUARIOS(
  USERNAME,
  NOMBRE,
  APATERNO,
  AMATERNO,
  CORREO,
  ENABLED,
  CAMBIAR_PWD,
  PASSWORD,
  ID_EMPRESA) VALUES ('admin', 'Administrador', '', '', '', 1, 0, '$2a$10$VfZ8MLVlYUNquqDkevP1kuoKElHh/sUsWrLeccRfZgogFIYBTARZu', 1);

-- Se asigna el Role Super-Administrador al usuario creado --
INSERT INTO AW_ADMIN.USER_ROLES (ID_USER, ID_ROLE) VALUES (1, 1);