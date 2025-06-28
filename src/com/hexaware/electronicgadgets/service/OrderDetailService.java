package com.hexaware.electronicgadgets.service;

import com.hexaware.electronicgadgets.dao.OrderDetailDAOImpl;
import com.hexaware.electronicgadgets.entity.OrderDetail;

import java.util.List;

public class OrderDetailService {
    private final OrderDetailDAOImpl orderDetailDAO = new OrderDetailDAOImpl();

    public void addOrderDetail(OrderDetail detail) {
        orderDetailDAO.addOrderDetail(detail);
    }

    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) {
        return orderDetailDAO.getOrderDetailsByOrderId(orderId);
    }

    public void updateOrderDetail(OrderDetail detail) {
        orderDetailDAO.updateOrderDetail(detail);
    }

    public void deleteOrderDetail(int orderDetailId) {
        orderDetailDAO.deleteOrderDetail(orderDetailId);
    }
}
