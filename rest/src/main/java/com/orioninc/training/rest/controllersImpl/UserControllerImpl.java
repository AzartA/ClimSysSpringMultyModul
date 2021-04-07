package com.orioninc.training.rest.controllersImpl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orioninc.training.model.api.entities.Role;
import com.orioninc.training.model.api.entities.User;
import com.orioninc.training.repo.api.UserRepo;
import com.orioninc.training.rest.controllers.UserController;
import com.orioninc.training.rest.dtos.RoleDTO;
import com.orioninc.training.rest.dtos.UserDTO;
import com.orioninc.training.service.api.EntityService;
import com.orioninc.training.service.api.UserService;
import com.orioninc.training.view.api.UserView;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/users")
public class UserControllerImpl implements UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserControllerImpl.class);
    private final UserView userView;
    private final UserService userService;
    private final EntityService entityService;
    private final UserRepo userRepo;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ResponseEntity<List<UserDTO>> getAll() {
        //return ResponseEntity.ok(mapper.mapList(userView.getAll(), UserDTO.class));
        return ResponseEntity.ok(objectMapper.convertValue(userView.getAll(), new TypeReference<List<UserDTO>>() {}));
    }

    @Override
    public ResponseEntity<UserDTO> get(Long id) {
        return userView.get(id)
                //.map(udo -> modelMapper.map(udo, UserDTO.class))
                .map(udo -> objectMapper.convertValue(udo, UserDTO.class))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Set<RoleDTO>> getRoles(long id) {
        Set<Role> roles = userView.getRoles(id);
        return ResponseEntity.ok(objectMapper.convertValue(roles, new TypeReference<Set<RoleDTO>>() {}));
    }

    @Override
    public ResponseEntity<List<UserDTO>> findByName(String name) {
        List<User> byName = userView.findByName(name);
        LOG.debug(byName.stream().map(String::valueOf).collect(Collectors.joining("\n", "\nUsersByName:\n", "\n---")));
        return ResponseEntity.ok(objectMapper.convertValue(byName, new TypeReference<List<UserDTO>>() {}));
    }

    @Override
    public ResponseEntity<UserDTO> getByLogin(String login) {
        return userRepo.findByLogin(login)
                .map(udo ->objectMapper.convertValue(udo, UserDTO.class))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
