var EMPRESA_SELECTED = new Empresa();

$(document).ready(function () {
    sendPostAction(EMPRESA_CONTROLLER_URL + 'list', null, loadEmpresaCombo);
    sendPostAction(USER_CONTROLLER_URL + 'list', null, loadTable);
    $('#btn-add-user').on('click', function () {
        sendPostAction(USER_CONTROLLER_URL + 'roles/list', null, loadRolesCombo);
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
        setEmpresaSelected();
        var dataRole = $('#combo-role').select2('data');
        var roleSelected = new UserRoles();
        var role = new Roles();
        role.id = dataRole[0].id;
        role.nombre = dataRole[0].text;
        roleSelected.idRole = role.id;
        roleSelected.rolesByIdRole = role;
        var user = new Usuarios();
        user.nombre = $('#user-name').val();
        user.apaterno = $('#user-apaterno').val();
        user.amaterno = $('#user-amaterno').val();
        user.correo = $('#user-correo').val();
        user.telefono = $('#user-telefono').val();
        user.empresaByIdEmpresa = EMPRESA_SELECTED;
        user.userRolesById = [roleSelected];
        user.cambiarPwd = true;
        sendPostAction(USER_CONTROLLER_URL + 'save', user, userSaved);
        return false;
    });
});
$.validate({
    form: '#new-user-form'
});

// Funciones de JavaSCript para la pantalla de Usuarios

function userSaved(data) {
    showDivMessage("Usuario Guardado Correctamente", "alert-info", 3000);
    sendPostAction(USER_CONTROLLER_URL + 'list', null, loadTable);
    showTableCard();
}

function setEmpresaSelected(){
    var dataEmp = $('#combo-empresa').select2('data');
    EMPRESA_SELECTED = new Empresa();
    EMPRESA_SELECTED.id = dataEmp[0].id;
    EMPRESA_SELECTED.nombre = dataEmp[0].text;
}

function loadTable(data) {
    var tbody_users = $('#tbody_users');
    tbody_users.empty();
    for (var i = 0; i < data.length; i++) {
        var tr = "<tr>" +
            "<td></td>" +
            "<td>"+ data[i].id +"</td>" +
            "<td>"+ data[i].username +"</td>" +
            "<td>"+ data[i].nombre +"</td>" +
            "<td>"+ data[i].apaterno + " " + data[i].amaterno +"</td>" +
            "<td>"+ data[i].correo +"</td>" +
            "<td id='role"+ data[i].id +"'></td>";
        tr += "</tr>";
        sendPostAction(USER_CONTROLLER_URL + 'roles/' + data[i].id, null, loadRolesByUser);
        tbody_users.append(tr);
    }
}

function loadRolesByUser(data) {
    for (var i = 0; i < data.length; i++){

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