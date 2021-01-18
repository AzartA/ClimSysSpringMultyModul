package com.orioninc.training.repo.api;

import com.orioninc.training.model.dos.RoleDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<RoleDO, Long> {
    RoleDO findByName(String admin);
}
