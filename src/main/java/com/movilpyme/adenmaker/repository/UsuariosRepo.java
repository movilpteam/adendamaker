package com.movilpyme.adenmaker.repository;

import com.movilpyme.adenmaker.domain.Usuarios;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepo extends CrudRepository<Usuarios, Long>{

    public int countByUsername(String username);
    public Usuarios findByUsername(String username);
}
