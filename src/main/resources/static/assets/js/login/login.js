var PWD_REGEX = '^(?=.{8,})(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$';
var GET_QUESTION = true;

$(document).ready(function () {
    $.getScript('assets/js/SHA512.js', null);
    $('#txt_pwd_login').keyup(function (event) {
        if (event.which === 13) {
            checkCredentials();
        }
    });
    sendPostLogin(LOGIN_CONTROLLER_URL + 'autoreset', null, autoResetResponse);
    sendPostLogin(LOGIN_CONTROLLER_URL + 'pwdregex', null, getPwdRegex);
});

function getPreguntas(data) {
    var combo = $('#question_combo');
    var user = JSON.parse(sessionStorage.getItem("login")).usuario;
    for (var i = 0; i < data.length; i++){
        var option = new Option(data[i].pregunta, data[i].id);
        option.selected = (i + 1) === user.preguntaSecreta;
        combo.append(option);
    }
}

function getPwdRegex(data) {
    if (data.regex.length > 0) {
        PWD_REGEX = data.regex;
    }
}

function autoResetResponse(data) {
    if (!data) {
        $('#a-autoreset').css('display', 'none');
    }
}

function resetPassword() {
    var model = {
        username: $('#username_reset').val()
    };
    if (GET_QUESTION){
        sendPostLogin(LOGIN_CONTROLLER_URL + 'user/pregunta', model, getQuestionForReset);
    }else {
        model['answer'] = $('#answer-reset').val();
        sendPostLogin(LOGIN_CONTROLLER_URL + 'resetPwd', model, resetPasswordSend);
        GET_QUESTION = true;
    }
}

function getQuestionForReset(data){
    if (data.NA.toLowerCase() === 'true') {
        var model = {
            answer: 'NA',
            username: $('#username_reset').val()
        };
        sendPostLogin(LOGIN_CONTROLLER_URL + 'resetPwd', model, resetPasswordSend);
    }else {
        $('#div-reset-q').css('display', 'block');
        $('#pregunta-reset').text(data.pregunta);
    }
    GET_QUESTION = false;
}

function resetPasswordSend(data) {
    $('#a_login').trigger('click');
    showDivMessage('Correo Enviado. Ingrese con su nueva Contraseña', 'alert-info', 4000);
}

function btnchangeInitPwd() {
    var respuesta = $('#answer');
    if (respuesta.val().length === 0){
        showDivMessage('Debe escribir una respuesta', 'alert-danger', 5000);
        respuesta.focus();
    }else {
        var login = JSON.parse(sessionStorage.getItem('login'));
        var model = {
            username: login.usuario.username,
            actual: SHA512($('#chg_pwd_actual').val()),
            nuevo: SHA512($('#chg_pwd_new').val()),
            idpregunta: $('#question_combo').val(),
            answer: respuesta.val()
        };
        sendPostAction(USER_CONTROLLER_URL + 'changePassword', model, changeInitPwd);
    }

}

function changeInitPwd(data) {
    sessionStorage.clear();
    var pwd_component = $('#txt_pwd_login');
    pwd_component.val("");
    pwd_component.focus();
    showDivMessage('Contraseña Actualizada. Favor de Iniciar Sesion con la Nueva Contraseña', 'alert-info', 3000);
    $('#a_login').trigger('click');
}

function checkCredentials() {
    var hash = SHA512($('#txt_pwd_login').val());
    var model = {
        username: $('#txt_login_id').val(),
        pwd: hash
    };
    sendPostLogin(LOGIN_CONTROLLER_URL + 'credentials', model, loginResponse);
}

function loginResponse(data) {
    if (data.token != null) {
        sessionStorage.setItem('login', JSON.stringify(data));
        if (data.usuario.cambiarPwd) {
            sendPostLogin(LOGIN_CONTROLLER_URL + 'preguntas', null, getPreguntas);
            $('#a_chgpwd').trigger("click");
        } else {
            window.location = 'home';
        }
    } else {
        showDivMessage('Respuesta del Servidor Corrupta', 'alert-danger', 4000);
    }
}