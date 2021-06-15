package com.example.onlineshop.model.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@MappedSuperclass
public class Person extends BaseEntity {

    @NotBlank(message = "First name is mandatory")
    @Column(name = "firstName")
    @Pattern(regexp = "^[А-Я][а-я]+$",message = "The first name must only consist of Cyrillic letters")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Column(name = "lastName")
    @Pattern(regexp = "^[А-Я][а-я]+$",message = "The first name must only consist of Cyrillic letters")
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
