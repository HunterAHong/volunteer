package com.hhong.Volunteer.api;

import com.hhong.Volunteer.common.TestUtils;
import com.hhong.Volunteer.models.Profile;
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

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class UserAPITest {
    @Autowired
    private UserService service;

    @Autowired
    private MockMvc mvc;

    /**
     * helper to make a user
     */
    private User createUser() {
        Profile profile = new Profile("Hunter", "Hong", "Coder");
        User user = new User();
        user.setPhoneNumber("5253932000");
        user.setProfile(profile);

        return user;
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
}
