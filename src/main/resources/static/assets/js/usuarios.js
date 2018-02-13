$(document).ready(function () {
    sendPostAction(USER_CONTROLLER_URL + 'list', null, loadTable);
});

// Funciones de JavaSCript para la pantalla de Usuarios
function loadTable(data) {
    for (var i = 0; i < data.length; i++) {
        var tr = "<tr>";

        tr += "</tr>";
    }
}