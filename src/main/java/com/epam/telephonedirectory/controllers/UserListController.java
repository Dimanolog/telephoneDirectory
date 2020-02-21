package com.epam.telephonedirectory.controllers;

import com.epam.telephonedirectory.entities.User;
import com.epam.telephonedirectory.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.telephonedirectory.views.UserPdfView.USERS_KEY;

@Controller
public class UserListController {

    IUserService userService;

    @Autowired
    public UserListController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/userPdf")
    ModelAndView downloadPdf(HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=userReport.pdf");

        Iterable<User> allUsers = userService.getAllUsers();

        ModelAndView modelAndView = new ModelAndView("userPdf", USERS_KEY , allUsers);

        return modelAndView;
    }

    @GetMapping(value = "/userList")
    ModelAndView userList(HttpServletRequest request,
                             HttpServletResponse response) throws Exception {


        Iterable<User> allUsers = userService.getAllUsers();

        ModelAndView modelAndView = new ModelAndView("userList", "userList", allUsers);

        return modelAndView;
    }


}
