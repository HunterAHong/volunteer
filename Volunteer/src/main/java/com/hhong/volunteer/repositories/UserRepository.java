package com.hhong.Volunteer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hhong.Volunteer.models.User;

/**
 * User repository used to provide CRUD operations.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a User object with the provided phoneNumber.
     *
     * @param email the email of the user to find
     * @return the found user, or null if not found
     */
    User findByEmail(String email);
}
