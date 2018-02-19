package com.movilpyme.adenmaker.repository;

import com.movilpyme.adenmaker.domain.Login;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepo extends CrudRepository<Login, Long> {
}
