package com.example.onlineshop.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "customers",
        uniqueConstraints = @UniqueConstraint(columnNames = {"firstName","lastName","birthDate"}))
public class Customer extends Person {

    @NotNull(message = "Date of Birthday is mandatory")
    @Column(name = "birthDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @NotBlank(message = "Address can not be empty")
    @Column(name = "shippingAddress")
    @Pattern(regexp = "^(ул.)[А-Я][а-я|\\s]*(,д.)[1-9]{1,3}(,кв.)[1-9]{1,3}$",
            message = "Address must be like this - ул.{text},д.{number},кв.{number}")
    private String shippingAddress;

    @NotNull
    @Enumerated
    private Gender gender;

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return birthDate.equals(customer.birthDate) && Objects.equals(shippingAddress, customer.shippingAddress) && gender == customer.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(birthDate, shippingAddress, gender);
    }
}
