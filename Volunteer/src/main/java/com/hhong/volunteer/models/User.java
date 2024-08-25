package com.hhong.Volunteer.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class User extends DomainObject {
    @Id
    @GeneratedValue
    private Long id;

    /**
     * the user's email, first name, last name, bio, city, state, and volunteer status
     */
    private String email;
    private String first;
    private String last;
    private String bio;
    private String city;
    private String state;
    private boolean isVolunteer;

    /**
     * the user's matches
     */
    @OneToMany
    private List<User> matches;

    /**
     * Create a new user with an empty email and no matches
     */
    public User() {
        this.email = "";
        this.matches = new ArrayList<>();
    }

    /**
     * Create a new user with a specified email and no matches
     *
     * @param email the email of the user
     */
    public User(String email) {
        super();
        this.email = email;
    }

    /**
     * Set the ID of the Recipe (Used by Hibernate).
     *
     * @param id the ID
     */
    @SuppressWarnings("unused")
    private void setId(final Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return null;
    }

    /**
     * Sets the email of user
     *
     * @param email email to set to
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the bio of the user
     */
    public String getBio() {
        return bio;
    }

    /**
     * Sets the bio of user
     *
     * @param bio bio to set to
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     * Gets the last name of the user
     */
    public String getLast() {
        return last;
    }

    /**
     * Sets the last of user
     *
     * @param last last to set to
     */
    public void setLast(String last) {
        this.last = last;
    }

    /**
     * Gets the first name of the user
     */
    public String getFirst() {
        return first;
    }

    /**
     * Sets the first of user
     *
     * @param first first to set to
     */
    public void setFirst(String first) {
        this.first = first;
    }

    /**
     * Sets the city of user
     *
     * @param city city to set to
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Sets the state of user
     *
     * @param state state to set to
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets the city of the user
     */
    public String getCity() {
        return city;
    }

    /**
     * Gets the state of the user
     */
    public String getState() {
        return state;
    }

    /**
     * Gets the volunteer status of the user
     */
    public boolean isVolunteer() {
        return isVolunteer;
    }

    /**
     * Sets the volunteer status of the user
     *
     * @param volunteer true if volunteer, false if organizer
     */
    public void setVolunteer(boolean volunteer) {
        isVolunteer = volunteer;
    }

    /**
     * Gets list of the user's matches
     */
    public List<User> getMatches() {
        return this.matches;
    }

    /**
     * Adds a match to the user's matches
     *
     * @param match match to add
     */
    public void addMatch(User match) {
        this.matches.add(match);
    }

    /**
     * Deletes the specified match
     *
     * @param matchEmail the email of the match to delete
     * @return true if successful, false if not
     */
    public boolean deleteMatch(String matchEmail) {
        for (int i =0; i < this.matches.size(); i++) {
            if (this.matches.get(i).getEmail().equals(matchEmail)) {
                this.matches.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * edits the user by updating all of its fields
     *
     * @param editedUser the user obj containing the fields to update with
     */
    public void editUser(User editedUser) {
        this.email = editedUser.getEmail();
        this.bio = editedUser.getBio();
        this.last = editedUser.getLast();
        this.first = editedUser.getFirst();
        this.state = editedUser.getState();
        this.city = editedUser.getCity();
        this.isVolunteer = editedUser.isVolunteer();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email) && Objects.equals(first, user.first) && Objects.equals(last, user.last) && Objects.equals(bio, user.bio) && Objects.equals(city, user.city) && Objects.equals(state, user.state) && Objects.equals(matches, user.matches);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, first, last, bio, city, state, matches);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", bio='" + bio + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", matches=" + matches +
                '}';
    }
}
