Dropzone.options.uploadWidget = {
  paramName: 'file',
  maxFilesize: 500, // MB
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