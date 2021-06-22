package com.example.onlineshop.model.order;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class OrderDTO {

    @NotNull
    private Long customerId;

    @NotNull
    private List<Long> itemIdList;
}
