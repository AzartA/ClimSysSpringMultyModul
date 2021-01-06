package com.orioninc.training.rest;

import com.orioninc.training.model.dos.RoleDO;
import com.orioninc.training.model.dos.UserDO;
import com.orioninc.training.model.repo.RoleRepo;
import com.orioninc.training.model.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
@Transactional
public class UserInitializer implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(RestApplication.class);
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;

    @Override
    public void run(String... args) {
        if (userRepo.count() == 0) {
            initDB();
        }
    }

    @Transactional
    private void initDB() {
        LOG.info("init DB");
        UserDO user1 = new UserDO("bob", "Bobby", "bob");
        UserDO user2 = new UserDO("adm", "Nick", "adm");
        user1.setProperties(new HashSet<>(Arrays.asList("mad", "sad")));
        user2.setProperties(new HashSet<>(Arrays.asList("bad", "angry")));
        user2.addRoles(getRole("Admin"));
        user1.addRoles(getRole("Operator", "User"));
        userRepo.saveAll(Arrays.asList(user1, user2));
    }

    private Set<RoleDO> getRole(String... role) {
        Predicate<RoleDO> filter = r -> Arrays.asList(role).contains(r.getName());
        Function<Predicate<RoleDO>, Set<RoleDO>> getRoles = f -> roleRepo.findAll()
                .stream().filter(f).collect(Collectors.toSet());
        return getRoles.apply(filter);
    }
}
