package com.example.onlineshop.controller.closed;

import com.example.onlineshop.model.transaction.TransactionType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class TransactionDTO {

    @NotNull
    private Long customerId;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private TransactionType transactionType;
}
