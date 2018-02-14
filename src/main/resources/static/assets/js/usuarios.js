$(document).ready(function () {
    sendPostAction(USER_CONTROLLER_URL + 'list', null, loadTable);
    $('#btn-add-user').on('click', function () {
        sendPostAction(USER_CONTROLLER_URL + 'roles/list', null, loadRolesCombo);
        sendPostAction(USER_CONTROLLER_URL + 'list', null, loadEmpresaCombo);
        showEditCard();
    });
    $('#btn-cancel-user').on('click', function () {
        showDivMessage('Accion Cancelada. No se guardaron los cambios', 'alert-danger', 4000);
        showTableCard();
    });
    $('#user-correo-2').on('change', function () {
       if ($('#user-correo').val() !== $(this).val()) {
           alert('Correos No Coinciden');
           $(this).val('');
           $(this).focus();
       }
    });
    $('#new-user-form').on('submit', function () {
        alert('Antes del Submit');
    });
});
$.validate({
    form: '#new-user-form'
});

// Funciones de JavaSCript para la pantalla de Usuarios
function loadTable(data) {
    for (var i = 0; i < data.length; i++) {
        var tr = "<tr>";

        tr += "</tr>";
    }
}

function loadEmpresaCombo(data) {
    for (var i = 0; i < data.length; i++){
        var op = new Option(data[i].nombre, data[i].id);
        $('#combo-empresa').append(op);
    }
}

function loadRolesCombo(data) {
    for (var i = 0; i < data.length; i++){
        var op = new Option(data[i].nombre, data[i].id);
        $('#combo-role').append(op);
    }
}

function validateNewUserForm() {
    var bad_input = $('#new-user-form .is-invalid');
    if (bad_input.length > 0) {
        $('#btn-save-user').prop('disabled', true);
    }else {
        var valid = true;

        $('#btn-save-user').prop('disabled', false);
    }
}

function showEditCard() {
    $('#table-card').css('display', 'none');
    $('#btn-add-user').css('display', 'none');
    $('#edit-card').css('display', 'block');
}

function showTableCard() {
    $('#table-card').css('display', 'block');
    $('#edit-card').css('display', 'none');
    $('#btn-add-user').css('display', 'block');
}