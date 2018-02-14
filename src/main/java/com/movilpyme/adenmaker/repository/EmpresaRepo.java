package com.movilpyme.adenmaker.repository;

import com.movilpyme.adenmaker.domain.Empresa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepo extends CrudRepository<Empresa, Long> {
}
