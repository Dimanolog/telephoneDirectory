package com.epam.telephonedirectory.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class UserAccount extends AbstractEntity{

    @Column
    private  BigDecimal money;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
