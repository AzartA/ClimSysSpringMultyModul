package com.orioninc.training.rest.controllersImpl;

import com.orioninc.training.rest.controllers.UserController;
import com.orioninc.training.rest.dtos.EntityDTO;
import com.orioninc.training.rest.dtos.UserDTO;
import com.orioninc.training.rest.util.Mapper;
import com.orioninc.training.service.api.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserControllerImpl implements UserController {
    UserService userService;
    Mapper mapper;
    ModelMapper modelMapper;


    @Autowired
    public UserControllerImpl(UserService userService, Mapper mapper, ModelMapper modelMapper) {
        this.userService = userService;
        this.mapper = mapper;
        this.modelMapper = modelMapper;
    }

    public List<UserDTO> all() {
        return mapper.mapList(userService.getAll(),UserDTO.class);
    }

    @Override
    public UserDTO getUser(Long id) {
        return modelMapper.map(userService.getUser(id),UserDTO.class);
    }
}
