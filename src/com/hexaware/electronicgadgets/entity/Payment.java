package com.hexaware.electronicgadgets.entity;

import java.util.Date;

public class Payment {
    private int paymentId;
    private int orderId;
    private Date paymentDate;
    private String paymentMethod;
    private double amount;
    private String status;

    public Payment(int paymentId, int orderId, Date paymentDate, String paymentMethod, double amount, String status) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.status = status;
    }

    public Payment(int orderId, String paymentMethod, double amount) {
        this.orderId = orderId;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.status = "Paid";
        this.paymentDate = new Date();
    }

    public int getPaymentId() { return paymentId; }
    public void setPaymentId(int paymentId) { this.paymentId = paymentId; }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public Date getPaymentDate() { return paymentDate; }
    public void setPaymentDate(Date paymentDate) { this.paymentDate = paymentDate; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Payment [paymentId=" + paymentId + ", orderId=" + orderId + ", paymentDate=" + paymentDate +
                ", method=" + paymentMethod + ", amount=" + amount + ", status=" + status + "]";
    }
}