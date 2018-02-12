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