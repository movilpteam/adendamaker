CREATE OR REPLACE AW_ADMIN.PACKAGE PKG_APPLICATION_BURO IS
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
  /*
  || ---------------------------------------------------------------------------------||
  || PROCEDURE STP_INS_TEMPLATE                                                       ||
  || Definicion: Inserta el TEMPLATE de correo                                        ||
  || P_ID: Identificador de la plantilla                                              ||
  || P_NOMBRE: Nombre de la plantilla 'welcome, reset, recover'                       ||
  || P_ASUNTO: Asunto del correo                                                      ||
  || P_BODY: Cuerpo del correo                                                        ||
  || ---------------------------------------------------------------------------------||
  */
  --
  PROCEDURE STP_INS_TEMPLATE(P_ID            IN CORREO_PLANTILLA.ID%TYPE,
                             P_NOMBRE        IN CORREO_PLANTILLA.NOMBRE%TYPE,
                             P_ASUNTO        IN CORREO_PLANTILLA.ASUNTO%TYPE,
                             P_BODY          IN CORREO_PLANTILLA.BODY%TYPE,
                             P_SP_STATUS     OUT NUMBER,
                             P_SP_STATUS_MSG OUT VARCHAR2);
  --
END PKG_APPLICATION_BURO;
/
CREATE OR REPLACE PACKAGE BODY AW_ADMIN.PKG_APPLICATION_BURO IS
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
      SELECT Y.ASUNTO, Y.BODY, Y.NOMBRE
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
  /*
  || ---------------------------------------------------------------------------------||
  || PROCEDURE STP_INS_TEMPLATE                                                       ||
  || Definicion: Inserta el TEMPLATE de correo                                        ||
  || P_ID: Identificador de la plantilla                                              ||
  || P_NOMBRE: Nombre de la plantilla 'welcome, reset, recover'                       ||
  || P_ASUNTO: Asunto del correo                                                      ||
  || P_BODY: Cuerpo del correo                                                        ||
  || ---------------------------------------------------------------------------------||
  */
  --
  PROCEDURE STP_INS_TEMPLATE(P_ID            IN CORREO_PLANTILLA.ID%TYPE,
                             P_NOMBRE        IN CORREO_PLANTILLA.NOMBRE%TYPE,
                             P_ASUNTO        IN CORREO_PLANTILLA.ASUNTO%TYPE,
                             P_BODY          IN CORREO_PLANTILLA.BODY%TYPE,
                             P_SP_STATUS     OUT NUMBER,
                             P_SP_STATUS_MSG OUT VARCHAR2) IS
    --
  BEGIN
    --
    P_SP_STATUS     := V_CONST_OK;
    P_SP_STATUS_MSG := 'Plantilla guardada con exito.';
    --
    UPDATE CORREO_PLANTILLA
       SET ASUNTO = P_ASUNTO, BODY = P_BODY
     WHERE ID_CORREO = P_ID
       AND NOMBRE = P_NOMBRE;
    IF (sql%rowcount = 0) THEN
      INSERT INTO CORREO_PLANTILLA
      VALUES
        (CORREO_PLANTILLA_SEQ.NEXTVAL,
         P_ASUNTO,
         P_BODY,
         V_CONST_NOK,
         P_ID,
         P_NOMBRE);
    END IF;
    --
    COMMIT;
    --
  EXCEPTION
    WHEN OTHERS THEN
      --
      ROLLBACK;
      P_SP_STATUS     := V_CONST_NOK;
      P_SP_STATUS_MSG := LPAD(TO_CHAR(ABS(SQLCODE)), 4, V_CONST_SZERO) ||
                         V_CONST_BLANK_SPACE || SQLERRM;
      --
  END STP_INS_TEMPLATE;
  --
END PKG_APPLICATION_BURO;
/
