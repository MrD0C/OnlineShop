package com.example.onlineshop.model;

import com.example.onlineshop.model.domain.NamedEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Data
@Entity
@Table(name = "items",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name", "manufacturer", "vendor_code"}))
public class Item extends NamedEntity {

    @NotBlank(message = "Manufacturer is mandatory")
    @Column(name = "manufacturer", nullable = false)
    private String manufacturer;

    @NotBlank(message = "Vendor code is mandatory")
    @Column(name = "vendor_code", nullable = false)
    private String vendorCode;

    @NotNull
    @DecimalMin(value = "0.0")
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(manufacturer, item.manufacturer) && Objects.equals(vendorCode, item.vendorCode) && Objects.equals(price, item.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manufacturer, vendorCode, price);
    }

    @Override
    public String toString() {
        return "Item:" + this.getName() +
                "\nManufacturer:" + this.getManufacturer() +
                "\nVendor code:" + this.getVendorCode() +
                "\nPrice:" + this.getPrice();
    }
}
