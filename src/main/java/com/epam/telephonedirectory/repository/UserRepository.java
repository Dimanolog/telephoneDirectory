package com.epam.telephonedirectory.repository;

import com.epam.telephonedirectory.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {


}
