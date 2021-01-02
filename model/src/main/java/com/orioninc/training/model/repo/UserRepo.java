package com.orioninc.training.model.repo;

import com.orioninc.training.model.dos.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserDO, Long> {

}
