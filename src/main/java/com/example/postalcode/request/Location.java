package com.example.postalcode.request;

import lombok.Data;

@Data
public class Location {

    private String postalCode;
    private Double latitude;
    private Double longitude;

}
