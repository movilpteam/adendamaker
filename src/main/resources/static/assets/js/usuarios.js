$(document).ready(function () {
    sendPostAction(USER_CONTROLLER_URL + 'list', null, loadTable);
});

// Funciones de JavaSCript para la pantalla de Usuarios
function loadTable(data) {
    var users = JSON.parse(data);
}