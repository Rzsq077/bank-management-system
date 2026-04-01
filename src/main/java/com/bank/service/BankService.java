package com.bank.service;

import com.bank.dao.TransactionDAO;

public class BankService {

    private TransactionDAO tdao = new TransactionDAO();

    public void depositMoney(int userId, double amount) {
        try {
            if (amount <= 0) {
                System.out.println("Invalid amount ❌");
                return;
            }
            tdao.deposit(userId, amount);
            System.out.println("Deposit Successful ✅");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void withdrawMoney(int userId, double amount) {
        try {
            if (amount <= 0) {
                System.out.println("Invalid amount ❌");
                return;
            }
            tdao.withdraw(userId, amount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void showTransactions(int userId) {
        tdao.getTransactionHistory(userId);
    }
}