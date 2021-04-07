package com.orioninc.training.rest.controllers;

import com.orioninc.training.rest.dtos.RoleDTO;
import com.orioninc.training.rest.dtos.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RequestMapping("/users")
public interface UserController {

    @GetMapping
    ResponseEntity<List<UserDTO>> getAll();

    @GetMapping("/{id}")
    ResponseEntity<UserDTO> get(@PathVariable Long id);

    @GetMapping("/{id}/roles")
    ResponseEntity<Set<RoleDTO>> getRoles(@PathVariable long id);

    @GetMapping("/name/{name}")
    ResponseEntity<List<UserDTO>> findByName(@PathVariable String name);

    @GetMapping("/{login}")
    ResponseEntity<UserDTO> getByLogin(@PathVariable String login);

}
