$(function () {
    if (sessionStorage.getItem('login') != null){
        var login = JSON.parse(sessionStorage.getItem('login'));
        $('#header_div').load('/pages/templates/header.html');
        $('#sidebar_div').load('/pages/templates/sidebar.html', function () {
            $('div.user__name').text(login.usuario.nombre + ' ' + login.usuario.apaterno);
            $('div.user__email').text(login.usuario.correo);
        });
        $(document).inactivity({
            timeout: 15000
        });
        $(document).on('inactivity', function () {
            showDivMessage("La sesion de cerrará por inactividad en <label id='seg_to_close'></label> segundos", 'alert-danger', 8000);
            showMessageInactivity(5);
        });
    }else {
        // window.location = '/';
    }
});

function showMessageInactivity(seg) {
    if (seg > 0){
        seg--;
        $('#seg_to_close').text(seg);
        setTimeout(showMessageInactivity(seg), 1000);
    }
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

function showDivMessage(message,divclass, time) {
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
        $(document).inactivity("destroy");
        window.location = "/";
    }else {
        sendPostAction(USER_CONTROLLER_URL + 'logout', login, logout_callback);
    }
}

function logout_callback(data) {
    if (data == true){
        sessionStorage.clear();
        $(document).inactivity("destroy");
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


