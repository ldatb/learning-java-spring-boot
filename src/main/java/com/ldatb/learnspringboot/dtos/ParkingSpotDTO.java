package com.ldatb.learnspringboot.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

public class ParkingSpotDTO {
    @NotNull
    @Range(min = 1, max = 100)
    private Integer parkingSpotNumber;

    @NotBlank
    @Size(max = 7)
    private String carLicensePlate;

    @NotBlank
    private String carBrand;

    @NotBlank
    private String carModel;

    @NotBlank
    private String carColor;

    public @NotNull Integer getParkingSpotNumber() {
        return parkingSpotNumber;
    }

    public void setParkingSpotNumber(@NotBlank Integer parkingSpotNumber) {
        this.parkingSpotNumber = parkingSpotNumber;
    }

    public @NotBlank @Size(max = 7) String getCarLicensePlate() {
        return carLicensePlate;
    }

    public void setCarLicensePlate(@NotBlank @Size(max = 7) String carLicensePlate) {
        this.carLicensePlate = carLicensePlate;
    }

    public @NotBlank String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(@NotBlank String carBrand) {
        this.carBrand = carBrand;
    }

    public @NotBlank String getCarModel() {
        return carModel;
    }

    public void setCarModel(@NotBlank String carModel) {
        this.carModel = carModel;
    }

    public @NotBlank String getCarColor() {
        return carColor;
    }

    public void setCarColor(@NotBlank String carColor) {
        this.carColor = carColor;
    }
}
