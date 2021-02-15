package com.orioninc.training.rest.controllersImpl;

import com.orioninc.training.model.api.entities.Role;
import com.orioninc.training.repo.api.UserRepo;
import com.orioninc.training.model.api.entities.User;
import com.orioninc.training.rest.controllers.UserController;
import com.orioninc.training.rest.dtos.RoleDTO;
import com.orioninc.training.rest.dtos.UserDTO;
import com.orioninc.training.rest.util.Mapper;
import com.orioninc.training.service.api.EntityService;
import com.orioninc.training.view.api.UserView;

import com.orioninc.training.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor(onConstructor =  @__(@Autowired))
@RestController
@RequestMapping("/users")
public class UserControllerImpl implements UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserControllerImpl.class);
    private final UserView userView;
    private final UserService userService;
    private final EntityService entityService;
    private final UserRepo userRepo;
    private final Mapper mapper;
    private final ModelMapper modelMapper;

    public List<UserDTO> all() {
        return mapper.mapList(userView.getAll(),UserDTO.class);
    }

    @Override
    public UserDTO getUser(Long id) {
        User user = userView.getUser(id);
        return modelMapper.map(user,UserDTO.class);
    }

    @Override
    public ResponseEntity<UserDTO> findUser(Long id) {
        return userRepo.findById(id)
                .map(udo -> modelMapper.map(udo,UserDTO.class))
                .map(userDTO -> new ResponseEntity<>(userDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>( HttpStatus.NOT_FOUND));
    }

    @Override
    public List<RoleDTO> getRoles() {
        return mapper.mapList(entityService.getAll(Role.class),RoleDTO.class);
    }

    @Override
    public UserDTO getUserByName(String name) {
        User byName = userService.getByName(name);
        LOG.debug(String.valueOf(byName));
        return byName==null? new UserDTO(): modelMapper.map(byName,UserDTO.class);
    }

    @Override
    public UserDTO findUserByName(String name) {
        User byName = userService.findByName(name).orElse(null);
        LOG.debug(String.valueOf(byName));
        return byName==null? new UserDTO(): modelMapper.map(byName,UserDTO.class);
    }
}
