package com.hhong.Volunteer.models;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Profile {
    private String first;
    private String last;
    private String bio;

    public Profile() {
        //empty constructor
    }
    public Profile(String first, String last, String bio) {
        super();
        this.bio = bio;
        this.last = last;
        this.first = first;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return Objects.equals(first, profile.first) && Objects.equals(last, profile.last) && Objects.equals(bio, profile.bio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, last, bio);
    }
}
