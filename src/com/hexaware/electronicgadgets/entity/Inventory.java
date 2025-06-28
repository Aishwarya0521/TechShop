package com.hexaware.electronicgadgets.entity;

import com.hexaware.electronicgadgets.exception.InsufficientStockException;

import java.util.Date;

public class Inventory {
    private int inventoryId;
    private int productId;
    private int quantityInStock;
    private Date lastStockUpdate;

    public Inventory(int inventoryId, int productId, int quantityInStock, Date lastStockUpdate) {
        this.inventoryId = inventoryId;
        this.productId = productId;
        this.quantityInStock = quantityInStock;
        this.lastStockUpdate = lastStockUpdate;
    }

    public int getInventoryId() { return inventoryId; }
    public void setInventoryId(int inventoryId) { this.inventoryId = inventoryId; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public int getQuantityInStock() { return quantityInStock; }
    public void setQuantityInStock(int quantityInStock) {
        if (quantityInStock < 0) {
            throw new InsufficientStockException("Inventory cannot be negative.");
        }
        this.quantityInStock = quantityInStock;
    }

    public Date getLastStockUpdate() { return lastStockUpdate; }
    public void setLastStockUpdate(Date lastStockUpdate) { this.lastStockUpdate = lastStockUpdate; }

    public boolean isProductAvailable(int quantityToCheck) {
        return this.quantityInStock >= quantityToCheck;
    }

    public void addToInventory(int quantity) {
        this.quantityInStock += quantity;
        this.lastStockUpdate = new Date();
    }

    public void removeFromInventory(int quantity) {
        if (quantity > this.quantityInStock) {
            throw new InsufficientStockException("Not enough stock to remove.");
        }
        this.quantityInStock -= quantity;
        this.lastStockUpdate = new Date();
    }

    @Override
    public String toString() {
        return "Inventory [ID=" + inventoryId + ", ProductID=" + productId +
               ", Stock=" + quantityInStock + ", LastUpdate=" + lastStockUpdate + "]";
    }
}
