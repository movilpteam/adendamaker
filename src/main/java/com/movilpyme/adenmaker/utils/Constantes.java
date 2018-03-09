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
}
