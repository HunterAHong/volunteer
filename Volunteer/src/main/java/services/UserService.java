package services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import models.User;
import repositories.UserRepository;

@Component
@Transactional
public class UserService extends Service<User, Long>{
    /**
     * Local UserRepository.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Returns this instance.
     */
    @Override
    protected JpaRepository<User, Long> getRepository() {
        return userRepository;
    }

    /**
     * Find a user with the provided phone number.
     *
     * @param phoneNumber number of the user to find
     *
     * @return found user, null if none
     */
    public User findByPhoneNumber(final String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber.trim().toLowerCase());
    }
}
