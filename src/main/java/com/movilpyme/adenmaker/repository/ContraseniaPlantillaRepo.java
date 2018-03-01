package com.movilpyme.adenmaker.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.movilpyme.adenmaker.domain.Parametros;

@Repository
public interface ContraseniaPlantillaRepo extends CrudRepository<Parametros, Long> {

    public List<Parametros> findAllByConfiguration(int configuration);
}