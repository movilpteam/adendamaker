function Usuarios() {
    this.id = 0;
    this.username = "";
    this.nombre = "";
    this.apaterno = "";
    this.amaterno = "";
    this.correo = "";
    this.password = "";
    this.telefono = "";
    this.enabled = "";
    this.preguntaSecreta = "";
    this.respuestaSecreta = "";
    this.cambiarPwd = false;
    this.lasPwdChg = new Date();
    this.userRolesById = [];
    this.empresaByIdEmpresa = new Empresa();
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

function Roles() {
    this.id = 0;
    this.nombre = '';
}

function UserRoles() {
    this.id = 0;
    this.idUser = 0;
    this.idRole = 0;
    this.usuariosByIdUser = new Usuarios();
    this.rolesByIdRole = new Roles();
}