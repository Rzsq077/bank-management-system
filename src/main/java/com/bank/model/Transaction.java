package com.bank.model;

import java.sql.Timestamp;

public class Transaction {
    private int txnId;
    private int customerId;
    private String type;
    private double amount;
    private Timestamp txnDate;

    // Getters & Setters
    public int getTxnId() { return txnId; }
    public void setTxnId(int txnId) { this.txnId = txnId; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public Timestamp getTxnDate() { return txnDate; }
    public void setTxnDate(Timestamp txnDate) { this.txnDate = txnDate; }
}