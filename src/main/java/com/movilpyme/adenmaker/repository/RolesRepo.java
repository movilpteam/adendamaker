package com.movilpyme.adenmaker.repository;

import com.movilpyme.adenmaker.domain.Roles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepo extends CrudRepository<Roles, Long>{
}
