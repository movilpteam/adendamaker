package com.movilpyme.adenmaker.repository;

import com.movilpyme.adenmaker.domain.UserRoles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRolesRepo extends CrudRepository<UserRoles, Long> {
    public void deleteByIdUser(Long iduser);
    public List<UserRoles> findByIdUser(Long iduser);
}
