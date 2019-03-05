package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class VehicleService {

	private VehicleInformation vehicalInfo;

	public VehicleInformation GetVehicalInfo(String s[]) {
		vehicalInfo=new VehicleInformation();
		
		vehicalInfo.setNumber(s[2]);
		vehicalInfo.setName(s[5]);
		vehicalInfo.setCity(s[6]);
		vehicalInfo.setData(s[6]);
		vehicalInfo.setColour(s[11]);
		vehicalInfo.setType(s[12]);
		vehicalInfo.setBikemaker(s[13]);
		vehicalInfo.setModel(s[14]);
		return vehicalInfo;

	}

}
