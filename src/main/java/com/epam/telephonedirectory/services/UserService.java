package com.epam.telephonedirectory.services;

import com.epam.telephonedirectory.entities.User;
import com.epam.telephonedirectory.exceptions.BusinesException;
import com.epam.telephonedirectory.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class UserService implements IUserService, UserDetailsService {
    private UserRepository userRepository;
    private ObjectMapper objectMapper;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void saveUserFromFile(MultipartFile multipartFile) {
        try(InputStream inputStream = multipartFile.getInputStream()) {
            List<User> userList = Arrays.asList(objectMapper.readValue(inputStream, User[].class));
            userRepository.saveAll(userList);
            encodeUserPassword(userList);
        } catch (IOException e) {
            throw new BusinesException("upload file error", e);
        }
    }

    @Override
    public void saveUserFromFile(InputStream inputStream) {

        try(InputStream is = inputStream) {
            List<User> userList = Arrays.asList(objectMapper.readValue(is, User[].class));
            encodeUserPassword(userList);
            userRepository.saveAll(userList);
        } catch (IOException e) {
            throw new BusinesException("upload file error", e);
        }
    }

    private void encodeUserPassword(Collection<? extends User> userList) {
        for (User user : userList) {
            user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getByLogin(username);

        return buildUserForAuthentication(user);
    }


    private UserDetails buildUserForAuthentication(User user) {

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getLogin())
                .password(user.getUserPassword())
                .authorities(user.getUserRole().name())
                .build();

    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
