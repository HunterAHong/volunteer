package com.hhong.Volunteer.models;

import java.io.Serializable;

abstract public class DomainObject {
    /**
     * Returns the ID of this object. the ID is used for uniquely identifying this object for
     * persistent storage in the database.
     *
     * @return ID of the object
     */
    public abstract Serializable getId();
}
