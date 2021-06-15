package com.example.onlineshop.model.transaction;

public enum TransactionType {
    DEPOSIT("Пополнение"),
    ONLINE("Списание");

    private final String transactionType;

    TransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public boolean isSame(String transactionType) {
        return this.transactionType.equals(transactionType);
    }

    public String getTransactionType() {
        return transactionType;
    }
}
