package org.example.dao;

import org.example.entity.Customer;

import java.util.List;

public interface CustomerDAO {
    public boolean addCustomer(Customer customer);

    public List<Customer> getAllCustomers();

    public boolean deleteCustomer(Long customerId);
}
