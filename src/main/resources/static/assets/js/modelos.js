function Usuarios() {
    this.id = 0;
    this.username = "";
    this.nombre = "";
    this.apaterno = "";
    this.amaterno = "";
    this.correo = "";
    this.password = "";
    this.enabled = "";
    this.preguntaSecreta = "";
    this.respuestaSecreta = "";
    this.cambiarPwd = false;
    this.lasPwdChg = new Date();
    this.userRolesById = [];
    this.idEmpresa = 0;
}

function Empresa() {
    this.id = 0;
    this.nombre = "";
    this.logo = "";
    this.direccion = "";
    this.telefono= "";
    this.responsable = "";
    this.correoContacto = "";
}