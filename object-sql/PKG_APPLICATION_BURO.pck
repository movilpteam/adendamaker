CREATE OR REPLACE PACKAGE PKG_APPLICATION_BURO IS
  --
  /* ---------------------- DESCRIPCION ----------------------------------------------||
  || Paquete Cotrol de Aplicaciones JAVA                                              ||
  || Folio:                                                                           ||
  || Nombre del proyecto:  AUTOMATIZACION ADDENDAS                                    ||
  || ---------------------------------------------------------------------------------||
  || Realizado por: Ing. N. Dazaeev Gonzalez Herrera                                  ||
  || Fabrica: BURO                                                                    ||
  || Fecha: Febrero 2017                                                              ||
  */
  /* ---------------------- MODIFICACIONES ---------------------------------------------
  || Control de Aplicaciones                                                          ||
  */
  /* ------------------------- OBJETOS -------------------------------------------------
  || Control de SQL ...                                                               ||
  || Procesos:                                                                        ||
  */
  /* ---------------------- VERSION 1.0 ------------------------------------------------
  || ##- Inicial                                                                      ||
  */
  --
  /*
  || ---------------------------------------------------------------------------------||
  || PROCEDURE STP_SEL_TEMPLATE                                                       ||
  || Definicion: Devuelve el TEMPLATE de correo                                       ||
  || ---------------------------------------------------------------------------------||
  */
  --
  PROCEDURE STP_SEL_TEMPLATE(P_ID            IN VARCHAR2,
                             P_NOMBRE        IN VARCHAR,
                             P_SP_STATUS     OUT NUMBER,
                             P_SP_STATUS_MSG OUT VARCHAR2,
                             P_CONTENT       OUT SYS_REFCURSOR);
  --
END PKG_APPLICATION_BURO;
/
CREATE OR REPLACE PACKAGE BODY PKG_APPLICATION_BURO IS
  --
  /* ---------------------- DESCRIPCION ----------------------------------------------||
  || Paquete Cotrol de Aplicaciones JAVA                                              ||
  || Folio:                                                                           ||
  || Nombre del proyecto:  AUTOMATIZACION ADDENDAS                                    ||
  || ---------------------------------------------------------------------------------||
  || Realizado por: Ing. N. Dazaeev Gonzalez Herrera                                  ||
  || Fabrica: BURO                                                                    ||
  || Fecha: Febrero 2017                                                              ||
  */
  /* ---------------------- MODIFICACIONES ---------------------------------------------
  || Control de Aplicaciones                                                          ||
  */
  /* ------------------------- OBJETOS -------------------------------------------------
  || Control de SQL ...                                                               ||
  || Procesos:                                                                        ||
  */
  /* ---------------------- VERSION 1.0 ------------------------------------------------
  || ##- Inicial                                                                      ||
  */
  --
  /*
  || ---------------------------------------------------------------------------------||
  || VARIABLES CONSTANTES                                                             ||
  || ---------------------------------------------------------------------------------||
  */
  --
  V_CONST_NOK CONSTANT NUMBER := 1;
  V_CONST_OK  CONSTANT NUMBER := 0;
  --
  V_CONST_SZERO       CONSTANT VARCHAR2(1) := '0';
  V_CONST_BLANK_SPACE CONSTANT VARCHAR2(1) := ' ';
  --
  /*
  || ---------------------------------------------------------------------------------||
  || PROCEDURE'S                                                                      ||
  || ---------------------------------------------------------------------------------||
  */
  --
  /*
  || ---------------------------------------------------------------------------------||
  || PROCEDURE STP_SEL_TEMPLATE                                                       ||
  || Definicion: Devuelve el TEMPLATE de correo                                       ||
  || ---------------------------------------------------------------------------------||
  */
  --
  PROCEDURE STP_SEL_TEMPLATE(P_ID            IN VARCHAR2,
                             P_NOMBRE        IN VARCHAR,
                             P_SP_STATUS     OUT NUMBER,
                             P_SP_STATUS_MSG OUT VARCHAR2,
                             P_CONTENT       OUT SYS_REFCURSOR) IS
    --
  BEGIN
    --
    P_SP_STATUS     := V_CONST_OK;
    P_SP_STATUS_MSG := 'STP_SEL_TEMPLATE EXITOSO';
    --
    OPEN P_CONTENT FOR
      SELECT Y.BODY, Y.NOMBRE
        FROM CORREO X, CORREO_PLANTILLA Y
       WHERE X.ID = P_ID
         AND Y.ID_CORREO = X.ID
         AND Y.NOMBRE = NVL(P_NOMBRE, Y.NOMBRE);
    --
  EXCEPTION
    WHEN OTHERS THEN
      --
      ROLLBACK;
      P_SP_STATUS     := V_CONST_NOK;
      P_SP_STATUS_MSG := LPAD(TO_CHAR(ABS(SQLCODE)), 4, V_CONST_SZERO) ||
                         V_CONST_BLANK_SPACE || SQLERRM;
      --
  END STP_SEL_TEMPLATE;
  --
END PKG_APPLICATION_BURO;
/
