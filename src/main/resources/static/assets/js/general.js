$(function () {
    $('#header_div').load('/pages/templates/header.html');
    $('#sidebar_div').load('/pages/templates/sidebar.html');
});

// Funcion para enviar las peticiones al WS
function sendPostAction(ur, model, callBackFunction) {
    $.post(url, JSON.stringify(model), callBackFunction(data));
}

// Funcion para recuperar un parametro de la URL
function GetURLParameter(sParam) {
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++) {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam) {
            return sParameterName[1];
        }
    }
}

// URL Controllers *************************** //
var USER_CONTROLLER_URL = '/adm/usuarios/';


