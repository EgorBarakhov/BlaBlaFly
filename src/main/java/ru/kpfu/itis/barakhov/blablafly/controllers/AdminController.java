package ru.kpfu.itis.barakhov.blablafly.controllers;

import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.barakhov.blablafly.services.UserService;

@Log4j2
@Controller
public class AdminController {

    private final Logger LOG = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String index(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        return "admin";
    }

    @PostMapping("/admin")
    public String delete(@RequestParam(required = true, defaultValue = "") String userId,
                         @RequestParam(required = true, defaultValue = "") String action) {
        if (action.equals("delete")){
            if (userService.deleteUser(Long.parseLong(userId))) {
                LOG.info("User with id {} had been deleted", userId);
            } else {
                LOG.warn("Couldn't delete user with id {}", userId);
            }
        }
        return "redirect:/admin";
    }

}
