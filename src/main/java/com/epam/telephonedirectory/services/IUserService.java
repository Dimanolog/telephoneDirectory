package com.epam.telephonedirectory.services;

import com.epam.telephonedirectory.entities.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface IUserService {
    void saveUserFromFile(MultipartFile multipartFile);

    void saveUserFromFile(InputStream inputStream);

    List<User> getAllUsers();
}
