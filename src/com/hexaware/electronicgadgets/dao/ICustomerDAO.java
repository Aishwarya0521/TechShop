package com.hexaware.electronicgadgets.dao;

import com.hexaware.electronicgadgets.entity.Customer;
import java.util.List;

public interface ICustomerDAO {
    void addCustomer(Customer customer);
    Customer getCustomerById(int customerId);
    List<Customer> getAllCustomers();
    void updateCustomer(Customer customer);
    void deleteCustomer(int customerId);
}
