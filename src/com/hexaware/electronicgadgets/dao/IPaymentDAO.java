package com.hexaware.electronicgadgets.dao;

import com.hexaware.electronicgadgets.entity.Payment;
import java.util.List;

public interface IPaymentDAO {
    void addPayment(Payment payment);
    List<Payment> getAllPayments();
    Payment getPaymentById(int paymentId);
    List<Payment> getPaymentsByOrderId(int orderId);
}