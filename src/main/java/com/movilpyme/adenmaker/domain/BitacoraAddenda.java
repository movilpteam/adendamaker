package com.movilpyme.adenmaker.domain;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "BITACORA_ADDENDA", schema = "AW_ADMIN", catalog = "")
public class BitacoraAddenda {
    private long idBitacora;
    private Time fecha;
    private long idUser;
    private long idAddenda;
    private String tipoModificacion;
    private String descripcion;
    private String comentarios;
    private Usuarios usuariosByIdUser;
    private Addenda addendaByIdAddenda;

    @Id
    @Column(name = "ID_BITACORA", nullable = false, precision = 0)
    public long getIdBitacora() {
        return idBitacora;
    }

    public void setIdBitacora(long idBitacora) {
        this.idBitacora = idBitacora;
    }

    @Basic
    @Column(name = "FECHA", nullable = false)
    public Time getFecha() {
        return fecha;
    }

    public void setFecha(Time fecha) {
        this.fecha = fecha;
    }

    @Basic
    @Column(name = "ID_USER", nullable = false, precision = 0, insertable = false, updatable = false)
    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "ID_ADDENDA", nullable = false, precision = 0, insertable = false, updatable = false)
    public long getIdAddenda() {
        return idAddenda;
    }

    public void setIdAddenda(long idAddenda) {
        this.idAddenda = idAddenda;
    }

    @Basic
    @Column(name = "TIPO_MODIFICACION", nullable = false, length = 20)
    public String getTipoModificacion() {
        return tipoModificacion;
    }

    public void setTipoModificacion(String tipoModificacion) {
        this.tipoModificacion = tipoModificacion;
    }

    @Basic
    @Column(name = "DESCRIPCION", nullable = false, length = 200)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Basic
    @Column(name = "COMENTARIOS", nullable = true, length = 200)
    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BitacoraAddenda that = (BitacoraAddenda) o;
        return idBitacora == that.idBitacora &&
                idUser == that.idUser &&
                idAddenda == that.idAddenda &&
                Objects.equals(fecha, that.fecha) &&
                Objects.equals(tipoModificacion, that.tipoModificacion) &&
                Objects.equals(descripcion, that.descripcion) &&
                Objects.equals(comentarios, that.comentarios);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idBitacora, fecha, idUser, idAddenda, tipoModificacion, descripcion, comentarios);
    }

    @ManyToOne
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID", nullable = false)
    public Usuarios getUsuariosByIdUser() {
        return usuariosByIdUser;
    }

    public void setUsuariosByIdUser(Usuarios usuariosByIdUser) {
        this.usuariosByIdUser = usuariosByIdUser;
    }

    @ManyToOne
    @JoinColumn(name = "ID_ADDENDA", referencedColumnName = "ID_ADDENDA", nullable = false)
    public Addenda getAddendaByIdAddenda() {
        return addendaByIdAddenda;
    }

    public void setAddendaByIdAddenda(Addenda addendaByIdAddenda) {
        this.addendaByIdAddenda = addendaByIdAddenda;
    }
}
