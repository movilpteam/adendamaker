$(document).ready(function() {
	sendPostAction(PASSWORD_CONTROLLER_URL + 'byName/length', null, loadTablePwd);
	sendPostAction(PASSWORD_CONTROLLER_URL + 'byName/customCheckUppercase', null, loadTablePwd);
	sendPostAction(PASSWORD_CONTROLLER_URL + 'byName/customCheckNumber', null, loadTablePwd);
	sendPostAction(PASSWORD_CONTROLLER_URL + 'byName/customCheckChar', null, loadTablePwd);
	sendPostAction(PASSWORD_CONTROLLER_URL + 'byName/combo-renewal-password', null, loadTablePwd);
	sendPostAction(PASSWORD_CONTROLLER_URL + 'byName/customCheckAutoReset', null, loadTablePwd);
	$('#form-contrasenia').on('submit', function() {
		console.log('length: ' + $('#length').val());
		console.log('customCheckUppercase: ' + $('#customCheckUppercase:checked').val());
		console.log('customCheckNumber: ' + $('#customCheckNumber:checked').val());
		console.log('customCheckChar: ' + $('#customCheckChar:checked').val());
		console.log('combo-renewal-password: ' + $('#combo-renewal-password').val());
		console.log('customCheckAutoReset: ' + $('#customCheckAutoReset:checked').val());
		// Guardar
		var pwdConf = new PasswordConfig();

		pwdConf.name  = 'length';
		pwdConf.valor = $('#length').val();
		sendPostAction(PASSWORD_CONTROLLER_URL + 'save', pwdConf, save);

		pwdConf.name  = 'customCheckUppercase';
		pwdConf.valor = $('#customCheckUppercase:checked').val();
		sendPostAction(PASSWORD_CONTROLLER_URL + 'save', pwdConf, save);

		pwdConf.name  = 'customCheckNumber';
		pwdConf.valor = $('#customCheckNumber:checked').val();
		sendPostAction(PASSWORD_CONTROLLER_URL + 'save', pwdConf, save);

		pwdConf.name  = 'customCheckChar';
		pwdConf.valor = $('#customCheckChar:checked').val();
		sendPostAction(PASSWORD_CONTROLLER_URL + 'save', pwdConf, save);

		pwdConf.name  = 'combo-renewal-password';
		pwdConf.valor = $('#combo-renewal-password').val();
		sendPostAction(PASSWORD_CONTROLLER_URL + 'save', pwdConf, save);

		pwdConf.name  = 'customCheckAutoReset';
		pwdConf.valor = $('#customCheckAutoReset:checked').val();
		sendPostAction(PASSWORD_CONTROLLER_URL + 'save', pwdConf, save);
		
		return false;
	});
});
$.validate({
	form : '#form-contrasenia'
});
function loadTablePwd(data) {
	for (var i = 0; i < data.length; i++) {
		if (data[i].name === 'length') {
			$('#length').val("" + data[i].valor);
		} else if (data[i].name === 'customCheckUppercase') {
			$("#customCheckUppercase").prop("checked", data[i].valor === 'on' ? true : false);
		} else if (data[i].name === 'customCheckNumber') {
			$('#customCheckNumber').prop("checked", data[i].valor === 'on' ? true : false);
		} else if (data[i].name === 'customCheckChar') {
			$('#customCheckChar').prop("checked", data[i].valor === 'on' ? true : false);
		} else if (data[i].name === 'combo-renewal-password') {
			$('#combo-renewal-password').val("" + data[i].valor).trigger('change');
		} else if (data[i].name === 'customCheckAutoReset') {
			$('#customCheckAutoReset').prop("checked", data[i].valor === 'on' ? true : false);
		}
	}
}
function save(data) {
    if (data === true){
        showDivMessage('Configuración Guardada', 'alert-info', 3000);
        sendPostAction(EMAIL_CONTROLLER_URL + 'list', null, loadTableEmail);
    }else {
        showDivMessage('Error al guardar informacion', 'alert-danger', 3000);
    }
    showTableCard();
}