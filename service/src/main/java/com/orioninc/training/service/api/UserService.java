package com.orioninc.training.service.api;

import java.util.List;
import java.util.Optional;

import com.orioninc.training.model.api.entities.Role;
import com.orioninc.training.model.api.entities.User;

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
