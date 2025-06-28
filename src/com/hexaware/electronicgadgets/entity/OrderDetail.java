package com.hexaware.electronicgadgets.entity;

import com.hexaware.electronicgadgets.exception.InvalidDataException;

public class OrderDetail {
    private int orderDetailId;
    private int orderId;
    private int productId;
    private int quantity;

    public OrderDetail(int orderDetailId, int orderId, int productId, int quantity) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.productId = productId;
        setQuantity(quantity);
    }

    public int getOrderDetailId() { return orderDetailId; }
    public void setOrderDetailId(int orderDetailId) { this.orderDetailId = orderDetailId; }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) {
        if (quantity <= 0) throw new InvalidDataException("Quantity must be greater than zero.");
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderDetail [ID=" + orderDetailId + ", OrderID=" + orderId +
               ", ProductID=" + productId + ", Quantity=" + quantity + "]";
    }
}
