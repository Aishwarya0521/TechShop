package com.hexaware.electronicgadgets.dao;

import com.hexaware.electronicgadgets.entity.Inventory;
import com.hexaware.electronicgadgets.util.DBConnUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAOImpl implements IInventoryDAO {

    private Connection getConnection() throws SQLException {
        return DBConnUtil.getConnection();
    }

    @Override
    public void addInventory(Inventory inventory) {
        String sql = "INSERT INTO Inventory (InventoryID, ProductID, QuantityInStock, LastStockUpdate) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, inventory.getInventoryId());
            ps.setInt(2, inventory.getProductId());
            ps.setInt(3, inventory.getQuantityInStock());
            ps.setDate(4, new java.sql.Date(inventory.getLastStockUpdate().getTime()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Inventory getInventoryByProductId(int productId) {
        String sql = "SELECT * FROM Inventory WHERE ProductID = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Inventory(
                    rs.getInt("InventoryID"),
                    rs.getInt("ProductID"),
                    rs.getInt("QuantityInStock"),
                    rs.getDate("LastStockUpdate")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Inventory> getAllInventory() {
        List<Inventory> list = new ArrayList<>();
        String sql = "SELECT * FROM Inventory";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Inventory(
                    rs.getInt("InventoryID"),
                    rs.getInt("ProductID"),
                    rs.getInt("QuantityInStock"),
                    rs.getDate("LastStockUpdate")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void updateInventory(Inventory inventory) {
        String sql = "UPDATE Inventory SET QuantityInStock = ?, LastStockUpdate = ? WHERE InventoryID = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, inventory.getQuantityInStock());
            ps.setDate(2, new java.sql.Date(inventory.getLastStockUpdate().getTime()));
            ps.setInt(3, inventory.getInventoryId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteInventory(int inventoryId) {
        String sql = "DELETE FROM Inventory WHERE InventoryID = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, inventoryId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
