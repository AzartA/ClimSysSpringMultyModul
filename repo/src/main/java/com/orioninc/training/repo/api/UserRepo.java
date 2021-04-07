package com.orioninc.training.repo.api;

import com.orioninc.training.model.api.entities.User;
import com.orioninc.training.model.dos.UserDO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends EntityRepo<User,UserDO>{
    Optional<User> findByLogin(String login);
}
