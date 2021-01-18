package com.orioninc.training.service.impl;

import com.orioninc.training.model.dos.RoleDO;
import com.orioninc.training.model.dos.UserDO;
import com.orioninc.training.model.entities.User;
import com.orioninc.training.repo.api.RoleRepo;
import com.orioninc.training.repo.api.UserRepo;
import com.orioninc.training.service.api.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
    private UserRepo userRepo;
    private RoleRepo roleRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    @Override
    public void initDB() {
        LOG.debug("init DB");
        UserDO user1 = new UserDO("bob", "Bobby", "bob");
        UserDO user2 = new UserDO("adm", "Nick", "adm");
        user1.setProperties(new HashSet<>(Arrays.asList("mad", "sad")));
        user2.setProperties(new HashSet<>(Arrays.asList("bad", "angry")));
        user2.addRoles(getRole("Admin"));
        user1.addRoles(getRole("Operator", "User"));
        userRepo.saveAll(Arrays.asList(user1, user2));
    }

    @Override
    public Long count() {
        return userRepo.count();
    }

    @Override
    public List<User> getAll(){
        return userRepo.findAll().stream().map(uDO -> (User) uDO).collect(Collectors.toList());
    }

    @Override
    public User getUser(Long id) {
        return userRepo.getOne(id);
    }

    private Set<RoleDO> getRole(String... role) {
        Predicate<RoleDO> filter = r -> Arrays.asList(role).contains(r.getName());
        Function<Predicate<RoleDO>, Set<RoleDO>> getRoles = f -> roleRepo.findAll()
                .stream().filter(f).collect(Collectors.toSet());
        return getRoles.apply(filter);
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
