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
            '<td>'+ data[i].entrancePort +'</td>';
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