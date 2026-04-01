package com.bank.dao;

import com.bank.model.Customer;
import com.bank.util.DBConnection;

import java.sql.*;

public class CustomerDAO {

    public boolean register(Customer c) {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "INSERT INTO customers(name, email, password) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getName());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getPassword());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Customer login(String email, String password) {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT * FROM customers WHERE email=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setBalance(rs.getDouble("balance"));
                return c;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public double getBalance(int userId) {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT balance FROM customers WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("balance");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}