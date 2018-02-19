var nameLogo;
Dropzone.prototype.submitRequest = function(xhr, formData, files) {
	nameLogo = files[0].name;
	return xhr.send(formData);
};
Dropzone.options.uploadWidget = {
  paramName: 'file',
  maxFilesize: 5, // MB
  maxFiles: 1,
  dictDefaultMessage: 'Arrastre una imagen aquí para cargar, o haga clic para seleccionar una',
  headers: {
    'x-csrf-token': document.querySelectorAll('meta[name=csrf-token]')[0].getAttributeNode('content').value,
  },
  acceptedFiles: 'image/*',
  init: function() {
    this.on('success', function( file, resp ){
      console.log( file );
      console.log( resp );
    });
    this.on('thumbnail', function(file) {
      if ( file.width < 1) {
        file.rejectDimensions();
      }
      else {
        file.acceptDimensions();
      }
    });
  },
  accept: function(file, done) {
    file.acceptDimensions = done;
    file.rejectDimensions = function() {
      done('La imagen debe tener al menos 1 x 1 px')
    };
  }
};
$(document).ready(function () {
    sendPostAction(EMPRESA_CONTROLLER_URL + 'list', null, loadEmpresaCombo);
	$('#new-logo-form').on('submit', function () {
       var empresa = new Empresa();
	   empresa.id = $('#combo-empresa-logo').val();
       empresa.logo = nameLogo;
	   sendPostAction(EMPRESA_CONTROLLER_URL + 'update', empresa, saveEmpresa);
	   return false;
    });
	$("select").change(function() {
		$("select option:selected").each(function() {
			// Ejecutar viewLogo
			var empresa = new Empresa();
			empresa.id = $('#combo-empresa-logo').val();
			if(empresa.id != ""){
				//sendPostActionParams(EMPRESA_CONTROLLER_URL + 'viewLogo', empresa, srcViewLogo);
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