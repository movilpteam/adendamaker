package com.movilpyme.adenmaker.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.movilpyme.adenmaker.domain.PreguntasPwd;

@Repository
public interface PreguntasRepo extends CrudRepository<PreguntasPwd, Long> {
}