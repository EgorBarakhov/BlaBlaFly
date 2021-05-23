package ru.kpfu.itis.barakhov.blablafly.controllers;

import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kpfu.itis.barakhov.blablafly.dto.UserForm;
import ru.kpfu.itis.barakhov.blablafly.models.User;
import ru.kpfu.itis.barakhov.blablafly.services.RegistrationService;

import javax.validation.Valid;
import java.util.Arrays;

@Log4j2
@Controller
public class RegistrationController {
    private final Logger LOG = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/signup")
    public String signUp(Model model) {
        model.addAttribute("userForm", new User());

        return "signUp";
    }

    @PostMapping("/signup")
    public String addUser(@ModelAttribute("userForm") @Valid UserForm userForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            LOG.error("SignUp form has received {} error(s): {}", bindingResult.getErrorCount(), Arrays.toString(bindingResult.getAllErrors().toArray()));
            model.addAttribute("usernameError", "Something went wrong. Product owner is notified about this incident");
            return "signUp";
        }
        if (userForm.getUsername().length() < 5) {
            model.addAttribute("usernameError", "At least 5 characters");
            return "signUp";
        }
        if (userForm.getPassword().length() < 5) {
            model.addAttribute("passwordError", "At least 5 characters");
            return "signUp";
        }
        if (!userForm.getPassword().equals(userForm.getConfirmPassword())) {
            model.addAttribute("passwordError", "Passwords do not match");
            return "signUp";
        }
        if (!registrationService.signUp(userForm)){
            model.addAttribute("usernameError", "The username is already taken");
            return "signUp";
        } else {
            model.addAttribute("success", "Your account has been created!");
            LOG.info("Created new user with name {}", userForm.getUsername());
        }

        return "redirect:/login";
    }

}
