function checkCredentials() {
    var model = {
        username: $('#txt_login_id').val(),
        pwd: $('#txt_pwd_login').val()
    };
    sendPostAction(LOGIN_CONTROLLER_URL + 'credentials', model, loginResponse);
}

function loginResponse(data) {
    sessionStorage.setItem('login', data);
}