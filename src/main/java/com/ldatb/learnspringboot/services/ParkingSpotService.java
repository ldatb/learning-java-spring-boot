package com.ldatb.learnspringboot.services;

import com.ldatb.learnspringboot.models.ParkingSpotModel;
import com.ldatb.learnspringboot.repositories.ParkingSpotRepository;
import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingSpotService {

    final ParkingSpotRepository parkingSpotRepository;

    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }

    // CREATE
    @TransactionScoped
    public ParkingSpotModel saveSpot(ParkingSpotModel parkingSpotModel) {
        return parkingSpotRepository.save(parkingSpotModel);
    }

    // READ
    public List<ParkingSpotModel> findAll() {
        return parkingSpotRepository.findAll();
    }

    public Optional<ParkingSpotModel> findBySpot(Integer parkingSpotNumber) {
        return parkingSpotRepository.findByParkingSpotNumber(parkingSpotNumber);
    }

    // CHECK
    public Boolean isSpotTaken(Integer parkingSpotNumber) {
        return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber);
    }

    public Boolean isCarAlreadyInside(String carLicensePlate) {
        return parkingSpotRepository.existsByCarLicensePlate(carLicensePlate);
    }

    // DELETE
    @Transactional
    public void deleteSpot(Integer parkingSpotNumber) {
        parkingSpotRepository.deleteByParkingSpotNumber(parkingSpotNumber);
    }
}
