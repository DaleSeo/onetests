package com.onestorecorp.onetests.repository;

import com.onestorecorp.onetests.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
@ActiveProfiles("development")
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void test() {
        User toSave = new User();
        toSave.setEmail("test@onestorecorp.com");
        toSave.setPassword("test");
        toSave.setName("TEST");
        toSave.setCreatedDate(new Date());
        repository.save(toSave);

        assertThat(toSave.getId()).isNotBlank();

        User found = repository.findOneByEmail("test@onestorecorp.com");
        System.out.println("#found: " + found);

        assertThat(found).isNotNull();
        repository.delete(found);
    }

}