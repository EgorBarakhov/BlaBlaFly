package ru.kpfu.itis.barakhov.blablafly.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kpfu.itis.barakhov.blablafly.dto.forms.AircraftForm;
import ru.kpfu.itis.barakhov.blablafly.dto.forms.FlightForm;
import ru.kpfu.itis.barakhov.blablafly.exceptions.AircraftSavingException;
import ru.kpfu.itis.barakhov.blablafly.exceptions.FlightSavingException;
import ru.kpfu.itis.barakhov.blablafly.services.AircraftsService;
import ru.kpfu.itis.barakhov.blablafly.services.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class AircraftsController {

    private final Logger LOG = LoggerFactory.getLogger(AircraftsController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AircraftsService aircraftsService;

    @GetMapping("/aircrafts")
    public String listAircrafts(Model model, Principal principal) {
        UserDetails currentUser = userService.loadUserByUsername(principal.getName());
        model.addAttribute("aircraftsList", aircraftsService.findOwnedBy(currentUser));
        return "aircrafts/index";
    }

    @GetMapping("/aircrafts/new")
    public String newAircraft(Model model) {
        model.addAttribute("aircraftForm", new AircraftForm());
        return "aircrafts/new";
    }

    @PostMapping("/aircrafts/new")
    public String createAircraft(@ModelAttribute("aircraftForm") @Valid AircraftForm aircraftForm,
                                 BindingResult bindingResult,
                                 Model model,
                                 Principal principal) {
        UserDetails currentUser = userService.loadUserByUsername(principal.getName());
        if (bindingResult.hasErrors()){
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("flightForm", new FlightForm());
            return "/aircrafts/new";
        } else {
            try {
                aircraftsService.createAircraft(aircraftForm, currentUser);
                model.addAttribute("success", "The aircraft has been created");
                return "redirect:/aircrafts";
            } catch (Exception exception) {
                LOG.warn("User {} couldn't create aircraft {}", currentUser, aircraftForm);
                throw new AircraftSavingException("Couldn't create new aircraft", exception);
            }
        }
    }
}
