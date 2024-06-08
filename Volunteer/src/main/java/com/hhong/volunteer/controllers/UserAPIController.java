package com.hhong.Volunteer.controllers;

import com.hhong.Volunteer.models.User;
import com.hhong.Volunteer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This is the controller that holds the REST endpoints that handle CRUD operations for Recipes.
 * Spring will automatically convert all ResponseEntity and List results to JSON.
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@RestController
public class UserAPIController extends APIController {
    @Autowired
    private UserService service;

    /**
     * REST API method to provide GET access to all users in the system.
     *
     * @return JSON representation of all Users
     */
    @GetMapping(BASE_PATH + "/users")
    public List<User> getUsers() {
        return service.findAll();
    }

    /**
     * REST API method to provide GET access to a specific User, as indicated by the path variable
     * provided (the phone number of the User desired).
     *
     * @param phoneNumber User phoneNumber
     * @return response to the request
     */
    @GetMapping(BASE_PATH + "/users/{phoneNumber}")
    public ResponseEntity getUser(@PathVariable("phoneNumber") final String phoneNumber) {
        final User user = service.findByPhoneNumber(phoneNumber.trim().toLowerCase());
        return null == user
                ? new ResponseEntity(
                errorResponse("No User found with name " + phoneNumber.trim().toLowerCase()),
                HttpStatus.NOT_FOUND)
                : new ResponseEntity(user, HttpStatus.OK);
    }

    /**
     * REST API method to provide POST access. Creates a new User by automatically
     * converting the JSON RequestBody provided to a User object.
     * Invalid JSON will fail.
     *
     * @param user The valid user to be saved.
     * @return ResponseEntity indicating success if the User could be saved, or
     *         an error if it could not be
     */
    @PostMapping(BASE_PATH + "/users")
    public ResponseEntity createUser(@RequestBody final User user) {
        if (null != service.findByPhoneNumber(user.getPhoneNumber().trim().toLowerCase())) {
            return new ResponseEntity(errorResponse("User with the phone number "
                    + user.getPhoneNumber().trim().toLowerCase() + " already exists"),
                    HttpStatus.CONFLICT);
        } else {
            // makes sure phone number is trimmed before entering into database
            user.setPhoneNumber(user.getPhoneNumber().trim().toLowerCase());
            service.save(user);
            return new ResponseEntity(successResponse(user.getPhoneNumber() + " successfully created"),
                    HttpStatus.OK);
        }
    }

    /**
     * Update access to a User.
     *
     * @param user the user to edit
     * @return the response to the request
     */
    @PutMapping(BASE_PATH + "/users/{name}")
    public ResponseEntity editUser(@RequestBody final User user) {
        final User dbUser = service.findByPhoneNumber(user.getPhoneNumber());
        dbUser.editUser(user);
        service.save(dbUser);
        return new ResponseEntity(dbUser, HttpStatus.OK);
    }

    /**
     * REST API method to allow deleting a User.
     *
     * @param phoneNumber The phone number of the User to delete
     * @return Success if the User could be deleted; an error if the User does not exist
     */
    @DeleteMapping(BASE_PATH + "/users/{phoneNumber}")
    public ResponseEntity deleteUser(@PathVariable final String phoneNumber) {
        final User user = service.findByPhoneNumber(phoneNumber.trim().toLowerCase());
        if (null == user) {
            return new ResponseEntity(
                    errorResponse("No User found for name " + phoneNumber.trim().toLowerCase()),
                    HttpStatus.NOT_FOUND);
        }
        service.delete(user);

        return new ResponseEntity(successResponse(user.getPhoneNumber() + " was deleted successfully"),
                HttpStatus.OK);
    }
}