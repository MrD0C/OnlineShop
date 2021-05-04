package com.example.onlineshop.model;

import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "items")
public class Item extends NamedEntity{

    @NotEmpty(message = "Manufacturer is mandatory")
    @Column(name = "manufacturer",nullable = false)
    private String manufacturer;
    
    @NotEmpty(message = "Vendor code is mandatory")
    @Column(name = "vendor_code",nullable = false)
    private String vendorCode;

    @NotNull
    @DecimalMin(value = "0.0")
    @Column(name = "price",nullable = false)
    private BigDecimal price;

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
