package com.example.postalcode.service;

import com.example.postalcode.model.PostalCode;
import com.example.postalcode.repository.PostalCodeRepository;
import com.example.postalcode.request.Location;
import com.example.postalcode.request.PostalCodeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostalCodeCrudService{

    @Autowired
    private PostalCodeRepository postalCodeRepository;

    public void updateLocationByPostalCode(Location location) throws Exception {
        if (location.getPostalCode().isEmpty())
            throw new Exception("Missing Postal Code to Update");
        else if (location.getLatitude()==null)
            throw new Exception("Missing/Invalid Latitude");
        else if (location.getLongitude()==null)
            throw new Exception("Missing/Invalid Longitude");

        Optional<PostalCode> postalCodeSearch =  postalCodeRepository.getByPostalCode(location.getPostalCode());
        if(postalCodeSearch.isPresent()){
            PostalCode newPostalCode = postalCodeSearch.get();

            newPostalCode.setLatitude(location.getLatitude());
            newPostalCode.setLongitude(location.getLongitude());

            postalCodeRepository.save(newPostalCode);
        }
        else {
            throw new Exception("Postal Code not found in Database");
        }



    }





}
