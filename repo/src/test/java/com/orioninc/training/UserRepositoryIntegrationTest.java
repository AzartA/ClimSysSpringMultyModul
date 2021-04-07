package com.orioninc.training;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import com.orioninc.training.model.api.entities.User;
import com.orioninc.training.model.dos.RoleDO;
import com.orioninc.training.model.dos.UserDO;
import com.orioninc.training.model.filters.FilterImpl;
import com.orioninc.training.model.filters.Operator;
import com.orioninc.training.repo.api.GenericSpecification;
import com.orioninc.training.repo.api.RoleRepo;
import com.orioninc.training.repo.api.UserRepo;
import com.orioninc.training.repo.impl.GenericSpecificationImpl;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.PropertySource;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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

    @BeforeAll
    void init4Users() {
        LOG.debug("Create 4 users for tests");
        user1 = new UserDO("John", "john", "john");
        user2 = new UserDO("Ray", "ray", "ray");
        user3 = new UserDO("Johnny", "johnny", "johnny");
        user4 = new UserDO("Ray", "ray2", "ray2");
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

    @AfterAll
    void deletingTestUsers() {
        LOG.debug("Delete 4 test users");
        userRepo.deleteInBatch(Arrays.asList(user1, user2, user3, user4));
    }

    @Test
    public void givenACorrectSetup_thenAnEntityManagerWillBeAvailable() {
        Assertions.assertNotNull(entityManager);
    }

    @Test
    public void deleteUserDeleteIt() {
        long count = userRepo.count();
        userRepo.delete(user1);
        Assertions.assertEquals(count - 1, userRepo.count(), "Demo users are not deleted");
    }

    @Test
    public void whenFindByName_thenReturnUser() {
        String name = "Ray";
        List<String> found = userRepo.getByName(name).stream().map(User::getName).collect(Collectors.toList());
        Assertions.assertAll(
                "Don't found users!",
                () -> Assertions.assertEquals(2, found.size()),
                () -> Assertions.assertEquals(name, found.get(0)),
                () -> Assertions.assertEquals(name, found.get(1)));
    }

    @Test
    public void getSetOfRoleDOsReturnRoles() {
        Set<RoleDO> found = roleRepo.getSetOfRoleDOs("Operator", "User");
        List<String> foundRoles = found.stream().map(RoleDO::getName).collect(Collectors.toList());
        Assertions.assertAll(
                "Wrong list of roles!",
                () -> Assertions.assertEquals(2, found.size()),
                () -> Assertions.assertTrue(foundRoles.contains("Operator")),
                () -> Assertions.assertTrue(foundRoles.contains("User")));
    }

    @Test
    public void getAllUsersWithNameFilterReturnIt() {
        String name = "John";
        GenericSpecification<User> spec =
                new GenericSpecificationImpl<>(new FilterImpl("name", Operator.getOperator("="), name));
        List<User> results = userRepo.findAll(spec);
        List<User> found = userRepo.getByName(name);
        Assertions.assertAll(
                "Must be one John!",
                () -> Assertions.assertIterableEquals(results, found),
                () -> Assertions.assertEquals(results.size(), 1));
    }

    @Test
    void getAllUsersWithNameLikeReturnTwoUsers() {
        String name = "John";
        GenericSpecification<User> spec =
                new GenericSpecificationImpl<>(new FilterImpl("name", Operator.LIKE, name));
        List<User> results = userRepo.findAll(spec);
        Assertions.assertAll(
                "Must be two *John* in filter",
                () -> Assertions.assertEquals(results.size(), 2),
                () -> Assertions.assertTrue(results.get(0).getName().contains(name)),
                () -> Assertions.assertTrue(results.get(1).getName().contains(name)));
    }

    @Test
    public void SerializeDeserializeUserByJavaStandard() throws IOException, ClassNotFoundException {
        RoleDO tester = new RoleDO("Tester");
        UserDO userA = new UserDO("Aaa", "aaa", "aaa");
        UserDO userB = new UserDO("Bbb", "bbb", "bbb");
        userA.setRoles(new HashSet<>(Collections.singletonList(tester)));
        userB.setRoles(new HashSet<>(Collections.singletonList(tester)));

        Object nullObject = new NullObject();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(userA);
        objectOutputStream.writeObject(userB);
        //objectOutputStream.writeObject(userA.getRoles().iterator().next());
        //objectOutputStream.writeObject(userB.getRoles().iterator().next());
        objectOutputStream.writeObject(nullObject);
        objectOutputStream.flush();
        objectOutputStream.close();

        ObjectInputStream objectInputStream = new ObjectInputStream(
                new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        Object o;
        List<UserDO> users = new ArrayList<>();
        List<List<RoleDO>> rolesList = new ArrayList<>();
        List<RoleDO> roles = new ArrayList<>();
        while (true) {
            o = objectInputStream.readObject();
            if( o instanceof UserDO){
                users.add((UserDO) o);
            }else if(o instanceof List) {
                rolesList.add((ArrayList<RoleDO>) o);
            }else if(o instanceof RoleDO) {
                roles.add((RoleDO) o);
            }else if(o  instanceof NullObject){
                objectInputStream.close();
                System.out.println("Users count = " + users.size());
                System.out.println("Roles count = " + roles.size());
                break;
            }
        }
        objectInputStream.close();

        Assertions.assertAll(
                "The Roles of two users must be the same object",
                () -> Assertions.assertEquals(2, users.size()),
                () -> Assertions.assertSame(users.get(0).getRoles().iterator().next(), users.get(1).getRoles().iterator().next())//,
                //() -> Assertions.assertSame(roles.get(0), roles.get(1)),
                //() -> Assertions.assertSame(users.get(0).getRoles().iterator().next(), roles.get(0))
        );



    }

    @SpringBootApplication
    @PropertySource("classpath:testapp.properties")
    static class TestConfiguration {
    }

    static class NullObject implements Serializable{

    }

}

