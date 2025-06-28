package com.hexaware.electronicgadgets.dao;

import com.hexaware.electronicgadgets.entity.OrderDetail;
import com.hexaware.electronicgadgets.util.DBConnUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAOImpl implements IOrderDetailDAO {

    private Connection getConnection() throws SQLException {
        return DBConnUtil.getConnection();
    }

    @Override
    public void addOrderDetail(OrderDetail detail) {
        String sql = "INSERT INTO OrderDetails (OrderDetailID, OrderID, ProductID, Quantity) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, detail.getOrderDetailId());
            ps.setInt(2, detail.getOrderId());
            ps.setInt(3, detail.getProductId());
            ps.setInt(4, detail.getQuantity());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) {
        String sql = "SELECT * FROM OrderDetails WHERE OrderID = ?";
        List<OrderDetail> details = new ArrayList<>();
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                details.add(new OrderDetail(
                    rs.getInt("OrderDetailID"),
                    rs.getInt("OrderID"),
                    rs.getInt("ProductID"),
                    rs.getInt("Quantity")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return details;
    }

    @Override
    public void updateOrderDetail(OrderDetail detail) {
        String sql = "UPDATE OrderDetails SET OrderID = ?, ProductID = ?, Quantity = ? WHERE OrderDetailID = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, detail.getOrderId());
            ps.setInt(2, detail.getProductId());
            ps.setInt(3, detail.getQuantity());
            ps.setInt(4, detail.getOrderDetailId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrderDetail(int orderDetailId) {
        String sql = "DELETE FROM OrderDetails WHERE OrderDetailID = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderDetailId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
