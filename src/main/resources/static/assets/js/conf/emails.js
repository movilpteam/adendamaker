var ID_CURRENT_EMAIL = 0;
var NAME_EDITOR_EMAIL = '';
var WELCOME_TEMPLATE = new CorreoPlantilla();
var RESET_TEMPLATE = new CorreoPlantilla();
var RECOVER_TEMPLATE = new CorreoPlantilla();
var COUNT_TABLE = 0;

var WELCOME_EDITOR = CKEDITOR.replace('textWelcome', {
    language: 'es-mx'
});
var RECOVER_EDITOR = CKEDITOR.replace('textRecover', {
    language: 'es-mx'
});
var RESET_EDITOR = CKEDITOR.replace('textReset', {
    language: 'es-mx'
});

for (var ins in CKEDITOR.instances) {
    CKEDITOR.instances[ins].addCommand('addPassword', {
        exec: function (edt) {
            edt.insertHtml('{password}');
        }
    });
    CKEDITOR.instances[ins].addCommand('addUsername', {
        exec: function (edt) {
            edt.insertHtml('{username}');
        }
    });
    CKEDITOR.instances[ins].ui.addButton('addUserNBtn', {
        label: 'Usuario ID',
        command: 'addUsername',
        toolbar: 'others',
        icon: '/images/icons/usericon.ico'
    });
    CKEDITOR.instances[ins].ui.addButton('addPwdBtn', {
        label: 'Contraseña',
        command: 'addPassword',
        toolbar: 'others',
        icon: '/images/icons/pwdicon.png'
    });
}

$(document).ready(function () {
	sendPostAction(EMAIL_CONTROLLER_URL + 'list', null, loadTableEmail);
    $('#btn-add-email').on('click', function () {
        cleanForm();
        showEditCard();
    });
    $('#btn-cancel-email').on('click', function () {
        showDivMessage('Acción Cancelada. No se guardaron los cambios', 'alert-danger', 3000);
        showTableCard();
    });
    $('#new-email-form').on('submit', function () {
       var email = new Correo();
       email.id = ID_CURRENT_EMAIL;
       email.nameUser = $('#user-name').val();
       email.pwd = $('#password').val();
       email.endServer = $('#end-server').val();
       email.entrancePort = $('#entrance-port').val();
       if($('#combo-ssl').val() === 'Si'){
    	   email.certificate = true;
       } else {
    	   email.certificate = false;
       }
       sendPostAction(EMAIL_CONTROLLER_URL + 'save', email, saveEmail);
       return false;
    });
    // Init editors
   // $(".editor-welcome").jqte();
   // $(".editor-reset").jqte();
   // $(".editor-recover").jqte();
});
$.validate({
    form: '#new-email-form'
});

$('div#id_welcome_image').dropzone({
    url: '/adm/correo/fileInsTemplate',
    maxFilesize: 0.5,
    acceptedFiles: 'image/*',
    addRemoveLinks: true
});

$(document).on('shown.bs.tab', 'a[data-toggle="tab"]', function (e) {
    var tab = $(e.target);
    NAME_EDITOR_EMAIL = tab.attr("href").replace('#', '');
});

// Funciones de la pantalla de Email
function loadTableEmail(data) {
    $('#tbody_email').empty();
    if(data.length > 0){
    	$("#btn-add-email").attr("disabled", true);
    }
    for (var i = 0; i < data.length; i++) {
        var tr = '<tr>' +
            '<td><i style="cursor: pointer" class="zmdi zmdi-edit zmdi-hc-2x" onclick="onEditAction('+ data[i].id +')"></i></td>' +
            '<td>'+ data[i].id +'</td>' +
            '<td>'+ data[i].nameUser +'</td>' +
            '<td>'+ data[i].pwd +'</td>' +
            '<td>'+ data[i].endServer +'</td>' +
            '<td>'+ data[i].entrancePort +'</td>' + 
            '<td><i style="cursor: pointer" class="zmdi zmdi-globe-alt zmdi-hc-2x" onclick="onModalAction('+ data[i].id +',\''+ data[i].nameUser +'\')"></i></td>';
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

function onModalAction(id, nameUserTemplate){
	ID_CURRENT_EMAIL = id;
	$('#user-name-templete').text("- " + nameUserTemplate);
	// Llenar editor
    sendPostAction(EMAIL_CONTROLLER_URL + 'getPlantillas/' + ID_CURRENT_EMAIL, null, loadTemplates);
    $("#mi-modal-email").modal('show');
}

function loadTemplates(data) {
    for (var i = 0; i < data.length; i++) {
        if (data[i].nombre === 'welcome'){
            WELCOME_TEMPLATE = data[i];
        }else if (data[i].nombre === 'reset') {
            RESET_TEMPLATE = data[i];
        }else if (data[i].nombre === 'recover'){
            RECOVER_TEMPLATE = data[i];
        }
    }
    $('#asunto-welcome').val(WELCOME_TEMPLATE.asunto);
  //  $('#text-welcome').jqteVal(WELCOME_TEMPLATE.body);
    $('#asunto-reset').val(RESET_TEMPLATE.asunto);
  //  $('.editor-reset').jqteVal(RESET_TEMPLATE.body);
    $('#asunto-recover').val(RECOVER_TEMPLATE.asunto);
  //  $('.editor-recover').jqteVal(RECOVER_TEMPLATE.body);

    WELCOME_EDITOR.setData(WELCOME_TEMPLATE.body);
    RECOVER_EDITOR.setData(RECOVER_TEMPLATE.body);
    RESET_EDITOR.setData(RESET_TEMPLATE.body);
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
		var saveAction = false;
        if (WELCOME_EDITOR.getData().length > 0){
            WELCOME_TEMPLATE.idCorreo = ID_CURRENT_EMAIL;
            WELCOME_TEMPLATE.nombre = 'welcome';
            WELCOME_TEMPLATE.asunto = "" + $('#asunto-welcome').val();
            WELCOME_TEMPLATE.body = "" + WELCOME_EDITOR.getData();
            saveAction = true;
            sendPostAction(EMAIL_CONTROLLER_URL + 'saveEmailTemplate', WELCOME_TEMPLATE, saveEditor);
        }

		if (RESET_EDITOR.getData().length > 0){
            RESET_TEMPLATE.idCorreo = ID_CURRENT_EMAIL;
            RESET_TEMPLATE.nombre = 'reset';
            RESET_TEMPLATE.asunto = "" + $('#asunto-reset').val();
            RESET_TEMPLATE.body = "" + RESET_EDITOR.getData();
            saveAction = true;
            sendPostAction(EMAIL_CONTROLLER_URL + 'saveEmailTemplate', RESET_TEMPLATE, saveEditor);
        }

		if (RECOVER_EDITOR.getData().length > 0){
            RECOVER_TEMPLATE.idCorreo = ID_CURRENT_EMAIL;
            RECOVER_TEMPLATE.nombre = 'recover';
            RECOVER_TEMPLATE.asunto = "" + $('#asunto-recover').val();
            RECOVER_TEMPLATE.body = "" + RECOVER_EDITOR.getData();
            saveAction = true;
            sendPostAction(EMAIL_CONTROLLER_URL + 'saveEmailTemplate', RECOVER_TEMPLATE, saveEditor);
        }
		if (!saveAction){
            showDivMessage('Plantillas vacias, no se guardo informacion', 'alert-danger', 4000);
        }
	} else {
		//Acciones si el usuario no confirma
		initEditor();
		showDivMessage('Acción Cancelada en Plantilla. No se guardaron los cambios', 'alert-danger', 3000);
	}
});

function initEditor(){
	$('#asunto-welcome').val("");
	$('#asunto-reset').val("");
	$('#asunto-recover').val("");
//	$('.editor-welcome').jqteVal("");
//	$('.editor-reset').jqteVal("");
//	$('.editor-recover').jqteVal("");
}

function saveEditor(data) {
//	if(data.pSpStatus == 0){
    if (data) {
		initEditor();
		showDivMessage('Plantilla Guardada', 'alert-info', 3000);
	} else {
		initEditor();
		showDivMessage('Error al guardar plantilla', 'alert-danger', 3000);
	}
}