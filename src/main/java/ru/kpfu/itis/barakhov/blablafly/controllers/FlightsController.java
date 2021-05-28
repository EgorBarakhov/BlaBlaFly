package ru.kpfu.itis.barakhov.blablafly.controllers;

import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.barakhov.blablafly.dto.FlightDto;
import ru.kpfu.itis.barakhov.blablafly.dto.forms.FlightForm;
import ru.kpfu.itis.barakhov.blablafly.dto.forms.FlightSearchForm;
import ru.kpfu.itis.barakhov.blablafly.exceptions.FlightSavingException;
import ru.kpfu.itis.barakhov.blablafly.exceptions.FlightNotFoundException;
import ru.kpfu.itis.barakhov.blablafly.models.Flight;
import ru.kpfu.itis.barakhov.blablafly.services.AircraftsService;
import ru.kpfu.itis.barakhov.blablafly.services.CitiesService;
import ru.kpfu.itis.barakhov.blablafly.services.FlightsService;
import ru.kpfu.itis.barakhov.blablafly.services.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Arrays;

@Log4j2
@Controller
public class FlightsController {

    @Autowired
    private UserService userService;

    @Autowired
    private CitiesService citiesService;

    @Autowired
    private FlightsService flightsService;

    @Autowired
    private AircraftsService aircraftsService;

    private final Logger LOG = LoggerFactory.getLogger(FlightsController.class);

    @GetMapping("/flights")
    public String listFlights(@ModelAttribute("search") @Valid FlightSearchForm flightSearchForm, Model model) {
        model.addAttribute("flightSearchForm", new FlightSearchForm());
        model.addAttribute("flightsList", flightsService.searchFlights(flightSearchForm, model));
        model.addAttribute("citiesList", citiesService.findAll());
        return "flights/index";
    }

    @GetMapping("/flights/new")
    public String newFlight(Model model, Principal principal) {
        UserDetails currentUser = userService.loadUserByUsername(principal.getName());
        model.addAttribute("flightForm", new FlightForm());
        model.addAttribute("citiesList", citiesService.findAll());
        model.addAttribute("aircraftsList", aircraftsService.findOwnedBy(currentUser));
        return "flights/new";
    }

    @PostMapping("/flights/new")
    public String createFlight(@ModelAttribute("flightForm") @Valid FlightForm flightForm,
                               BindingResult bindingResult,
                               Model model,
                               Principal principal) {
        UserDetails currentUser = userService.loadUserByUsername(principal.getName());
        if (bindingResult.hasErrors()) {
            LOG.info("Flights form has received {} error(s): {}", bindingResult.getErrorCount(), Arrays.toString(bindingResult.getAllErrors().toArray()));
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("flightForm", new FlightForm());
            model.addAttribute("citiesList", citiesService.findAll());
            model.addAttribute("aircraftsList", aircraftsService.findOwnedBy(currentUser));
            return "/flights/new";
        } else {
            try {
                FlightDto flight = flightsService.createFlight(flightForm, currentUser);
                model.addAttribute("success", "The flight has been created");
                LOG.info("{} created new flight from form {}", currentUser.getUsername(), flightForm);
                return "redirect:/flights/" + flight.getId();
            } catch (Exception exception) {
                LOG.warn("User {} couldn't create flight {}", currentUser, flightForm);
                throw new FlightSavingException("Couldn't create new flight", exception);
            }
        }
    }

    @GetMapping("/flights/{id}")
    public String showFlight(@PathVariable("id") String id, Model model) {
        try {
            FlightDto flight = FlightDto.from(flightsService.findById(Long.parseLong(id)));
            model.addAttribute("apiKey", System.getenv("API_KEY"));
            model.addAttribute("flight", flight);
            return "flights/show";
        } catch (IllegalArgumentException exception) {
            throw new FlightNotFoundException("Flight with id " + id + " did not found", exception);
        }
    }

    @GetMapping("/flights/{id}/edit")
    public String editFlight(@PathVariable("id") String id, Model model, Principal principal) {
        UserDetails currentUser = userService.loadUserByUsername(principal.getName());
        try {
            Flight flightToEdit = flightsService.findById(Long.parseLong(id));
            if (flightToEdit.getAircraft().getOwner().getUsername().equals(currentUser.getUsername())) {
                model.addAttribute("success", "The flight has been updated");
                model.addAttribute("flightForm", flightsService.convertToForm(flightToEdit));
                model.addAttribute("flight", FlightDto.from(flightToEdit));
                model.addAttribute("citiesList", citiesService.findAll());
                model.addAttribute("aircraftsList", aircraftsService.findOwnedBy(currentUser));
                return "flights/edit";
            } else {
                model.addAttribute("error", "You don't have access to edit this flight!");
                LOG.info("{} tried to access the flight with id {} owned by {}", currentUser, id, flightToEdit.getAircraft().getOwner().getUsername());
                return "redirect:/flights/" + id;
            }
        } catch (IllegalArgumentException exception) {
            LOG.info("{} tried to access the empty flight with id {}", currentUser.getUsername(), id);
            throw new FlightNotFoundException("Flight with id " + id + " did not found", exception);
        }
    }

    @PostMapping("/flights/{id}/edit")
    public String updateFlight(@PathVariable("id") String id,
                               Model model,
                               @Valid FlightForm flightForm,
                               BindingResult bindingResult,
                               Principal principal) {
        UserDetails currentUser = userService.loadUserByUsername(principal.getName());
        try {
            Flight flightToEdit = flightsService.findById(Long.parseLong(id));
            if (bindingResult.hasErrors()) {
                LOG.info("Flights form has received {} error(s): {}", bindingResult.getErrorCount(), Arrays.toString(bindingResult.getAllErrors().toArray()));
                model.addAttribute("errors", bindingResult.getAllErrors());
                model.addAttribute("flightForm", flightsService.convertToForm(flightToEdit));
                model.addAttribute("citiesList", citiesService.findAll());
                model.addAttribute("aircraftsList", aircraftsService.findOwnedBy(currentUser));
                return "/flights/" + id + "/edit";
            } else {
                try {
                    if (flightToEdit.getAircraft().getOwner().getUsername().equals(currentUser.getUsername())) {
                        flightsService.updateFlight(flightToEdit, flightForm);
                        LOG.info("{} updated flight with id {}", currentUser, id);
                        model.addAttribute("success", "The flight has been updated");
                    } else {
                        LOG.info("{} tried to edit the flight with id {} owned by {}", currentUser, id, flightToEdit.getAircraft().getOwner().getUsername());
                        model.addAttribute("error", "You don't have access to edit this flight!");
                    }
                    return "redirect:/flights/" + id;
                } catch (Exception exception) {
                    LOG.warn("User {} couldn't create flight {}", currentUser, flightForm);
                    throw new FlightSavingException("Couldn't update flight", exception);
                }
            }
        } catch (IllegalArgumentException exception) {
            LOG.info("{} tried to access the empty flight with id {}", currentUser.getUsername(), id);
            throw new FlightNotFoundException("Flight with id " + id + " did not found", exception);
        }
    }

    @PostMapping("/flights/{id}")
    public String deleteFlight(@PathVariable("id") String id, Principal principal, Model model) {
        UserDetails currentUser = userService.loadUserByUsername(principal.getName());
        try {
            Flight flightToDelete = flightsService.findById(Long.parseLong(id));
            if (flightToDelete.getAircraft().getOwner().getUsername().equals(currentUser.getUsername())) {
                LOG.info("{} deleted flight with id {}", currentUser, id);
                flightsService.deleteFlight(flightToDelete);
            } else {
                LOG.info("{} tried to edit the flight with id {} owned by {}", currentUser, id, flightToDelete.getAircraft().getOwner().getUsername());
                model.addAttribute("error", "You don't have access to delete this flight!");
                return "redirect:/flights/" + id;
            }
        } catch (Exception exception) {
            LOG.warn("User {} couldn't delete flight with id {}", currentUser, id);
            throw new FlightSavingException("Couldn't delete flight", exception);
        }
        return "redirect:/flights";
    }
}
