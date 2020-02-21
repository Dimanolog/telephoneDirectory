package com.epam.telephonedirectory.entities;

import javax.persistence.*;

@Entity
public class TelephoneNumber extends AbstractEntity {
    @Column
    private Long telephoneNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="telephone_company_id")
    private TelephoneCompany telephoneCompany;

    public TelephoneCompany getTelephoneCompany() {
        return telephoneCompany;
    }

    public void setTelephoneCompany(TelephoneCompany telephoneCompany) {
        this.telephoneCompany = telephoneCompany;
    }

    public Long getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(Long telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
}
