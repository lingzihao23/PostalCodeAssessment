package com.example.postalcode.controller;


import com.example.postalcode.model.PostalCode;
import com.example.postalcode.repository.PostalCodeRepository;
import com.example.postalcode.request.Location;
import com.example.postalcode.request.PostalCodeRequest;
import com.example.postalcode.service.DistanceCalculatorService;
import com.example.postalcode.service.PostalCodeCrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Slf4j
public class PostalCodeController {

    @Autowired
    DistanceCalculatorService calculatorService;

    @Autowired
    PostalCodeCrudService crudService;

    @PostMapping("/calculateDistance")
    public ResponseEntity<String> calculateDistance(@RequestBody PostalCodeRequest request){
            log.info("Request Received: {}", request);
        double distance = 0;
        try {
            distance = calculatorService.calculateDistance(
               request
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok("Distance between 2 postal code is: " + distance + " " + request.getUnit());
    }



    @PostMapping("/updateLocationByPostalCode")
    public ResponseEntity<String> calculateDistance(@RequestBody Location location){
        log.info("Update Request Received: {}", location);

        try {
            crudService.updateLocationByPostalCode(location);
            return ResponseEntity.ok("Update Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
