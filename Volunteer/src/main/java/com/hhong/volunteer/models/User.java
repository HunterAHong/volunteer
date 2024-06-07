package com.hhong.Volunteer.models;

import jakarta.persistence.*;

@Entity
public class User extends DomainObject {
    @Id
    @GeneratedValue
    private Long id;

    private String phoneNumber;

    @Embedded
    private Profile profile;

    public User() {
        this.profile = new Profile();
        this.phoneNumber = "";
    }
    public User(Profile profile, String phoneNumber) {
        super();
        this.profile = profile;
        this.phoneNumber = phoneNumber;
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

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Profile getProfile() {
        return profile;
    }
}
