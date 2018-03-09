var EMPRESA_SELECTED = new Empresa();
var CURRENT_USER = new Usuarios();
var EDIT = false;

$(document).ready(function () {
    sendPostAction(EMPRESA_CONTROLLER_URL + 'list/' + getLoggedUser().id, null, loadEmpresaCombo);
    sendPostAction(USER_CONTROLLER_URL + 'list', null, loadTable);
    sendPostAction(USER_CONTROLLER_URL + 'roles/list', null, loadRolesCombo);
    $('#btn-add-user').on('click', function () {
        showEditCard();
    });
    $('#btn-cancel-user').on('click', function () {
        showDivMessage('Accion Cancelada. No se guardaron los cambios', 'alert-danger', 4000);
        showTableCard();
    });
    $('#user-correo-2').on('change', function () {
       if ($('#user-correo').val() !== $(this).val()) {
           errorAlert('Error Validacion', 'Correos no son Iguales');
           $(this).val('');
           $(this).focus();
       }
    });
    $('#user-correo').on('change', function () {
        var correo2 = $('#user-correo-2');
        if (correo2.val() !== $(this).val() && correo2.val().length > 0) {
            errorAlert('Error Validacion', 'Correos no son Iguales');
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
        CURRENT_USER.nombre = $('#user-name').val();
        CURRENT_USER.apaterno = $('#user-apaterno').val();
        CURRENT_USER.amaterno = $('#user-amaterno').val();
        CURRENT_USER.correo = $('#user-correo').val();
        CURRENT_USER.telefono = $('#user-telefono').val();
        CURRENT_USER.empresaByIdEmpresa = EMPRESA_SELECTED;
        CURRENT_USER.userRolesById = [roleSelected];
        CURRENT_USER.cambiarPwd = true;
        sendPostAction(USER_CONTROLLER_URL + 'save', CURRENT_USER, userSaved);
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
    CURRENT_USER = new Usuarios();
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
        var tr = "<tr id='user_"+ data[i].id +"'>" +
            "<td>" +
            "<i class='zmdi zmdi-delete zmdi-hc-2x' title='Eliminar Usuario' style='cursor: pointer' onclick='deleteUserAction("+ data[i].id +")'></i>" +
            "<i class='zmdi zmdi-key zmdi-hc-2x' title='Generar Nueva Contraseña' style='cursor: pointer;margin-left: 5px' onclick='resetPasswordAction("+ data[i].id +")'></i>" +
            "<i class='zmdi zmdi-edit zmdi-hc-2x' title='Editar Usuario' style='cursor: pointer;margin-left: 5px' onclick='editUserAction("+ data[i].id +")'></i>" +
            "</td>" +
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

function editUserAction(iduser) {
    EDIT = true;
    sendPostAction(USER_CONTROLLER_URL + 'byId/' + iduser, null, editUserResponse);
    sendPostAction(USER_CONTROLLER_URL + 'roles/' + iduser, null, loadRolesByUser);
}

function editUserResponse(data) {
    $('#user-name').val(data.nombre);
    $('#user-apaterno').val(data.apaterno);
    $('#user-amaterno').val(data.amaterno);
    $('#user-correo').val(data.correo);
    $('#user-correo-2').val(data.correo);
    $('#user-telefono').val(data.telefono);
    CURRENT_USER = data;
    showEditCard();
}

function resetPasswordAction(iduser) {
    $.jAlert({
        'type': 'confirm',
        'title': 'Confirmación',
        'confirmQuestion': '¿ Seguro de generar una nueva contraseña del usuario con ID = ' + iduser + ' ?',
        'confirmBtnText': 'Generar Nueva Contraseña',
        'denyBtnText': 'Cancelar',
        'theme': 'red',
        'size': 'md',
        'showAnimation': 'fadeInUp',
        'hideAnimation': 'fadeOutDown',
        'onConfirm': function (e, btn) {
            sendPostAction(USER_CONTROLLER_URL + 'resetPwd/' + iduser, null, resetPasswordResponse);
        },
        'onDeny': function (e, btn) {
            errorAlert('Accion Cancelada');
        }
    });
}

function resetPasswordResponse(data) {
    if (data) {
        showDivMessage('Nueva Contraseña enviada al correo del usuario', 'alert-info', 3000);
    }else {
        showDivMessage('Error al generar nueva contraseña', 'alert-danger', 3000);
    }
}

function deleteUserAction(iduser) {
    $.jAlert({
        'type': 'confirm',
        'title': 'Confirmación',
        'confirmQuestion': '¿ Seguro de eliminar al usuario con ID = ' + iduser + ' ?',
        'confirmBtnText': 'Eliminar',
        'denyBtnText': 'Cancelar',
        'theme': 'red',
        'size': 'md',
        'showAnimation': 'fadeInUp',
        'hideAnimation': 'fadeOutDown',
        'onConfirm': function (e, btn) {
            sendPostAction(USER_CONTROLLER_URL + 'disabledUser/' + iduser, null, deleteUserResponse);
        },
        'onDeny': function (e, btn) {
            errorAlert('Accion Cancelada');
        }
    });
}

function deleteUserResponse(data) {
    if (data > 0){
        showDivMessage('Usuario Eliminado', 'alert-info', 3000);
        $('#tbody_users tr#user_' + data).remove();
    }else {
        showDivMessage('Error al eliminar usuario', 'alert-danger', 3000);
    }
}

function loadRolesByUser(data) {
    if (EDIT) {
        $('#combo-role').val(data[0].rolesByIdRole.id).trigger('change');
    }else {
        var rolestr = '';
        var iduser = 0;
        for (var i = 0; i < data.length; i++){
            rolestr += data[i].rolesByIdRole.nombre;
            iduser = data[i].idUser;
        }
        $('#role' + iduser).html(rolestr);
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
    EDIT = false;
}