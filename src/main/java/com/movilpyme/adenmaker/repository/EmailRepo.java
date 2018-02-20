package com.movilpyme.adenmaker.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.movilpyme.adenmaker.domain.Correo;

@Repository
public interface EmailRepo extends CrudRepository<Correo, Long> {
}
