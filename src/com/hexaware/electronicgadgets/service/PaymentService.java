package com.hexaware.electronicgadgets.service;

import com.hexaware.electronicgadgets.dao.IPaymentDAO;
import com.hexaware.electronicgadgets.dao.PaymentDAOImpl;
import com.hexaware.electronicgadgets.entity.Payment;

import java.util.List;

public class PaymentService {
    private final IPaymentDAO paymentDAO = new PaymentDAOImpl();

    public void recordPayment(Payment payment) {
        paymentDAO.addPayment(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentDAO.getAllPayments();
    }

    public Payment getPaymentById(int id) {
        return paymentDAO.getPaymentById(id);
    }

    public List<Payment> getPaymentsByOrderId(int orderId) {
        return paymentDAO.getPaymentsByOrderId(orderId);
    }
}
