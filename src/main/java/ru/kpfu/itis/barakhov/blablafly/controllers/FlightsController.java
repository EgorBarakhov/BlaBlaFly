package ru.kpfu.itis.barakhov.blablafly.controllers;

import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.barakhov.blablafly.dto.forms.FlightSearchForm;
import ru.kpfu.itis.barakhov.blablafly.services.CitiesService;
import ru.kpfu.itis.barakhov.blablafly.services.FlightsService;

@Log4j2
@Controller
public class FlightsController {

    @Autowired
    private CitiesService citiesService;

    @Autowired
    private FlightsService flightsService;

    private final Logger LOG = LoggerFactory.getLogger(FlightsController.class);

    @GetMapping("/flights")
    public String listFlights(@ModelAttribute("search") FlightSearchForm flight, Model model) {
        model.addAttribute("flightsList", flightsService.searchFlights(flight, model));
        model.addAttribute("citiesList", citiesService.findAll());
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
