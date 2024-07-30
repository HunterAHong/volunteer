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

    private String email;
    private String first;
    private String last;
    private String bio;

    private List<String> matches;

    public User() {
        this.email = "";
        this.matches = new ArrayList<>();
    }
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public List<String> getMatches() {
        return this.matches;
    }

    public void addMatch(String match) {
        this.matches.add(match);
    }

    public boolean deleteMatch(String match) {
        if (matches.contains(match)) {
            this.matches.remove(match);
            return true;
        }
        return false;
    }

    public void editUser(User editedUser) {
        this.email = editedUser.getEmail();
        this.bio = editedUser.getBio();
        this.last = editedUser.getLast();
        this.first = editedUser.getFirst();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email) && Objects.equals(first, user.first) && Objects.equals(last, user.last) && Objects.equals(bio, user.bio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, first, last, bio);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }
}
