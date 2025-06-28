package com.hexaware.electronicgadgets.dao;

import com.hexaware.electronicgadgets.entity.OrderDetail;
import java.util.List;

public interface IOrderDetailDAO {
    void addOrderDetail(OrderDetail detail);
    List<OrderDetail> getOrderDetailsByOrderId(int orderId);
    void updateOrderDetail(OrderDetail detail);
    void deleteOrderDetail(int orderDetailId);
}
