package com.example.onlineshop.model.order;

import com.example.onlineshop.model.Item;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class OrderInfo {

    @NotNull
    private UUID uuid;
    @NotNull
    private LocalDateTime date;
    @NotNull
    private String customerLastName;
    @NotNull
    private String customerFirstName;
    @NotNull
    private List<Item> items;
}
