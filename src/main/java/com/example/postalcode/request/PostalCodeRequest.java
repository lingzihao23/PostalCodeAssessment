package com.example.postalcode.request;

import lombok.Data;

@Data
public class PostalCodeRequest {

        private Location location1;
        private Location location2;
        private final String unit = "km";
}
