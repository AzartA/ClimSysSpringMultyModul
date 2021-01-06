package com.orioninc.training.model.repo;

import com.orioninc.training.model.dos.RoleDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<RoleDO, Long> {
    RoleDO findByName(String admin);
}
