package com.epam.telephonedirectory.controllers;

import com.epam.telephonedirectory.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("uploadUserFile")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        userService.saveUserFromFile(file);

        return "redirect:/uploadUserFile";

    }

    @GetMapping
    public String uploading(Model model) {
        return "uploading";
    }
}
