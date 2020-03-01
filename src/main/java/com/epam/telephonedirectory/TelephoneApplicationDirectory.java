package com.epam.telephonedirectory;

import com.epam.telephonedirectory.services.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@EnableWebMvc
@SpringBootApplication
public class TelephoneApplicationDirectory {

    private static final Logger LOGGER = LoggerFactory.getLogger(TelephoneApplicationDirectory.class);

    private IUserService userService;

    @Autowired
    public TelephoneApplicationDirectory(IUserService userService) {
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TelephoneApplicationDirectory.class, args);
    }

    @PostConstruct
    private void addDefaultUsers() {
        try {
            FileInputStream inputFile = new FileInputStream(this.getClass().getResource("userTelephones.json").getFile());
            userService.saveUserFromFile(inputFile);

        } catch (FileNotFoundException e) {
            LOGGER.warn("Не найден json файл с пользователями");
        }
    }
}
