package com.example.mpvehicle.service;

import com.example.mpvehicle.entities.VehicleInformation;
import com.example.mpvehicle.service.webcrawler.WebsiteCrawler;
import org.jsoup.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    public WebsiteCrawler VehicleWebsiteCrawler;
    private VehicleInformation vehicleInfo;

    public VehicleInformation getVehicleInfo(String[] s) {
        vehicleInfo = new VehicleInformation();
        vehicleInfo.setNumber(s[2]);
        vehicleInfo.setName(s[5]);
        vehicleInfo.setCity(s[6]);
        vehicleInfo.setData(s[6]);
        vehicleInfo.setColour(s[11]);
        vehicleInfo.setType(s[12]);
        vehicleInfo.setBikemaker(s[13]);
        vehicleInfo.setModel(s[14]);
        return vehicleInfo;
    }

    @Override
    public VehicleInformation getVehicleInformation(String vehicleId) throws IOException {
        String url = "http://mis.mptransport.org/MPLogin/eSewa/VehicleSearch.aspx";
        Connection.Response resp = VehicleWebsiteCrawler.getURLResponse(url, 5000, Connection.Method.GET);
        String[] vehicleResponse = VehicleWebsiteCrawler.getDesiredResponse(resp, vehicleId);
        return getVehicleInfo(vehicleResponse);
    }

}
