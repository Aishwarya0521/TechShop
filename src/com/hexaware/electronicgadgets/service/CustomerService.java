package com.hexaware.electronicgadgets.service;

import com.hexaware.electronicgadgets.dao.CustomerDAOImpl;
import com.hexaware.electronicgadgets.entity.Customer;

import java.util.List;

public class CustomerService {
    private final CustomerDAOImpl customerDAO = new CustomerDAOImpl();

    public void registerCustomer(Customer customer) {
        // Add validation if needed
        customerDAO.addCustomer(customer);
    }

    public Customer getCustomer(int customerId) {
        return customerDAO.getCustomerById(customerId);
    }

    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }

    public void updateCustomer(Customer customer) {
        customerDAO.updateCustomer(customer);
    }

    public void deleteCustomer(int customerId) {
        customerDAO.deleteCustomer(customerId);
    }
}
