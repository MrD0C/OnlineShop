package com.example.onlineshop.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@MappedSuperclass
public class Person extends BaseEntity{

    @NotEmpty(message = "First name is mandatory")
    @Column(name = "firstName")
    @Pattern(regexp = "^[А-Я][а-я]+$")
    private String firstName;

    @NotEmpty(message = "Last name is mandatory")
    @Column(name = "lastName")
    @Pattern(regexp = "^[А-Я][а-я]+$")
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
