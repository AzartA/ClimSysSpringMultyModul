package com.orioninc.training;

import java.util.*;
import java.util.stream.Collectors;

import com.orioninc.training.model.api.entities.Role;
import com.orioninc.training.model.api.entities.User;
import com.orioninc.training.model.dos.RoleDO;
import com.orioninc.training.model.dos.UserDO;
import com.orioninc.training.model.filters.FilterImpl;
import com.orioninc.training.model.filters.Operator;
import com.orioninc.training.repo.api.GenericSpecification;
import com.orioninc.training.repo.api.RoleRepo;
import com.orioninc.training.repo.api.UserRepo;
import com.orioninc.training.repo.impl.GenericSpecificationImpl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.PropertySource;

//@ExtendWith(SpringExtension.class) //for junit5 instead of @RunWith(SpringRunner.class) in junit4
@DataJpaTest
public class UserRepositoryIntegrationTest {
    private static final Logger LOG = LoggerFactory.getLogger(UserRepositoryIntegrationTest.class);
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;


    private UserDO user1;
    private UserDO user2;
    private UserDO user3;
    private UserDO user4;

    @BeforeEach
    void init4Users() {
        LOG.debug("Create 4 users for tests");
        user1 = new UserDO("Bobby","bob2",  "bob2");
        user2 = new UserDO("Ray","ray",  "ray");
        user3 = new UserDO("User3","user3",  "user3");
        user4 = new UserDO("Rayby","rayby",  "rayby");
        user1.setProperties(new HashSet<>(Collections.singletonList("sad a little")));
        user2.setProperties(new HashSet<>(Arrays.asList("sad", "angry")));
        user3.setProperties(new HashSet<>(Arrays.asList("good", "crazy")));
        user4.setProperties(new HashSet<>(Arrays.asList("very good", "very sad")));
        user2.addRoles(roleRepo.getSetOfRoleDOs("Admin"));
        user1.addRoles(roleRepo.getSetOfRoleDOs("User"));
        user3.addRoles(roleRepo.getSetOfRoleDOs("User"));
        user4.addRoles(roleRepo.getSetOfRoleDOs("Operator", "User"));
        userRepo.saveAll(Arrays.asList(user1, user2, user3, user4));
    }

    /*@AfterEach
    void deletingTestUsers(){
        LOG.debug("Delete 4 test users");
        userRepo.deleteInBatch(Arrays.asList(user1, user2, user3, user4));
    };*/


    @Test
    public void givenACorrectSetup_thenAnEntityManagerWillBeAvailable() {
        Assertions.assertNotNull(entityManager);
    }

    @Test
    public void whenFindByName_thenReturnUser() {
        // when
        User found = userRepo.getByName("Nick");
        // then
        Assertions.assertEquals(found.getName(), "Nick", "Wrong user!");
    }

    @Test
    public void towRoles() {
        Set<RoleDO> found = roleRepo.getSetOfRoleDOs("Operator","User");
        List<String> foundNames = found.stream().map(RoleDO::getName).collect(Collectors.toList());
        Assertions.assertAll("Wrong list of roles!",
                () -> Assertions.assertEquals(found.size(), 2),
                () -> Assertions.assertTrue(foundNames.contains("Operator")),
                () -> Assertions.assertTrue(foundNames.contains("User"))
                );
    }

    @Test
    public void givenLast_whenGettingListOfUsers_thenCorrect() {
        GenericSpecification<UserDO> spec =
                new GenericSpecificationImpl<>(new FilterImpl("name", Operator.getOperator("="), "Nick"));
        List<UserDO> results = userRepo.findAll(spec);
        User found = userRepo.getByName("Nick");
        Assertions.assertAll("Only one Nick in filter",
                () -> Assertions.assertEquals(results.get(0),found),
                () -> Assertions.assertEquals(results.size(), 1)
        );
    }

    @Test
    void givenFiltredUsers() {
        GenericSpecification<UserDO> spec =
                new GenericSpecificationImpl<>(new FilterImpl("name", Operator.LIKE, "Ray"));
        List<UserDO> results = userRepo.findAll(spec);
        Assertions.assertAll("Two *Ray* in filter",
                () -> Assertions.assertEquals(results.size(), 2),
                () -> Assertions.assertTrue(results.get(0).getName().contains("Ray")),
                () -> Assertions.assertTrue(results.get(1).getName().contains("Ray"))
        );
    }

    @SpringBootApplication
    @PropertySource("classpath:testapp.properties")
    static class TestConfiguration {
    }
}

