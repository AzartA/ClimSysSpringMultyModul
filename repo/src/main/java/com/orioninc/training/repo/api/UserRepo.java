package com.orioninc.training.repo.api;

import com.orioninc.training.model.api.entities.User;
import com.orioninc.training.model.dos.RoleDO;
import com.orioninc.training.model.dos.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepo extends EntityRepo<UserDO>{
    Optional<User> findByName(String name);
    User getByName(String name);
}
