package com.hhong.Volunteer.api;

import com.hhong.Volunteer.TestConfig;
import com.hhong.Volunteer.common.TestUtils;
import com.hhong.Volunteer.models.User;
import com.hhong.Volunteer.services.UserService;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import jakarta.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestConfig.class)
public class UserAPITest {
    @Autowired
    private UserService service;

    @Autowired
    private MockMvc mvc;

    /**
     * helper to make a user
     */
    private User createUser() {
        User user = new User();
        user.setPhoneNumber("5253932000");
        user.setFirst("Hunter");

        return user;
    }

    @Test
    @Transactional
    public void testGetUsers() throws Exception {
        service.save(createUser());

        String user = mvc.perform(get("/api/v1/users")).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Assertions.assertTrue(user.contains("Hunter"));
    }

    /**
     * Ensures that POST requesting a new user to the API does not throw an exception.
     *
     * @throws Exception if POST fails
     */
    @Test
    @Transactional
    public void ensureUser() throws Exception {
        service.deleteAll();

        final User user = createUser();
        Assertions.assertNotNull(user.getPhoneNumber());

        mvc.perform(post("/api/v1/users").contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.asJsonString(user))).andExpect(status().isOk());
    }

    /**
     * Tests adding (POST) a user
     */
    @Test
    @Transactional
    public void testAddUser() throws Exception {
        service.deleteAll();

        Assertions.assertEquals(0, service.findAll().size(),
                "There should be no Users");

        final User user = createUser();
        mvc.perform(post("/api/v1/users").contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.asJsonString(user)));

        Assertions.assertEquals(1, service.count());
        Assertions.assertEquals(service.findByPhoneNumber("5253932000").getFirst(), "Hunter");
    }

    /**
     * Tests getting a nonexistent User from the database.
     *
     * @throws Exception if GET fails
     */
    @Test
    @Transactional
    public void testGetInvalidUser() throws Exception {
        service.deleteAll();

        final User user = createUser();
        service.save(user);

        String newUser = mvc.perform(get("/api/v1/users/3333333333"))
                .andExpect(status().isNotFound()).andReturn().getResponse().getContentAsString();
    }

    /**
     * Tests deleting a User using DELETE.
     */
    @Test
    @Transactional
    public void testDeleteUser() {
        service.deleteAll(); // Clean slate
        final User user = createUser();
        service.save(user);

        try {
            mvc.perform(delete("/api/v1/users/5253932000")).andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail("Deleting a user returned bad status");
        }

        try {
            mvc.perform(delete("/api/v1/users/5253932000")).andExpect(status().isNotFound());
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail("Deleting a non-existent user returned unexpected status");
        }
    }

    /**
     * Tests editing a user in the db
     */
    @Test
    @Transactional
    public void testEditUser() {
        final User user = createUser();
        service.save(user);

        User user2 = createUser();
        user2.setPhoneNumber("5253932000");
        user2.setFirst("NotHunter");

        try {
            mvc.perform(put("/api/v1/users/5253932000").contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtils.asJsonString(user2))).andExpect(status().isOk());
            String newUser = mvc.perform(get("/api/v1/users/5253932000")).andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();
            Assertions.assertTrue(newUser.contains("NotHunter"));
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }
}
