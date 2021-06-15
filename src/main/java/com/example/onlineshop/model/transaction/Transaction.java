package com.example.onlineshop.model.transaction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@NoArgsConstructor
public final class Transaction{

    @Id
    @Column(name = "transaction_id",nullable = false)
    private final UUID transactionCode = UUID.randomUUID();
    @Column(name = "amount",nullable = false)
    private BigDecimal amount;
    @Column(name = "transaction_type",nullable = false)
    private TransactionType transactionType;
    @Column(name = "transaction_date",nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy hh:mm:ss")
    private final LocalDateTime transactionDate = LocalDateTime.now();
    @JsonIgnore
    @Column(name = "customer_id",nullable = false)
    private Long customerId;

    public Transaction(BigDecimal amount, TransactionType transactionType, Long customerId) {
        this.amount = amount;
        this.transactionType = transactionType;
        this.customerId = customerId;
    }

    @Override
    public String toString(){
        return "Transaction ID:" + transactionCode + "\n" +
                "Timestamp:" + transactionDate + "\n" +
                "Transaction Type:" + transactionType + "\n" +
                "Amount:" + amount + "\n";
    }
}
