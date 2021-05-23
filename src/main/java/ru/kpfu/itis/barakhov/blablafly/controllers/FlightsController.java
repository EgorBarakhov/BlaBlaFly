package ru.kpfu.itis.barakhov.blablafly.controllers;

import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Controller
public class FlightsController {

    private final Logger LOG = LoggerFactory.getLogger(FlightsController.class);

    @GetMapping("/flights")
    public String listFlights() {
        return "flights/index";
    }

    @GetMapping("/flights/new")
    public String newFlight() {
        return "flights/new";
    }

    @PostMapping("/flights/new")
    public String createFlight() {
        String successMessage = "The flight has been created";
        return "redirect:/flights/{id}";
    }

    @GetMapping("/flights/{id}")
    public String showFlight(@PathVariable("id") Long id) {
        return "flights/show";
    }

    @GetMapping("/flights/{id}/edit")
    public String editFlight(@PathVariable("id") Long id) {
        return "flights/edit";
    }

    @PatchMapping("/flights/{id}/edit")
    public String updateFlight(@PathVariable("id") Long id) {
        String successMessage = "The flight has been updated";
        return "redirect:/flights/" + id.toString();
    }

    @DeleteMapping("/flights/{id}")
    public String deleteFlight(@PathVariable("id") Long id) {
        String successMessage = "The flight has been deleted";
        return "redirect:/flights";
    }

}
