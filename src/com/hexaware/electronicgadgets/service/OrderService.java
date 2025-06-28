package com.hexaware.electronicgadgets.service;

import com.hexaware.electronicgadgets.dao.OrderDAOImpl;
import com.hexaware.electronicgadgets.entity.Order;

import java.util.List;

public class OrderService {
    private final OrderDAOImpl orderDAO = new OrderDAOImpl();

    public void placeOrder(Order order) {
        orderDAO.addOrder(order);
    }

    public Order getOrder(int orderId) {
        return orderDAO.getOrderById(orderId);
    }

    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    public void updateOrder(Order order) {
        orderDAO.updateOrder(order);
    }

    public void cancelOrder(int orderId) {
        orderDAO.deleteOrder(orderId);
    }
}
