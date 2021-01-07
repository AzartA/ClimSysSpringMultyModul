package com.orioninc.training.rest;

import com.orioninc.training.model.dos.RoleDO;
import com.orioninc.training.model.dos.UserDO;
import com.orioninc.training.model.repo.RoleRepo;
import com.orioninc.training.model.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {
    private static final Logger LOG = LoggerFactory.getLogger(RestApplication.class);
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;


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

    public Long count() {
        return userRepo.count();
    }

    private Set<RoleDO> getRole(String... role) {
        Predicate<RoleDO> filter = r -> Arrays.asList(role).contains(r.getName());
        Function<Predicate<RoleDO>, Set<RoleDO>> getRoles = f -> roleRepo.findAll()
                .stream().filter(f).collect(Collectors.toSet());
        return getRoles.apply(filter);
    }

    public void printUsers() {
        LOG.debug("start testJpaMethods()");
        userRepo.findAll().forEach(System.out::println);
        LOG.debug("end testJpaMethods()");
    }
}
