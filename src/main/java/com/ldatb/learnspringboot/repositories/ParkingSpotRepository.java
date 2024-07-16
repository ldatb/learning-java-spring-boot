package com.ldatb.learnspringboot.repositories;

import com.ldatb.learnspringboot.models.ParkingSpotModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpotModel, UUID> {
    // READ
    public Optional<ParkingSpotModel> findByParkingSpotNumber(Integer parkingSpotNumber);

    // EXIST
    public boolean existsByParkingSpotNumber(Integer parkingSpotNumber);
    public boolean existsByCarLicensePlate(String carLicensePlate);

    // DELETE
    public void deleteByParkingSpotNumber(Integer parkingSpotNumber);
}
