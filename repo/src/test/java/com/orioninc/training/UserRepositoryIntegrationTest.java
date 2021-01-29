package com.orioninc.training;

import com.orioninc.training.model.dos.UserDO;

import com.orioninc.training.repo.api.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.PropertySource;

//@ExtendWith(SpringExtension.class) //for junit5 instead of @RunWith(SpringRunner.class) in junit4
@DataJpaTest
public class UserRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepo userRepo;

    @Test
    public void givenACorrectSetup_thenAnEntityManagerWillBeAvailable() {
        Assertions.assertNotNull(entityManager);
    }

    @Test
    public void whenFindByName_thenReturnUser() {
        // when
        UserDO found = userRepo.getByName("Nick");

        // then
        Assertions.assertEquals(found.getName(),"Nick","Wrong user!");
    }

    @SpringBootApplication
    @PropertySource("classpath:testapp.properties")
    static class TestConfiguration {
    }

}

