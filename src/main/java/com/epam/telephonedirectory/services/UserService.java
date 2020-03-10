package com.epam.telephonedirectory.services;

import com.epam.telephonedirectory.entities.User;
import com.epam.telephonedirectory.entities.UserRole;
import com.epam.telephonedirectory.exceptions.BusinesException;
import com.epam.telephonedirectory.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.epam.telephonedirectory.entities.UserRole.BOOKING_MANAGER;
import static com.epam.telephonedirectory.entities.UserRole.REGISTERED_USER;

@Service
public class UserService implements IUserService, UserDetailsService {
    private UserRepository userRepository;
    private ObjectMapper objectMapper;
    private PasswordEncoder passwordEncoder;
    private IUserAccountService userAccountService;

    @Override
    @Transactional
    @Secured("BOOKING_MANAGER")
    public void saveUserFromFile(MultipartFile multipartFile) {
        try(InputStream inputStream = multipartFile.getInputStream()) {
            List<User> userList = Arrays.asList(objectMapper.readValue(inputStream, User[].class));
            saveUserList(userList);
        } catch (IOException e) {
            throw new BusinesException("upload file error", e);
        }
    }

    private void saveUserList(List<User> userList) {
        setDefaultRole(userList);
        userRepository.saveAll(userList);
        encodeUserPassword(userList);

        BigDecimal money = new BigDecimal(5);

        for (User user : userList) {
            userAccountService.createUserAccount(user, money);
        }
    }

    private void setDefaultRole(List<User> userList) {
        for (User user : userList) {
           if(user.getUserRole() == null){
               user.setUserRole(REGISTERED_USER);
           }
        }
    }

    @Override
    @Transactional
    public void saveUserFromFile(InputStream inputStream) {

        try(InputStream is = inputStream) {
            List<User> userList = Arrays.asList(objectMapper.readValue(is, User[].class));
            saveUserList(userList);
        } catch (IOException e) {
            throw new BusinesException("upload file error", e);
        }
    }

    private void encodeUserPassword(Collection<User> userList) {
        for (User user : userList) {
            user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        }
    }

    @Override
    @Transactional
    @PostFilter("hasAuthority('BOOKING_MANAGER') or filterObject.login == authentication.name")
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
    
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    
    @Autowired
    public void setUserAccountService(IUserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }
}
