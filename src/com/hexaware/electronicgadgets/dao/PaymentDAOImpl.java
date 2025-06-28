package com.hexaware.electronicgadgets.dao;

import com.hexaware.electronicgadgets.entity.Payment;
import com.hexaware.electronicgadgets.util.DBConnUtil;

import java.sql.*;
import java.util.*;

public class PaymentDAOImpl implements IPaymentDAO {

    @Override
    public void addPayment(Payment payment) {
        String sql = "INSERT INTO Payment (orderId, paymentDate, paymentMethod, amount, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, payment.getOrderId());
            ps.setTimestamp(2, new Timestamp(payment.getPaymentDate().getTime()));
            ps.setString(3, payment.getPaymentMethod());
            ps.setDouble(4, payment.getAmount());
            ps.setString(5, payment.getStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting payment: " + e.getMessage());
        }
    }

    @Override
    public List<Payment> getAllPayments() {
        List<Payment> list = new ArrayList<>();
        String sql = "SELECT * FROM Payment";
        try (Connection conn = DBConnUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapPayment(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching payments: " + e.getMessage());
        }
        return list;
    }

    @Override
    public Payment getPaymentById(int paymentId) {
        String sql = "SELECT * FROM Payment WHERE paymentId = ?";
        try (Connection conn = DBConnUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, paymentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapPayment(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding payment: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Payment> getPaymentsByOrderId(int orderId) {
        List<Payment> list = new ArrayList<>();
        String sql = "SELECT * FROM Payment WHERE orderId = ?";
        try (Connection conn = DBConnUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapPayment(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding payments for order: " + e.getMessage());
        }
        return list;
    }

    private Payment mapPayment(ResultSet rs) throws SQLException {
        return new Payment(
            rs.getInt("paymentId"),
            rs.getInt("orderId"),
            rs.getTimestamp("paymentDate"),
            rs.getString("paymentMethod"),
            rs.getDouble("amount"),
            rs.getString("status")
        );
    }
}