package com.orioninc.training.service.api;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.orioninc.training.model.api.entities.Role;
import com.orioninc.training.model.api.entities.User;

public interface UserService {
    List<User> getAll();

    Optional<User> getById(Long id);

    Long count();

    void initTwoDemoUsers();

    void printUsers();

    String showUsers();

    List<User> findByName(String name);

    Optional<? extends User> getByLogin(String login);

    Set<Role> getRoles(long userId);
}
