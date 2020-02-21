package com.epam.telephonedirectory.entities;

import javax.persistence.*;

@Entity
public class User extends AbstractEntity {
    @Column
    private String firstName;
    @Column
    private String lastName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="telephone_number_id")
    private TelephoneNumber telephoneNumber;

    public TelephoneNumber getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(TelephoneNumber telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
