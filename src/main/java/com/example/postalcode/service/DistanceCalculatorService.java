package com.example.postalcode.service;

import com.example.postalcode.model.PostalCode;
import com.example.postalcode.repository.PostalCodeRepository;
import com.example.postalcode.request.PostalCodeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DistanceCalculatorService {

    @Autowired
    private PostalCodeRepository postalCodeRepository;
    private final static double EARTH_RADIUS = 6371; // radius in kilometers


    public double calculateDistance(PostalCodeRequest request) throws Exception {
        boolean isEmptyPostCode = request.getLocation1().getPostalCode().isEmpty() || request.getLocation2().getPostalCode().isEmpty();
        boolean isEmptyLatLong =
                request.getLocation1().getLongitude()==null || request.getLocation1().getLatitude()==null ||
                request.getLocation2().getLongitude()==null || request.getLocation2().getLatitude()==null;


        if (isEmptyPostCode && isEmptyLatLong){
           throw new Exception("Either postal code or (Latitude and Longitude) must present in both locations");
       }
       else if (!isEmptyPostCode){
           Optional<PostalCode> postal1 =  postalCodeRepository.getByPostalCode(request.getLocation1().getPostalCode());
           Optional<PostalCode> postal2 =  postalCodeRepository.getByPostalCode(request.getLocation2().getPostalCode());

           if (postal1.isPresent() && postal2.isPresent()){
               return calculateDistance(
                       postal1.get().getLatitude(),
                       postal1.get().getLongitude(),
                       postal2.get().getLatitude(),
                       postal2.get().getLongitude()
               );
           }
           else{
               throw new Exception("PostalCode not found in either Location1/Location2");
           }

       }

       else {
           return calculateDistance(
                   request.getLocation1().getLatitude(),
                   request.getLocation1().getLongitude(),
                   request.getLocation2().getLatitude(),
                   request.getLocation2().getLongitude()
           );
       }
    }
    public double calculateDistance(double latitude, double longitude, double latitude2, double longitude2) {
// Using Haversine formula! See Wikipedia;
        double lon1Radians = Math.toRadians(longitude);
        double lon2Radians = Math.toRadians(longitude2);
        double lat1Radians = Math.toRadians(latitude);
        double lat2Radians = Math.toRadians(latitude2);
        double a = haversine(lat1Radians, lat2Radians) + Math.cos(lat1Radians) * Math.cos(lat2Radians) * haversine(lon1Radians, lon2Radians);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return (EARTH_RADIUS * c);
    }
    private double haversine(double deg1, double deg2) { return square(Math.sin((deg1 - deg2) / 2.0));
    }
    private double square(double x) { return x * x;
    }
}
