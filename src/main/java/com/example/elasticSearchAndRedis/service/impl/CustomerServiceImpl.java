package com.example.elasticSearchAndRedis.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.elasticSearchAndRedis.model.Customer;
import com.example.elasticSearchAndRedis.repository.CustomerRepository;
import com.example.elasticSearchAndRedis.service.CustomerService;

@Component
public class CustomerServiceImpl implements CustomerService {

    @Autowired(required = true)
    private CustomerRepository customerRepository;

    @Override
    public void saveCustomerDetails(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Iterable<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> getCustomerById(String id) {
        return customerRepository.findByFirstName(id);
    }

}
