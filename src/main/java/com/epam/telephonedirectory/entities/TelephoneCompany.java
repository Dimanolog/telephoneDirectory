package com.epam.telephonedirectory.entities;

import javax.persistence.Entity;

@Entity
public class TelephoneCompany extends AbstractEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
