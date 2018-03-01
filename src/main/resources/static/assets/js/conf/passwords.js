var PWD = new Parametros();
$(document).ready(function() {
	sendPostAction(PASSWORD_CONTROLLER_URL + 'byId/1', null, loadTablePwd);
	$('#form-contrasenia').on('submit', function() {
		console.log('length: ' + $('#length').val());
		console.log('customCheckUppercase: ' + $('#customCheckUppercase:checked').val());
		console.log('customCheckNumber: ' + $('#customCheckNumber:checked').val());
		console.log('customCheckChar: ' + $('#customCheckChar:checked').val());
		console.log('combo-renewal-password: ' + $('#combo-renewal-password').val());
		console.log('customCheckAutoReset: ' + $('#customCheckAutoReset:checked').val());
		// Guardar
		return false;
	});
});
$.validate({
	form : '#form-contrasenia'
});
function loadTablePwd(data) {
    for (var i = 0; i < data.length; i++) {
    	if(data[i].parametro === 'length'){
    		$('#length').val("" + data[i].valor);
    	} else if(data[i].parametro === 'customCheckUppercase'){
    		$('#customCheckUppercase').val("" + data[i].valor);
    	} else if(data[i].parametro === 'customCheckNumber'){
    		$('#customCheckNumber').val("" + data[i].valor);
    	} else if(data[i].parametro === 'customCheckChar'){
    		$('#customCheckChar').val("" + data[i].valor);
    	} else if(data[i].parametro === 'combo-renewal-password'){
    		$('#combo-renewal-password').val("" + data[i].valor).trigger('change');
    	} else if(data[i].parametro === 'customCheckAutoReset'){
    		$('#customCheckAutoReset').val("" + data[i].valor);
    	}
    }
}