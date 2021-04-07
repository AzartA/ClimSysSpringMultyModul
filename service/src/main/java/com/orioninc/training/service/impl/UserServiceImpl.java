package com.orioninc.training.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.orioninc.training.model.api.entities.Role;
import com.orioninc.training.model.api.entities.User;
import com.orioninc.training.model.dos.UserDO;
import com.orioninc.training.repo.api.RepositoryFacade;
import com.orioninc.training.repo.api.RoleRepo;
import com.orioninc.training.repo.api.UserRepo;
import com.orioninc.training.service.api.EntityService;
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
    private final EntityService entityService;

    @Override
    public void initTwoDemoUsers() {
        LOG.debug("init 2 demo users");
        if (userRepo.count() == 0) {
            UserDO user1 = new UserDO("Bobby", "bob", "bob");
            UserDO user2 = new UserDO("Nick", "adm", "adm");
            user1.setProperties(new HashSet<>(Arrays.asList("mad", "sad")));
            user2.setProperties(new HashSet<>(Arrays.asList("bad", "angry")));
            RoleRepo roleRepo = repositoryFacade.getByEntity(Role.class);
            user2.addRoles(roleRepo.getSetOfRoleDOs("Admin"));
            user1.addRoles(roleRepo.getSetOfRoleDOs("Admin", "Operator"));
            userRepo.saveAll(Arrays.asList(user1, user2));
        } else {
            System.out.println("There are users in the repository:");
        }
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
    public Optional<User> getById(Long id) {
        return entityService.getEntityById(id, User.class);
    }

    @Override
    public Optional<? extends User> getByLogin(String login) {
        return userRepo.findByLogin(login);
    }

    @Override
    public List<User> findByName(String name) {
        List<User> byName = userRepo.getByName(name);
        LOG.debug(byName.stream().map(String::valueOf).collect(Collectors.joining("\n", "UsersByName:\n", "---")));
        return byName;
    }

    @Override
    public Set<Role> getRoles(long userId) {
        RoleRepo roleRepo = repositoryFacade.getByEntity(Role.class);
        return roleRepo.getByUserId(userId);
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
}
