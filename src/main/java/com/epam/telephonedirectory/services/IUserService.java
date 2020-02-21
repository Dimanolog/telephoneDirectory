package com.epam.telephonedirectory.services;

import com.epam.telephonedirectory.entities.User;
import org.springframework.web.multipart.MultipartFile;

public interface IUserService {
    void saveUserFromFile(MultipartFile multipartFile);

    Iterable<User> getAllUsers();
}
