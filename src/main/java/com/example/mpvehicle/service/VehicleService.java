package com.example.mpvehicle.service;

import com.example.mpvehicle.entities.VehicleInformation;

import java.io.IOException;

public interface VehicleService {

    public VehicleInformation getVehicleInformation(String vehicleId) throws IOException;
}
