package com.orioninc.training.rest.controllersImpl;

import com.orioninc.training.rest.controllers.UserController;
import com.orioninc.training.rest.dtos.RoleDTO;
import com.orioninc.training.rest.dtos.UserDTO;
import com.orioninc.training.rest.util.Mapper;

import com.orioninc.training.app.api.UserRepo;
import com.orioninc.training.service.api.UserService;
import com.orioninc.training.view.api.UserView;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserControllerImpl implements UserController {
    UserView userView;
    UserService userService;
    UserRepo userRepo;
    Mapper mapper;
    ModelMapper modelMapper;

    @Autowired
    public UserControllerImpl(UserView userView, UserService userService, UserRepo userRepo, Mapper mapper, ModelMapper modelMapper) {
        this.userView = userView;
        this.userService = userService;
        this.userRepo = userRepo;
        this.mapper = mapper;
        this.modelMapper = modelMapper;
    }

    public List<UserDTO> all() {
        return mapper.mapList(userView.getAll(),UserDTO.class);
    }

    @Override
    public UserDTO getUser(Long id) {
        return modelMapper.map(userView.getUser(id),UserDTO.class);
    }

    @Override
    public List<RoleDTO> getRoles() {
        return mapper.mapList(userView.getRoles(),RoleDTO.class);
    }
}
