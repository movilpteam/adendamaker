package com.movilpyme.adenmaker.repository;

import com.movilpyme.adenmaker.domain.Login;
import com.movilpyme.adenmaker.domain.Usuarios;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginRepo extends CrudRepository<Login, Long> {

    public List<Login> findAllByUsuario(Usuarios usuarios);

    public List<Login> findAllByUsuarioAndActivaTrue(Usuarios usuarios);
}
