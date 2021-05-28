package ru.kpfu.itis.barakhov.blablafly.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.barakhov.blablafly.dto.FlightDto;
import ru.kpfu.itis.barakhov.blablafly.dto.forms.FlightForm;
import ru.kpfu.itis.barakhov.blablafly.dto.forms.FlightSearchForm;
import ru.kpfu.itis.barakhov.blablafly.models.Flight;
import ru.kpfu.itis.barakhov.blablafly.services.FlightsService;
import ru.kpfu.itis.barakhov.blablafly.services.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@RestController
public class FlightsRestController {
    @Autowired
    private UserService userService;

    @Autowired
    private FlightsService flightsService;

    @GetMapping("/api/v1/flights")
    @ResponseBody
    public ResponseEntity<List<FlightDto>> listFlightsApi(@RequestParam("search") @Valid FlightSearchForm flightSearchForm) {
        List<FlightDto> flights = flightsService.searchFlights(flightSearchForm);
        return ResponseEntity.ok(flights);
    }

    @PutMapping("/api/v1/flights")
    @ResponseBody
    public ResponseEntity<Map<String, String>> createFlightApi(@RequestBody @Valid FlightForm flightForm,
                                                               Principal principal) {
        UserDetails currentUser = userService.loadUserByUsername(principal.getName());
        Map<String,String> stringHashMap = new HashMap<>();
        try {
            flightsService.createFlight(flightForm, currentUser);
            stringHashMap.put("msg", "flight was created");
            return new ResponseEntity<>(stringHashMap, HttpStatus.CREATED);
        } catch (Exception exception) {
            stringHashMap.put("msg", "flight cannot be created in order of " + exception.getMessage());
            return new ResponseEntity<>(stringHashMap, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/v1/flights/{id}")
    @ResponseBody
    public ResponseEntity<FlightDto> showFlightApi(@PathVariable("id") String id) {
        try {
            FlightDto fligth = FlightDto.from(flightsService.findById(Long.parseLong(id)));
            return ResponseEntity.ok(fligth);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/api/v1/flights/{id}")
    @ResponseBody
    public ResponseEntity<Map<String,String>> updateFlightApi(
            @PathVariable("id") String id,
            @RequestBody @Valid FlightForm flightForm,
            Principal principal) {
        UserDetails currentUser = userService.loadUserByUsername(principal.getName());
        Map<String,String> stringHashMap = new HashMap<>();
        try {
            Flight flightToEdit = flightsService.findById(Long.parseLong(id));
            if (flightToEdit.getAircraft().getOwner().getUsername().equals(currentUser.getUsername())) {
                flightsService.updateFlight(flightToEdit, flightForm);
                stringHashMap.put("msg", "flight was updated successfully");
                return new ResponseEntity<>(stringHashMap, HttpStatus.CREATED);
            } else {
                stringHashMap.put("msg", "flight does not belong to you");
                return new ResponseEntity<>(stringHashMap, HttpStatus.FORBIDDEN);
            }
        } catch (IllegalArgumentException e) {
            stringHashMap.put("msg", "flight does not exist");
            return ResponseEntity.notFound().build();
        } catch (Exception exception) {
            stringHashMap.put("msg", "flight cannot be updated in order of " + exception.getMessage());
            return new ResponseEntity<>(stringHashMap, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/v1/flights/{id}")
    @ResponseBody
    public ResponseEntity<Map<String,String>> deleteFlightApi(@PathVariable("id") String id,
                                                             Principal principal) {
        UserDetails currentUser = userService.loadUserByUsername(principal.getName());
        Map<String,String> stringHashMap = new HashMap<>();
        try {
            Flight flightToDelete = flightsService.findById(Long.parseLong(id));
            if (flightToDelete.getAircraft().getOwner().getUsername().equals(currentUser.getUsername())) {
                flightsService.deleteFlight(flightToDelete);
                stringHashMap.put("msg", "flight was deleted successfully");
                return new ResponseEntity<>(stringHashMap, HttpStatus.OK);
            } else {
                stringHashMap.put("msg", "flight does not belong to you");
                return new ResponseEntity<>(stringHashMap, HttpStatus.FORBIDDEN);
            }
        } catch (IllegalArgumentException e) {
            stringHashMap.put("msg", "flight does not exist");
            return ResponseEntity.notFound().build();
        } catch (Exception exception) {
            stringHashMap.put("msg", "flight cannot be updated in order of " + exception.getMessage());
            return new ResponseEntity<>(stringHashMap, HttpStatus.BAD_REQUEST);
        }
    }
}
