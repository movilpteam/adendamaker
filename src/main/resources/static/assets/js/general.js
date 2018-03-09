$(function () {
    if (sessionStorage.getItem('login') != null){
        var login = JSON.parse(sessionStorage.getItem('login'));
        $('#header_div').load('/pages/templates/header.html');
        $('#sidebar_div').load('/pages/templates/sidebar.html', function () {
            $('div.user__name').text(login.usuario.nombre + ' ' + login.usuario.apaterno);
            $('div.user__email').text(login.usuario.correo);
        });
        $.jTimeout({
            'timeoutAfter': 1800,
            'heartbeat': 1,
            'secondsPrior': 30,
            'flashingTitleText': 'Sesion por Terminar',
            'onTimeout': function (jTimeout) {
                logout();
            }
        });
        sendPostAction(USER_CONTROLLER_URL + 'menu/' + login.usuario.id, null, mountMenu);
    }else {
        // window.location = '/';
    }
});

function mountMenu(data) {
    $('#ul_menu').append(data.menu);
}

function getLoggedUser() {
    var login = JSON.parse(sessionStorage.getItem('login'));
    return login.usuario;
}

// Funcion para enviar las peticiones al WS
function sendPostAction(url, model, callBackFunction) {
    var login = JSON.parse(sessionStorage.getItem('login'));
    if (login == null) {
        window.location = "/";
    }else {
        $.ajax(url, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + login.token
            },
            'type': 'POST',
            'data': JSON.stringify(model),
            'dataType': 'json',
            'success': callBackFunction,
            'error': function (data) {
                var msj = "Error sin mensaje";
                if (data.responseJSON != null){
                    msj = data.responseJSON['status'];
                    msj = msj + ' - ' + data.responseJSON['message'];
                }
                showDivMessage(msj, 'alert-danger', 4000);
            }
        });
    }
}

function sendPostLogin(url, model, callBackFunction) {
    $.ajax(url, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'data': JSON.stringify(model),
        'dataType': 'json',
        'success': callBackFunction,
        'error': function (data) {
            var msj = "Error sin mensaje";
            if (data.responseJSON != null){
                msj = data.responseJSON['status'];
                msj = msj + ' - ' + data.responseJSON['message'];
            }
            showDivMessage(msj, 'alert-danger', 4000);
        }
    });
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

function showDivMessage(message, divclass, time) {
    var div_message = $('#div-message');
    $('#message-content').html(message);
    div_message.addClass(divclass);
    div_message.css('display', 'block');
    setTimeout(function () {
        div_message.css('display', 'none');
    }, time);
}

function logout() {
    var login = JSON.parse(sessionStorage.getItem('login'));
    if (login == null){
        window.location = "/";
    }else {
        sendPostAction(USER_CONTROLLER_URL + 'logout', login, logout_callback);
    }
}

function logout_callback(data) {
    if (data === true){
        sessionStorage.clear();
        window.location = "/";
    }else {
        showDivMessage("Error al cerrar sesion", "alert-danger", 3000);
    }
}

// URL Controllers *************************** //
var LOGIN_CONTROLLER_URL = '/login/';
var USER_CONTROLLER_URL = '/adm/usuarios/';
var EMPRESA_CONTROLLER_URL = '/adm/empresa/';
var EMAIL_CONTROLLER_URL = '/adm/correo/';
var PASSWORD_CONTROLLER_URL = '/adm/contrasenia/';
var ADDENDA_CONTROLLER_URL = '/addenda/upload/';