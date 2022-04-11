package dev.ufuk.bakan.controllers;

import dev.ufuk.bakan.entities.VehicleAd;
import dev.ufuk.bakan.entities.enums.City;
import dev.ufuk.bakan.entities.enums.VehicleType;
import dev.ufuk.bakan.repositories.VehicleAdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/ads")
public class AdsController {

    @Autowired
    VehicleAdRepository vehicleAdRepository;

    @GetMapping("/getBy")
    public List<VehicleAd> getBy(@RequestParam(required = false) String city, @RequestParam(required = false) String vehicleType){
        List<VehicleAd> vehicleAdList = vehicleAdRepository.findAll();
        // filter by params:
        if(city != null && vehicleType != null){
            vehicleAdList = vehicleAdList.stream().filter(ad -> ad.getVehicle().getType().equals(VehicleType.valueOf(vehicleType)) && ad.getOwner().getCity().equals(City.valueOf(city)) ).collect(Collectors.toList());
        }else if(city != null){
            vehicleAdList = vehicleAdList.stream().filter(ad -> ad.getOwner().getCity().equals(City.valueOf(city))).collect(Collectors.toList());
        }else if(vehicleType != null){
            vehicleAdList =  vehicleAdList.stream().filter(ad -> ad.getVehicle().getType().equals(VehicleType.valueOf(vehicleType))).collect(Collectors.toList());
        }
        // filter expired ads:
        vehicleAdList = vehicleAdList.stream().filter(ad -> ad.getCreationTime().plus(ad.getValidForDuration()).isAfter(LocalDateTime.now()) ).collect(Collectors.toList());
        return vehicleAdList;
    }


}
