package com.hexaware.electronicgadgets.entity;

import com.hexaware.electronicgadgets.exception.InvalidDataException;

public class Product {
    private int productId;
    private String productName;
    private String description;
    private double price;
    private String category;

    public Product(int productId, String productName, String description, double price, String category) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        setPrice(price);
        this.category = category;
    }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) {
        if (price < 0) {
            throw new InvalidDataException("Product price cannot be negative.");
        }
        this.price = price;
    }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    @Override
    public String toString() {
        return "Product [ID=" + productId + ", Name=" + productName +
               ", Price=" + price + ", Category=" + category + "]";
    }
}