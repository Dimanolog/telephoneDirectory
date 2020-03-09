package com.epam.telephonedirectory.entities;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<TelephoneNumber> telephoneNumbers;

    @Enumerated(EnumType.ORDINAL)
    private UserRole userRole;

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

    public List<TelephoneNumber> getTelephoneNumbers() {
        return telephoneNumbers;
    }

    public void setTelephoneNumbers(List<TelephoneNumber> telephoneNumbers) {
        this.telephoneNumbers = telephoneNumbers;
    }
}
