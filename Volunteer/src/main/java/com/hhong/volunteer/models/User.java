package com.hhong.Volunteer.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class User extends DomainObject {
    @Id
    @GeneratedValue
    private Long id;

    private String phoneNumber;
    private String first;
    private String last;
    private String bio;

    public User() {
        this.phoneNumber = "";
    }
    public User(String phoneNumber) {
        super();
        this.phoneNumber = phoneNumber;
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

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
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

    public void editUser(User editedUser) {
        this.phoneNumber = editedUser.getPhoneNumber();
        this.bio = editedUser.getBio();
        this.last = editedUser.getLast();
        this.first = editedUser.getFirst();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(first, user.first) && Objects.equals(last, user.last) && Objects.equals(bio, user.bio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phoneNumber, first, last, bio);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }
}
