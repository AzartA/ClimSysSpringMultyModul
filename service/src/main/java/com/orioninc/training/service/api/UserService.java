package com.orioninc.training.service.api;

import com.orioninc.training.model.entities.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getUser(Long id);
    Long count();
    void initDB();
    void printUsers();
    String showUsers();
}
