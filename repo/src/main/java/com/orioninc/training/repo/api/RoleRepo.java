package com.orioninc.training.repo.api;

import com.orioninc.training.model.api.entities.Role;
import com.orioninc.training.model.dos.RoleDO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepo extends EntityRepo<Role, RoleDO> {

    @Query("from RoleDO r where r.name in ?#{[0]}")
    Set<RoleDO> getSetOfRoleDOs(String... role);

    @Query("select distinct r from RoleDO r join r.users u where u.id = ?#{[0]} ")
    Set<Role> getByUserId(long userId);
}

