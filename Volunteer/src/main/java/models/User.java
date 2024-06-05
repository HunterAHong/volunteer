package models;

import java.io.Serializable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class User extends DomainObject {
    @Id
    @GeneratedValue
    private Long id;

    @Override
    public Serializable getId() {
        return null;
    }
}
