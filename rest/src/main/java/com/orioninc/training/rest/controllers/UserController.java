package com.orioninc.training.rest.controllers;

import com.orioninc.training.rest.dtos.RoleDTO;
import com.orioninc.training.rest.dtos.UserDTO;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{id}/find")
    ResponseEntity<UserDTO> findUser(@PathVariable Long id);

    @GetMapping("/roles")
    List<RoleDTO> getRoles();

    @GetMapping("/get/{name}")
    UserDTO getUserByName(@PathVariable String name);

    @GetMapping("/find/{name}")
    UserDTO findUserByName(@PathVariable String name);


}
