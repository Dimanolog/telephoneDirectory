package com.epam.telephonedirectory.entities;

import javax.persistence.*;

@Entity
public class User extends AbstractEntity {

    @Column
    private String login;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String userPassword;

    @Enumerated(EnumType.ORDINAL)
    private UserRole userRole;

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

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
