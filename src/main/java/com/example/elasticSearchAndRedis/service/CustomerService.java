package com.example.elasticSearchAndRedis.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.elasticSearchAndRedis.model.Customer;

@Service
public interface CustomerService {

    public void saveCustomerDetails(Customer customer);

    public Iterable<Customer> getAllCustomer();

    public List<Customer> getCustomerById(String id);
}
