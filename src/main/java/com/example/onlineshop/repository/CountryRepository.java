package com.example.onlineshop.repository;

import com.example.onlineshop.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    Country getByAlpha2Code(String alpha2Code);

    Country getByAlpha3Code(String alpha3Code);
}
