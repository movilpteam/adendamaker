package com.movilpyme.adenmaker.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
public class Usuarios {
    private long id;
    private String username;
    private String nombre;
    private String apaterno;
    private String amaterno;
    private String correo;
    @JsonIgnore
    private String password;
    private String telefono;
    private boolean enabled;
    private long preguntaSecreta;
    @JsonIgnore
    private String respuestaSecreta;
    private boolean cambiarPwd;
    private Date lastPwdChg;
    private long idEmpresa;
    private Collection<BitacoraAddenda> bitacoraAddendaById;
    private Collection<UserRoles> userRolesById;
    @JsonIgnore
    private Collection<Login> logins;
    private Empresa empresaByIdEmpresa;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="usuarios_seq_gen")
    @SequenceGenerator(name="usuarios_seq_gen", sequenceName="USUARIOS_SEQ")
    @Column(name = "ID", nullable = false, precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "USERNAME", nullable = false, length = 45)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "NOMBRE", nullable = true, length = 20)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "APATERNO", nullable = true, length = 20)
    public String getApaterno() {
        return apaterno;
    }

    public void setApaterno(String apaterno) {
        this.apaterno = apaterno;
    }

    @Basic
    @Column(name = "AMATERNO", nullable = true, length = 20)
    public String getAmaterno() {
        return amaterno;
    }

    public void setAmaterno(String amaterno) {
        this.amaterno = amaterno;
    }

    @Basic
    @Column(name = "CORREO", nullable = true, length = 150)
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Basic
    @Column(name = "PASSWORD", nullable = true, length = 200)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "TELEFONO", nullable = true, length = 20)
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Basic
    @Column(name = "ENABLED", nullable = true, length = 1)
    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Basic
    @Column(name = "PREGUNTA_SECRETA")
    public long getPreguntaSecreta() {
        return preguntaSecreta;
    }

    public void setPreguntaSecreta(long preguntaSecreta) {
        this.preguntaSecreta = preguntaSecreta;
    }

    @Basic
    @Column(name = "RESPUESTA_SECRETA", nullable = true, length = 100)
    public String getRespuestaSecreta() {
        return respuestaSecreta;
    }

    public void setRespuestaSecreta(String respuestaSecreta) {
        this.respuestaSecreta = respuestaSecreta;
    }

    @Basic
    @Type(type = "yes_no")
    @Column(name = "CAMBIAR_PWD")
    public boolean getCambiarPwd() {
        return cambiarPwd;
    }

    public void setCambiarPwd(boolean cambiarPwd) {
        this.cambiarPwd = cambiarPwd;
    }

    @Basic
    @Column(name = "LAST_PWD_CHG", nullable = true)
    public Date getLastPwdChg() {
        return lastPwdChg;
    }

    public void setLastPwdChg(Date lastPwdChg) {
        this.lastPwdChg = lastPwdChg;
    }

    @Basic
    @Column(name = "ID_EMPRESA", nullable = false, precision = 0, insertable = false, updatable = false)
    public long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuarios usuarios = (Usuarios) o;
        return id == usuarios.id &&
                idEmpresa == usuarios.idEmpresa &&
                Objects.equals(username, usuarios.username) &&
                Objects.equals(nombre, usuarios.nombre) &&
                Objects.equals(apaterno, usuarios.apaterno) &&
                Objects.equals(amaterno, usuarios.amaterno) &&
                Objects.equals(correo, usuarios.correo) &&
                Objects.equals(password, usuarios.password) &&
                Objects.equals(enabled, usuarios.enabled) &&
                Objects.equals(preguntaSecreta, usuarios.preguntaSecreta) &&
                Objects.equals(respuestaSecreta, usuarios.respuestaSecreta) &&
                Objects.equals(cambiarPwd, usuarios.cambiarPwd) &&
                Objects.equals(lastPwdChg, usuarios.lastPwdChg);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, username, nombre, apaterno, amaterno, correo, password, enabled, preguntaSecreta, respuestaSecreta, cambiarPwd, lastPwdChg, idEmpresa);
    }

    @OneToMany(mappedBy = "usuariosByIdUser")
    public Collection<BitacoraAddenda> getBitacoraAddendaById() {
        return bitacoraAddendaById;
    }

    public void setBitacoraAddendaById(Collection<BitacoraAddenda> bitacoraAddendaById) {
        this.bitacoraAddendaById = bitacoraAddendaById;
    }

    @OneToMany(mappedBy = "usuariosByIdUser")
    public Collection<UserRoles> getUserRolesById() {
        return userRolesById;
    }

    public void setUserRolesById(Collection<UserRoles> userRolesById) {
        this.userRolesById = userRolesById;
    }

    @OneToMany(mappedBy = "usuario")
    public Collection<Login> getLogins() {
        return logins;
    }

    public void setLogins(Collection<Login> logins) {
        this.logins = logins;
    }

    @ManyToOne
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID", nullable = false)
    public Empresa getEmpresaByIdEmpresa() {
        return empresaByIdEmpresa;
    }

    public void setEmpresaByIdEmpresa(Empresa empresaByIdEmpresa) {
        this.empresaByIdEmpresa = empresaByIdEmpresa;
    }
}
