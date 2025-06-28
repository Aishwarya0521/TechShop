package com.hexaware.electronicgadgets.dao;

import com.hexaware.electronicgadgets.entity.Order;
import java.util.List;

public interface IOrderDAO {
    void addOrder(Order order);
    Order getOrderById(int orderId);
    List<Order> getAllOrders();
    void updateOrder(Order order);
    void deleteOrder(int orderId);
}
