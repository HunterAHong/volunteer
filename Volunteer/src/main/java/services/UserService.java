package services;

import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.UserRepository;

public class UserService extends Service<User, Long>{
    /**
     * Local IngredientRepository.
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
     * Find an user with the provided phone number.
     *
     * @param phoneNumber number of the user to find
     *
     * @return found user, null if none
     */
    public User findByPhoneNumber(final String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber.trim().toLowerCase());
    }
}
