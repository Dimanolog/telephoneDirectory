package com.epam.telephonedirectory.services;

import com.epam.telephonedirectory.entities.User;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

public interface IUserAccountService {

    void createUserAccount(User user, BigDecimal money);

    void  payMoney(User user, BigDecimal amount);
}
