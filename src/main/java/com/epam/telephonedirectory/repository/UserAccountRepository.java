package com.epam.telephonedirectory.repository;

import com.epam.telephonedirectory.entities.User;
import com.epam.telephonedirectory.entities.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {
    
   UserAccount findTopByUser(User user);
}

