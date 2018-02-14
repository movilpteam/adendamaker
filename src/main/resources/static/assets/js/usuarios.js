$(document).ready(function () {
    sendPostAction(USER_CONTROLLER_URL + 'list', null, loadTable);
    $('#btn-add-user').on('click', function () {
       showEditCard();
    });
    $('#btn-cancel-user').on('click', function () {
        showDivMessage('Accion Cancelada. No se guardaron los cambios', 'alert-danger', 4000);
        showTableCard();
    });
    $.validate({
        form: '#new-user-form',
        lang: 'es',
        errorMessageClass: 'is-invalid'
    });
});

// Funciones de JavaSCript para la pantalla de Usuarios
function loadTable(data) {
    for (var i = 0; i < data.length; i++) {
        var tr = "<tr>";

        tr += "</tr>";
    }
}

function validateNewUserForm() {
    var status = false;

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