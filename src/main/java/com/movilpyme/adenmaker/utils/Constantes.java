package com.movilpyme.adenmaker.utils;

public class Constantes {

    // ******************* Configuracion de Contraseña. Nombres de los parametros ********************** //
    public static final String AUTORENEW = "customCheckAutoReset";
    public static final String DAYS_ = "combo-renewal-password";
    public static final String UPPERCASE = "customCheckUppercase";
    public static final String SPECIALCHAR = "customCheckChar";
    public static final String NUMBERCHAR = "customCheckNumber";
    public static final String LENGTH = "length";

    // ****************** Opciones del Menu ************************************************************* //
    public static final String MENU_HOME = "<li><a href='/home'><i class='zmdi zmdi-home'></i> Home</a></li>";
    public static final String MENU_CONFIGURACION = "<li class='navigation__sub'>"+
            "<a href=''><i class='zmdi zmdi-wrench'></i>Configuración</a>"+
            "<ul>"+
            "<li><a href='/conf/logo'>Logotipo</a></li>"+
            "<li><a href='/conf/email'>Correo Electrónico</a></li>"+
            "<li><a href='/conf/password'>Contraseña</a></li>"+
            "</ul>"+
            "</li>";

    public static final String MENU_ADMINISTRACION = "<li class='navigation__sub'>"+
            "<a href=''><i class='zmdi zmdi-collection-text'></i>Administración</a>"+
            "<ul>"+
            "<li><a href='/adm/empresas'>Empresas</a></li>"+
            "<li><a href='/adm/usuarios'>Usuarios</a></li>"+
            "</ul>"+
            "</li>";

    public static final String MENU_ADDENDAS = "<li class='navigation__sub'>" +
            "                <a href=''><i class='zmdi zmdi-view-week'></i> Addendas</a>" +
            "                <ul>" +
            "                    <li><a href='/addenda/list'>Lista Addendas</a></li>" +
            "                    <li><a href='/addenda/layout'>Layout</a></li>" +
            "                    <li><a href='/addenda/plantillas'>Plantillas</a></li>" +
            "                </ul>" +
            "            </li>";

    public static final String MENU_TEST = "<li><a href='/test'><i class='zmdi zmdi-format-underlined'></i> Test</a></li>";

    public static final String MENU_REPORTES = "<li><a href='/reportes'><i class='zmdi zmdi-widgets'></i> Reportes</a></li>";

    public static final String MENU_SALIR = "<li><a href='' onclick='logout()'><i class='zmdi zmdi-lock'></i> Salir</a></li>";
    // ******************* Configuracion de Addendas. Nombres de los parametros ********************** //
    public static final String BACK_SLASH = "/";
    public static final String BACK_SLASH_DOUBLE = "//";
    public static final String DOUBLE_QUOTE = "\"";
    public static final String OPEN_CORCHETE = "[";
    public static final String CLOSE_CORCHETE = "]";

    public static final String JAVA_TEST = "Main.java";
    public static final String JAVA_TEST_DATA = "package mx.com.buro.mc.generated.schema;\n" +
    		"\n" +
    		"		public class Main {\n" +
    		"\n" +
    		"			public static void main(String[] args) {\n" +
    		"				System.out.println(\"Hola Mundo !!!\");\n" +
    		"			}\n" +
    		"		}";
    public static final String MANIFEST = "MANIFEST.MF";
    public static final String MANIFEST_DATA = "Manifest-Version: 1.0\n" +
    		"Created-By: 1.8.0_161 (Oracle Corporation)\n" +
    		"Main-Class: mx.com.buro.mc.generated.schema.Main";

    public static final String ERROR = "ERROR";
    public static final String ADDENDA = "addenda";
    public static final String PATH_XSD = "path-xsd";
    public static final String COMMAND_XJC = "command-xjc";
    public static final String SRC = "src";
    public static final String COMMAND_JAR = "command-jar";
}
