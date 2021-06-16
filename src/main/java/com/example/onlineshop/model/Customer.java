package com.example.onlineshop.model;

import com.example.onlineshop.model.domain.Person;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

//Todo создать toString
@Data
@Entity
@Table(name = "customers",
        uniqueConstraints = @UniqueConstraint(columnNames = {"firstName", "lastName", "birthDate"}))
public class Customer extends Person {

    @NotNull(message = "Date of Birthday is mandatory")
    @Column(name = "birthDate")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;

    private BigDecimal balance = BigDecimal.ZERO;

    @NotNull
    @Enumerated
    private Gender gender;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "address_city")),
            @AttributeOverride(name = "street", column = @Column(name = "address_street")),
            @AttributeOverride(name = "houseNumber", column = @Column(name = "address_house_number")),
            @AttributeOverride(name = "flat", column = @Column(name = "address_flat"))
    })
    private Address address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(birthDate, customer.birthDate) && Objects.equals(address, customer.address) && Objects.equals(balance, customer.balance) && gender == customer.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(birthDate, address, balance, gender);
    }
}
