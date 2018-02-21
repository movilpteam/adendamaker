var ID_CURRENT_EMAIL = 0;

$(document).ready(function () {
    sendPostAction(EMAIL_CONTROLLER_URL + 'list', null, loadTableEmail);
    $('#btn-add-email').on('click', function () {
        cleanForm();
        showEditCard();
    });
    $('#btn-cancel-email').on('click', function () {
        showDivMessage('Accion Cancelada. No se guardaron los cambios', 'alert-danger', 3000);
        showTableCard();
    });
    $('#new-email-form').on('submit', function () {
       var email = new Correo();
       email.id = ID_CURRENT_EMAIL;
       email.nameUser = $('#user-name').val();
       email.pwd = $('#password').val();
       email.endServer = $('#end-server').val();
       email.entrancePort = $('#entrance-port').val();
       sendPostAction(EMAIL_CONTROLLER_URL + 'save', email, saveEmail);
       return false;
    });
    // Init editors
    $(".editor-welcome").jqte();
    $(".editor-reset").jqte();
    $(".editor-recover").jqte();
});
$.validate({
    form: '#new-email-form'
});

// Funciones de la pantalla de Email
function loadTableEmail(data) {
    $('#tbody_email').empty();
    for (var i = 0; i < data.length; i++) {
        var tr = '<tr>' +
            '<td><i style="cursor: pointer" class="zmdi zmdi-edit btn-link" onclick="onEditAction('+ data[i].id +')"></i></td>' +
            '<td>'+ data[i].id +'</td>' +
            '<td>'+ data[i].nameUser +'</td>' +
            '<td>'+ data[i].pwd +'</td>' +
            '<td>'+ data[i].endServer +'</td>' +
            '<td>'+ data[i].entrancePort +'</td>' + 
            '<td><i style="cursor: pointer" class="zmdi zmdi-globe-alt btn-link" onclick="onModalAction('+ data[i].id +')"></i></td>';
        $('#tbody_email').append(tr);
    }
}

function cleanForm() {
	ID_CURRENT_EMAIL = 0;
    $('#user-name').val('');
    $('#password').val('');
    $('#end-server').val('');
    $('#entrance-port').val('');
}

function onEditAction(id) {
    sendPostAction(EMAIL_CONTROLLER_URL + 'byId/' + id, null, onEditResponse);
}

function onModalAction(id){
	// Llenar editor
	var correoPlantilla = new CorreoPlantilla();
	correoPlantilla.id = id;
	correoPlantilla.nombre = 'welcome';
	sendPostAction(EMAIL_CONTROLLER_URL + 'findSelTemplate', correoPlantilla, loadEditorWelcome);
	correoPlantilla.nombre = 'reset';
	sendPostAction(EMAIL_CONTROLLER_URL + 'findSelTemplate', correoPlantilla, loadEditorReset);
	correoPlantilla.nombre = 'recover';
	sendPostAction(EMAIL_CONTROLLER_URL + 'findSelTemplate', correoPlantilla, loadEditorRecover);
    // 
    $("#mi-modal-email").modal('show');
}

function onEditResponse(data) {
	ID_CURRENT_EMAIL = data.id;
    $('#user-name').val(data.nameUser);
    $('#password').val(data.pwd);
    $('#end-server').val(data.endServer);
    $('#entrance-port').val(data.entrancePort);
    showEditCard();
}

function saveEmail(data) {
    if (data === true){
        showDivMessage('Correo Guardado', 'alert-info', 3000);
        sendPostAction(EMAIL_CONTROLLER_URL + 'list', null, loadTableEmail);
    }else {
        showDivMessage('Error al guardar informacion', 'alert-danger', 3000);
    }
    showTableCard();
}

function showEditCard() {
    $('#table-card').css('display', 'none');
    $('#btn-add-email').css('display', 'none');
    $('#edit-card').css('display', 'block');
}

function showTableCard() {
    $('#table-card').css('display', 'block');
    $('#edit-card').css('display', 'none');
    $('#btn-add-email').css('display', 'block');
}

var modalConfirm = function(callback) {
	$("#btn-confirm").on("click", function() {
		$("#mi-modal-email").modal('show');
	});
	$("#modal-btn-si").on("click", function() {
		callback(true);
		$("#mi-modal-email").modal('hide');
	});
	$("#modal-btn-no").on("click", function() {
		callback(false);
		$("#mi-modal-email").modal('hide');
	});
};
modalConfirm(function(confirm) {
	if (confirm) {
		//Acciones si el usuario confirma
		var valEditor = $('.editor-welcome').val();
		var valEditor = $('.editor-reset').val();
		var valEditor = $('.editor-recover').val();
	} else {
		//Acciones si el usuario no confirma
		initEditor();
		showDivMessage('Accion Cancelada en Plantilla. No se guardaron los cambios', 'alert-danger', 3000);
	}
});

function initEditor(){
	$('.editor-welcome').jqteVal("");
	$('.editor-reset').jqteVal("");
	$('.editor-recover').jqteVal("");
}

function loadEditorWelcome(data) {
	if(data.pContent.length > 0){
		$('.editor-welcome').jqteVal("" + data.pContent[0].body);
	} else {
		$('.editor-welcome').jqteVal("");
	}
}

function loadEditorReset(data) {
	if(data.pContent.length > 0){
		$('.editor-reset').jqteVal("" + data.pContent[0].body);
	} else {
		$('.editor-reset').jqteVal("");
	}
}

function loadEditorRecover(data) {
	if(data.pContent.length > 0){
		$('.editor-recover').jqteVal("" + data.pContent[0].body);
	} else {
		$('.editor-recover').jqteVal("");
	}
}