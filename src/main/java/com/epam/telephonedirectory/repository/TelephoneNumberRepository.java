package com.epam.telephonedirectory.repository;

import com.epam.telephonedirectory.entities.TelephoneNumber;
import com.epam.telephonedirectory.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface TelephoneNumberRepository extends CrudRepository<TelephoneNumber, Long> {
    
    
    
}
