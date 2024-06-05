package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import models.User;

/**
 * User repository used to provide CRUD operations.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a User object with the provided phoneNumber.
     *
     * @param phoneNumber the phone number of the user to find
     * @return the found user, or null if not found
     */
    User findByPhoneNumber(String phoneNumber);
}
