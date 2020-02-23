package com.epam.telephonedirectory.services;

import com.epam.telephonedirectory.entities.User;
import com.epam.telephonedirectory.exceptions.BusinesException;
import com.epam.telephonedirectory.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService implements IUserService {
    private UserRepository userRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public UserService(UserRepository userRepository, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void saveUserFromFile(MultipartFile multipartFile) {
        try {
            InputStream inputStream = multipartFile.getInputStream();
            User[] user = objectMapper.readValue(inputStream, User[].class);
            userRepository.saveAll(Arrays.asList(user));
        } catch (IOException e) {
            throw new BusinesException("upload file error", e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
