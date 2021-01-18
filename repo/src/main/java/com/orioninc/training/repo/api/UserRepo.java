package com.orioninc.training.repo.api;

import com.orioninc.training.model.dos.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserDO, Long> {

}
