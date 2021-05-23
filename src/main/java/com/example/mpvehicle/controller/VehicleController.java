package com.example.mpvehicle.controller;


import java.io.IOException;

import com.example.mpvehicle.entities.VehicleInformation;
import com.example.mpvehicle.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class VehicleController {
    @Autowired
    private VehicleService vehicleServiceImpl;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping("/MIS/{id}") // takes the parameter in which url it is used to
    public VehicleInformation fetchVehicelInforamtion(@PathVariable String id) throws IOException {
        return vehicleServiceImpl.getVehicleInformation(id);
    }
}
