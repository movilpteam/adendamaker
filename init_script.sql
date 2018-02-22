-- Roles definidos ************************************************ --
INSERT INTO AW_ADMIN.ROLES (ID, NOMBRE) VALUES (1, 'Super-Administrador');
INSERT INTO AW_ADMIN.ROLES (ID, NOMBRE) VALUES (2, 'Administrador');
INSERT INTO AW_ADMIN.ROLES (ID, NOMBRE) VALUES (3, 'Operador');
INSERT INTO AW_ADMIN.ROLES (ID, NOMBRE) VALUES (4, 'Auditor');

-- Empresa Inicial
INSERT INTO AW_ADMIN.EMPRESA (NOMBRE, RFC, DIRECCION, TELEFONO, RESPONSABLE, CORREO_CONTACTO)
    VALUES ('Empresa Inicial', 'X01000000X0X', '', '', '', '');

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
  ID_EMPRESA) VALUES ('admin', 'Administrador', '', '', '', 'Y', 'N', '$2a$10$/oVsMeMEz2FoaxpUEEiTB.Rypjm3KXR4dmynzIoShH2grbEyqvY0a', 1);