package com.epam.telephonedirectory.controllers;

import com.epam.telephonedirectory.entities.User;
import com.epam.telephonedirectory.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static com.epam.telephonedirectory.views.UserPdfView.USERS_KEY;

@Controller
@RequestMapping("/userList")
public class UserListController {

    IUserService userService;

    @Autowired
    public UserListController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    ModelAndView userList(HttpServletRequest request,
                             HttpServletResponse response) throws Exception {

        List<User> allUsers = userService.getAllUsers();

        ModelAndView modelAndView = new ModelAndView("userList", "userList", allUsers);

        return modelAndView;
    }


    @PostMapping("/uploadUserFile")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        userService.saveUserFromFile(file);

        return "redirect:/userList/";

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
}
