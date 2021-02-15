package com.orioninc.training.service.impl;

import java.util.*;

import com.orioninc.training.model.api.entities.Role;
import com.orioninc.training.model.api.entities.User;
import com.orioninc.training.model.dos.RoleDO;
import com.orioninc.training.model.dos.UserDO;
import com.orioninc.training.repo.api.RepositoryFacade;
import com.orioninc.training.repo.api.RoleRepo;
import com.orioninc.training.repo.api.UserRepo;
import com.orioninc.training.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Transactional
public class UserServiceImpl implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepo userRepo;
    private final RepositoryFacade repositoryFacade;

    @Override
    public void initDB() {
        LOG.debug("init DB");
        UserDO user1 = new UserDO("Bobby", "bob", "bob");
        UserDO user2 = new UserDO("Nick", "adm", "adm");
        user1.setProperties(new HashSet<>(Arrays.asList("mad", "sad")));
        user2.setProperties(new HashSet<>(Arrays.asList("bad", "angry")));
        RoleRepo roleRepo = repositoryFacade.getByEntity(RoleDO.class);
        user2.addRoles(roleRepo.getSetOfRoleDOs("Admin"));
        user1.addRoles(roleRepo.getSetOfRoleDOs("Operator", "User"));
        userRepo.saveAll(Arrays.asList(user1, user2));
    }

    @Override
    public Long count() {
        return userRepo.count();
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(userRepo.findAll());
    }

    @Override
    public User getUser(Long id) {
        return userRepo.getOne(id);
    }

    @Override
    public Optional<? extends User> findUser(Long id) {
        return userRepo.findById(id);
    }

    @Override
    public void printUsers() {
        LOG.debug("start testJpaMethods()");
        userRepo.findAll().forEach(System.out::println);
        LOG.debug("end testJpaMethods()");
    }

    @Override
    public String showUsers() {
        StringBuilder sb = new StringBuilder();
        userRepo.findAll().forEach(user -> sb.append(user).append("\n"));
        return sb.toString();
    }

    @Override
    public User getByName(String name) {
        User byName = userRepo.getByName(name);
        LOG.debug(String.valueOf(byName));
        return byName;
    }

    @Override
    public Optional<? extends User> findByName(String name) {
        return userRepo.findByName(name);
    }

    @Override
    public List<Role> getRoles() {
        return new ArrayList<>(repositoryFacade.get(RoleRepo.class).findAll());
    }
}
