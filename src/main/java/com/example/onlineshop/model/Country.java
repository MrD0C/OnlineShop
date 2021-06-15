package com.example.onlineshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Objects;

@Entity
@Table(name = "countries",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name","alpha2Code","alpha3Code","numeric"}))
public final class Country extends BaseEntity {

    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "alpha2Code",nullable = false)
    private String alpha2Code;
    @Column(name = "alpha3Code",nullable = false)
    private String alpha3Code;
    @Column(name = "numeric",nullable = false)
    private String numeric;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public String getNumeric() {
        return numeric;
    }

    public void setNumeric(String numeric) {
        this.numeric = numeric;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(name, country.name) && Objects.equals(alpha2Code, country.alpha2Code) && Objects.equals(alpha3Code, country.alpha3Code) && Objects.equals(numeric, country.numeric);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, alpha2Code, alpha3Code, numeric);
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", alpha2Code='" + alpha2Code + '\'' +
                ", alpha3Code='" + alpha3Code + '\'' +
                ", numeric='" + numeric + '\'' +
                '}';
    }
}
