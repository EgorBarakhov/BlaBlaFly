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
import ru.kpfu.itis.barakhov.blablafly.dto.forms.UserForm;
import ru.kpfu.itis.barakhov.blablafly.services.RegistrationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
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
        model.addAttribute("userForm", new UserForm());

        return "signUp";
    }

    @PostMapping("/signup")
    public String addUser(@ModelAttribute("userForm") @Valid UserForm userForm, BindingResult bindingResult,
                          Model model, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            LOG.info("SignUp form has received {} error(s): {}", bindingResult.getErrorCount(), Arrays.toString(bindingResult.getAllErrors().toArray()));
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("userForm", userForm);
            return "signUp";
        }
        registrationService.signUp(userForm);
        model.addAttribute("success", "Your account has been created!");
        LOG.info("Created new user with name {}", userForm.getUsername());
        try {
            httpServletRequest.login(userForm.getUsername(), userForm.getPassword());
            return "redirect:/flights";
        } catch (ServletException exception) {
            return "redirect:/login";
        }
    }
}
