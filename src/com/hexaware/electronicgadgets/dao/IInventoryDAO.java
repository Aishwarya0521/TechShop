package com.hexaware.electronicgadgets.dao;

import com.hexaware.electronicgadgets.entity.Inventory;
import java.util.List;

public interface IInventoryDAO {
    void addInventory(Inventory inventory);
    Inventory getInventoryByProductId(int productId);
    List<Inventory> getAllInventory();
    void updateInventory(Inventory inventory);
    void deleteInventory(int inventoryId);
}
