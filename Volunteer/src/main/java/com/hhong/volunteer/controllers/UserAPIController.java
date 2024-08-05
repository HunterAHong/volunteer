package com.hhong.Volunteer.controllers;

import com.hhong.Volunteer.models.User;
import com.hhong.Volunteer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is the controller that holds the REST endpoints that handle CRUD operations for Recipes.
 * Spring will automatically convert all ResponseEntity and List results to JSON.
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@RestController
@RequestMapping("/api/v1")
public class UserAPIController extends APIController {
    @Autowired
    private UserService service;

    /**
     * REST API method to provide GET access to all users in the system.
     *
     * @return JSON representation of all Users
     */
    @GetMapping("/users")
    public List<User> getUsers() {
        return service.findAll();
    }

    /**
     * REST API method to provide GET access to a specific User, as indicated by the path variable
     * provided (the phone number of the User desired).
     *
     * @param email User email
     * @return response to the request
     */
    @GetMapping("/users/{email}")
    public ResponseEntity getUser(@PathVariable("email") final String email) {
        final User user = service.findByEmail(email.trim().toLowerCase());
        return null == user
                ? new ResponseEntity(
                errorResponse("No User found with name " + email.trim().toLowerCase()),
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
    @PostMapping("/users")
    public ResponseEntity createUser(@RequestBody final User user) {
        if (null != service.findByEmail(user.getEmail().trim().toLowerCase())) {
            return new ResponseEntity(errorResponse("User with the email "
                    + user.getEmail().trim().toLowerCase() + " already exists"),
                    HttpStatus.CONFLICT);
        } else {
            // makes sure phone number is trimmed before entering into database
            user.setEmail(user.getEmail().trim().toLowerCase());
            service.save(user);
            return new ResponseEntity(successResponse(user.getEmail() + " successfully created"),
                    HttpStatus.OK);
        }
    }

    /**
     * Update a User.
     *
     * @param user the user to edit
     * @return the response to the request
     */
    @PutMapping("/users/{name}")
    public ResponseEntity editUser(@RequestBody final User user) {
        final User dbUser = service.findByEmail(user.getEmail());
        dbUser.editUser(user);
        service.save(dbUser);
        return new ResponseEntity(dbUser, HttpStatus.OK);
    }

    /**
     * REST API method to allow deleting a User.
     *
     * @param email The phone number of the User to delete
     * @return Success if the User could be deleted; an error if the User does not exist
     */
    @DeleteMapping("/users/{email}")
    public ResponseEntity deleteUser(@PathVariable final String email) {
        final User user = service.findByEmail(email.trim().toLowerCase());
        if (null == user) {
            return new ResponseEntity(
                    errorResponse("No User found for phone number " + email.trim().toLowerCase()),
                    HttpStatus.NOT_FOUND);
        }
        service.delete(user);

        return new ResponseEntity(successResponse(user.getEmail() + " was deleted successfully"),
                HttpStatus.OK);
    }

    @GetMapping("/matches/{email}")
    public ResponseEntity getMatches(@PathVariable final String email) {
        final User user = service.findByEmail(email.trim().toLowerCase());
        if (null == user) {
            return new ResponseEntity(
                    errorResponse("No User found for phone number " + email.trim().toLowerCase()),
                    HttpStatus.NOT_FOUND);
        }

        System.out.println(user.getMatches());
        System.out.println("RESPONSE ENTITY:" + new ResponseEntity(user.getMatches(), HttpStatus.OK));

        return new ResponseEntity(user.getMatches(), HttpStatus.OK);
    }

    @PutMapping("/matches/{email}")
    public ResponseEntity addMatch(@PathVariable final String email, @RequestBody final String matchEmail) {
        final User user = service.findByEmail(email.trim().toLowerCase());
        if (null == user) {
            return new ResponseEntity(
                    errorResponse("No User found for phone number " + email.trim().toLowerCase()),
                    HttpStatus.NOT_FOUND);
        }

        final User match = service.findByEmail(matchEmail.trim().toLowerCase());
        if (null == match) {
            return new ResponseEntity(
                    errorResponse("No match found for email " + matchEmail.trim().toLowerCase()),
                    HttpStatus.NOT_FOUND);
        }
        user.addMatch(match);
        System.out.println(user.getMatches());
        service.save(user);

        return new ResponseEntity(user.getMatches(), HttpStatus.OK);
    }

    @DeleteMapping("/matches/{email}")
    public ResponseEntity deleteMatch(@PathVariable final String email, @RequestBody final String matchEmail) {
        final User user = service.findByEmail(email.trim().toLowerCase());
        if (null == user) {
            return new ResponseEntity(
                    errorResponse("No User found for phone number " + email.trim().toLowerCase()),
                    HttpStatus.NOT_FOUND);
        }

        boolean bool = user.deleteMatch(matchEmail.substring(1, matchEmail.length()-1));
        if (bool) {
            return new ResponseEntity(successResponse(matchEmail + " was deleted successfully"), HttpStatus.OK);
        }
        return new  ResponseEntity(errorResponse("Could not find match: " + matchEmail.substring(1, matchEmail.length()-1)), HttpStatus.NOT_FOUND);
    }
}