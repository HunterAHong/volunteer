package com.hhong.Volunteer.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.hhong.Volunteer.models.User;
import com.hhong.Volunteer.repositories.UserRepository;

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
     * @param email email of the user to find
     *
     * @return found user, null if none
     */
    public User findByEmail(final String email) {
        return userRepository.findByEmail(email.trim().toLowerCase());
    }
}
