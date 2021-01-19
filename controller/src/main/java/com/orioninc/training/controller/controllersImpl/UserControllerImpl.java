package com.orioninc.training.controller.controllersImpl;

import com.orioninc.training.controller.controllers.UserController;
import com.orioninc.training.controller.dtos.UserDTO;
import com.orioninc.training.controller.util.Mapper;

import com.orioninc.training.model.entities.User;
import com.orioninc.training.repo.api.UserRepo;
import com.orioninc.training.service.api.UserService;
import com.orioninc.training.view.api.UserView;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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
        return userRepo.findAll().stream().map(userDO -> modelMapper.map(userDO, UserDTO.class)).collect(Collectors.toList());
        //return mapper.mapList(userRepo.findAll(),UserDTO.class);
    }

    @Override
    public UserDTO getUser(Long id) {
        return modelMapper.map(userView.getUser(id),UserDTO.class);
    }
}
