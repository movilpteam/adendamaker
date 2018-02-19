package com.movilpyme.adenmaker.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Login {
    private Long id;
    private Long iduser;
    private String token;
    private Date fecha_inicio;
    private Date fecha_final;
    private Usuarios usuario;
    private boolean activa;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="login_seq_gen")
    @SequenceGenerator(name="login_seq_gen", sequenceName="LOGIN_SEQ")
    @Column(name = "ID", nullable = false, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ID_USER", nullable = false, precision = 0, insertable = false, updatable = false)
    public Long getIduser() {
        return iduser;
    }

    public void setIduser(Long iduser) {
        this.iduser = iduser;
    }

    @Basic
    @Column(name = "TOKEN", nullable = true, length = 200)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Basic
    @Column(name = "FECHA_INICIO", nullable = false)
    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    @Basic
    @Column(name = "FECHA_FINAL")
    public Date getFecha_final() {
        return fecha_final;
    }

    public void setFecha_final(Date fecha_final) {
        this.fecha_final = fecha_final;
    }

    @ManyToOne
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID", nullable = false)
    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    @Basic
    @Type(type = "yes_no")
    @Column(name = "ACTIVA")
    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
}
