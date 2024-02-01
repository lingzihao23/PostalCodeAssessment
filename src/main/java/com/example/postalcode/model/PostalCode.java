package com.example.postalcode.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Data
@Entity
@Table(name = "postcodelatlng")
public class PostalCode {

    @Id
    @Column
    private Integer id;

    @Column(name = "postcode")
    private String postCode;

    @Column
    private Double latitude;

    @Column
    private Double longitude;


}
