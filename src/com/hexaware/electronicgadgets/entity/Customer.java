package com.hexaware.electronicgadgets.entity;

import com.hexaware.electronicgadgets.exception.InvalidDataException;

public class Customer {
    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private int orderCount;

    public Customer(int customerId, String firstName, String lastName, String email, String phone, String address) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        setEmail(email);
        this.phone = phone;
        this.address = address;
        this.orderCount = 0;
    }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) {
        if (!email.contains("@")) {
            throw new InvalidDataException("Invalid email address.");
        }
        this.email = email;
    }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public int getOrderCount() { return orderCount; }
    public void setOrderCount(int orderCount) { this.orderCount = orderCount; }

    public void incrementOrderCount() {
        this.orderCount++;
    }

    public void updateCustomerInfo(String newEmail, String newPhone, String newAddress) {
        setEmail(newEmail);
        setPhone(newPhone);
        setAddress(newAddress);
    }

    public String getCustomerDetails() {
        return "Customer: " + firstName + " " + lastName + ", Email: " + email +
               ", Phone: " + phone + ", Address: " + address;
    }

    @Override
    public String toString() {
        return "Customer [ID=" + customerId + ", Name=" + firstName + " " + lastName +
               ", Email=" + email + ", Phone=" + phone + ", Address=" + address + "]";
    }
}