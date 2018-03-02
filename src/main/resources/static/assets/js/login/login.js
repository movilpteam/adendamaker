$(document).ready(function () {
    $.getScript('assets/js/SHA512.js', null);
    $('#txt_pwd_login').keyup(function (event) {
        if (event.which === 13) {
            checkCredentials();
        }
    });
});

function resetPassword() {
    var model = {
        username: $('#username_reset').val()
    };
    sendPostLogin(LOGIN_CONTROLLER_URL + 'resetPwd', model, resetPasswordSend);
}

function resetPasswordSend() {
    $('#a_login').trigger('click');
    showDivMessage('Correo Enviado. Ingrese con su nueva Contraseña', 'alert-info', 4000);
}

function btnchangeInitPwd() {
    var login = JSON.parse(sessionStorage.getItem('login'));
    var model = {
        username: login.usuario.username,
        actual: SHA512($('#chg_pwd_actual').val()),
        nuevo: SHA512($('#chg_pwd_new').val())
    };
    sendPostAction(USER_CONTROLLER_URL + 'changePassword', model, changeInitPwd);
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
            $('#a_chgpwd').trigger("click");
        } else {
            window.location = 'home';
        }
    } else {
        showDivMessage('Respuesta del Servidor Corrupta', 'alert-danger', 4000);
    }
}