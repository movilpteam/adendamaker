$(document).ready(function () {
    sendPostAction(EMPRESA_CONTROLLER_URL + 'list', null, loadTableEmpresa);
    $('#btn-add-empresa').on('click', function () {
        showEditCard();
    });
    $('#btn-cancel-emp').on('click', function () {
        showDivMessage('Accion Cancelada. No se guardaron los cambios', 'alert-danger', 3000);
        showTableCard();
    });
    $('#new-empresa-form').on('submit', function () {
       var empresa = new Empresa();
       empresa.nombre = $('#emp-name').val();
       empresa.direccion = $('#emp-direccion').val();
       empresa.telefono = $('#emp-telefono').val();
       empresa.responsable = $('#emp-resp').val();
       empresa.correoContacto = $('#emp-correo').val();
       sendPostAction(EMPRESA_CONTROLLER_URL + 'save', empresa, saveEmpresa);
       return false;
    });
});
$.validate({
    form: '#new-empresa-form'
});

// Funciones de la pantalla de Empresa
function loadTableEmpresa(data) {
    for (var i = 0; i < data.length; i++) {
        var tr = '<tr>' +
            '<td></td>' +
            '<td>'+ data[i].id +'</td>' +
            '<td>'+ data[i].nombre +'</td>' +
            '<td>'+ data[i].responsable +'</td>' +
            '<td></td>';
        $('#tbody_empresa').append(tr);
    }
}

function saveEmpresa(data) {
    if (data === true){
        showDivMessage('Empresa Guardada', 'alert-info', 3000);
    }else {
        showDivMessage('Error al guardar informacion', 'alert-danger', 3000);
    }
    showTableCard();
}

function showEditCard() {
    $('#table-card').css('display', 'none');
    $('#btn-add-empresa').css('display', 'none');
    $('#edit-card').css('display', 'block');
}

function showTableCard() {
    $('#table-card').css('display', 'block');
    $('#edit-card').css('display', 'none');
    $('#btn-add-empresa').css('display', 'block');
}