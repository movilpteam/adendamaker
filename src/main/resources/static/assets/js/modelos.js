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
    this.preguntaSecreta = 0;
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
    this.bodyLogo = "";
    this.direccion = "";
    this.telefono= "";
    this.responsable = "";
    this.correoContacto = "";
    this.enabled = true;
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

function CorreoPlantilla(){
	this.id = 0;
	this.nombre = "";
	this.asunto = "";
	this.body = "";
	this.html = "";
	this.idCorreo = 0;
    this.correoByIdCorreo = new Correo();
}

function Correo(){
	this.id = 0;
	this.nameUser = "";
	this.pwd = "";
	this.initServer = "";
	this.endServer = "";
	this.entrancePort = "";
	this.exitPort = "";
	this.issuerName = "";
	this.certificate = true;
}

function Parametros(){
	this.id = 0;
	this.name = "";
	this.parametro = "";
	this.valor = "";
}

function PasswordConfig(){
	this.id = 0;
	this.name = "";
	this.valor = "";
}

function PreguntasPwd(){
	this.id = 0;
	this.pregunta = "";
}