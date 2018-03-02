package com.movilpyme.adenmaker.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.movilpyme.adenmaker.domain.PasswordConfig;

@Repository
public interface ContraseniaPlantillaRepo extends CrudRepository<PasswordConfig, Long> {
	
    public List<PasswordConfig> findAllByName(String name);
}