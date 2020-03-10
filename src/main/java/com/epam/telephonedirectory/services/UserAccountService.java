package com.epam.telephonedirectory.services;

import com.epam.telephonedirectory.entities.User;
import com.epam.telephonedirectory.entities.UserAccount;
import com.epam.telephonedirectory.exceptions.BusinesException;
import com.epam.telephonedirectory.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class UserAccountService implements IUserAccountService {

    private UserAccountRepository userAccountRepository;

    @Autowired
    public UserAccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    @Transactional
    public void createUserAccount(User user, BigDecimal money) {

        boolean accountExist = userAccountRepository.existsByUser(user);

        if (accountExist) {
            throw new BusinesException("user already have account");
        }

        UserAccount userAccount = new UserAccount();
        userAccount.setMoney(money);
        userAccount.setUser(user);
        userAccountRepository.save(userAccount);
    }

    @Override
    @Transactional
    public void payMoney(User user, BigDecimal amount) {
        UserAccount userAccount = userAccountRepository.findTopByUser(user);

        BigDecimal money = userAccount.getMoney();

        money = money.subtract(amount);

        if (money.compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinesException("Not enough money in the account");
        }
        userAccount.setMoney(money);
        
        userAccountRepository.save(userAccount);
    }

}
