package com.example.onlineshop.model;

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
    private LocalDateTime transactionDate = LocalDateTime.now();
    @JsonIgnore
    @Column(name = "customer_id",nullable = false)
    private Long customerId;

    public Transaction(BigDecimal amount, TransactionType transactionType, Long customerId) {
        this.amount = amount;
        this.transactionType = transactionType;
        this.customerId = customerId;
    }

    public UUID getTransactionCode() {
        return transactionCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public String toString(){
        return "Transaction ID:" + transactionCode + "\n" +
                "Timestamp:" + transactionDate + "\n" +
                "Transaction Type:" + transactionType + "\n" +
                "Amount:" + amount + "\n";
    }
}
