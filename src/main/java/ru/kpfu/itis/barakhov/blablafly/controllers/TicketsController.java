package ru.kpfu.itis.barakhov.blablafly.controllers;

import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kpfu.itis.barakhov.blablafly.services.TicketsService;
import ru.kpfu.itis.barakhov.blablafly.services.UserService;

import java.security.Principal;

@Log4j2
@Controller
public class TicketsController {

    private final Logger LOG = LoggerFactory.getLogger(TicketsController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private TicketsService ticketsService;

    @GetMapping("/tickets")
    public String listTickets(Model model, Principal principal) {
        UserDetails currentUser = userService.loadUserByUsername(principal.getName());
        model.addAttribute("ticketsList", ticketsService.findOwnedBy(currentUser));
        return "tickets/index";
    }

    @PostMapping("/flights/{id}/ticket")
    public String buyTicket(@PathVariable String id, Model model, Principal principal) {
        UserDetails currentUser = userService.loadUserByUsername(principal.getName());
        ticketsService.bookTicket(Long.parseLong(id), currentUser);
        model.addAttribute("success", "The ticket has been booked");
        LOG.info("{} booked new ticket to the flight with id {}", currentUser.getUsername(), id);
        return "redirect:/tickets";
    }
}
