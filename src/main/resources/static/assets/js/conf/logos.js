Dropzone.prototype.defaultOptions.dictDefaultMessage = "Arrastre una imagen aquí para cargar, o haga clic para seleccionar una";
$(document).ready(function () {
	sendPostAction(EMPRESA_CONTROLLER_URL + 'list', null, loadEmpresaCombo);
	$('#new-logo-form').on('submit', function () {
       var empresa = new Empresa();
	   empresa.id = $('#combo-empresa-logo').val();
       empresa.logo = nameImageDrop;
	   sendPostAction(EMPRESA_CONTROLLER_URL + 'update', empresa, saveEmpresa);
	   return false;
    });
	$("select").change(function() {
		$("select option:selected").each(function() {
			// Ejecutar viewLogo
			var empresa = new Empresa();
			empresa.id = $('#combo-empresa-logo').val();
			if(empresa.id != ""){
				sendPostAction(EMPRESA_CONTROLLER_URL + 'viewLogo', empresa, srcViewLogo);
			}
		});
	}).change();
});
$.validate({
    form: '#new-logo-form'
});
function addImage(op){
	document.getElementById("imagen-actual").src="../../images/logos/" + op;
}
function loadEmpresaCombo(data) {
    for (var i = 0; i < data.length; i++){
        var op = new Option(data[i].nombre, data[i].id);
        $('#combo-empresa-logo').append(op);
    }
}
function saveEmpresa(data) {
    if (data === true){
    	addImage(nameImageDrop);
    	showDivMessage('Logo Guardado', 'alert-info', 3000);
    }else {
        showDivMessage('Error al guardar informacion', 'alert-danger', 3000);
    }
}
function srcViewLogo(data){
	if (data === null){
		showDivMessage('No existe logo para esa empresa.', 'alert-danger', 3000);
	} else{
		addImage(data.logo);
	}
}