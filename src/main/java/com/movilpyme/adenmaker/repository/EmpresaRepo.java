package com.movilpyme.adenmaker.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.movilpyme.adenmaker.domain.Empresa;

import java.util.List;

@Repository
public interface EmpresaRepo extends CrudRepository<Empresa, Long> {

    public List<Empresa> findAllByEnabledTrue();
}
