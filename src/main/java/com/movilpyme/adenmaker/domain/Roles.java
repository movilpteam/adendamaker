package com.movilpyme.adenmaker.domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Roles {
    private long id;
    private String nombre;
    private Collection<UserRoles> userRolesById;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NOMBRE", nullable = true, length = 50)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Roles roles = (Roles) o;
        return id == roles.id &&
                Objects.equals(nombre, roles.nombre);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, nombre);
    }

    @OneToMany(mappedBy = "rolesByIdRole")
    public Collection<UserRoles> getUserRolesById() {
        return userRolesById;
    }

    public void setUserRolesById(Collection<UserRoles> userRolesById) {
        this.userRolesById = userRolesById;
    }
}
