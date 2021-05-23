package com.example.mpvehicle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
public class VehicleRegistrationApplication {
	@RequestMapping("/")
    @ResponseBody
    String home() {
      return "Hello Rishabh!";
    }
	public static void main(String[] args) {
		SpringApplication.run(VehicleRegistrationApplication.class, args);
	}

}
