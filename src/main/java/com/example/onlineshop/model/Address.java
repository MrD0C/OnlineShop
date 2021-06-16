package com.example.onlineshop.model;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

//Todo добавить проверки для полей
//Todo переделать toString
@Data
@Embeddable
public class Address {

    @NotBlank
    private String city;
    @NotBlank
    private String street;
    @NotBlank
    private String houseNumber;
    private String flat = "";
    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(houseNumber, address.houseNumber) && Objects.equals(flat, address.flat) && Objects.equals(country, address.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, houseNumber, flat, country);
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", flat='" + flat + '\'' +
                ", country=" + country +
                '}';
    }
}
