package com.ldatb.learnspringboot.controllers;

import com.ldatb.learnspringboot.dtos.ParkingSpotDTO;
import com.ldatb.learnspringboot.exceptions.CommonException;
import com.ldatb.learnspringboot.models.ParkingSpotModel;
import com.ldatb.learnspringboot.services.ParkingSpotService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking")
public class ParkingSpotController {

    final ParkingSpotService parkingSpotService;

    public ParkingSpotController(ParkingSpotService parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }

    @PostMapping
    public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDTO parkingSpotDTO) {
        // Copy DTO to model
        var parkingSpot = new ParkingSpotModel();
        BeanUtils.copyProperties(parkingSpotDTO, parkingSpot);

        // Make sure spot is empty
        if (parkingSpotService.isSpotTaken(parkingSpot.getParkingSpotNumber())) {
            var error = new CommonException();
            error.setError("This spot is already taken");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }

        // Make sure car is not already inside
        if (parkingSpotService.isCarAlreadyInside(parkingSpot.getCarLicensePlate())) {
            var error = new CommonException();
            error.setError("This car is already inside the parking lot");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }

        // Save spot
        parkingSpot.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.saveSpot(parkingSpot));
    }

    @GetMapping
    public ResponseEntity<List<ParkingSpotModel>> getAllParkingSpots() {
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAll());
    }

    @GetMapping("/{spot}")
    public ResponseEntity<Optional<ParkingSpotModel>> getParkingSpot(@PathVariable(value = "spot") Integer spot) {
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findBySpot(spot));
    }

    @DeleteMapping("/{spot}")
    public ResponseEntity<Object> deleteParkingSpot(@PathVariable(value = "spot") Integer spot) {
        // Check if spot is not empty
        if (!parkingSpotService.isSpotTaken(spot)) {
            var error = new CommonException();
            error.setError("This spot is empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        // Empty spot
        parkingSpotService.deleteSpot(spot);
        return ResponseEntity.status(HttpStatus.OK).body("  ");
    }
}
