package com.epam.telephonedirectory;

import com.epam.telephonedirectory.entities.User;
import com.epam.telephonedirectory.services.IUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    private IUserService userService;

    @LocalServerPort
    private int port;

    @Test
    public void saveUserFromFile() throws IOException {
        FileInputStream inputFile = new FileInputStream(this.getClass().getResource("userTelephones.json").getFile());
        MockMultipartFile jsonFile = new MockMultipartFile("userTelephones.json", "", "application/json", inputFile);

        userService.saveUserFromFile(jsonFile);

        Iterable<User> allUsers = userService.getAllUsers();

        Assert.assertTrue(allUsers.iterator().hasNext());
    }
}
