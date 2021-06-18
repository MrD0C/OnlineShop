package com.example.onlineshop.model.transaction;

import com.example.onlineshop.model.Customer;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public Transaction(BigDecimal amount,TransactionType type,Customer customer){
        this.amount = amount;
        this.transactionType = type;
        this.customer = customer;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return transactionCode.equals(that.transactionCode) && amount.equals(that.amount) && transactionType == that.transactionType && transactionDate.equals(that.transactionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionCode, amount, transactionType, transactionDate);
    }

    @Override
    public String toString(){
        return "Transaction ID:" + this.transactionCode + "\n" +
                "Timestamp:" + this.transactionDate + "\n" +
                "Transaction Type:" + this.transactionType + "\n" +
                "Amount:" + this.amount + "\n";
    }
}
