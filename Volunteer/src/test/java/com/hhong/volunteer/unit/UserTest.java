package com.hhong.Volunteer.unit;

import com.hhong.Volunteer.TestConfig;
import com.hhong.Volunteer.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.hhong.Volunteer.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Tests User.java
 */
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@SpringBootTest(classes = TestConfig.class)
public class UserTest {
    /**
     * Local UserService
     */
    @Autowired
    private UserService userService;

    @BeforeEach
    public void setup() {
        userService.deleteAll();
    }

    /**
     * helper to make a user
     */
    private User createUser() {
        User user = new User();
        user.setEmail("5253932000");

        return user;
    }

    @Test
    @Transactional
    public void testAddUser() {
        User user = createUser();
        userService.save(user);

        final List<User> users = userService.findAll();
        Assertions.assertEquals(1, users.size(),
                "Creating one user should result in one user in the database");

        Assertions.assertEquals(user, users.get(0),
                "The retrieved user should match the created one");
    }

    @Test
    @Transactional
    public void testDeleteUser() {
        Assertions.assertEquals(0, userService.findAll().size(),
                "There should be no users");

        final User user = createUser();
        userService.save(user);

        Assertions.assertEquals(1, userService.count(), "There should be one user in the database");

        userService.delete(user);
        Assertions.assertEquals(0, userService.findAll().size(),
                "There should be no users");
    }
}
