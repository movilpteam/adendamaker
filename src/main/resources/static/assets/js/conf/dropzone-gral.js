var nameImageDrop;
var currentFile = null;
Dropzone.prototype.submitRequest = function(xhr, formData, files) {
	nameImageDrop = files[0].name;
	return xhr.send(formData);
};
Dropzone.options.uploadWidget = {
  paramName: 'file',
  maxFilesize: 5, // MB
  maxFiles: 1,
  // dictDefaultMessage: 'Arrastre una imagen aquí para cargar, o haga clic para seleccionar una',
  headers: {
    'x-csrf-token': document.querySelectorAll('meta[name=csrf-token]')[0].getAttributeNode('content').value,
  },
  acceptedFiles: ".jpg, .jpeg, .png",
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
    this.on("addedfile", function(file) {
		if (currentFile) {
			this.removeFile(currentFile);
		}
		currentFile = file;
	});
  },
  accept: function(file, done) {
    file.acceptDimensions = done;
    file.rejectDimensions = function() {
      done('La imagen debe tener al menos 1 x 1 px')
    };
  }
};
// Email
var currentFileWelcome = null;
var currentFileReset = null;
var currentFileRecover = null;
Dropzone.options.uploadWidgetEmail = {
	paramName : 'file',
	maxFilesize : 5, // MB
	maxFiles : 1,
	// dictDefaultMessage: 'Arrastre una imagen aquí para cargar, o haga clic para seleccionar una',
	headers : {
		'x-csrf-token' : document.querySelectorAll('meta[name=csrf-token]')[0].getAttributeNode('content').value,
	},
	acceptedFiles : ".jpg, .jpeg, .png",
	init : function() {
		this.on('success', function(file, resp) {
			console.log(file);
			console.log(resp);
		});
		this.on('thumbnail', function(file) {
			if (file.width < 1) {
				file.rejectDimensions();
			} else {
				file.acceptDimensions();
			}
		});
		this.on("addedfile", function(file) {
			if(NAME_EDITOR_EMAIL == 'welcome'){
				if (currentFileWelcome) {
					this.removeFile(currentFileWelcome);
				}
				currentFileWelcome = file;
				var valEditor = "" + $('.editor-welcome').val();
				var valImg = valEditor + '<img src="../\../\images/\email/\{replace}>';
				valImg = valImg.replace('{replace}', '' + currentFileWelcome.name + '" width="200" height="150"');
				// Agregar Campo de texto
				$('.editor-welcome').jqteVal('' + valImg);
			}
			if(NAME_EDITOR_EMAIL == 'reset'){
				if (currentFileReset) {
					this.removeFile(currentFileReset);
				}
				currentFileReset = file;
				var valEditor = "" + $('.editor-reset').val();
				var valImg = valEditor + '<img src="../\../\images/\email/\{replace}>';
				valImg = valImg.replace('{replace}', '' + currentFileReset.name + '" width="200" height="150"');
				// Agregar Campo de texto
				$('.editor-reset').jqteVal('' + valImg);
			}
			if(NAME_EDITOR_EMAIL == 'recover'){
				if (currentFileRecover) {
					this.removeFile(currentFileRecover);
				}
				currentFileRecover = file;
				var valEditor = "" + $('.editor-recover').val();
				var valImg = valEditor + '<img src="../\../\images/\email/\{replace}>';
				valImg = valImg.replace('{replace}', '' + currentFileRecover.name + '" width="200" height="150"');
				// Agregar Campo de texto
				$('.editor-recover').jqteVal('' + valImg);
			}
		});
	},
	accept : function(file, done) {
		file.acceptDimensions = done;
		file.rejectDimensions = function() {
			done('La imagen debe tener al menos 1 x 1 px')
		};
	}
};