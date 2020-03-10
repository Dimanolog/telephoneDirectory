package com.epam.telephonedirectory.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class UserAccount extends AbstractEntity{

    @Column
    private  BigDecimal money;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="telephone_number_id")
    private TelephoneNumber telephoneNumber;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public TelephoneNumber getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(TelephoneNumber telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
}
