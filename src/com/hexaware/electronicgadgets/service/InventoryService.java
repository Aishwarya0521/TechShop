package com.hexaware.electronicgadgets.service;

import com.hexaware.electronicgadgets.dao.InventoryDAOImpl;
import com.hexaware.electronicgadgets.entity.Inventory;

import java.util.List;

public class InventoryService {
    private final InventoryDAOImpl inventoryDAO = new InventoryDAOImpl();

    public void addInventory(Inventory inventory) {
        inventoryDAO.addInventory(inventory);
    }

    public Inventory getInventoryByProductId(int productId) {
        return inventoryDAO.getInventoryByProductId(productId);
    }

    public List<Inventory> getAllInventory() {
        return inventoryDAO.getAllInventory();
    }

    public void updateInventory(Inventory inventory) {
        inventoryDAO.updateInventory(inventory);
    }

    public void deleteInventory(int inventoryId) {
        inventoryDAO.deleteInventory(inventoryId);
    }
}
