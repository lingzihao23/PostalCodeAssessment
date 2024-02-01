package com.example.postalcode.repository;

import com.example.postalcode.model.PostalCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostalCodeRepository extends JpaRepository<PostalCode, Integer> {

    @Query("SELECT PC FROM PostalCode PC where postcode = :code")
    Optional<PostalCode> getByPostalCode(String code);
}
