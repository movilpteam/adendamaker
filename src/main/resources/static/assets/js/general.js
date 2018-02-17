$(function () {
    $('#header_div').load('/pages/templates/header.html');
    $('#sidebar_div').load('/pages/templates/sidebar.html');
});

// Funcion para enviar las peticiones al WS
function sendPostAction(url, model, callBackFunction) {
   /* $.post(url, JSON.stringify(model), function (data){
        callBackFunction(data);
    }); */

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
            var msj = data.responseJSON['status'];
            var msj = msj + ' - ' + data.responseJSON['message'];
            showDivMessage(msj, 'alert-danger', 4000);
        }
    });
}
function callbackFunctionHttp(xmlhttp) {}
function sendPostActionParams(url, model, callBackFunction) {
	try{
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange = callbackFunctionHttp(xmlhttp);
		xmlhttp.open("POST", url, false);
		xmlhttp.setRequestHeader("Content-Type", "application/json");
		xmlhttp.onreadystatechange = callbackFunctionHttp(xmlhttp);
		xmlhttp.send(JSON.stringify(model));
		callBackFunction(xmlhttp.responseText);
	} catch (e) {
		showDivMessage(e, 'alert-danger', 4000);
	}
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
    $('#message-content').html(message);
    $('#div-message').addClass(divclass);
    $('#div-message').css('display', 'block');
    setTimeout(function () {
        $('#div-message').css('display', 'none');
    }, time);
}

// URL Controllers *************************** //
var USER_CONTROLLER_URL = '/adm/usuarios/';
var EMPRESA_CONTROLLER_URL = '/adm/empresa/';


