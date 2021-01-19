package com.orioninc.training.controller.controllers;

import com.orioninc.training.controller.dtos.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@RequestMapping("/users")
public interface UserController {

    @GetMapping
    List<UserDTO> all();

    @GetMapping("/{id}")
    UserDTO getUser(@PathVariable Long id);
}
