package com.orioninc.training.repo.api;

import com.orioninc.training.model.api.entities.Role;
import com.orioninc.training.model.dos.RoleDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepo extends JpaRepository<RoleDO, Long> {
    Role findByName(String name);
    Role getByName(String name);
    @Query("from RoleDO r where r.name in ?#{[0]}")
    Set<RoleDO> getSetOfRoleDOs(String... role);

}

