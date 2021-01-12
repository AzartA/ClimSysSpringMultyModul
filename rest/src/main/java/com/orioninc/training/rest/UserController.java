package com.orioninc.training.rest;

import com.orioninc.training.model.dos.UserDO;
import com.orioninc.training.model.repo.UserRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private final UserRepo repository;

    public UserController(UserRepo repository) {
        this.repository = repository;
    }

    @GetMapping("/users")
    List<UserDO> all() {
        return repository.findAll();
    }

}
