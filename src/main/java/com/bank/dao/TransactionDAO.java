package com.bank.dao;

import com.bank.util.DBConnection;
import java.sql.*;

public class TransactionDAO {

    public void deposit(int userId, double amount) throws Exception {
        Connection con = DBConnection.getConnection();
        con.setAutoCommit(false);

        try {
            PreparedStatement ps1 = con.prepareStatement(
                "UPDATE customers SET balance = balance + ? WHERE id = ?");
            ps1.setDouble(1, amount);
            ps1.setInt(2, userId);
            ps1.executeUpdate();

            PreparedStatement ps2 = con.prepareStatement(
                "INSERT INTO transactions(customer_id, type, amount) VALUES (?, 'deposit', ?)");
            ps2.setInt(1, userId);
            ps2.setDouble(2, amount);
            ps2.executeUpdate();

            con.commit();
        } catch (Exception e) {
            con.rollback();
            e.printStackTrace();
        }
    }
    
    public void withdraw(int userId, double amount) throws Exception {
        Connection con = DBConnection.getConnection();
        con.setAutoCommit(false);

        try {
            // Check balance
            PreparedStatement check = con.prepareStatement(
                    "SELECT balance FROM customers WHERE id=?");
            check.setInt(1, userId);
            ResultSet rs = check.executeQuery();

            if (rs.next() && rs.getDouble("balance") >= amount) {

                // Deduct balance
                PreparedStatement ps1 = con.prepareStatement(
                        "UPDATE customers SET balance = balance - ? WHERE id = ?");
                ps1.setDouble(1, amount);
                ps1.setInt(2, userId);
                ps1.executeUpdate();

                // Insert transaction
                PreparedStatement ps2 = con.prepareStatement(
                        "INSERT INTO transactions(customer_id, type, amount) VALUES (?, 'withdraw', ?)");
                ps2.setInt(1, userId);
                ps2.setDouble(2, amount);
                ps2.executeUpdate();

                con.commit();
                System.out.println("Withdraw Successful ✅");

            } else {
                System.out.println("Insufficient Balance ❌");
                con.rollback();
            }

        } catch (Exception e) {
            con.rollback();
            e.printStackTrace();
        }
    }
    
    public void getTransactionHistory(int userId) {
        try (Connection con = DBConnection.getConnection()) {

            String sql = "SELECT * FROM transactions WHERE customer_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n--- Transaction History ---");

            while (rs.next()) {
                System.out.println(
                    rs.getString("type") + " | " +
                    rs.getDouble("amount") + " | " +
                    rs.getTimestamp("txn_date")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}