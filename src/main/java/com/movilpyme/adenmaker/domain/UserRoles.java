package com.movilpyme.adenmaker.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "USER_ROLES", schema = "AW_ADMIN", catalog = "")
public class UserRoles {
    private long id;
    private long idUser;
    private long idRole;
    @JsonIgnore
    private Usuarios usuariosByIdUser;
    private Roles rolesByIdRole;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="useroles_seq_gen")
    @SequenceGenerator(name="useroles_seq_gen", sequenceName="USEROLES_SEQ")
    @Column(name = "ID", nullable = false, precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
    @Column(name = "ID_ROLE", nullable = false, precision = 0, insertable = false, updatable = false)
    public long getIdRole() {
        return idRole;
    }

    public void setIdRole(long idRole) {
        this.idRole = idRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoles userRoles = (UserRoles) o;
        return id == userRoles.id &&
                idUser == userRoles.idUser &&
                idRole == userRoles.idRole;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, idUser, idRole);
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
    @JoinColumn(name = "ID_ROLE", referencedColumnName = "ID", nullable = false)
    public Roles getRolesByIdRole() {
        return rolesByIdRole;
    }

    public void setRolesByIdRole(Roles rolesByIdRole) {
        this.rolesByIdRole = rolesByIdRole;
    }
}
