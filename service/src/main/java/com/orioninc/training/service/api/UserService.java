package com.orioninc.training.service.api;

import com.orioninc.training.model.entities.Role;
import com.orioninc.training.model.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAll();
    User getUser(Long id);
    Long count();
    void initDB();
    void printUsers();
    String showUsers();
    User getByName(String name);
    Optional<? extends User> findByName(String name);
    Optional<? extends User> findUser(Long id);

    List<Role> getRoles();
}
