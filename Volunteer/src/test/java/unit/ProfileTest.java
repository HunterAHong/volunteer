package unit;

import Volunteer.TestConfig;
import models.Profile;
import services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Tests Profile.java
 */
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@SpringBootTest(classes = TestConfig.class)
public class ProfileTest {
    private static Profile profile;

    @BeforeAll
    public static void setup() {
        profile = new Profile("First", "Last", "Bio");
    }

    @Test
    public void testGetters() {
        // expected, actual
        Assertions.assertEquals("First", profile.getFirst());
        Assertions.assertEquals("Last", profile.getLast());
        Assertions.assertEquals("Bio", profile.getBio());
    }

    @Test
    public void testSetters() {
        profile.setFirst("First2");
        profile.setLast("Last2");
        profile.setBio("Bio2");

        Assertions.assertEquals("First2", profile.getFirst());
        Assertions.assertEquals("Last2", profile.getLast());
        Assertions.assertEquals("Bio2", profile.getBio());
    }
}
