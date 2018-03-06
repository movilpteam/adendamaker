var ID_CURRENT_PASSWORD = 0;
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
	// 
	$('#customCheckAutoReset').on('change', function() { 
	    if (this.checked) {
	    	viewTablePwd(true);
	    } else {
	    	viewTablePwd(false);
	    }
	});
	$('#new-questions-form').on('submit', function() {
		var questions = new PreguntasPwd();
		questions.pregunta = $('#questions').val();
		sendPostAction(PASSWORD_CONTROLLER_URL + 'saveQuestions', questions, saveQuestions);
		initData();
		return false;
	});
});
$.validate({
	form : '#form-contrasenia'
});
$.validate({
	form : '#new-questions-form'
});
var modalConfirm = function(callback) {
	$("#modal-btn-ok").on("click", function() {
		callback(true);
		$("#mi-modal-questions").modal('hide');
	});
	$("#modal-btn-nok").on("click", function() {
		callback(false);
		$("#mi-modal-questions").modal('hide');
	});
};
modalConfirm(function(confirm) {
	if (confirm) {
		// Guardar
		if(null === $('#edit-questions').val() || $('#edit-questions').val() == ''){
			showDivMessage('Pregunta con ID: ' + ID_CURRENT_PASSWORD + ' no puede ser modificada con valor vacio', 'alert-danger', 3000);
		} else {
			var questions = new PreguntasPwd();
			questions.id = ID_CURRENT_PASSWORD;
			questions.pregunta = $('#edit-questions').val();
			sendPostAction(PASSWORD_CONTROLLER_URL + 'saveQuestions', questions, saveQuestions);
		}
		initData();
	} else {
		// Acciones si el usuario no confirma
		// Iniciar campos
		initData();
		showDivMessage('Acción Cancelada en Pregunta. No se guardaron los cambios', 'alert-danger', 3000);
	}
});
function initData(){
	ID_CURRENT_PASSWORD = 0;
	$('#questions').val("");
	$('#id-questions').text("");
	$('#edit-questions').val("");
}
function viewTablePwd(data){
	if(data){
		document.getElementById("div-table").style.display = "block";
		sendPostAction(PASSWORD_CONTROLLER_URL + 'listQuestions', null, loadTableQuestions);
	} else {
		document.getElementById("div-table").style.display = "none";
	}
}
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
			data[i].valor === 'on' ? viewTablePwd(true) : viewTablePwd(false);
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
}
function loadTableQuestions(data) {
    $('#tbody_questions').empty();
    for (var i = 0; i < data.length; i++) {
        var tr = "<tr id='questions_"+ data[i].id +"'>" +
	        "<td>" +
	        "<i class='zmdi zmdi-edit zmdi-hc-2x' title='Modificar Pregunta' style='cursor: pointer;margin-left: 5px' onclick='modifyQuestionsAction("+ data[i].id +",\""+ data[i].pregunta +"\")'></i>" +
	        "<i class='zmdi zmdi-delete zmdi-hc-2x' title='Eliminar Pregunta' style='cursor: pointer;margin-left: 5px' onclick='deleteQuestionsAction("+ data[i].id +")'></i>" +
	        "</td>" +
	      //  "<td>"+ data[i].id +"</td>" +
	        "<td>"+ data[i].pregunta +"</td>";
        tr += "</tr>";
        $('#tbody_questions').append(tr);
    }
    // Questions Default
    if(data.length === 0){
    	var questions = new PreguntasPwd();
		questions.pregunta = '¿Nombre de tu primaria?';
		sendPostAction(PASSWORD_CONTROLLER_URL + 'saveQuestions', questions, saveQuestions);
    }
}
function saveQuestions(data) {
    if (data === true){
    	sendPostAction(PASSWORD_CONTROLLER_URL + 'listQuestions', null, loadTableQuestions);
    }else {
        showDivMessage('Error al guardar pregunta', 'alert-danger', 3000);
    }
    showTableCard();
}
function showTableCard() {
    $('#table-card').css('display', 'block');
}
function deleteQuestionsAction(id) {
    $.jAlert({
        'type': 'confirm',
        'title': 'Confirmación',
        'confirmQuestion': '¿ Seguro de eliminar la pregunta secreta ?',
        'confirmBtnText': 'Eliminar',
        'denyBtnText': 'Cancelar',
        'theme': 'red',
        'size': 'md',
        'showAnimation': 'fadeInUp',
        'hideAnimation': 'fadeOutDown',
        'onConfirm': function (e, btn) {
            sendPostAction(PASSWORD_CONTROLLER_URL + 'disabledQuestion/' + id, null, deleteQuestionsResponse);
        }
    });
}
function deleteQuestionsResponse(data) {
    if (data > 0){
        showDivMessage('Pregunta Eliminada', 'alert-info', 3000);
        sendPostAction(PASSWORD_CONTROLLER_URL + 'listQuestions', null, loadTableQuestions);
    }else {
        showDivMessage('Error al eliminar pregunta', 'alert-danger', 3000);
    }
}
function modifyQuestionsAction(id, pregunta){
	console.log(id + ' - ' + pregunta);
	ID_CURRENT_PASSWORD = id;
	// Llenar edicion
	$('#id-questions').text("ID: " + id);
	$('#edit-questions').val(pregunta);
	$("#mi-modal-questions").modal('show');
}